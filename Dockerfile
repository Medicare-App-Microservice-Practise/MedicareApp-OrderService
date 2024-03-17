FROM openjdk:8
EXPOSE 8093
ADD target/Medicare-OrderService-0.0.1-SNAPSHOT.war Medicare-OrderService-0.0.1-SNAPSHOT.war
ENTRYPOINT [ "java","-jar","/Medicare-OrderService-0.0.1-SNAPSHOT.war" ]