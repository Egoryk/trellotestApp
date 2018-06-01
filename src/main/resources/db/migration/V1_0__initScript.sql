
CREATE TABLE lines
(id bigint auto_increment NOT NULL,
title CHAR(250) NOT NULL ,
PRIMARY KEY (id));

CREATE table cards(
  id bigint auto_increment NOT NULL ,
  title char(250) not null,
  line integer,
  PRIMARY KEY (id),
  foreign key (line) references lines(id)
)