CREATE DATABASE adventure;

\c adventure;

SET search_path TO adventure, public;

-- Таблица Expedition (создаем первой, так как на нее есть ссылки)
CREATE TABLE Expeditions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

-- Таблица Backlog
CREATE TABLE Backlog (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255),
    difficult VARCHAR(255),
    manaStrat INT,
    manaMagic INT,
    manaBattle INT
);

-- Создание временной таблицы
CREATE TEMP TABLE temp_table (
    column1 VARCHAR(255),
    column2 VARCHAR(255),
    column3 VARCHAR(255),
    column4 INT,
    column5 INT,
    column6 INT
);

-- Импорт данных во временную таблицу
COPY temp_table(column1, column2, column3, column4, column5, column6)
FROM 'C:\\D\\desktop\\Grimuar.csv'
DELIMITER ';'
CSV HEADER ENCODING 'UTF-8';

-- Вставка данных в таблицу Backlog
INSERT INTO Backlog(name, type, difficult, manaStrat, manaMagic, manaBattle)
SELECT column1, column2, column3, column4, column5, column6
FROM temp_table;

-- Удаление временной таблицы (необязательно, так как она временная)
DROP TABLE temp_table;

-- Таблица HeroEntity
CREATE TABLE Heroes (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    level INT,
    manaStrat INT,
    manaMagic INT,
    manaBattle INT
);

-- Импорт данных во временную таблицу
COPY temp_table(column1, column2, column3, column4, column5)
FROM 'C:\\D\\desktop\\HeroParam.csv'
DELIMITER ';'
CSV HEADER ENCODING 'UTF-8';

-- Вставка данных в таблицу Backlog
INSERT INTO Heroes(type, level, manaStrat, manaMagic, manaBattle)
SELECT column1, column2, column3, column4, column5, column6
FROM temp_table;

-- Удаление временной таблицы (необязательно, так как она временная)
DROP TABLE temp_table;

-- Таблица HeroTeam
CREATE TABLE HeroResolution (
    id SERIAL PRIMARY KEY,
    idExpedition INT,
    idHero INT,
    FOREIGN KEY (idExpedition) REFERENCES Expeditions(id),
    FOREIGN KEY (idHero) REFERENCES Heroes(id)
);

-- Таблица Tasks
CREATE TABLE Tasks (
    id SERIAL PRIMARY KEY,
    idExpedition INT,
    name VARCHAR(255),
    description VARCHAR(255),
    FOREIGN KEY (idExpedition) REFERENCES Expeditions(id)
);

-- Таблица SubTasks
CREATE TABLE SubTasks (
    id SERIAL PRIMARY KEY,
    idTask INT,
    FOREIGN KEY (idTask) REFERENCES Tasks(id)
);

-- Таблица SubTaskResolution
CREATE TABLE SubTaskResolution (
    id INT,
    idBackLogSubTask INT,
    PRIMARY KEY (id, idBackLogSubTask),
    FOREIGN KEY (id) REFERENCES SubTasks(id),
    FOREIGN KEY (idBackLogSubTask) REFERENCES Backlog(id)
);