DROP DATABASE menagerie;
CREATE DATABASE Colection;
USE Colection;
CREATE TABLE Libro(
id int primary key not null auto_increment,
tipo_libro varchar(10) not null,
pagina int not null,
texto text

);


SELECT * FROM Colection.Libro;

INSERT INTO Colection.Libro(tipo_libro,pagina,texto)
VALUES('Prueba2', 1, 'Info del libro prueba');

SHOW FULL PROCESSLIST;

show status where `variable_name` = 'Threads_connected';
