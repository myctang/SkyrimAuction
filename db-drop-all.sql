alter table code drop foreign key fk_code_item_id;
drop index ix_code_item_id on code;

alter table inventoryitems drop foreign key fk_inventoryitems_item_id;
drop index ix_inventoryitems_item_id on inventoryitems;

alter table inventoryitems drop foreign key fk_inventoryitems_user_id;
drop index ix_inventoryitems_user_id on inventoryitems;

alter table rewards drop foreign key fk_rewards_quests;
drop index ix_rewards_quests on rewards;

alter table rewards drop foreign key fk_rewards_items;
drop index ix_rewards_items on rewards;

alter table sellingitems drop foreign key fk_sellingitems_item_id;
drop index ix_sellingitems_item_id on sellingitems;

alter table sellingitems drop foreign key fk_sellingitems_holder_id;
drop index ix_sellingitems_holder_id on sellingitems;

alter table sellingitems drop foreign key fk_sellingitems_last_bidder_id;
drop index ix_sellingitems_last_bidder_id on sellingitems;

alter table users drop foreign key fk_users_quest_id;
drop index ix_users_quest_id on users;

drop table if exists code;

drop table if exists inventoryitems;

drop table if exists items;

drop table if exists quests;

drop table if exists rewards;

drop table if exists sellingitems;

drop table if exists users;

