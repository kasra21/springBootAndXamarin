
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost' WITH GRANT OPTION;
CREATE USER 'admin'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' WITH GRANT OPTION;


CREATE SCHEMA `test_schema` ;

CREATE TABLE `test_schema`.`test_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(65) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

SELECT * FROM test_schema.test_table;

ALTER TABLE test_schema.test_table AUTO_INCREMENT = 1;
ALTER TABLE test_schema.test_table ADD UNIQUE (user_name, email);

CREATE INDEX test_index ON test_schema.test_table (id);

