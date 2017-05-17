-- MySQL Script generated by MySQL Workbench
-- Seg 13 Jul 2015 17:06:02 BRT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema fumetteria
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fumetteria` ;

-- -----------------------------------------------------
-- Schema fumetteria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fumetteria` DEFAULT CHARACTER SET utf8 ;
USE `fumetteria` ;

-- -----------------------------------------------------
-- Table `fumetteria`.`author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`author` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`author` (
  `id` INT(11) NOT NULL COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`publishing_house`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`publishing_house` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`publishing_house` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `phone` VARCHAR(14) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`comic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`comic` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`comic` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `vol` INT(11) NOT NULL COMMENT '',
  `edition` INT(11) NOT NULL COMMENT '',
  `publication` DATE NOT NULL COMMENT '',
  `url_image_cover` VARCHAR(85) NULL DEFAULT NULL COMMENT '',
  `description` VARCHAR(300) NOT NULL COMMENT '',
  `stock` INT(11) NOT NULL COMMENT '',
  `publishing_house_id` INT(11) NOT NULL COMMENT '',
  `value` DECIMAL(8,2) NOT NULL COMMENT '',
  `show_home` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_comics_publishing_house1_idx` (`publishing_house_id` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`comic_author`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`comic_author` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`comic_author` (
  `type` VARCHAR(45) NOT NULL COMMENT '',
  `comic_id` INT(11) NOT NULL COMMENT '',
  `author_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`comic_id`, `author_id`)  COMMENT '',
  INDEX `fk_comics_author_comics1_idx` (`comic_id` ASC)  COMMENT '',
  INDEX `fk_comics_author_author1_idx` (`author_id` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`genre` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`genre` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`comic_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`comic_genre` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`comic_genre` (
  `comic_id` INT(11) NOT NULL COMMENT '',
  `genre_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`comic_id`, `genre_id`)  COMMENT '',
  INDEX `fk_comics_has_genre_genre1_idx` (`genre_id` ASC)  COMMENT '',
  INDEX `fk_comics_has_genre_comics1_idx` (`comic_id` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`customer` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`customer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(60) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(45) NOT NULL COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `type_user` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`reserve`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`reserve` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`reserve` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `data` DATETIME NOT NULL COMMENT '',
  `total` DECIMAL(8,2) NOT NULL COMMENT '',
  `customer_id` INT(11) NOT NULL COMMENT '',
  `status` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_reserve_customer1_idx` (`customer_id` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`comic_reserve`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`comic_reserve` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`comic_reserve` (
  `comic_id` INT(11) NOT NULL COMMENT '',
  `reserve_id` INT(11) NOT NULL COMMENT '',
  `qty` INT(11) NOT NULL COMMENT '',
  `value` DECIMAL(8,2) NOT NULL COMMENT '',
  PRIMARY KEY (`comic_id`, `reserve_id`)  COMMENT '',
  INDEX `fk_comics_has_reserve_reserve1_idx` (`reserve_id` ASC)  COMMENT '',
  INDEX `fk_comics_has_reserve_comics1_idx` (`comic_id` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`note` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`note` (
  `id` INT(11) NOT NULL COMMENT '',
  `note` VARCHAR(300) NULL DEFAULT NULL COMMENT '',
  `status` INT(11) NOT NULL COMMENT '',
  `author_id` INT(11) NOT NULL COMMENT '',
  `comic_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `author_id`)  COMMENT '',
  INDEX `fk_note_author1_idx` (`author_id` ASC)  COMMENT '',
  INDEX `fk_note_comic1_idx` (`comic_id` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`phone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`phone` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`phone` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `number` VARCHAR(16) NOT NULL COMMENT '',
  `type` VARCHAR(20) NOT NULL COMMENT '',
  `customer_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_phone_customer1_idx` (`customer_id` ASC)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fumetteria`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fumetteria`.`notice` ;

CREATE TABLE IF NOT EXISTS `fumetteria`.`notice` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `comic_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_notice_comic1_idx` (`comic_id` ASC)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
