create table if not exists books (
	id bigserial PRIMARY KEY,
	name varchar(100) unique not null,
	is_borrowed boolean not null default 'false'
);



create table if not exists clients (
	id bigserial PRIMARY KEY,
	name varchar(100) unique not null
);



create table if not exists borrow_info (
	id bigserial PRIMARY KEY,
	book_id bigint not null,
	client_id bigint not null,
	is_returned boolean default 'false',
	constraint fk_brw_info_on_books foreign key (book_id) references books(id),
	constraint fk_brw_info_on_clients foreign key (client_id) references clients(id)
);


insert into books(name, is_borrowed) values ('Bible', false);
insert into books(name, is_borrowed) values ('Torah', false);
insert into books(name, is_borrowed) values ('Quran', false);
insert into books(name, is_borrowed) values ('Tripitaka', false);

insert into clients(name) values ('Jesus Christ');
insert into clients(name) values ('G-d');
insert into clients(name) values ('Allah');
insert into clients(name) values ('Buddah');

insert into borrow_info (book_id, client_id, is_returned) values (1, 1, false);


--drop table if exists borrow_info;
--drop table if exists books;
--drop table if exists clients;



