-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema myletterbox
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema myletterbox
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myletterbox` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `myletterbox` ;

-- -----------------------------------------------------
-- Table `myletterbox`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`movie` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `overview` VARCHAR(250) NULL DEFAULT NULL,
  `popularity` DOUBLE NULL DEFAULT NULL,
  `release_date` DATE NULL DEFAULT NULL,
  `revenue` INT NULL DEFAULT NULL,
  `runtime` INT NULL DEFAULT NULL,
  `status` ENUM('RELEASED', 'RUMORED', 'POST_PRODUCTION') NULL DEFAULT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `vote_average` DOUBLE NULL DEFAULT NULL,
  `vote_count` INT NULL DEFAULT NULL,
  `budget` INT NULL DEFAULT NULL,
  `original_language` VARCHAR(2) NULL DEFAULT NULL,
  `original_title` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`persona` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`crew` (
  `PERSONA_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  `crew_role` VARCHAR(45) NULL DEFAULT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_PERSONA_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_Movie_PERSONA1_idx` (`PERSONA_id` ASC) VISIBLE,
  CONSTRAINT `fk_PERSONA_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`),
  CONSTRAINT `fk_PERSONA_has_Movie_PERSONA1`
    FOREIGN KEY (`PERSONA_id`)
    REFERENCES `myletterbox`.`persona` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`cast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`cast` (
  `character` VARCHAR(45) NULL DEFAULT NULL,
  `gender` INT NULL DEFAULT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `Crew_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Crew_id`),
  INDEX `fk_Cast_Crew1_idx` (`Crew_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cast_Crew1`
    FOREIGN KEY (`Crew_id`)
    REFERENCES `myletterbox`.`crew` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `myletterbox`.`genre_has_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`genre_has_movie` (
  `Genre_id` INT NOT NULL,
  `Movie_id` INT NOT NULL,
  PRIMARY KEY (`Genre_id`, `Movie_id`),
  INDEX `fk_Genre_has_Movie_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  INDEX `fk_Genre_has_Movie_Genre1_idx` (`Genre_id` ASC) VISIBLE,
  CONSTRAINT `fk_Genre_has_Movie_Genre1`
    FOREIGN KEY (`Genre_id`)
    REFERENCES `myletterbox`.`genre` (`id`),
  CONSTRAINT `fk_Genre_has_Movie_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `myletterbox`.`keywords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`keywords` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`keywords_has_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`keywords_has_movie` (
  `Keywords_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  PRIMARY KEY (`Keywords_id`, `Movie_movie_id`),
  INDEX `fk_Keywords_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_Keywords_has_Movie_Keywords1_idx` (`Keywords_id` ASC) VISIBLE,
  CONSTRAINT `fk_Keywords_has_Movie_Keywords1`
    FOREIGN KEY (`Keywords_id`)
    REFERENCES `myletterbox`.`keywords` (`id`),
  CONSTRAINT `fk_Keywords_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`spoken_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`spoken_language` (
  `ISO_639_1` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ISO_639_1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`movie_has_spoken_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`movie_has_spoken_language` (
  `Movie_id` INT NOT NULL,
  `Spoken_language_ISO_639_1` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Movie_id`, `Spoken_language_ISO_639_1`),
  INDEX `fk_Movie_has_Spoken_language_Spoken_language1_idx` (`Spoken_language_ISO_639_1` ASC) VISIBLE,
  INDEX `fk_Movie_has_Spoken_language_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movie_has_Spoken_language_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`),
  CONSTRAINT `fk_Movie_has_Spoken_language_Spoken_language1`
    FOREIGN KEY (`Spoken_language_ISO_639_1`)
    REFERENCES `myletterbox`.`spoken_language` (`ISO_639_1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`production_companies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`production_companies` (
  `name` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`production_companies_has_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`production_companies_has_movie` (
  `Production_companies_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  PRIMARY KEY (`Production_companies_id`, `Movie_movie_id`),
  INDEX `fk_Production_companies_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_Production_companies_has_Movie_Production_companies1_idx` (`Production_companies_id` ASC) VISIBLE,
  CONSTRAINT `fk_Production_companies_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`),
  CONSTRAINT `fk_Production_companies_has_Movie_Production_companies1`
    FOREIGN KEY (`Production_companies_id`)
    REFERENCES `myletterbox`.`production_companies` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`production_countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`production_countries` (
  `ISO_3166_1` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ISO_3166_1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`production_countries_has_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`production_countries_has_movie` (
  `production_countries_ISO_3166_1` VARCHAR(45) NOT NULL,
  `Movie_id` INT NOT NULL,
  PRIMARY KEY (`production_countries_ISO_3166_1`, `Movie_id`),
  INDEX `fk_production_countries_has_Movie_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  INDEX `fk_production_countries_has_Movie_production_countries1_idx` (`production_countries_ISO_3166_1` ASC) VISIBLE,
  CONSTRAINT `fk_production_countries_has_Movie_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`),
  CONSTRAINT `fk_production_countries_has_Movie_production_countries1`
    FOREIGN KEY (`production_countries_ISO_3166_1`)
    REFERENCES `myletterbox`.`production_countries` (`ISO_3166_1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`usuario` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `rol` ENUM('usuario', 'administrador', 'editor', 'recomendador', 'analista') NULL DEFAULT 'usuario',
  `user_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`usuario_save_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`usuario_save_movie` (
  `usuario_user_id` INT NOT NULL,
  `movie_movie_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`usuario_user_id`, `movie_movie_id`),
  INDEX `fk_usuario_has_movie_movie1_idx` (`movie_movie_id` ASC) VISIBLE,
  INDEX `fk_usuario_has_movie_usuario1_idx` (`usuario_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_movie_usuario1`
    FOREIGN KEY (`usuario_user_id`)
    REFERENCES `myletterbox`.`usuario` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_movie_movie1`
    FOREIGN KEY (`movie_movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myletterbox`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myletterbox`.`review` (
  `usuario_user_id` INT NOT NULL,
  `movie_movie_id` INT NOT NULL,
  `comment` VARCHAR(250) NULL,
  `rate` DOUBLE NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`usuario_user_id`, `movie_movie_id`),
  INDEX `fk_usuario_has_movie_movie2_idx` (`movie_movie_id` ASC) VISIBLE,
  INDEX `fk_usuario_has_movie_usuario2_idx` (`usuario_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_movie_usuario2`
    FOREIGN KEY (`usuario_user_id`)
    REFERENCES `myletterbox`.`usuario` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_movie_movie2`
    FOREIGN KEY (`movie_movie_id`)
    REFERENCES `myletterbox`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

