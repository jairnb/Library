-- MySQL Script generated by MySQL Workbench
-- Wed 19 Jan 2022 02:46:15 PM CST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema library_mpp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema library_mpp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library_mpp` ;
USE `library_mpp` ;

-- -----------------------------------------------------
-- Table `library_mpp`.`adress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`adress` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `adress_id` INT NOT NULL,
  PRIMARY KEY (`id`, `adress_id`),
  INDEX `fk_member_adress1_idx` (`adress_id` ASC) VISIBLE,
  CONSTRAINT `fk_member_adress1`
    FOREIGN KEY (`adress_id`)
    REFERENCES `library_mpp`.`adress` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`author` (
  `idAuthor` INT NOT NULL AUTO_INCREMENT,
  `credential` VARCHAR(45) NULL,
  `shortBio` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `adress_id` INT NOT NULL,
  PRIMARY KEY (`idAuthor`, `adress_id`),
  INDEX `fk_author_adress1_idx` (`adress_id` ASC) VISIBLE,
  CONSTRAINT `fk_author_adress1`
    FOREIGN KEY (`adress_id`)
    REFERENCES `library_mpp`.`adress` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `isbn` VARCHAR(45) NULL,
  `availability` VARCHAR(45) NULL,
  `borrow_day_number` VARCHAR(45) NULL,
  `author_idAuthor` INT NOT NULL,
  PRIMARY KEY (`id`, `author_idAuthor`),
  INDEX `fk_book_author1_idx` (`author_idAuthor` ASC) VISIBLE,
  CONSTRAINT `fk_book_author1`
    FOREIGN KEY (`author_idAuthor`)
    REFERENCES `library_mpp`.`author` (`idAuthor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`checkout_entriers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`checkout_entriers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `due_date` DATE NULL,
  `checkout_date` DATE NULL,
  `book_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`id`, `book_id`, `member_id`),
  INDEX `fk_checkoutEntriers_Book1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_checkout_entriers_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_checkoutEntriers_Book1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library_mpp`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_checkout_entriers_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `library_mpp`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`fine_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`fine_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datePaiment` DATE NULL,
  `checkoutEntriers_id` INT NOT NULL,
  PRIMARY KEY (`id`, `checkoutEntriers_id`),
  INDEX `fk_fine_record_checkoutEntriers1_idx` (`checkoutEntriers_id` ASC) VISIBLE,
  CONSTRAINT `fk_fine_record_checkoutEntriers1`
    FOREIGN KEY (`checkoutEntriers_id`)
    REFERENCES `library_mpp`.`checkout_entriers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`book_copy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`book_copy` (
  `idbook_copy` INT NOT NULL AUTO_INCREMENT,
  `isbn` VARCHAR(45) NULL,
  `Book_idBook` INT NOT NULL,
  PRIMARY KEY (`idbook_copy`, `Book_idBook`),
  INDEX `fk_book_copy_Book1_idx` (`Book_idBook` ASC) VISIBLE,
  CONSTRAINT `fk_book_copy_Book1`
    FOREIGN KEY (`Book_idBook`)
    REFERENCES `library_mpp`.`book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `library_mpp`.`role_has_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library_mpp`.`role_has_member` (
  `role_idrole` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`role_idrole`, `member_id`),
  INDEX `fk_role_has_member_member1_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_role_has_member_role1_idx` (`role_idrole` ASC) VISIBLE,
  CONSTRAINT `fk_role_has_member_role1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `library_mpp`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_member_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `library_mpp`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;