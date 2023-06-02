FROM openjdk:11-jdk-slim

ENV JAICP_API_TOKEN = "PMInaDxr:892922faad6bc85848da71d1bc9da07519418641"

EXPOSE 8080

ADD build/libs/app.jar /opt/jaicf/app.jar

ENTRYPOINT ["java", "-DJAICP_API_TOKEN=$JAICP_API_TOKEN", "-jar", "/opt/jaicf/app.jar"]
