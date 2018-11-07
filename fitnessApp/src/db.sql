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
height_inches decimal,
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

