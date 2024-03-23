CREATE TABLE avenger(
    id BIGSERIAL NOT NULL,
    nick VARCHAR(32),
    person VARCHAR(128),
    description VARCHAR(512),
    history TEXT,
    PRIMARY KEY(id)
);

ALTER TABLE avenger ADD CONSTRAINT UK_5r88eemotwgru6kOilqb2ledh UNIQUE(nick);
