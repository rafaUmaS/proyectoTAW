CREATE SCHEMA IF NOT EXISTS `myLetterBox` DEFAULT CHARACTER SET utf8mb4 ;
USE `myLetterBox` ;

-- -----------------------------------------------------
-- Table `myLetterBox`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`movie` (
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
-- Table `myLetterBox`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`persona` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myLetterBox`.`crew`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`crew` (
  `PERSONA_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  `crew_role` VARCHAR(45) NULL DEFAULT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  INDEX `fk_PERSONA_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_Movie_PERSONA1_idx` (`PERSONA_id` ASC) VISIBLE,
  CONSTRAINT `fk_PERSONA_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `myLetterBox`.`movie` (`movie_id`),
  CONSTRAINT `fk_PERSONA_has_Movie_PERSONA1`
    FOREIGN KEY (`PERSONA_id`)
    REFERENCES `myLetterBox`.`persona` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myLetterBox`.`cast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`cast` (
  `character` VARCHAR(45) NULL DEFAULT NULL,
  `gender` INT NULL DEFAULT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `Crew_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Crew_id`),
  INDEX `fk_Cast_Crew1_idx` (`Crew_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cast_Crew1`
    FOREIGN KEY (`Crew_id`)
    REFERENCES `myLetterBox`.`crew` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myLetterBox`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `myLetterBox`.`genre_has_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`genre_has_movie` (
  `Genre_id` INT NOT NULL,
  `Movie_id` INT NOT NULL,
  PRIMARY KEY (`Genre_id`, `Movie_id`),
  INDEX `fk_Genre_has_Movie_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  INDEX `fk_Genre_has_Movie_Genre1_idx` (`Genre_id` ASC) VISIBLE,
  CONSTRAINT `fk_Genre_has_Movie_Genre1`
    FOREIGN KEY (`Genre_id`)
    REFERENCES `myLetterBox`.`genre` (`id`),
  CONSTRAINT `fk_Genre_has_Movie_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `myLetterBox`.`movie` (`movie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = big5;


-- -----------------------------------------------------
-- Table `myLetterBox`.`keywords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`keywords` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myLetterBox`.`keywords_has_movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`keywords_has_movie` (
  `Keywords_id` INT NOT NULL,
  `Movie_movie_id` INT NOT NULL,
  PRIMARY KEY (`Keywords_id`, `Movie_movie_id`),
  INDEX `fk_Keywords_has_Movie_Movie1_idx` (`Movie_movie_id` ASC) VISIBLE,
  INDEX `fk_Keywords_has_Movie_Keywords1_idx` (`Keywords_id` ASC) VISIBLE,
  CONSTRAINT `fk_Keywords_has_Movie_Keywords1`
    FOREIGN KEY (`Keywords_id`)
    REFERENCES `myLetterBox`.`keywords` (`id`),
  CONSTRAINT `fk_Keywords_has_Movie_Movie1`
    FOREIGN KEY (`Movie_movie_id`)
    REFERENCES `myLetterBox`.`movie` (`movie_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myLetterBox`.`spoken_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`spoken_language` (
  `ISO_639_1` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ISO_639_1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `myLetterBox`.`movie_has_spoken_language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myLetterBox`.`movie_has_spoken_language` (
  `Movie_id` INT NOT NULL,
  `Spoken_language_ISO_639_1` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Movie_id`, `Spoken_language_ISO_639_1`),
  INDEX `fk_Movie_has_Spoken_language_Spoken_language1_idx` (`Spoken_language_ISO_639_1` ASC) VISIBLE,
  INDEX `fk_Movie_has_Spoken_language_Movie1_idx` (`Movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_Movie_has_Spoken_language_Movie1`
    FOREIGN KEY (`Movie_id`)
    REFERENCES `myLetterBox`.`movie` (`movie_id`),
  CONSTRAINT `fk_Movie_has_Spoken_language_Spoken_language1`
    FOREIGN KEY (`Spoken_language_ISO_639_1`)
    REFERENCES `myLetterBox`.`spoken_language` (`ISO_639_1`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table
