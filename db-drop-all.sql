alter table code drop foreign key fk_code_item_id;
drop index ix_code_item_id on code;

alter table quests drop foreign key fk_quests_current_quest_id;
drop index ix_quests_current_quest_id on quests;

alter table rewards drop foreign key fk_rewards_quests;
drop index ix_rewards_quests on rewards;

alter table rewards drop foreign key fk_rewards_items;
drop index ix_rewards_items on rewards;

alter table sellingitems drop foreign key fk_sellingitems_item_id;
drop index ix_sellingitems_item_id on sellingitems;

alter table users drop foreign key fk_users_quest_id;
drop index ix_users_quest_id on users;

alter table inventory drop foreign key fk_inventory_users;
drop index ix_inventory_users on inventory;

alter table inventory drop foreign key fk_inventory_items;
drop index ix_inventory_items on inventory;

drop table if exists code;

drop table if exists items;

drop table if exists quests;

drop table if exists rewards;

drop table if exists sellingitems;

drop table if exists users;

drop table if exists inventory;

