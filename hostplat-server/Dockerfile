FROM openjdk:8
MAINTAINER daka
ADD hostplat-server/target/hostplat-server-0.0.1-SNAPSHOT.jar hostplat-server.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "hostplat-server.jar"]