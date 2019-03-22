FROM postgres:11

ENV POSTGRES_USER=test
ENV POSTGRES_PASSWORD=test
ENV POSTGRES_DB=test

COPY schema.sql /docker-entrypoint-initdb.d
