CREATE TABLE USERS (
id int auto_increment primary key not null,
username varchar(50) not null,
password varchar(50) not null
);

CREATE TABLE ACCOUNTINFO(
id int auto_increment primary key not null,
user_id int not null,
name varchar(50) not null,
height_feet int,
height_inches int,
current_weight decimal,
foreign key(user_id) references USERS(id) ON DELETE CASCADE
);

CREATE TABLE WEIGHT(
id int auto_increment primary key not null,
user_id int not null,
date date not null,
weight decimal not null,
bmi decimal not null,
foreign key(user_id) references USERS(id) ON DELETE CASCADE
);

CREATE TABLE EXERCISE(
id int auto_increment primary key not null,
user_id int not null,
date date not null,
type varchar(50) not null,
duration time,
distance varchar(10),
cals_burned varchar(5),
foreign key(user_id) references USERS(id) ON DELETE CASCADE
);

CREATE TABLE MEALS(
id int auto_increment primary key not null,
user_id int not null,
date date not null,
breakfast int,
lunch int,
dinner int, 
snacks int,
foreign key(user_id) references USERS(id) ON DELETE CASCADE
);

CREATE TABLE STEPS(
id int auto_increment primary key not null,
user_id int not null,
date date not null,
steps int,
floors int,
miles decimal,
foreign key(user_id) references USERS(id) ON DELETE CASCADE
);

CREATE TABLE ACTIVE(
id int auto_increment primary key not null,
user_id int not null,
date date not null,
mins int,
floors int,
cals_burned int,
foreign key(user_id) references USERS(id) ON DELETE CASCADE
);


SELECT * FROM USERS
SELECT * FROM ACCOUNTINFO

SELECT * FROM EXERCISE

SET ISO_DAY_OF_WEEK 7
SELECT steps FROM STEPS 
AND WorkDate >= dateadd(day, 1-datepart(dw, getdate()), CONVERT(date,getdate())) 
AND WorkDate <  dateadd(day, 8-datepart(dw, getdate()), CONVERT(date,getdate()))

SELECT distance, date FROM EXERCISE WHERE user_id=1 AND type='Running' 
AND date BETWEEN '2018-12-02' AND '2018-12-08'

DELETE FROM MEALS;
DELETE FROM EXERCISE WHERE type='Running';

UPDATE ACCOUNTINFO SET name='Carson Uecker-Herman', height_feet=5, height_inches=8 WHERE user_id=1;
SELECT * FROM ACCOUNTINFO;

