create table code (
  id                            bigint auto_increment not null,
  code                          varchar(255),
  description                   varchar(255),
  item_id                       bigint not null,
  constraint pk_code primary key (id)
);

create table items (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  weight                        integer not null,
  defence                       integer not null,
  damage                        integer not null,
  constraint pk_items primary key (id)
);

create table quests (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  type                          tinyint(1) default 0 not null,
  description                   varchar(255),
  constraint pk_quests primary key (id)
);

create table rewards (
  quest                         bigint not null,
  item                          bigint not null,
  constraint pk_rewards primary key (quest,item)
);

create table sellingitems (
  id                            bigint auto_increment not null,
  item_id                       bigint not null,
  price                         integer not null,
  selling_start                 date,
  duration                      bigint not null,
  constraint pk_sellingitems primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  money                         bigint not null,
  quest_id                      bigint,
  constraint pk_users primary key (id)
);

create table inventory (
  owner                         bigint not null,
  item                          bigint not null,
  constraint pk_inventory primary key (owner,item)
);

alter table code add constraint fk_code_item_id foreign key (item_id) references items (id) on delete restrict on update restrict;
create index ix_code_item_id on code (item_id);

alter table rewards add constraint fk_rewards_quests foreign key (quest) references quests (id) on delete restrict on update restrict;
create index ix_rewards_quests on rewards (quest);

alter table rewards add constraint fk_rewards_items foreign key (item) references items (id) on delete restrict on update restrict;
create index ix_rewards_items on rewards (item);

alter table sellingitems add constraint fk_sellingitems_item_id foreign key (item_id) references items (id) on delete restrict on update restrict;
create index ix_sellingitems_item_id on sellingitems (item_id);

alter table users add constraint fk_users_quest_id foreign key (quest_id) references quests (id) on delete restrict on update restrict;
create index ix_users_quest_id on users (quest_id);

alter table inventory add constraint fk_inventory_users foreign key (owner) references users (id) on delete restrict on update restrict;
create index ix_inventory_users on inventory (owner);

alter table inventory add constraint fk_inventory_items foreign key (item) references items (id) on delete restrict on update restrict;
create index ix_inventory_items on inventory (item);

