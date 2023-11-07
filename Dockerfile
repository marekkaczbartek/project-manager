FROM openjdk:21-oracle
COPY target/*.jar projectmanager.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "projectmanager.jar"]
