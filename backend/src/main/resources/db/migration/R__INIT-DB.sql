create table tss_conversation_messages (id integer generated by default as identity, wrote_on timestamp(6), conversation_id uuid, userr_id uuid, content varchar(500), userr varchar(255), primary key (id));
create table tss_conversation_statuses (id integer generated by default as identity, state tinyint check (state between 0 and 2), occurred_on timestamp(6), conversation_id uuid, description varchar(100), primary key (id));
create table tss_conversations (created_on timestamp(6), assigned_agent_id uuid, customer_id uuid, id uuid not null, name varchar(100), primary key (id));
create table tss_users (type char(1) not null, id uuid not null, password varchar(500), email varchar(255), name varchar(255), primary key (id));
alter table if exists tss_conversation_messages add constraint FK1bxkudh8vxhw13gp5pfd2lud foreign key (conversation_id) references tss_conversations;
alter table if exists tss_conversation_statuses add constraint FK3t09utr7otj6qe058om3lgsgv foreign key (conversation_id) references tss_conversations;
alter table if exists tss_conversations add constraint FKcdhh2srh3g10kbj7tcvegsk80 foreign key (customer_id) references tss_users;
alter table if exists tss_conversations add constraint FKkihb9xx7irad4h9c7ilyskvhf foreign key (assigned_agent_id) references tss_users;
