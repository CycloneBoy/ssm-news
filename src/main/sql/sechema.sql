drop TABLE if EXISTS user;
create table user(
  id int(11) unsigned not null auto_increment,
  name varchar(64) not null default '',
  password varchar(128) not null default '',
  salt varchar(32) not null default '',
  head_url varchar(256) not null default '',
  primary key(id),
  unique key name (name)
)ENGINE=INNODB default charset=utf8;

drop table if EXISTS news;
create table news(
  id int(11) unsigned not null auto_increment,
  title varchar(128) not null default '',
  link varchar(256) not null DEFAULT '',
  image varchar(256) not null default '',
  like_count int not null,
  comment_count int not null,
  create_date datetime not null,
  user_id int(11) not null,
  primary key(id)
)ENGINE=INNODB default charset=utf8;
