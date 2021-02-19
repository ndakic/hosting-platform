FROM maven:3.6.3-jdk-8
LABEL MAINTAINER="daka"
ARG env
COPY ./ hostplat-server
WORKDIR /hostplat-server
RUN mvn package -DskipTests
EXPOSE 9000
ENTRYPOINT ["java", "-Dspring.profiles.active=${env}", "-jar", "target/hostplat-server-0.0.1-SNAPSHOT.jar"]