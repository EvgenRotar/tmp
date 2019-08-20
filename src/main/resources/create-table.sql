DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id          SERIAL        primary key,
  first_name       VARCHAR(50)   NOT NULL,
  last_name        VARCHAR(50)   NOT NULL,
  department_name  VARCHAR(50)   NOT NULL,
  job_title        VARCHAR(50)   NOT NULL,
  gender           VARCHAR(6)    NOT NULL,
  date_of_birth    DATE          NOT NULL
);