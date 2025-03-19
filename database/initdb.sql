CREATE DATABASE Adventure;

\c Adventure;

-- Создаем схему, если она еще не существует
CREATE SCHEMA IF NOT EXISTS canban;

-- Устанавливаем схему по умолчанию
SET search_path TO canban;

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
FROM '\Grimuar.csv'
DELIMITER ','
CSV HEADER;

-- Вставка данных в целевую таблицу с генерацией id
INSERT INTO Backlog(column1, column2, column3, column4, column5, column6)
SELECT column1, column2, column3, column4, column5, column6
FROM temp_table;

-- Удаление временной таблицы (необязательно, так как она временная)
DROP TABLE temp_table;

-- Таблица HeroEntity
CREATE TABLE Heroes (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    level INT,
    mana INT
);

-- Таблица HeroTeam
CREATE TABLE HeroesResolution (
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
    idBackLog INT,
    FOREIGN KEY (idTask) REFERENCES Tasks(id),
    FOREIGN KEY (idBackLog) REFERENCES Backlog(id)
);


