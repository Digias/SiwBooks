--=======================================================================================================
-- =========================== Books
--=======================================================================================================
INSERT INTO book (title, year_of_publication) VALUES ('Il nome della rosa', 1980); -- ID 1
INSERT INTO book (title, year_of_publication) VALUES ('Good Omens', 1990); -- ID 2
INSERT INTO book (title, year_of_publication) VALUES ('Guida galattica per autostoppisti', 1979); -- ID 3
INSERT INTO book (title, year_of_publication) VALUES ('Il libro della giungla', 1894); -- ID 4
INSERT INTO book (title, year_of_publication) VALUES ('Il Talismano', 1984); -- ID 5
INSERT INTO book (title, year_of_publication) VALUES ('Il libro delle meraviglie per ragazzi e ragazze', 1851); -- ID 6 (Anno corretto)
INSERT INTO book (title, year_of_publication) VALUES ('Delitto e castigo', 1866); -- ID 7
INSERT INTO book (title, year_of_publication) VALUES ('Il barone rampante', 1957); -- ID 8
INSERT INTO book (title, year_of_publication) VALUES ('La coscienza di Zeno', 1923); -- ID 9
INSERT INTO book (title, year_of_publication) VALUES ('Don Chisciotte della Mancia', 1605); -- ID 10
INSERT INTO book (title, year_of_publication) VALUES ('I promessi sposi', 1827); -- ID 11
INSERT INTO book (title, year_of_publication) VALUES ('Il piccolo principe', 1943); -- ID 12
INSERT INTO book (title, year_of_publication) VALUES ('Harry Potter e la pietra filosofale', 1997); -- ID 13
INSERT INTO book (title, year_of_publication) VALUES ('Il signore degli anelli', 1954); -- ID 14
INSERT INTO book (title, year_of_publication) VALUES ('La metamorfosi', 1915); -- ID 15
INSERT INTO book (title, year_of_publication) VALUES ('Il giovane Holden', 1951); -- ID 16
INSERT INTO book (title, year_of_publication) VALUES ('Fahrenheit 451', 1953); -- ID 17
INSERT INTO book (title, year_of_publication) VALUES ('Il grande Gatsby', 1925); -- ID 18
INSERT INTO book (title, year_of_publication) VALUES ('Moby Dick', 1851); -- ID 19
INSERT INTO book (title, year_of_publication) VALUES ('Guerra e pace', 1869); -- ID 20
INSERT INTO book (title, year_of_publication) VALUES ('Il giardino segreto', 1911); -- ID 21
INSERT INTO book (title, year_of_publication) VALUES ('Le avventure di Sherlock Holmes', 1892); -- ID 22

-- Libri aggiuntivi di George Orwell
INSERT INTO book (title, year_of_publication) VALUES ('1984', 1949); -- ID 23
INSERT INTO book (title, year_of_publication) VALUES ('La fattoria degli animali', 1945); -- ID 24 (Titolo italiano per Animal Farm)
INSERT INTO book (title, year_of_publication) VALUES ('Omaggio alla Catalogna', 1938); -- ID 25 (Titolo italiano per Homage to Catalonia)
INSERT INTO book (title, year_of_publication) VALUES ('Senza un soldo a Parigi e Londra', 1933); -- ID 26 (Titolo italiano per Down and Out in Paris and London)
INSERT INTO book (title, year_of_publication) VALUES ('La strada di Wigan Pier', 1937); -- ID 27 (Titolo italiano per The Road to Wigan Pier)

-- Libri aggiuntivi di J.K. Rowling
INSERT INTO book (title, year_of_publication) VALUES ('Harry Potter e la camera dei segreti', 1998); -- ID 28
INSERT INTO book (title, year_of_publication) VALUES ('Harry Potter e il prigioniero di Azkaban', 1999); -- ID 29
INSERT INTO book (title, year_of_publication) VALUES ('Harry Potter e il calice di fuoco', 2000); -- ID 30
INSERT INTO book (title, year_of_publication) VALUES ('Harry Potter e l''ordine della fenice', 2003); -- ID 31


--=======================================================================================================
-- =========================== Authors
--=======================================================================================================
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Umberto', 'Eco', '1932-01-05', '2016-02-19', 'Italiana'); -- ID 1
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('George', 'Orwell', '1903-06-25', '1950-01-21', 'Britannica'); -- ID 2
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Gabriel', 'García Márquez', '1927-03-06', '2014-04-17', 'Colombiana'); -- ID 3
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Giuseppe', 'Tomasi di Lampedusa', '1896-12-23', '1957-07-23', 'Italiana'); -- ID 4
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Jane', 'Austen', '1775-12-16', '1817-07-18', 'Britannica'); -- ID 5
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Fëdor', 'Dostoevskij', '1821-11-11', '1881-02-09', 'Russa'); -- ID 6
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Italo', 'Calvino', '1923-10-15', '1985-09-19', 'Italiana'); -- ID 7
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Italo', 'Svevo', '1861-12-19', '1928-09-13', 'Italiana'); -- ID 8
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Miguel', 'de Cervantes', '1547-09-29', '1616-04-23', 'Spagnola'); -- ID 9
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Alessandro', 'Manzoni', '1785-03-07', '1873-05-22', 'Italiana'); -- ID 10
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Antoine', 'de Saint-Exupéry', '1900-06-29', '1944-07-31', 'Francese'); -- ID 11
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('J.K.', 'Rowling', '1965-07-31', NULL, 'Britannica'); -- ID 12
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('J.R.R.', 'Tolkien', '1892-01-03', '1973-09-02', 'Britannica'); -- ID 13
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Franz', 'Kafka', '1883-07-03', '1924-06-03', 'Austro-Ungarica'); -- ID 14
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('J.D.', 'Salinger', '1919-01-01', '2010-01-27', 'Statunitense'); -- ID 15
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Ray', 'Bradbury', '1920-08-22', '2012-06-05', 'Statunitense'); -- ID 16
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('F. Scott', 'Fitzgerald', '1896-09-24', '1940-12-21', 'Statunitense'); -- ID 17
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Herman', 'Melville', '1819-08-01', '1891-09-28', 'Statunitense'); -- ID 18
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Lev', 'Tolstoj', '1828-09-09', '1910-11-20', 'Russa'); -- ID 19
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Lewis', 'Carroll', '1832-01-27', '1898-01-14', 'Britannica'); -- ID 20
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Frances', 'Hodgson Burnett', '1849-11-24', '1924-10-29', 'Britannica'); -- ID 21
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Elizabeth', 'Goudge', '1900-04-24', '1984-04-01', 'Britannica'); -- ID 22 (Data di morte corretta)
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Mark', 'Twain', '1835-11-30', '1910-04-21', 'Statunitense'); -- ID 23
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Carlos', 'Ruiz Zafón', '1964-09-25', '2020-06-19', 'Spagnolo'); -- ID 24
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Isabel', 'Allende', '1942-08-02', NULL, 'Cilena'); -- ID 25
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('John', 'Stephens', '1972-12-28', NULL, 'Statunitense'); -- ID 26 (Data di nascita corretta)
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Mario', 'Vargas Llosa', '1936-03-28', NULL, 'Peruviano'); -- ID 27

-- Autori aggiuntivi
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Terry', 'Pratchett', '1948-04-28', '2015-03-12', 'Britannica'); -- ID 28
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Neil', 'Gaiman', '1960-11-10', NULL, 'Britannica'); -- ID 29
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Douglas', 'Adams', '1952-03-11', '2001-05-11', 'Britannica'); -- ID 30
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Rudyard', 'Kipling', '1865-12-30', '1936-01-18', 'Britannica'); -- ID 31
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Stephen', 'King', '1947-09-21', NULL, 'Statunitense'); -- ID 32
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Peter', 'Straub', '1943-03-02', '2022-09-04', 'Statunitense'); -- ID 33
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Nathaniel', 'Hawthorne', '1804-07-04', '1864-05-19', 'Statunitense'); -- ID 34
INSERT INTO author (name, surname, date_of_birth, date_of_death, nationality) VALUES ('Arthur', 'Conan Doyle', '1859-05-22', '1930-07-07', 'Britannica'); -- ID 35


--=======================================================================================================
-- =========================== Book - Author
--=======================================================================================================
-- Associazioni libro-autore corrette
INSERT INTO book_authors (authors_id, books_id) VALUES (1, 1);  -- Il nome della rosa: Umberto Eco
INSERT INTO book_authors (authors_id, books_id) VALUES (28, 2); -- Good Omens: Terry Pratchett
INSERT INTO book_authors (authors_id, books_id) VALUES (29, 2); -- Good Omens: Neil Gaiman
INSERT INTO book_authors (authors_id, books_id) VALUES (30, 3); -- Guida galattica per autostoppisti: Douglas Adams
INSERT INTO book_authors (authors_id, books_id) VALUES (31, 4); -- Il libro della giungla: Rudyard Kipling
INSERT INTO book_authors (authors_id, books_id) VALUES (32, 5); -- Il Talismano: Stephen King
INSERT INTO book_authors (authors_id, books_id) VALUES (33, 5); -- Il Talismano: Peter Straub
INSERT INTO book_authors (authors_id, books_id) VALUES (34, 6); -- Il libro delle meraviglie per ragazzi e ragazze: Nathaniel Hawthorne
INSERT INTO book_authors (authors_id, books_id) VALUES (6, 7);  -- Delitto e castigo: Fëdor Dostoevskij
INSERT INTO book_authors (authors_id, books_id) VALUES (7, 8);  -- Il barone rampante: Italo Calvino
INSERT INTO book_authors (authors_id, books_id) VALUES (8, 9);  -- La coscienza di Zeno: Italo Svevo
INSERT INTO book_authors (authors_id, books_id) VALUES (9, 10); -- Don Chisciotte della Mancia: Miguel de Cervantes
INSERT INTO book_authors (authors_id, books_id) VALUES (10, 11);-- I promessi sposi: Alessandro Manzoni
INSERT INTO book_authors (authors_id, books_id) VALUES (11, 12);-- Il piccolo principe: Antoine de Saint-Exupéry
INSERT INTO book_authors (authors_id, books_id) VALUES (12, 13);-- Harry Potter e la pietra filosofale: J.K. Rowling
INSERT INTO book_authors (authors_id, books_id) VALUES (13, 14);-- Il signore degli anelli: J.R.R. Tolkien
INSERT INTO book_authors (authors_id, books_id) VALUES (14, 15);-- La metamorfosi: Franz Kafka
INSERT INTO book_authors (authors_id, books_id) VALUES (15, 16);-- Il giovane Holden: J.D. Salinger
INSERT INTO book_authors (authors_id, books_id) VALUES (16, 17);-- Fahrenheit 451: Ray Bradbury
INSERT INTO book_authors (authors_id, books_id) VALUES (17, 18);-- Il grande Gatsby: F. Scott Fitzgerald
INSERT INTO book_authors (authors_id, books_id) VALUES (18, 19);-- Moby Dick: Herman Melville
INSERT INTO book_authors (authors_id, books_id) VALUES (19, 20);-- Guerra e pace: Lev Tolstoj
INSERT INTO book_authors (authors_id, books_id) VALUES (21, 21);-- Il giardino segreto: Frances Hodgson Burnett
INSERT INTO book_authors (authors_id, books_id) VALUES (35, 22);-- Le avventure di Sherlock Holmes: Arthur Conan Doyle

-- Collegamenti per i libri di George Orwell (Autore ID 2)
INSERT INTO book_authors (authors_id, books_id) VALUES (2, 23); -- 1984
INSERT INTO book_authors (authors_id, books_id) VALUES (2, 24); -- La fattoria degli animali
INSERT INTO book_authors (authors_id, books_id) VALUES (2, 25); -- Omaggio alla Catalogna
INSERT INTO book_authors (authors_id, books_id) VALUES (2, 26); -- Senza un soldo a Parigi e Londra
INSERT INTO book_authors (authors_id, books_id) VALUES (2, 27); -- La strada di Wigan Pier

-- Collegamenti per i libri di J.K. Rowling (Autore ID 12)
-- (Harry Potter e la pietra filosofale è già associato sopra: (12, 13))
INSERT INTO book_authors (authors_id, books_id) VALUES (12, 28); -- Harry Potter e la camera dei segreti
INSERT INTO book_authors (authors_id, books_id) VALUES (12, 29); -- Harry Potter e il prigioniero di Azkaban
INSERT INTO book_authors (authors_id, books_id) VALUES (12, 30); -- Harry Potter e il calice di fuoco
INSERT INTO book_authors (authors_id, books_id) VALUES (12, 31); -- Harry Potter e l'ordine della fenice


--=======================================================================================================
-- =========================== User
--=======================================================================================================
INSERT INTO users(name, surname, email) VALUES('Francesco', 'Di Gianvincenzo', 'fra.digianvincenzo@stud.uniroma3.it');
INSERT INTO users(name, surname, email) VALUES('Admin', 'Admin', 'admin@gmail');

INSERT INTO users(name, surname, email) VALUES('Mario', 'Rossi', 'mario.rossi@example.com');
INSERT INTO users(name, surname, email) VALUES('Luca', 'Bianchi', 'luca.bianchi@example.com');
INSERT INTO users(name, surname, email) VALUES('Giulia', 'Verdi', 'giulia.verdi@example.com');
INSERT INTO users(name, surname, email) VALUES('Federico', 'Neri', 'federico.neri@example.com');
INSERT INTO users(name, surname, email) VALUES('Alessia', 'Gallo', 'alessia.gallo@example.com');
INSERT INTO users(name, surname, email) VALUES('Simone', 'Ferrari', 'simone.ferrari@example.com');
INSERT INTO users(name, surname, email) VALUES('Sara', 'Moretti', 'sara.moretti@example.com');
INSERT INTO users(name, surname, email) VALUES('Antonio', 'De Luca', 'antonio.deluca@example.com');
INSERT INTO users(name, surname, email) VALUES('Elena', 'Romano', 'elena.romano@example.com');
INSERT INTO users(name, surname, email) VALUES('Davide', 'Colombo', 'davide.colombo@example.com');

--=======================================================================================================
-- =========================== Credentials
--=======================================================================================================
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'digia', 1); --password: password
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$7P5cx4thx8/wWnGKfMyTOOSC3.g/gfzgF5SJ8PsXPb/jH17M..ixu', 'ADMIN','admin', 2); --password: admin

INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'mario', 3);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'luca', 4);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'giulia', 5);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'federico', 6);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'alessia', 7);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'simone', 8);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'sara', 9);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'antonio', 10);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'elena', 11);
INSERT INTO credentials(password, role, username, user_id) VALUES('$2a$10$XVwcSEnz6Kupbnx2U.Di9OjVK.Or0xFeZnx/sS0heSCvE3OSUrUp2', 'REGISTERED', 'davide', 12);

--=======================================================================================================
-- =========================== Review
--=======================================================================================================
-- BOOK ID 1
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Sorprendente inizio', 5, 'Un debutto eccezionale, da leggere tutto d’un fiato.', 1, 3);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Un po’ lento', 3, 'Interessante ma con ritmo discontinuo.', 1, 4);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Bellissimo', 4, 'Coinvolgente, scritto molto bene.', 1, 5);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Non mi ha preso', 2, 'Purtroppo non è il mio genere.', 1, 6);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Una vera scoperta', 5, 'L’autore ha uno stile magnifico.', 1, 7);

-- BOOK ID 2
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Trama geniale', 5, 'Una delle trame più originali che abbia letto.', 2, 8);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Troppo complesso', 2, 'Difficile da seguire, troppi personaggi.', 2, 9);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Consigliato', 4, 'Un romanzo profondo e toccante.', 2, 10);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Banale', 1, 'Mi aspettavo di più, tutto già visto.', 2, 11);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Mi è piaciuto', 4, 'Ottima lettura da portare in vacanza.', 2, 12);

-- BOOK ID 3
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Molto valido', 4, 'Mi ha tenuto incollato alle pagine.', 3, 3);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Deludente', 2, 'Buone premesse ma esecuzione scarsa.', 3, 4);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Avvincente', 5, 'Una storia che lascia il segno.', 3, 5);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Mediocre', 3, 'Non male, ma nulla di memorabile.', 3, 6);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Semplicemente bellissimo', 5, 'Un libro che rileggerò volentieri.', 3, 7);

-- BOOK ID 4
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Piacevole sorpresa', 4, 'Mi ha sorpreso in positivo.', 4, 8);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Noioso', 1, 'Non sono riuscito a finirlo.', 4, 9);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Buon ritmo narrativo', 4, 'Fluido e ben scritto.', 4, 10);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Troppo prevedibile', 2, 'Tutto già visto, nessuna sorpresa.', 4, 11);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Perfetto', 5, 'Uno dei miei libri preferiti.', 4, 12);

-- BOOK ID 5
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Emozionante', 5, 'Un viaggio pieno di emozioni.', 5, 3);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Poteva dare di più', 3, 'Le premesse c’erano, ma non è decollato.', 5, 4);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Una lettura leggera', 4, 'Perfetto per rilassarsi.', 5, 5);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Inconcludente', 2, 'Tante idee ma nessuna sviluppata.', 5, 6);
INSERT INTO review (title, rating, text, book_id, user_id) VALUES ('Affascinante', 5, 'Ambientazione e personaggi molto ben costruiti.', 5, 7);