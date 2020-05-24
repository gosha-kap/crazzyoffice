DROP TABLE IF EXISTS person_workday_relashion;
DROP TABLE IF EXISTS workDay;
DROP TABLE IF EXISTS person;
DROP TABLE  if exists jobtype;



CREATE TABLE person (
  id  serial PRIMARY KEY ,
  alias varchar DEFAULT NULL
 );

CREATE TABLE workday (
  id serial PRIMARY KEY,
  date DATE NOT NULL  DEFAULT CURRENT_DATE
  );

CREATE TABLE jobtype (
id serial primary key ,
description varchar  NOT NULL
);


CREATE TABLE person_workday_relashion (
  workday_id    int REFERENCES workday (id) ON UPDATE CASCADE ON DELETE CASCADE,
  person_id int REFERENCES person (id) ON UPDATE CASCADE ON DELETE CASCADE,
  person_job int REFERENCES jobtype (id) ON UPDATE Cascade  ON DELETE CASCADE ,
  CONSTRAINT person_workday PRIMARY KEY ( workday_id, person_id, person_job)
);
