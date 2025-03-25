-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Movie` (
  `movie_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `overview` VARCHAR(250) NULL,
  `popularity` DOUBLE NULL,
  `release_date` DATE NULL,
  `revenue` INT NULL,
  `runtime` INT NULL,
  `status` ENUM('RELEASED', 'RUMORED', 'POST_PRODUCTION') NULL,
  `title` VARCHAR(45) NULL,
  `vote_average` DOUBLE NULL,
  `vote_count` INT NULL,
  `budget` INT NULL,
  `original_language` VARCHAR(2) NULL,
  `original_title` VARCHAR(45) NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PERSONA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PERSONA` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Crew` (
  `PERSONA_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  `crew_role` VARCHAR(45) NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_PERSONA_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_Movie_PERSONA1_idx` (`PERSONA_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_PERSONA_has_Movie_PERSONA1`
    FOREIGN KEY (`PERSONA_id`)
    REFERENCES `mydb`.`PERSONA` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PERSONA_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cast` (
  `character` VARCHAR(45) NULL,
  `gender` INT NULL,
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `Crew_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Crew_id`),
  INDEX `fk_Cast_Crew1_idx` (`Crew_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cast_Crew1`
    FOREIGN KEY (`Crew_id`)
    REFERENCES `mydb`.`Crew` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Genre` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `mydb`.`Keywords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Keywords` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Production_companies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Production_companies` (
  `name` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`production_countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`production_countries` (
  `ISO_3166_1` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`ISO_3166_1`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Spoken_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Spoken_language` (
  `ISO_639_1` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`ISO_639_1`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Movie_has_Spoken_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Movie_has_Spoken_language` (
  `Movie_id` INT NOT NULL,
  `Spoken_language_ISO_639_1` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Movie_id`, `Spoken_language_ISO_639_1`),
  INDEX `fk_Movie_has_Spoken_language_Spoken_language1_idx` (`Spoken_language_ISO_639_1` ASC) VISIBLE,
  INDEX `fk_Movie_has_Spoken_language_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movie_has_Spoken_language_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Spoken_language_Spoken_language1`
    FOREIGN KEY (`Spoken_language_ISO_639_1`)
    REFERENCES `mydb`.`Spoken_language` (`ISO_639_1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`production_countries_has_Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`production_countries_has_Movie` (
  `production_countries_ISO_3166_1` VARCHAR(45) NOT NULL,
  `Movie_id` INT NOT NULL,
  PRIMARY KEY (`production_countries_ISO_3166_1`, `Movie_id`),
  INDEX `fk_production_countries_has_Movie_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  INDEX `fk_production_countries_has_Movie_production_countries1_idx` (`production_countries_ISO_3166_1` ASC) VISIBLE,
  CONSTRAINT `fk_production_countries_has_Movie_production_countries1`
    FOREIGN KEY (`production_countries_ISO_3166_1`)
    REFERENCES `mydb`.`production_countries` (`ISO_3166_1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_production_countries_has_Movie_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Genre_has_Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Genre_has_Movie` (
  `Genre_id` INT NOT NULL,
  `Movie_id` INT NOT NULL,
  PRIMARY KEY (`Genre_id`, `Movie_id`),
  INDEX `fk_Genre_has_Movie_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  INDEX `fk_Genre_has_Movie_Genre1_idx` (`Genre_id` ASC) VISIBLE,
  CONSTRAINT `fk_Genre_has_Movie_Genre1`
    FOREIGN KEY (`Genre_id`)
    REFERENCES `mydb`.`Genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Genre_has_Movie_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `mydb`.`Keywords_has_Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Keywords_has_Movie` (
  `Keywords_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  PRIMARY KEY (`Keywords_id`, `Movie_movie_id`),
  INDEX `fk_Keywords_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_Keywords_has_Movie_Keywords1_idx` (`Keywords_id` ASC) VISIBLE,
  CONSTRAINT `fk_Keywords_has_Movie_Keywords1`
    FOREIGN KEY (`Keywords_id`)
    REFERENCES `mydb`.`Keywords` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Keywords_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Production_companies_has_Movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Production_companies_has_Movie` (
  `Production_companies_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  INDEX `fk_Production_companies_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_Production_companies_has_Movie_Production_companies1_idx` (`Production_companies_id` ASC) VISIBLE,
  PRIMARY KEY (`Production_companies_id`, `Movie_movie_id`),
  CONSTRAINT `fk_Production_companies_has_Movie_Production_companies1`
    FOREIGN KEY (`Production_companies_id`)
    REFERENCES `mydb`.`Production_companies` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Production_companies_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `rol` ENUM('usuario', 'administrador', 'editor', 'recomendador', 'analista') NULL DEFAULT 'usuario',
  `Usuariocol` VARCHAR(45) NULL,
  PRIMARY KEY (`email`));


-- -----------------------------------------------------
-- Table `mydb`.`userSaveMovie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`userSaveMovie` (
  `Movie_movie_id` INT NOT NULL,
  `Usuario_email` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Movie_movie_id`, `Usuario_email`),
  INDEX `fk_Movie_has_Usuario_Usuario1_idx` (`Usuario_email` ASC) VISIBLE,
  INDEX `fk_Movie_has_Usuario_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movie_has_Usuario_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Usuario_Usuario1`
    FOREIGN KEY (`Usuario_email`)
    REFERENCES `mydb`.`Usuario` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`review` (
  `Movie_movie_id` INT NOT NULL,
  `Usuario_email` VARCHAR(255) NOT NULL,
  `comment` VARCHAR(150) NULL,
  `rate` DOUBLE NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Movie_movie_id`, `Usuario_email`),
  INDEX `fk_Movie_has_Usuario_Usuario2_idx` (`Usuario_email` ASC) VISIBLE,
  INDEX `fk_Movie_has_Usuario_Movie2_idx` (`Movie_movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movie_has_Usuario_Movie2`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `mydb`.`Movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Usuario_Usuario2`
    FOREIGN KEY (`Usuario_email`)
    REFERENCES `mydb`.`Usuario` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
