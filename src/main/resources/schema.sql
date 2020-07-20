CREATE TABLE task_type (
  id int(2) NOT NULL,
  type varchar(20) NOT NULL,
  comment varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE task (
  id int(5) NOT NULL AUTO_INCREMENT,
  user_id int(5) NOT NULL,
  type_id int(2) NOT NULL,
  title varchar(50) NOT NULL,
  detail text,
  deadline datetime NOT NULL,
  PRIMARY KEY (id)
) ;

CREATE TABLE USER(
ID INT PRIMARY KEY AUTO_INCREMENT, 
NAME VARCHAR(64) NOT NULL, 
PASSWORD VARCHAR(64) NOT NULL,
ROLES VARCHAR(64) ,
ENABLE_FLAG int(10) NOT NULL  DEFAULT 1)