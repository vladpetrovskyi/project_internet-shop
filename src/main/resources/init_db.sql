CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet_shop`.`products` (
                                            `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                            `name` VARCHAR(225) NOT NULL,
                                            `price` BIGINT(11) NOT NULL,
                                            PRIMARY KEY (`product_id`),
                                            UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);
CREATE TABLE `internet_shop`.`users` (
                                         `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                         `name` VARCHAR(255) NOT NULL,
                                         `login` VARCHAR(255) NOT NULL,
                                         `password` VARCHAR(255) NOT NULL,
                                         PRIMARY KEY (`user_id`),
                                         UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);
CREATE TABLE `internet_shop`.`shopping_carts` (
                                                  `cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                                  `user_id` BIGINT(11) NOT NULL,
                                                  PRIMARY KEY (`cart_id`),
                                                  INDEX `cart_user_fk_idx` (`user_id` ASC) VISIBLE,
                                                  CONSTRAINT `carts_users_fk`
                                                      FOREIGN KEY (`user_id`)
                                                          REFERENCES `internet_shop`.`users` (`user_id`)
                                                          ON DELETE NO ACTION
                                                          ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`shopping_carts_products` (
                                                           `cart_id` BIGINT(11) NULL,
                                                           `product_id` BIGINT(11) NULL,
                                                           INDEX `cart_cart_fk_idx` (`cart_id` ASC) VISIBLE,
                                                           INDEX `products_carts_fk_idx` (`product_id` ASC) VISIBLE,
                                                           CONSTRAINT `carts_carts_fk`
                                                               FOREIGN KEY (`cart_id`)
                                                                   REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION,
                                                           CONSTRAINT `products_carts_fk`
                                                               FOREIGN KEY (`product_id`)
                                                                   REFERENCES `internet_shop`.`products` (`product_id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`orders` (
                                          `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                          `user_id` BIGINT(11) NULL,
                                          PRIMARY KEY (`order_id`),
                                          INDEX `users_orders_fk_idx` (`user_id` ASC) VISIBLE,
                                          CONSTRAINT `users_orders_fk`
                                              FOREIGN KEY (`user_id`)
                                                  REFERENCES `internet_shop`.`users` (`user_id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`orders_products` (
                                                   `order_id` BIGINT(11) NULL,
                                                   `product_id` BIGINT(11) NULL,
                                                   INDEX `orders_orders_fk_idx` (`order_id` ASC) VISIBLE,
                                                   INDEX `products_products_fk_idx` (`product_id` ASC) VISIBLE,
                                                   CONSTRAINT `orders_orders_fk`
                                                       FOREIGN KEY (`order_id`)
                                                           REFERENCES `internet_shop`.`orders` (`order_id`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION,
                                                   CONSTRAINT `products_products_fk`
                                                       FOREIGN KEY (`product_id`)
                                                           REFERENCES `internet_shop`.`products` (`product_id`)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION);
CREATE TABLE `internet_shop`.`roles` (
                                         `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                         `role_name` VARCHAR(255) NOT NULL,
                                         PRIMARY KEY (`role_id`),
                                         UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE);
CREATE TABLE `internet_shop`.`users_roles` (
                                               `user_id` BIGINT(11) NULL,
                                               `role_id` BIGINT(11) NULL,
                                               INDEX `roles_roles_fk_idx` (`role_id` ASC) VISIBLE,
                                               INDEX `users_users_fk_idx` (`user_id` ASC) VISIBLE,
                                               CONSTRAINT `roles_roles_fk`
                                                   FOREIGN KEY (`role_id`)
                                                       REFERENCES `internet_shop`.`roles` (`role_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `users_users_fk`
                                                   FOREIGN KEY (`user_id`)
                                                       REFERENCES `internet_shop`.`users` (`user_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);
INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('ADMIN');
INSERT INTO `internet_shop`.`roles` (`role_name`) VALUES ('USER');
ALTER TABLE `internet_shop`.`users`
    ADD COLUMN `salt` BINARY(16) NOT NULL AFTER `password`;
