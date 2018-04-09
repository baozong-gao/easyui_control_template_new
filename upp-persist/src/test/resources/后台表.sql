create table SYS_FUNCTION(
  id              VARCHAR2(20) not null primary key,
  name            VARCHAR2(32) not null,
  parent_id       number  default 0 not null,
  grade           char(1)  default 'A' not null,
  uri             VARCHAR2(255),
  code            VARCHAR2(255) not null,
  status          char(1)  default 'O' not null,
  order_by        number default 0,
  type            char(1)  default '1' not null,
  logo            VARCHAR2(20)
);

comment on column SYS_FUNCTION.id  is '权限id';
comment on column SYS_FUNCTION.name  is '权限名称';
comment on column SYS_FUNCTION.parent_id  is '父级权限id   无则为0';
comment on column SYS_FUNCTION.grade  is '等级  A-一级,B-二级,C-三级';
comment on column SYS_FUNCTION.uri  is '权限访问uri  只有二级才有';
comment on column SYS_FUNCTION.code  is '权限码';
comment on column SYS_FUNCTION.status  is '状态  O--开启,C--关闭';
comment on column SYS_FUNCTION.type  is '类型  1--后台表,2--伙伴';
comment on column SYS_FUNCTION.order_by  is '排序';
comment on column SYS_FUNCTION.logo  is '图标常量';

create unique index SYS_FUNCTION_UK on SYS_FUNCTION (code);

create sequence SYS_FUNCTION_ID_SEQ
minvalue 10000001
maxvalue 99999999
start with 10000001
increment by 1
cache 20
cycle;


create table SYS_ROLE
(
  id                  VARCHAR2(20) not null primary key,
  name                VARCHAR2(50) not null,
  disable_tag         CHAR(1) default 1,
  remark              VARCHAR2(255)
);

comment on column SYS_ROLE.disable_tag  is '0-禁用 1-启用';
create unique index ROLE_NAME_UK1 on SYS_ROLE (NAME);

create sequence SYS_ROLE_ID_SEQ
minvalue 10000001
maxvalue 99999999
start with 10000001
increment by 1
cache 20
cycle;




create table SYS_USR
(
  id          VARCHAR2(20) not null primary key,
  name        VARCHAR2(20) not null,
  pwd         VARCHAR2(64) not null,
  remark      VARCHAR2(255),
  disable_tag CHAR(1) default 1 not null,
  email       VARCHAR2(50),
  create_by   VARCHAR2(20),
  create_date TIMESTAMP,
  update_by   VARCHAR2(20),
  update_date TIMESTAMP,
  type        VARCHAR2(24)
);
comment on column SYS_USR.remark  is '用户备注';
comment on column SYS_USR.disable_tag  is '0-禁用 1-启用';
comment on column SYS_USR.email  is '用户邮箱';
create unique index USR_NAME_UN1 on SYS_USR (NAME);

create sequence SYS_USR_ID_SEQ
minvalue 10000001
maxvalue 99999999
start with 10000001
increment by 1
cache 20
cycle;

--3. 创建角色资源表
create table rl_sys_role_func
(
  role_id                   VARCHAR2(20) not null,
  func_id                   VARCHAR2(20) not null,
  role_func_remark          VARCHAR2(255),
  role_func_create_by       VARCHAR2(20),
  role_func_create_datetime TIMESTAMP(6),
  role_func_update_by       VARCHAR2(20),
  role_func_update_datetime TIMESTAMP(6)
)
;
alter table rl_sys_role_func  add primary key (ROLE_ID, FUNC_ID)  using index ;
ALTER TABLE rl_sys_role_func ADD CONSTRAINT FK_role_ID FOREIGN KEY (role_id) REFERENCES SYS_ROLE (ID) ON DELETE CASCADE;
ALTER TABLE rl_sys_role_func ADD CONSTRAINT FK_func_ID FOREIGN KEY (func_id) REFERENCES SYS_FUNCTION (ID) ON DELETE CASCADE;

--5. 创建用户角色表
create table rl_sys_usr_role
(
  usr_id                   VARCHAR2(20) not null,
  role_id                  VARCHAR2(20) not null,
  usr_role_remark          VARCHAR2(255),
  usr_role_create_by       VARCHAR2(20),
  usr_role_create_datetime TIMESTAMP(6),
  usr_role_update_by       VARCHAR2(20),
  usr_role_update_datetime TIMESTAMP(6)
);
alter table rl_sys_usr_role  add primary key (USR_ID, ROLE_ID)  using index ;
ALTER TABLE rl_sys_usr_role ADD CONSTRAINT FK_role_ID_ FOREIGN KEY (role_id) REFERENCES SYS_ROLE (ID) ON DELETE CASCADE;
ALTER TABLE rl_sys_usr_role ADD CONSTRAINT FK_user_ID_ FOREIGN KEY (usr_id) REFERENCES SYS_USR (ID) ON DELETE CASCADE;




insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('900', '用户维护', '90', 'B', '/user/page', 'E1', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('910', '角色维护', '90', 'B', '/role/page', 'E2', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('920', '功能维护', '90', 'B', '/fun/page', 'func:query', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('90', '权限管理', '9', 'B', null, 'E5', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('9', '系统管理', '0', 'A', null, 'E6', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('905', '授权用户', '900', 'C', null, 'user:authority', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('911', '增加角色', '910', 'C', null, 'role:add', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('912', '删除角色', '910', 'C', null, 'role:del', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('913', '修改角色', '910', 'C', null, 'role:up', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('914', '查询角色', '910', 'C', null, 'role:query', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('915', '授权角色', '910', 'C', null, 'role:authority', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('921', '增加权限', '920', 'C', null, 'func:add', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('922', '删除权限', '920', 'C', null, 'func:del', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('923', '修改权限', '920', 'C', null, 'func:up', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('925', '授权权限', '920', 'C', null, 'func:authority', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('904', '查询用户', '900', 'C', null, 'user:query', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('903', '修改用户', '900', 'C', null, 'user:up', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('902', '删除用户', '900', 'C', null, 'user:del', 'O', 0, null);
insert into SYS_FUNCTION (id, name, parent_id, grade, uri, code, status, order_by, logo)
values ('901', '增加用户', '900', 'C', null, 'user:add', 'O', 0, null);

insert into SYS_ROLE (id, name, disable_tag, remark)
values ('1', '11111', '1', '1111');

insert into SYS_USR (id, name, pwd, remark, disable_tag, email, create_by, create_date, update_by, update_date, type)
values ('1', 'admin', 'HwR33kWfiZY7klz/RNPsuvNPNGx7HwQWn1byNSIQBai45FGp2wT1RNLMHSfspII9', '管理员', '1', 'admin@77pay.com.cn', null, null, null, null, null);

insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '10', '用户维护', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '11', '角色维护', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '12', '功能维护', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '9', '用户管理', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '900', '左菜单栏', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '910', '左菜单栏', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '920', '左菜单栏', null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '13', null, null, null, null, null);
insert into rl_sys_role_func (role_id, func_id, role_func_remark, role_func_create_by, role_func_create_datetime, role_func_update_by, role_func_update_datetime)
values ('1', '90', '左菜单栏', null, null, null, null);

insert into rl_sys_usr_role (usr_id, role_id, usr_role_remark, usr_role_create_by, usr_role_create_datetime, usr_role_update_by, usr_role_update_datetime)
values ('1', '1', null, null, null, null, null);

