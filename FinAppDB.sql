use master
go

create database FinAppDB
go

use FinAppDB
go

create table user_table(
	id_user			bigint			not null	primary key,
	name			nvarchar(max)	null,
	surname			nvarchar(max)	null,
	account_status	float			null
)
go

create table transaction_table(
	id_transaction	bigint			not null	primary key,
	date_created	datetime		not null,
	userid			bigint			not null	constraint userid foreign key references user_table(id_user),
	operation		nvarchar(max)	not null,
	amount			float			not null,
	debtorid		bigint			null		constraint debtorid foreign key references user_table(id_user)
)
go
