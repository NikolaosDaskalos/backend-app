FROM postgres:alpine
EXPOSE 5432
ENTRYPOINT ["java","-jar","backend-demo-0.0.1-SNAPSHOT.jar"]
