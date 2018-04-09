prompt PL/SQL Developer import file
prompt Created on 2018年3月14日 by Administrator
set feedback off
set define off
prompt Creating SYS_FUNCTION...
create table SYS_FUNCTION
(
  id          NUMBER not null,
  name        VARCHAR2(32) not null,
  parent_id   NUMBER,
  grade       CHAR(1),
  uri         VARCHAR2(128),
  code        VARCHAR2(128),
  status      VARCHAR2(16) default 'NORMAL' not null,
  order_by    NUMBER default 0,
  logo        VARCHAR2(50),
  type        VARCHAR2(2),
  deleted     CHAR(1),
  create_user VARCHAR2(32),
  update_user VARCHAR2(32),
  create_time TIMESTAMP(6) default sysdate,
  update_time TIMESTAMP(6) default sysdate,
  version     NUMBER
)
tablespace BASISDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_FUNCTION.id
  is '权限id';
comment on column SYS_FUNCTION.name
  is '权限名称';
comment on column SYS_FUNCTION.parent_id
  is '父级权限id   无则为0';
comment on column SYS_FUNCTION.grade
  is '等级  1-一级,2-二级,3-三级';
comment on column SYS_FUNCTION.uri
  is '权限访问uri  只有二级才有';
comment on column SYS_FUNCTION.code
  is '权限码';
comment on column SYS_FUNCTION.status
  is '状态';
comment on column SYS_FUNCTION.order_by
  is '排序';
comment on column SYS_FUNCTION.logo
  is '图标常量';
comment on column SYS_FUNCTION.deleted
  is '启用  状态  O--开启,C--关闭';
create unique index SYS_FUNCTION_UK on SYS_FUNCTION (CODE)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_FUNCTION
  add primary key (ID)
  using index 
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_ROLE...
create table SYS_ROLE
(
  id          NUMBER not null,
  name        VARCHAR2(50) not null,
  status      VARCHAR2(16) not null,
  remark      VARCHAR2(255),
  deleted     CHAR(1),
  create_user VARCHAR2(32),
  update_user VARCHAR2(32),
  create_time TIMESTAMP(6),
  update_time TIMESTAMP(6),
  version     NUMBER
)
tablespace BASISDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_ROLE.deleted
  is '0-启用 1-关闭';
create unique index ROLE_NAME_UK1 on SYS_ROLE (NAME)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_ROLE
  add constraint SYS_ROLE_PK primary key (ID)
  using index 
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating RL_SYS_ROLE_FUNC...
create table RL_SYS_ROLE_FUNC
(
  sys_role_id NUMBER not null,
  sys_func_id NUMBER not null
)
tablespace BASISDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RL_SYS_ROLE_FUNC_NK1 on RL_SYS_ROLE_FUNC (SYS_ROLE_ID)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RL_SYS_ROLE_FUNC_NK2 on RL_SYS_ROLE_FUNC (SYS_FUNC_ID)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table RL_SYS_ROLE_FUNC
  add constraint RL_SYS_ROLE_FUNC_FK1 foreign key (SYS_ROLE_ID)
  references SYS_ROLE (ID) on delete cascade;
alter table RL_SYS_ROLE_FUNC
  add constraint RL_SYS_ROLE_FUNC_FK2 foreign key (SYS_FUNC_ID)
  references SYS_FUNCTION (ID) on delete cascade;

prompt Creating SYS_USR...
create table SYS_USR
(
  id          NUMBER not null,
  inst_id     NUMBER not null,
  user_type   VARCHAR2(2) not null,
  name        VARCHAR2(32) not null,
  pwd         VARCHAR2(64) not null,
  remark      VARCHAR2(256),
  user_status VARCHAR2(16),
  email       VARCHAR2(64),
  create_user VARCHAR2(32),
  update_user VARCHAR2(32),
  create_time TIMESTAMP(6),
  update_time TIMESTAMP(6),
  version     NUMBER
)
tablespace BASISDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SYS_USR.inst_id
  is '用户隶属机构';
comment on column SYS_USR.user_type
  is '用户类型 00代理商操作员，01商户操作员，99机构运营操作员';
comment on column SYS_USR.remark
  is '用户备注';
comment on column SYS_USR.user_status
  is '用户状态';
comment on column SYS_USR.email
  is '用户邮箱';
create unique index USR_NAME_UN1 on SYS_USR (NAME)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USR
  add constraint SYS_USR_PK primary key (ID)
  using index 
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_USR
  add constraint SYS_USR_FK1 foreign key (INST_ID)
  references GL_INST (ID) on delete cascade;

prompt Creating RL_SYS_USR_ROLE...
create table RL_SYS_USR_ROLE
(
  sys_usr_id  NUMBER not null,
  sys_role_id NUMBER not null
)
tablespace BASISDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RL_SYS_USR_ROLE_NK1 on RL_SYS_USR_ROLE (SYS_USR_ID)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index RL_SYS_USR_ROLE_NK2 on RL_SYS_USR_ROLE (SYS_ROLE_ID)
  tablespace BASISDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table RL_SYS_USR_ROLE
  add constraint RL_SYS_USR_ROLE_FK1 foreign key (SYS_USR_ID)
  references SYS_USR (ID) on delete cascade;
alter table RL_SYS_USR_ROLE
  add constraint RL_SYS_USR_ROLE_FK2 foreign key (SYS_ROLE_ID)
  references SYS_ROLE (ID) on delete cascade;

prompt Disabling triggers for SYS_FUNCTION...
alter table SYS_FUNCTION disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for RL_SYS_ROLE_FUNC...
alter table RL_SYS_ROLE_FUNC disable all triggers;
prompt Disabling triggers for SYS_USR...
alter table SYS_USR disable all triggers;
prompt Disabling triggers for RL_SYS_USR_ROLE...
alter table RL_SYS_USR_ROLE disable all triggers;
prompt Disabling foreign key constraints for RL_SYS_ROLE_FUNC...
alter table RL_SYS_ROLE_FUNC disable constraint RL_SYS_ROLE_FUNC_FK1;
alter table RL_SYS_ROLE_FUNC disable constraint RL_SYS_ROLE_FUNC_FK2;
prompt Disabling foreign key constraints for SYS_USR...
alter table SYS_USR disable constraint SYS_USR_FK1;
prompt Disabling foreign key constraints for RL_SYS_USR_ROLE...
alter table RL_SYS_USR_ROLE disable constraint RL_SYS_USR_ROLE_FK1;
alter table RL_SYS_USR_ROLE disable constraint RL_SYS_USR_ROLE_FK2;
prompt Loading SYS_FUNCTION...
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (900, '用户维护', 90, 'B', '/user/page', 'E1', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (910, '角色维护', 90, 'B', '/role/page', 'E2', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (920, '功能维护', 90, 'B', '/fun/page', 'func:query', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (90, '系统管理', 0, 'A', null, 'E6', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (905, '授权用户', 900, 'C', null, 'user:authority', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (911, '增加角色', 910, 'C', null, 'role:add', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (912, '删除角色', 910, 'C', null, 'role:del', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (913, '修改角色', 910, 'C', null, 'role:up', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (914, '查询角色', 910, 'C', null, 'role:query', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:18.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (915, '授权角色', 910, 'C', null, 'role:authority', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (921, '增加权限', 920, 'C', null, 'func:add', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (922, '删除权限', 920, 'C', null, 'func:del', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (923, '修改权限', 920, 'C', null, 'func:up', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (925, '授权权限', 920, 'C', null, 'func:authority', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (904, '查询用户', 900, 'C', null, 'user:query', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (903, '修改用户', 900, 'C', null, 'user:up', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (902, '删除用户', 900, 'C', null, 'user:del', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo, type, deleted, create_user, update_user, create_time, update_time, version)
values (901, '增加用户', 900, 'C', null, 'user:add', 'NORMAL', 0, null, null, null, null, null, to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), to_timestamp('13-03-2018 20:35:19.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null);
commit;
prompt 18 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (id, name, status, remark, deleted, create_user, update_user, create_time, update_time, version)
values (1, '11111', '1', '1111', null, null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading RL_SYS_ROLE_FUNC...
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 900);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 901);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 902);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 903);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 904);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 905);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 910);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 911);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 912);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 913);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 914);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 915);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 920);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 921);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 922);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 923);
insert into RL_SYS_ROLE_FUNC (sys_role_id, sys_func_id)
values (1, 925);
commit;
prompt 17 records loaded
prompt Loading SYS_USR...
insert into SYS_USR (id, inst_id, user_type, name, pwd, remark, user_status, email, create_user, update_user, create_time, update_time, version)
values (1, 1000, '00', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', 'NORMAL', 'admin@77pay.com.cn', null, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading RL_SYS_USR_ROLE...
insert into RL_SYS_USR_ROLE (sys_usr_id, sys_role_id)
values (1, 1);
commit;
prompt 1 records loaded
prompt Enabling foreign key constraints for RL_SYS_ROLE_FUNC...
alter table RL_SYS_ROLE_FUNC enable constraint RL_SYS_ROLE_FUNC_FK1;
alter table RL_SYS_ROLE_FUNC enable constraint RL_SYS_ROLE_FUNC_FK2;
prompt Enabling foreign key constraints for SYS_USR...
alter table SYS_USR enable constraint SYS_USR_FK1;
prompt Enabling foreign key constraints for RL_SYS_USR_ROLE...
alter table RL_SYS_USR_ROLE enable constraint RL_SYS_USR_ROLE_FK1;
alter table RL_SYS_USR_ROLE enable constraint RL_SYS_USR_ROLE_FK2;
prompt Enabling triggers for SYS_FUNCTION...
alter table SYS_FUNCTION enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for RL_SYS_ROLE_FUNC...
alter table RL_SYS_ROLE_FUNC enable all triggers;
prompt Enabling triggers for SYS_USR...
alter table SYS_USR enable all triggers;
prompt Enabling triggers for RL_SYS_USR_ROLE...
alter table RL_SYS_USR_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
