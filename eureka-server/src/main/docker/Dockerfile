FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd \
     && mkdir -p /usr/local/licenses
COPY @project.build.finalName@.jar /usr/local/licenses/
CMD java -jar /usr/local/licenses/@project.build.finalName@.jar