FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd \
     && mkdir -p /usr/local/eurekaserver
COPY @project.build.finalName@.jar /usr/local/eurekaserver/
CMD java -jar /usr/local/eurekaserver/@project.build.finalName@.jar