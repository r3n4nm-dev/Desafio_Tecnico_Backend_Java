FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/desafio-tecnico-backend-java-0.0.1-SNAPSHOT.jar desafio-tecnico-backend-java.jar
ENTRYPOINT ["java","-jar","/desafio-tecnico-backend-java.jar"]