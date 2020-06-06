CREATE UNIQUE INDEX ix_auth_username on authorities (username,authority);

INSERT INTO bael_users (username, email, password, enabled) values ('user', 'user@email.com', 'password', 1);

INSERT INTO authorities (id,username, authority) values (1,'user', 'ROLE_USER');

INSERT INTO bael_users (username, email, password, enabled) values ('vijay', 'vijay@email.com', 'password', 1);

INSERT INTO authorities (id,username, authority) values (2,'vijay', 'ROLE_CUSTOMER');

INSERT INTO bael_users (username, email, password, enabled) values ('admin', 'admin@email.com', 'password', 1);

INSERT INTO authorities (id,username, authority) values (3,'admin', 'ROLE_ADMIN');
INSERT INTO authorities (id,username, authority) values (4,'admin', 'ROLE_CUSTOMER');
INSERT INTO authorities (id,username, authority) values (5,'admin', 'ROLE_VIEWER');
commit;