# Build provider
FROM registry.redhat.io/ubi8/openjdk-8 as build
COPY --chown=jboss:jboss ./*.js ./
RUN jar -cvf jsprovider.jar ./*

FROM quay.io/keycloak/keycloak

# install the provider built at stage 1
COPY --from=build /home/jboss/jsprovider.jar /opt/jboss/wildfly/standalone/deployments/

