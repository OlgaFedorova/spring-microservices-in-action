FROM openjdk:8-jdk-alpine
COPY run.sh run.sh
RUN  apk update && apk upgrade && apk add netcat-openbsd \
     && mkdir -p /usr/local/zuulserver \
     && chmod +x run.sh
COPY @project.build.finalName@.jar /usr/local/zuulserver/
CMD ./run.sh