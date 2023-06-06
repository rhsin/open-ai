CREATE TABLE IF NOT EXISTS prompt_records (
    id bigint NOT NULL,
    choices json,
    created date,
    max_tokens integer NOT NULL,
    messages json,
    model character varying(255) COLLATE pg_catalog."default",
    object character varying(255) COLLATE pg_catalog."default",
    prompt_id character varying(255) COLLATE pg_catalog."default",
    temperature double precision NOT NULL,
    usage json,
    CONSTRAINT prompt_records_pkey PRIMARY KEY (id)
);