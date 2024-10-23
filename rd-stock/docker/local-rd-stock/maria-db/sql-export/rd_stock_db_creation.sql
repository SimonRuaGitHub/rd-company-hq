-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rd_stock_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rd_stock_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rd_stock_db` DEFAULT CHARACTER SET utf8mb4 ;
USE `rd_stock_db` ;

-- -----------------------------------------------------
-- Table `rd_stock_db`.`OPTION_CATEGORIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`OPTION_CATEGORIES` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(140) NOT NULL,
  `label` VARCHAR(45) NOT NULL,
  `company_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PARENT_PRODUCTS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PARENT_PRODUCTS` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(280) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `company_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PRODUCT_TYPES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PRODUCT_TYPES` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PARENT_PRODUCTS_TYPES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PARENT_PRODUCTS_TYPES` (
  `type_id` INT(11) NOT NULL,
  `parent_product_id` INT(11) NOT NULL,
  PRIMARY KEY (`parent_product_id`, `type_id`),
  INDEX `fk_PRODUCT_TYPES_has_PARENT_PRODUCTS_PARENT_PRODUCTS1_idx` (`parent_product_id` ASC) VISIBLE,
  INDEX `fk_PRODUCT_TYPES_has_PARENT_PRODUCTS_PRODUCT_TYPES1_idx` (`type_id` ASC) VISIBLE,
  CONSTRAINT `FK_PRODUCT_TYPES_PARENT_PRODUCT`
    FOREIGN KEY (`type_id`)
    REFERENCES `rd_stock_db`.`PRODUCT_TYPES` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PARENT_PRODUCT_PRODUCT_TYPE`
    FOREIGN KEY (`parent_product_id`)
    REFERENCES `rd_stock_db`.`PARENT_PRODUCTS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PRODUCTS_OPTION_CATEGORIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PRODUCTS_OPTION_CATEGORIES` (
  `product_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX `fk_PARENT_PRODUCTS_has_OPTION_CATEGORIES_OPTION_CATEGORIES1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_PARENT_PRODUCTS_has_OPTION_CATEGORIES_PARENT_PRODUCTS1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_PARENT_PRODUCTS_has_OPTION_CATEGORIES_OPTION_CATEGORIES1`
    FOREIGN KEY (`category_id`)
    REFERENCES `rd_stock_db`.`OPTION_CATEGORIES` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PARENT_PRODUCTS_has_OPTION_CATEGORIES_PARENT_PRODUCTS1`
    FOREIGN KEY (`product_id`)
    REFERENCES `rd_stock_db`.`PARENT_PRODUCTS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`RACKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`RACKS` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `parent_rack_id` INT(11) NULL DEFAULT NULL,
  `company_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_RACKS_RACKS1_idx` (`parent_rack_id` ASC) VISIBLE,
  CONSTRAINT `fk_RACKS_RACKS1`
    FOREIGN KEY (`parent_rack_id`)
    REFERENCES `rd_stock_db`.`RACKS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PRODUCTS_RACKS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PRODUCTS_RACKS` (
  `product_id` INT(11) NOT NULL,
  `rack_id` INT(11) NOT NULL,
  PRIMARY KEY (`product_id`, `rack_id`),
  INDEX `fk_PARENT_PRODUCTS_has_RACKS_RACKS1_idx` (`rack_id` ASC) VISIBLE,
  INDEX `fk_PARENT_PRODUCTS_has_RACKS_PARENT_PRODUCTS1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_PARENT_PRODUCTS_has_RACKS_PARENT_PRODUCTS1`
    FOREIGN KEY (`product_id`)
    REFERENCES `rd_stock_db`.`PARENT_PRODUCTS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PARENT_PRODUCTS_has_RACKS_RACKS1`
    FOREIGN KEY (`rack_id`)
    REFERENCES `rd_stock_db`.`RACKS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PRODUCT_VERSIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PRODUCT_VERSIONS` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `version_id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(280) NOT NULL,
  `filename` VARCHAR(100) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `price` DECIMAL(10,8) NOT NULL,
  `is_available` TINYINT(4) NOT NULL,
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `productId_UNIQUE` (`version_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) VISIBLE,
  INDEX `fk_PRODUCT_VERSIONS_PARENT_PRODUCTS_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCT_VERSIONS_PARENT_PRODUCTS`
    FOREIGN KEY (`product_id`)
    REFERENCES `rd_stock_db`.`PARENT_PRODUCTS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`PRODUCT_AVAILABILITIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`PRODUCT_AVAILABILITIES` (
  `company_site_id` VARCHAR(45) NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `quantity_available` DECIMAL(8,0) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `product_versions_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`company_site_id`, `product_versions_id`),
  INDEX `fk_PRODUCT_AVAILABILITIES_PRODUCT_VERSIONS1_idx` (`product_versions_id` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCT_AVAILABILITIES_PRODUCT_VERSIONS1`
    FOREIGN KEY (`product_versions_id`)
    REFERENCES `rd_stock_db`.`PRODUCT_VERSIONS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`ADDITIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`ADDITIONS` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(10,8) NOT NULL,
  `file_name` VARCHAR(45) NULL,
  `company_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rd_stock_db`.`OPTION_CATEGORIES_ADDITIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rd_stock_db`.`OPTION_CATEGORIES_ADDITIONS` (
  `option_category_id` INT(11) NOT NULL,
  `addition_id` INT(11) NOT NULL,
  PRIMARY KEY (`option_category_id`, `addition_id`),
  INDEX `fk_OPTION_CATEGORIES_has_ADDITIONS_ADDITIONS1_idx` (`addition_id` ASC) VISIBLE,
  INDEX `fk_OPTION_CATEGORIES_has_ADDITIONS_OPTION_CATEGORIES1_idx` (`option_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_ADDITION_OPTION_CATEGORIES`
    FOREIGN KEY (`option_category_id`)
    REFERENCES `rd_stock_db`.`OPTION_CATEGORIES` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OPTION_CATEGORY_ADDITIONS`
    FOREIGN KEY (`addition_id`)
    REFERENCES `rd_stock_db`.`ADDITIONS` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
