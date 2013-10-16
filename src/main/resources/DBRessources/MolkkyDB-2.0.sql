SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `MolkkyDB` ;
CREATE SCHEMA IF NOT EXISTS `MolkkyDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin ;
USE `MolkkyDB` ;

-- -----------------------------------------------------
-- Table `MolkkyDB`.`Members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`Members` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`Members` (
  `idMember` INT NOT NULL AUTO_INCREMENT ,
  `prenom` VARCHAR(45) NULL ,
  `nom` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `anniversaire` DATE NULL ,
  `pseudonyme` VARCHAR(45) NULL ,
  PRIMARY KEY (`idMember`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MolkkyDB`.`Tournois`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`Tournois` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`Tournois` (
  `idTournoi` INT NOT NULL AUTO_INCREMENT ,
  `startDate` DATE NULL ,
  `endDate` DATE NULL ,
  `nom` VARCHAR(45) NULL ,
  PRIMARY KEY (`idTournoi`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MolkkyDB`.`Parties`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`Parties` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`Parties` (
  `idPartie` INT NOT NULL AUTO_INCREMENT ,
  `date` DATE NULL ,
  `lieu` VARCHAR(45) NULL ,
  `idTournoi` INT NOT NULL ,
  PRIMARY KEY (`idPartie`) ,
  INDEX `fk_Partie_Tournois1` (`idTournoi` ASC) ,
  CONSTRAINT `fk_Partie_Tournois1`
    FOREIGN KEY (`idTournoi` )
    REFERENCES `MolkkyDB`.`Tournois` (`idTournoi` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MolkkyDB`.`Scores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`Scores` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`Scores` (
  `idScore` INT NOT NULL AUTO_INCREMENT ,
  `idPartie` INT NOT NULL ,
  `idMember` INT NOT NULL ,
  `score` INT NULL DEFAULT 0 ,
  `manche` INT NULL ,
  INDEX `fk_Manche_has_Member_Member1` (`idMember` ASC) ,
  PRIMARY KEY (`idScore`) ,
  INDEX `fk_Scores_1` (`idPartie` ASC) ,
  CONSTRAINT `fk_Manche_has_Member_Member1`
    FOREIGN KEY (`idMember` )
    REFERENCES `MolkkyDB`.`Members` (`idMember` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Scores_1`
    FOREIGN KEY (`idPartie` )
    REFERENCES `MolkkyDB`.`Parties` (`idPartie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MolkkyDB`.`Groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`Groups` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`Groups` (
  `idGroup` INT NOT NULL AUTO_INCREMENT ,
  `numero_Group` VARCHAR(45) NULL ,
  `idMember` INT NOT NULL ,
  PRIMARY KEY (`idGroup`) ,
  INDEX `fk_Groups_Members1` (`idMember` ASC) ,
  CONSTRAINT `fk_Groups_Members1`
    FOREIGN KEY (`idMember` )
    REFERENCES `MolkkyDB`.`Members` (`idMember` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MolkkyDB`.`Equipes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`Equipes` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`Equipes` (
  `idEquipe` INT NOT NULL AUTO_INCREMENT ,
  `idMembre1` INT NOT NULL ,
  `idMembre2` INT NOT NULL ,
  `numero_equipe` INT NULL ,
  `idPartie` INT NOT NULL ,
  PRIMARY KEY (`idEquipe`) ,
  INDEX `fk_Equipes_Members1` (`idMembre1` ASC) ,
  INDEX `fk_Equipes_Members2` (`idMembre2` ASC) ,
  INDEX `fk_Equipes_Parties1` (`idPartie` ASC) ,
  CONSTRAINT `fk_Equipes_Members1`
    FOREIGN KEY (`idMembre1` )
    REFERENCES `MolkkyDB`.`Members` (`idMember` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipes_Members2`
    FOREIGN KEY (`idMembre2` )
    REFERENCES `MolkkyDB`.`Members` (`idMember` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipes_Parties1`
    FOREIGN KEY (`idPartie` )
    REFERENCES `MolkkyDB`.`Parties` (`idPartie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MolkkyDB`.`groups_lien_Equipes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MolkkyDB`.`groups_lien_Equipes` ;

CREATE  TABLE IF NOT EXISTS `MolkkyDB`.`groups_lien_Equipes` (
  `idGroup` INT NOT NULL ,
  `idEquipe` INT NOT NULL ,
  PRIMARY KEY (`idGroup`, `idEquipe`) ,
  INDEX `fk_group_has_Equipes_Equipes1` (`idEquipe` ASC) ,
  INDEX `fk_group_has_Equipes_group1` (`idGroup` ASC) ,
  CONSTRAINT `fk_group_has_Equipes_group1`
    FOREIGN KEY (`idGroup` )
    REFERENCES `MolkkyDB`.`Groups` (`idGroup` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_Equipes_Equipes1`
    FOREIGN KEY (`idEquipe` )
    REFERENCES `MolkkyDB`.`Equipes` (`idEquipe` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
