CREATE TABLE user_lk
(
    ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NICK    VARCHAR(256) NOT NULL,
    LVL    VARCHAR(256) NOT NULL,
    LEARNT_WORDS NUMBER NOT NULL DEFAULT 0

);