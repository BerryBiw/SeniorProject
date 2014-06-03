CREATE DATABASE IF NOT EXISTS petclinic;
GRANT ALL PRIVILEGES ON petclinic.* TO pc@localhost IDENTIFIED BY 'pc';

USE petclinic;

CREATE TABLE IF NOT EXISTS instructors (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email      VARCHAR(100),
  telephone  VARCHAR(100),
  user_name  VARCHAR (50),
  password   VARCHAR (10),

  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS faculties (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS instructor_faculties (
  instructor_id INT(4) UNSIGNED NOT NULL,
  faculty_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (instructor_id) REFERENCES instructors(id),
  FOREIGN KEY (faculty_id) REFERENCES faculties(id),
  UNIQUE (instructor_id,faculty_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS types (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS activities (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  description VARCHAR(255),
  number_student VARCHAR(80),
  start_date DATE,
  end_date DATE,
  type_id INT(4) UNSIGNED NOT NULL,
  INDEX(name),
  FOREIGN KEY (type_id) REFERENCES types(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS students (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  student_id VARCHAR(10),
  faculty_id INT(4) UNSIGNED NOT NULL,
  activity_id INT(4) UNSIGNED NOT NULL,
  INDEX(name),
  FOREIGN KEY (activity_id) REFERENCES activities(id),
  FOREIGN KEY (faculty_id) REFERENCES faculties(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS visits (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  student_id INT(4) UNSIGNED NOT NULL,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (student_id) REFERENCES students(id)
) engine=InnoDB;