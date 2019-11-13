FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd \
     && mkdir -p /usr/local/configserver \
     && cd /tmp/ \
     && wget "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" --header "Cookie: oraclelicense=accept-securebackup-cookie" \
     && unzip jce_policy-8.zip \
     && rm jce_policy-8.zip \
     && yes |cp -v /tmp/UnlimitedJCEPolicyJDK8/*.jar /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/
COPY @project.build.finalName@.jar /usr/local/configserver/
CMD java -jar /usr/local/configserver/@project.build.finalName@.jar