-- Runs on every start (JdbcConfig.schemaInitializer), on H2 and on MySQL.
-- DROP + CREATE means every run starts from empty tables, so the demos can be re-run.
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS account;

CREATE TABLE student (
    id   INT PRIMARY KEY,                 -- the application chooses the id (topic02)
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100)
);

CREATE TABLE course (
    id    INT AUTO_INCREMENT PRIMARY KEY, -- the database generates the id (topic04, KeyHolder)
    title VARCHAR(100) NOT NULL,
    fee   INT NOT NULL
);

CREATE TABLE account (
    id      INT PRIMARY KEY,
    owner   VARCHAR(100) NOT NULL,
    balance INT NOT NULL CHECK (balance >= 0)   -- topic05: overdrawing fails at the database
);
