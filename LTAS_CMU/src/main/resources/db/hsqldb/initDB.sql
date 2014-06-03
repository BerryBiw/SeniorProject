DROP TABLE instructor_faculties IF EXISTS;
DROP TABLE instructors IF EXISTS;
DROP TABLE faculties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE students IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE activities IF EXISTS;


CREATE TABLE instructors (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  email      VARCHAR(100),
  telephone  VARCHAR(100),
  user_name  VARCHAR (50),
  password   VARCHAR (10)

);
CREATE INDEX instructors_last_name ON instructors (last_name);

CREATE TABLE faculties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX faculties_name ON faculties (name);

CREATE TABLE instructor_faculties (
  instructor_id       INTEGER NOT NULL,
  faculty_id INTEGER NOT NULL
);
ALTER TABLE instructor_faculties ADD CONSTRAINT fk_instructor_faculties_instructors FOREIGN KEY (instructor_id) REFERENCES instructors (id);
ALTER TABLE instructor_faculties ADD CONSTRAINT fk_instructor_faculties_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE activities (
  id  INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  description VARCHAR(255),
  number_student VARCHAR(80),
  start_date   DATE,
  end_date DATE,
  type_id INTEGER NOT NULL
);
ALTER TABLE activities ADD CONSTRAINT fk_activities_types FOREIGN KEY (type_id) REFERENCES types (id);

CREATE INDEX activities_name ON activities (name);

CREATE TABLE students (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  student_id VARCHAR (10),
  faculty_id    INTEGER NOT NULL,
  activity_id   INTEGER NOT NULL
);
ALTER TABLE students ADD CONSTRAINT fk_students_activities FOREIGN KEY (activity_id) REFERENCES activities (id);
ALTER TABLE students ADD CONSTRAINT fk_students_faculties FOREIGN KEY (faculty_id) REFERENCES faculties (id);
CREATE INDEX students_name ON students (name);

CREATE TABLE visits (
  id          INTEGER IDENTITY PRIMARY KEY,
  student_id      INTEGER NOT NULL,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_students FOREIGN KEY (student_id) REFERENCES students (id);
CREATE INDEX visits_student_id ON visits (student_id);
