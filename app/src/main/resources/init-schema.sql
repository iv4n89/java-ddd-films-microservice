DROP SCHEMA IF EXISTS "film" CASCADE;

CREATE SCHEMA "film";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "film".films
(
    id uuid NOT NULL,
    title string NOT NULL,
    launchDate Date NOT NULL,
    imageId uuid,
    CONSTRAINT film_pkey PRIMARY KEY (id)
);