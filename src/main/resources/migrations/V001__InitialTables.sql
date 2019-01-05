create table USER (
  ID varchar(36) primary key,
  EMAIL nvarchar(128) not null,
  FIRST_NAME nvarchar(128),
  LAST_NAME nvarchar(128),
);

create table COLUMN (
  ID varchar(36) primary key,
  NAME nvarchar(128),
);
create table CARD (
  ID varchar(36) primary key,
  TITLE nvarchar(256),
  DESCRIPTION clob,
  CREATION_TIME timestamp,
  CREATOR_ID varchar(36) not null,
  ASSIGNEE_ID varchar(36),
  COLUMN_ID varchar(36),
);