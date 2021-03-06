create table WT_ID_GROUP (
    ID_ varchar(64),
    REV_ integer,
    NAME_ varchar(255),
    TYPE_ varchar(255),
    primary key (ID_)
);

create table WT_ID_MEMBERSHIP (
    USER_ID_ varchar(64),
    GROUP_ID_ varchar(64),
    primary key (USER_ID_, GROUP_ID_)
);

create table WT_ID_USER (
    ID_ varchar(64),
    REV_ integer,
    FIRST_ varchar(255),
    LAST_ varchar(255),
    EMAIL_ varchar(255),
    PWD_ varchar(255),
    PICTURE_ID_ varchar(64),
    primary key (ID_)
);

create table WT_ID_INFO (
    ID_ varchar(64),
    REV_ integer,
    USER_ID_ varchar(64),
    TYPE_ varchar(64),
    KEY_ varchar(255),
    VALUE_ varchar(255),
    PASSWORD_ longvarbinary,
    PARENT_ID_ varchar(255),
    primary key (ID_)
);

alter table WT_ID_MEMBERSHIP
    add constraint WT_FK_MEMB_GROUP
    foreign key (GROUP_ID_)
    references WT_ID_GROUP;

alter table WT_ID_MEMBERSHIP
    add constraint WT_FK_MEMB_USER
    foreign key (USER_ID_)
    references WT_ID_USER;

create table ID_TREE (
    ID_ varchar(64),
    REV_ integer,
    REFERENCE_ID_ varchar(64),
    REFERENCE_TYPE_ varchar(64),
    LABLE_ varchar(255),
    IS_LEAF_ varchar(255),
    PARENT_ID_ varchar(255),
    primary key (ID_)
);
