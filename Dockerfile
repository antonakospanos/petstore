FROM tomcat:9.0.7-jre10

LABEL maintainer="antonakospanos.gmail.com"

# Provision Tomcat with petstore webapp, keystore and server.xml
COPY target/petstore*.war $CATALINA_HOME/webapps/petstore.war
COPY src/main/resources/ssl/petstore.keystore /opt/tomcat/conf/localhost-rsa.jks
COPY src/conf/tomcat/server.xml /opt/tomcat/conf/server.xml

# Add env variables
ENV SPRING_CONFIG_LOCATION /opt/tomcat/conf/

# Expose Ports
EXPOSE 8080
EXPOSE 80
EXPOSE 443