# Build stage
FROM registry.redhat.io/ubi8/openjdk-8 as build
COPY --chown=jboss:jboss ./pom.xml ./
RUN mvn dependency:go-offline -B
COPY --chown=jboss:jboss ./ ./
RUN mvn clean package

# Create final image
FROM jboss/base-jdk:11

# Set the WILDFLY_VERSION env variable
ENV WILDFLY_VERSION 20.0.1.Final
ENV WILDFLY_SHA1 95366b4a0c8f2e6e74e3e4000a98371046c83eeb
ENV JBOSS_HOME /opt/jboss/wildfly

USER root

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME \
    && curl -O https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz \
    && sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 \
    && tar xf wildfly-$WILDFLY_VERSION.tar.gz \
    && mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME \
    && rm wildfly-$WILDFLY_VERSION.tar.gz \
    && chown -R jboss:0 ${JBOSS_HOME} \
    && chmod -R g+rw ${JBOSS_HOME}

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

USER jboss

# install the keycloak adater
COPY --chown=jboss:jboss ./adapter/keycloak-wildfly-adapter-dist-11.0.2.tar ./wildfly
WORKDIR /opt/jboss/wildfly
RUN tar xvf keycloak-wildfly-adapter-dist-11.0.2.tar
WORKDIR /opt/jboss/wildfly/bin
RUN ./jboss-cli.sh --file=adapter-elytron-install-offline.cli

# install the application built at stage 1
COPY --from=build /home/jboss/target/simple.war /opt/jboss/wildfly/standalone/deployments/

# Expose the ports we're interested in
EXPOSE 8080

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]






