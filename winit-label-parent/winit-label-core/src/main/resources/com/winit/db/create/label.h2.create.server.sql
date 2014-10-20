create table WT_PROPERTY (
    NAME_ varchar(64),
    VALUE_ varchar(4000),
    DESCRIPTIOIN_ varchar(512),
    REV_ integer,
    primary key (NAME_)
);


create table WT_BYTEARRAY (
    ID_ varchar(64),
    REV_ integer,
    NAME_ varchar(255),
    DEPLOYMENT_ID_ varchar(64),
    BYTES_ longvarbinary,
    GENERATED_ bit,
    primary key (ID_)
);

create table WT_VARIABLE (
    ID_ varchar(64) not null,
    REV_ integer,
    TYPE_ varchar(255) not null,
    NAME_ varchar(255) not null,
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint,
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    OWNER_ VARCHAR(256),
    REFERENCE_KEY_ VARCHAR(64),
    primary key (ID_)
);




create table WT_LOGISTIC_TYPE (
    ID_ varchar(64),
    REV_ integer,
    NAME_ varchar(255),
    CODE_ varchar(255),
    primary key (ID_)
);

create table WT_LABEL (
    ID_ varchar(64),
    REV_ integer,
    DOCUMENT_NO_ varchar(64),
    TRACK_NO_ varchar(255),
    FILE_CODE_ clob,
    STATUS_ varchar(64),
    primary key (ID_)
);




alter table WT_VARIABLE
    add constraint ACT_FK_VAR_BYTEARRAY
    foreign key (BYTEARRAY_ID_)
    references WT_BYTEARRAY;

