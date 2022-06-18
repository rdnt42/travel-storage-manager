FROM openjdk:17-oracle
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
COPY target/travel-storage-manager.jar /opt/travel-storage-manager.jar
ENTRYPOINT exec java $JAVA_OPTS -jar travel-storage-manager.jar