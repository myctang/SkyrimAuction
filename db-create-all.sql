create table code (
  id                            bigint auto_increment not null,
  code                          varchar(255),
  description                   varchar(255),
  item_id                       bigint not null,
  constraint pk_code primary key (id)
);

create table inventoryitems (
  id                            bigint auto_increment not null,
  item_id                       bigint,
  user_id                       bigint,
  quantity                      integer not null,
  constraint pk_inventoryitems primary key (id)
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
  duration                      bigint not null,
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
  buy_now_price                 integer not null,
  finished                      tinyint(1) default 0 not null,
  selling_end                   datetime,
  duration                      bigint not null,
  holder_id                     bigint not null,
  last_bidder_id                bigint,
  constraint pk_sellingitems primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  money                         bigint not null,
  password                      varchar(255),
  roles                         varchar(1000),
  quest_id                      bigint,
  start_of_quest                datetime,
  account_non_expired           tinyint(1) default 0 not null,
  account_non_locked            tinyint(1) default 0 not null,
  credentials_non_expired       tinyint(1) default 0 not null,
  enabled                       tinyint(1) default 0 not null,
  constraint uq_users_name unique (name),
  constraint pk_users primary key (id)
);

alter table code add constraint fk_code_item_id foreign key (item_id) references items (id) on delete restrict on update restrict;
create index ix_code_item_id on code (item_id);

alter table inventoryitems add constraint fk_inventoryitems_item_id foreign key (item_id) references items (id) on delete restrict on update restrict;
create index ix_inventoryitems_item_id on inventoryitems (item_id);

alter table inventoryitems add constraint fk_inventoryitems_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_inventoryitems_user_id on inventoryitems (user_id);

alter table rewards add constraint fk_rewards_quests foreign key (quest) references quests (id) on delete restrict on update restrict;
create index ix_rewards_quests on rewards (quest);

alter table rewards add constraint fk_rewards_items foreign key (item) references items (id) on delete restrict on update restrict;
create index ix_rewards_items on rewards (item);

alter table sellingitems add constraint fk_sellingitems_item_id foreign key (item_id) references items (id) on delete restrict on update restrict;
create index ix_sellingitems_item_id on sellingitems (item_id);

alter table sellingitems add constraint fk_sellingitems_holder_id foreign key (holder_id) references users (id) on delete restrict on update restrict;
create index ix_sellingitems_holder_id on sellingitems (holder_id);

alter table sellingitems add constraint fk_sellingitems_last_bidder_id foreign key (last_bidder_id) references users (id) on delete restrict on update restrict;
create index ix_sellingitems_last_bidder_id on sellingitems (last_bidder_id);

alter table users add constraint fk_users_quest_id foreign key (quest_id) references quests (id) on delete restrict on update restrict;
create index ix_users_quest_id on users (quest_id);

