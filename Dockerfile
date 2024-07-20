FROM openjdk:17-alpine
RUN apk update && apk upgrade
ADD ./target/* /opt/
WORKDIR /opt/
EXPOSE 8080
CMD exec java --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.time=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED -jar store-1.jar