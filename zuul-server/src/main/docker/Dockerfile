FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd \
     && mkdir -p /usr/local/zuulserver
COPY @project.build.finalName@.jar /usr/local/zuulserver/
CMD java -jar /usr/local/zuulserver/@project.build.finalName@.jar