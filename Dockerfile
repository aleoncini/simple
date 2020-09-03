# Build stage
FROM registry.redhat.io/ubi8/openjdk-8 as build
COPY --chown=jboss:jboss ./pom.xml ./
RUN mvn dependency:go-offline -B
COPY --chown=jboss:jboss ./ ./
RUN mvn clean package


# Create final image
FROM jboss/wildfly

FROM registry.redhat.io/ubi8/openjdk-8
COPY --from=build /home/jboss/target/simple.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
USER 1001