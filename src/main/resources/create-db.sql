delete from  public.jobtype;
delete from  public.person;
delete from  public.workday;
delete from  public.person_workday_relashion;
delete from public.jobentity;
delete from public.telegramuser;



INSERT INTO public.person (alias) VALUES ('Иван');
INSERT INTO public.person (alias) VALUES ('Петров');
INSERT INTO public.person (alias) VALUES ('Сидоров');


INSERT  INTO public.workday (date) VALUES (date_trunc('month', CURRENT_DATE + interval '1 month') - interval '1 day' );
INSERT  INTO public.workday (date) VALUES (date_trunc('month', CURRENT_DATE));

INSERT  INTO public.jobtype (description,icon) VALUES ('Водитель','far fa-car-side');
INSERT  INTO public.jobtype (description,icon) VALUES ('до 21:00','far fa-car-side');
INSERT  INTO public.jobtype (description,icon,backgroundcolor,textcolor) VALUES ('На телефоне','far fa-car-side','red','green');
INSERT  INTO public.jobtype (description,icon,backgroundcolor,textcolor) VALUES ('На сутках','far fa-car-side','yellow','black');

INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (1,1,1);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (1,2,2);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (2,1,1);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (2,2,3);
INSERT  INTO public.person_workday_relashion (workday_id,person_id,person_job) VALUES (2,3,2);

INSERT INTO public.telegramuser (userId) values (757320166);

INSERT INTO public.jobentity (date,job_person,job_event) values (date_trunc('month', CURRENT_DATE),2,2);
INSERT INTO public.jobentity (date,job_person,job_event) values (date_trunc('month', CURRENT_DATE),1,1);


