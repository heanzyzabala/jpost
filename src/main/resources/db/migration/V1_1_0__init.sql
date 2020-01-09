CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS posts (
    id              UUID                NOT NULL DEFAULT uuid_generate_v1(),
    title           TEXT                NOT NULL,
    content         TEXT                NOT NULL,
    created_at      TIMESTAMP           NOT NULL,
    updated_at      TIMESTAMP           NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS comments (
    id              UUID                NOT NULL DEFAULT uuid_generate_v1(),
    post_id         UUID                NOT NULL,
    content         TEXT                NOT NULL,
    created_at      TIMESTAMP           NOT NULL,
    updated_at      TIMESTAMP           NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES posts(id)
);