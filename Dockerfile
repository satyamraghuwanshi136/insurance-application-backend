FROM openjdk:8
EXPOSE 8072
ADD target/insurance.jar insurance.jar
ENTRYPOINT ["java", "-jar", "/insurance.jar"]