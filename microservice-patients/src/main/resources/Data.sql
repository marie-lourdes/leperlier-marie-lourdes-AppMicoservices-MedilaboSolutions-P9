use db_testpatient;

/*--------------- DEFAULT VALUES DB testpatient  table genre patient------------*/


INSERT INTO genre(genre_id, sex) VALUES
("M", "M");
INSERT INTO genre(genre_id, sex) VALUES
("F", "F");


/*--------------- DEFAULT VALUES DB testpatient  table patient------------*/

INSERT INTO patient(prenom, nom,date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestNone", 19661231,"F","1 Brookside St","100-222-3333");

INSERT INTO patient(prenom, nom, date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestBorderline",19450624, "M","2 High St","200-333-4444");

INSERT INTO patient(prenom, nom,date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestInDanger ",20040618, "M","3 Club Road","300-444-5555");

INSERT INTO patient(prenom, nom,date_de_naissance, genre_id, adresse, telephone) VALUES
("Test", "TestEarlyOnset",20020628,"F","4 Valley Dr","400-555-6666");