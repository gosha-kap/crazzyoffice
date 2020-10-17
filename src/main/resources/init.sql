DROP TABLE IF EXISTS person_workday_relashion;
DROP table if exists jobentity;
DROP TABLE IF EXISTS workDay;
DROP TABLE IF EXISTS person;
DROP TABLE if EXISTS jobtype;
Drop Table if exists telegramuser;


CREATE TABLE person (

  id  serial PRIMARY KEY ,
  alias varchar UNIQUE DEFAULT 'unkown',
  firstName varchar DEFAULT NULL,
  lastName varchar DEFAULT NULL,
  phone varchar DEFAULT NULL,
  department varchar DEFAULT NULL
);


Create TABLE telegramuser(
    id serial PRIMARY KEY ,
    userId int UNIQUE NOT NULL,
    chatId varchar DEFAULT NULL,
    autorised boolean DEFAULT false,
    person_id int,
    CONSTRAINT fk_customer  FOREIGN KEY(person_id)
	  REFERENCES person(id)
);



CREATE TABLE workday (
  id serial PRIMARY KEY,
  date DATE NOT NULL  DEFAULT CURRENT_DATE
  );

CREATE TABLE jobtype (
id serial primary key ,
description varchar  NOT NULL,
backgroundcolor varchar  DEFAULT 'blue',
textcolor varchar  DEFAULT 'white',
icon varchar DEFAULT NULL
);

CREATE TABLE jobentity(
    id serial PRIMARY KEY ,
    date DATE NOT NULL  DEFAULT CURRENT_DATE,
	job_person int REFERENCES person (id) ON UPDATE CASCADE ON DELETE CASCADE,
    job_event int REFERENCES jobtype (id) ON UPDATE Cascade  ON DELETE CASCADE

);

CREATE TABLE person_workday_relashion (
  workday_id    int REFERENCES workday (id) ON UPDATE CASCADE ON DELETE CASCADE,
  person_id int REFERENCES person (id) ON UPDATE CASCADE ON DELETE CASCADE,
  person_job int REFERENCES jobtype (id) ON UPDATE Cascade  ON DELETE CASCADE ,
  CONSTRAINT person_workday PRIMARY KEY ( workday_id, person_id, person_job)
);




