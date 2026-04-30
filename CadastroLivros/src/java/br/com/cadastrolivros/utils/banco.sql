create table livro (
	id serial primary key,
	nomelivro varchar(100) not null,
	isbn varchar(50) not null,
	autor varchar(100) unique not null,
	datapublicacao date not null,
	valorlivro decimal(15,2) not null
);
