FROM jboss/wildfly

ADD target/simple.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080
USER 1001

