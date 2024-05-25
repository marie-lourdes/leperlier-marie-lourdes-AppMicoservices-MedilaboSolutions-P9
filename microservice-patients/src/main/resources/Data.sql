use db_testpatient;

/*--------SHEMA BDD--------*/

CREATE TABLE genre(
genre_id VARCHAR(1)  NOT NULL PRIMARY KEY,
sex VARCHAR(1) NOT NULL
);

CREATE TABLE patient(
id int NOT NULL AUTO_INCREMENT  PRIMARY KEY,
prenom VARCHAR(100) NOT NULL,
nom VARCHAR(100) NOT NULL,
date_de_naissance date,
adresse VARCHAR(150),
telephone VARCHAR(15) ,
genre_id VARCHAR(1),
FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);


/*--------------- DEFAULT VALUES DB testpatient ------------*/


INSERT INTO genre(genre_id, sex) VALUES
("M", "M");
INSERT INTO genre(genre_id, sex) VALUES
("F", "F");

INSERT INTO patient(prenom, nom,date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestNone", 19661231,"F","1 Brookside St","100-222-3333");

INSERT INTO patient(prenom, nom, date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestBorderline",19450624, "M","2 High St","200-333-4444");

INSERT INTO patient(prenom, nom,date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestInDanger ",20040618, "M","3 Club Road","300-444-5555");

INSERT INTO patient(prenom, nom,date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestEarlyOnset",20020628,"F","4 Valley Dr","400-555-6666");

