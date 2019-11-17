FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd \
     && mkdir -p /usr/local/organizations
COPY @project.build.finalName@.jar /usr/local/organizations/
CMD java -jar /usr/local/organizations/@project.build.finalName@.jar