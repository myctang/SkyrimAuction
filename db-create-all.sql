create table items (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  weight                        integer not null,
  defence                       integer not null,
  damage                        integer not null,
  constraint pk_items primary key (id)
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
  constraint pk_users primary key (id)
);

create table inventory (
  owner                         bigint not null,
  item                          bigint not null,
  constraint pk_inventory primary key (owner,item)
);

alter table sellingitems add constraint fk_sellingitems_item_id foreign key (item_id) references items (id) on delete restrict on update restrict;
create index ix_sellingitems_item_id on sellingitems (item_id);

alter table inventory add constraint fk_inventory_users foreign key (owner) references users (id) on delete restrict on update restrict;
create index ix_inventory_users on inventory (owner);

alter table inventory add constraint fk_inventory_items foreign key (item) references items (id) on delete restrict on update restrict;
create index ix_inventory_items on inventory (item);

