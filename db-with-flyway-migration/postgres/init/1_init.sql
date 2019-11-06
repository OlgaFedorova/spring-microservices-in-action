CREATE USER olga WITH
	LOGIN
	SUPERUSER
	INHERIT
	CREATEDB
	CREATEROLE
	REPLICATION
	PASSWORD 'olga';

CREATE DATABASE spring_microservices
	WITH OWNER = postgres
	ENCODING = 'UTF8'
	TABLESPACE = pg_default
	LC_COLLATE = 'en_US.utf8'
	LC_CTYPE = 'en_US.utf8'
	CONNECTION LIMIT = -1;
GRANT CONNECT, TEMPORARY ON DATABASE spring_microservices TO public;
GRANT ALL ON DATABASE spring_microservices TO olga;

ALTER DATABASE spring_microservices SET search_path = public;

\connect spring_microservices;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
