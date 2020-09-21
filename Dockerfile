# Build stage
FROM registry.redhat.io/ubi8/openjdk-8 as build
COPY --chown=jboss:jboss ./pom.xml ./
RUN mvn dependency:go-offline -B
COPY --chown=jboss:jboss ./ ./
RUN mvn clean package

# Create final image
FROM jboss/wildfly

# install the keycloak adater
COPY --chown=jboss:0 ./adapter/keycloak-wildfly-adapter-dist-11.0.2.tar ./wildfly
WORKDIR /opt/jboss/wildfly
RUN tar xvf keycloak-wildfly-adapter-dist-11.0.2.tar
WORKDIR /opt/jboss/wildfly/bin
RUN ./jboss-cli.sh --file=adapter-elytron-install-offline.cli
WORKDIR /opt/jboss

# install the application built at stage 1
COPY --from=build /home/jboss/target/ROOT.war /opt/jboss/wildfly/standalone/deployments/

# fix an issue
RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current

# OpenShift assigns a random user to to run the container.
# According with OpenShift docker guidelines jboss dir needs to have root group permissions
USER root
RUN chgrp -R 0 $JBOSS_HOME &&\
    chmod -R g+rw $JBOSS_HOME

# Expose the ports we're interested in
EXPOSE 8443

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]






