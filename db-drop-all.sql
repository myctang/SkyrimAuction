alter table sellingitems drop foreign key fk_sellingitems_item_id;
drop index ix_sellingitems_item_id on sellingitems;

alter table inventory drop foreign key fk_inventory_users;
drop index ix_inventory_users on inventory;

alter table inventory drop foreign key fk_inventory_items;
drop index ix_inventory_items on inventory;

drop table if exists items;

drop table if exists sellingitems;

drop table if exists users;

drop table if exists inventory;

