FROM postgres:9.6

COPY ./init docker-entrypoint-initdb.d

RUN apt-get update \
  && apt-get install -y wget \
  && wget -qO- https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/6.0.8/flyway-commandline-6.0.8-linux-x64.tar.gz --no-check-certificate | tar xvz \
  && mv flyway-6.0.8 flyway \
  && ln -s `pwd`flyway/flyway /usr/local/bin \
  && chown -R postgres `pwd`flyway/flyway \
  && chmod 775 `pwd`flyway/flyway

COPY ./flyway/flyway.conf flyway/conf
COPY ./migration flyway/migration
COPY @project.build.finalName@.jar flyway/jars