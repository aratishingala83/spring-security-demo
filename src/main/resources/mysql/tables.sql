  
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS bael_users;

CREATE  TABLE bael_users (
  username VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);
  
CREATE TABLE authorities (
  id integer,
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES bael_users(username)
);

CREATE TABLE customer (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));


commit;