-- Table: miniproyecto.strings

-- DROP TABLE miniproyecto.strings;

CREATE TABLE IF NOT EXISTS miniproyecto.strings
(
    id smallint NOT NULL,
    name text COLLATE pg_catalog."default",
    counter smallint,
    maxvalue smallint,
    CONSTRAINT "Strings_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE miniproyecto.strings
    OWNER to postgres;