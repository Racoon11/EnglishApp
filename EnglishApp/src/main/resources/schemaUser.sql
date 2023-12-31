-- create table users(
-- 	username varchar_ignorecase(50) not null primary key,
-- 	password varchar_ignorecase(50) not null,
-- 	enabled boolean not null
-- );

-- create table authorities (
-- 	username varchar_ignorecase(50) not null,
-- 	authority varchar_ignorecase(50) not null,
-- 	constraint fk_authorities_users foreign key(username) references users(username)
-- );

-- create trable user_lk (
-- 	username varchar_ignorecase(50) not null
-- )

-- create unique index ix_auth_username on authorities (username,authority);


CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(200) NOT NULL PRIMARY KEY,
    password VARCHAR(254) NOT NULL ,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities
(
    username VARCHAR(200) NOT NULL,
    authority VARCHAR(254) NOT NULL ,
    constraint fk_authorities_users foreign key(username) references users(username)
);
CREATE TABLE IF NOT EXISTS user_lk
(
    username VARCHAR(200) NOT NULL
);

create unique index ix_auth_username on authorities (username,authority);