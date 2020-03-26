use actor;

drop table if exists movie;
create table movie ( actor varchar(20), movie varchar(20) );

insert into movie ( actor, movie ) values  ( 'Bob',   'Hard Day' );
insert into movie ( actor, movie ) values  ( 'John',  'Hard Day' );
insert into movie ( actor, movie ) values  ( 'Sally', 'Hard Day' );
insert into movie ( actor, movie ) values  ( 'Suzy',  'Hard Day' );

insert into movie ( actor, movie ) values  ( 'John',   'Christmas Eve' );
insert into movie ( actor, movie ) values  ( 'Carter', 'Christmas Eve' );
insert into movie ( actor, movie ) values  ( 'Ron',    'Christmas Eve' );

insert into movie ( actor, movie ) values  ( 'Sally',  'Jason Lives' );
insert into movie ( actor, movie ) values  ( 'Ron',    'Jason Lives' );
insert into movie ( actor, movie ) values  ( 'Bernie', 'Jason Lives' );
insert into movie ( actor, movie ) values  ( 'Donald', 'Jason Lives' );

insert into movie ( actor, movie ) values  ( 'Donald', 'Election Coverage' );
insert into movie ( actor, movie ) values  ( 'John',   'Election Coverage' );
insert into movie ( actor, movie ) values  ( 'Sally',  'Election Coverage' );
insert into movie ( actor, movie ) values  ( 'Fred',   'Election Coverage' );
insert into movie ( actor, movie ) values  ( 'Wilma',  'Election Coverage' );

insert into movie ( actor, movie ) values  ( 'Wilma',   'Dinoland' );
insert into movie ( actor, movie ) values  ( 'Donald',  'Dinoland' );
insert into movie ( actor, movie ) values  ( 'Carter',  'Dinoland' );

insert into movie ( actor, movie ) values  ( 'Clif',   'Bootcamp' );
insert into movie ( actor, movie ) values  ( 'Caleb',  'Bootcamp' );
insert into movie ( actor, movie ) values  ( 'Jerry',  'Bootcamp' );
insert into movie ( actor, movie ) values  ( 'Jeff',   'Bootcamp' );
insert into movie ( actor, movie ) values  ( 'Mia',    'Bootcamp' );
insert into movie ( actor, movie ) values  ( 'Alicja', 'Bootcamp' );
insert into movie ( actor, movie ) values  ( 'Bernie', 'Bootcamp' );

insert into movie ( actor, movie ) values  ( 'John',  'Subway' );
insert into movie ( actor, movie ) values  ( 'Jeff',  'Subway' );
insert into movie ( actor, movie ) values  ( 'Fred',  'Subway' );
insert into movie ( actor, movie ) values  ( 'Clif',  'Subway' );
insert into movie ( actor, movie ) values  ( 'Wilma', 'Subway' );

insert into movie ( actor, movie ) values  ( 'Clif',   'Downtown' );
insert into movie ( actor, movie ) values  ( 'Fred',   'Downtown' );
insert into movie ( actor, movie ) values  ( 'Wilma',  'Downtown' );
insert into movie ( actor, movie ) values  ( 'Bernie', 'Downtown' );
insert into movie ( actor, movie ) values  ( 'Carter', 'Downtown' );
insert into movie ( actor, movie ) values  ( 'Donald', 'Downtown' );

-- DETERMINE WHICH ACTORS WERE IN THE MOST MOVIES TOGETHER
SELECT a.actor, b.actor, COUNT(a.movie) FROM movie a JOIN movie b ON a.movie=b.movie WHERE a.actor<b.actor GROUP BY 1,2 ORDER BY 3 DESC LIMIT 5;

