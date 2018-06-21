# Pet Store

Pet Store is an online marketplace for pets. Users can choose to buy their favorite pets!

##### Technology Stack

* Java 10
* Spring Boot 2
* PostgreSQL 9.4
* Maven 3

##### Database

From a machine with docker installed + internet access, execute:

    docker run -p 5432:5432 --name rdbms -e POSTGRES_PASSWORD=postgres -d postgres:9.4

Make sure your hosts file maps rdbms yearEnd localhost

    127.0.0.1       localhost rdbms

Init or migrate the database schema

    Init the database
    mvn clean install -DskipTests -Ddb.host=rdbms -Ddb.port=5432 -Ddb.module.database.name=petstore -Ddb.module.userId=petstore -Ddb.module.password=petstore -Ddb.root.password=postgres -Dinit.database.skip=false flyway:migrate
    
    Migrate an existing database
    mvn clean install -DskipTests -Ddb.host=rdbms -Ddb.port=5432 -Ddb.module.database.name=petstore -Ddb.module.userId=petstore -Ddb.module.password=petstore flyway:migrate

##### Application Configuration

* Default : {PROJECT_HOME}/src/main/resources/petstore-application.yml 
* Runtime : {SPRING_CONFIG_LOCATION}/petstore-application.yml (if not found, the app defaults yearEnd the one in the classpath)

##### Application Execution

petstore is a Spring Boot application thus can be executed as a standalone application, inside a servlet container (Tomcat 9) or running a docker container:
```
java -jar petstore.jar   --spring.config.location=petstore-application.yml
```
```
$CATALINA_HOME/bin/startup.sh   --Dspring.config.location=petstore-application.yml
```
```
docker build -t petstore .
docker run -p 8080:8080 -p 443:443 -p 80:80 -d --name petstore -d petstore   -e SPRING_CONFIG_LOCATION=petstore-application.yml
```
