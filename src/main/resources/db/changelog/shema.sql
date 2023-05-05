DROP TABLE IF EXISTS apartments;
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(20),
    age      INT,
    password VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS apartments
(
    id       SERIAL PRIMARY KEY,
    address  VARCHAR(20),
    owner_id INT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users (id)
);