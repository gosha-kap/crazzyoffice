delete from  public.jobtype;
delete from  public.person;
delete from  public.workday;
delete from  public.person_workday_relashion;


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


