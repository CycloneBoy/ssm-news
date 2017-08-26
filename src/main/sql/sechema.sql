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


drop TABLE if EXISTS login_ticket;
CREATE table login_ticket(
  id int not null auto_increment,
  user_id int not null,
  ticket varchar(45) not null,
  expired datetime not null,
  status int null default 0,
  primary key(id),
  unique index ticket_unique (ticket asc)

)ENGINE=INNODB default charset=utf8;


drop table if exists comment;
create table comment(
  id int not null auto_increment,
  content text not null,
  user_id int not null,
  entity_id int not null,
  entity_type int not null,
  created_date datetime not null,
  status int not null default 0,
  primary key(id),
  index entity_index (entity_id asc ,entity_type asc)
)engine=innodb default charset=utf8;


drop table if EXISTS message;
create table message(
  id int not null auto_increment,
  from_id int not null,
  to_id int not null,
  content text not null,
  created_date datetime not null,
  has_read int not null,
  conversation_id varchar(20),
  primary key(id),
  index conversation_index (conversation_id)
)engine=INNODB default charset=utf8;
























