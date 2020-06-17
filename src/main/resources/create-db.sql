delete from  public.jobtype;
delete from  public.person;
delete from  public.workday;
delete from  public.person_workday_relashion;
delete from public.telegramuser;
delete from public.authorities;
delete from public.users;


INSERT INTO public.person (alias) VALUES ('Иван');
INSERT INTO public.person (alias) VALUES ('Петров');
INSERT INTO public.person (alias) VALUES ('Сидоров');


INSERT  INTO public.workday (date) VALUES (date_trunc('month', CURRENT_DATE + interval '1 month') - interval '1 day' );
INSERT  INTO public.workday (date) VALUES (date_trunc('month', CURRENT_DATE));

INSERT  INTO public.jobtype (description) VALUES ('Водитель');
INSERT  INTO public.jobtype (description) VALUES ('Дежурный');
INSERT  INTO public.jobtype (description) VALUES ('На телефоне');

INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (1,1,1);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (1,2,2);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (2,1,1);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (2,2,3);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (2,3,2);

INSERT INTO public.telegramuser (userId) values (757320166);

INSERT INTO public.users (username,password,enabled)  values('admin','$2a$10$hbxecwitQQ.dDT4JOFzQAulNySFwEpaFLw38jda6Td.Y/cOiRzDFu',true);
insert into public.authorities(username,authority) values('admin','ROLE_ADMIN');


