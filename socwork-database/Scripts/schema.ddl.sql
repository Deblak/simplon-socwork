DROP TABLE IF EXISTS t_account_roles;
DROP TABLE IF EXISTS t_roles;
DROP TABLE IF EXISTS t_accounts;

CREATE TABLE t_accounts (
	id int GENERATED ALWAYS AS IDENTITY,
	username varchar(255),
	password varchar(72),
	CONSTRAINT t_account_pkey PRIMARY KEY (id),
	CONSTRAINT t_account_ukey UNIQUE (username)
);

CREATE TABLE t_roles(
	role_id int GENERATED ALWAYS AS IDENTITY,
	name varchar(13),
	role_default boolean,
	CONSTRAINT t_role_pkey PRIMARY KEY (role_id),
	CONSTRAINT t_role_name_ukey UNIQUE (name)
);

CREATE TABLE t_account_roles (
    account_id int,
    role_id int,
    PRIMARY KEY (account_id, role_id),
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES t_accounts(id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES t_roles(role_id)
);
