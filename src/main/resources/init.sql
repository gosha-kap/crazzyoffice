DROP TABLE IF EXISTS person_workday_relashion;
DROP TABLE IF EXISTS workDay;
DROP TABLE IF EXISTS person;
DROP TABLE  if exists jobtype;
Drop Table if exists telegramuser;
drop table if exists authorities;
drop table if exists users;


create table users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);
create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);


Create TABLE telegramuser(
    id serial PRIMARY KEY ,
    userId int NOT NULL,
    chatId varchar DEFAULT NULL

);


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
