FROM openjdk:11-jdk-slim

ENV JAICP_API_TOKEN = ""

EXPOSE 8080

ADD build/libs/app.jar /opt/jaicf/app.jar

ENTRYPOINT ["java", "-DJAICP_API_TOKEN=$JAICP_API_TOKEN", "-jar", "/opt/jaicf/app.jar"]
