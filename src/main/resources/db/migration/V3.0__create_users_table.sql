CREATE TABLE users (

    id VARCHAR(36) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);