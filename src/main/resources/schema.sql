DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS students;

CREATE TABLE students (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  lastname VARCHAR(255),
  email VARCHAR(255),
  curp VARCHAR(255),
  phone_number VARCHAR(255),
  date_of_birth DATE
);

CREATE TABLE teachers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  lastname VARCHAR(30) NOT NULL,
  name VARCHAR(30) NOT NULL,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE subjects (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  semester INT NOT NULL,
  teacher_id BIGINT NOT NULL,
  name VARCHAR(30) NOT NULL,
  area VARCHAR(50) NOT NULL,
  CONSTRAINT FK_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id),
  CONSTRAINT semester_check CHECK (semester BETWEEN 1 AND 6)
);
