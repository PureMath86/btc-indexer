CREATE TABLE daily_index
(
  index_date  TIMESTAMP NOT NULL,
  index_value NUMERIC(10, 4),
  PRIMARY KEY (index_date)
);

CREATE TABLE total_btc
(
  date_  DATE NOT NULL,
  value_ INTEGER,
  PRIMARY KEY (date_)
);

CREATE TABLE sys_config
(
  name         VARCHAR(50),
  config_value VARCHAR(100),
  PRIMARY KEY (name)
);