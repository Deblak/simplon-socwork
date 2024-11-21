DROP TABLE IF EXISTS t_accounts;

CREATE TABLE t_accounts (
	id int GENERATED ALWAYS AS IDENTITY,
	username varchar(255),
	password varchar(72),
	CONSTRAINT t_account_pkey PRIMARY KEY (id),
	CONSTRAINT t_account_ukey UNIQUE (username)
);