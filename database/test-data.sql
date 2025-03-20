-- Вставка данных в таблицу Expeditions
INSERT INTO Expeditions (name, description)
VALUES
('Expedition to the Lost Temple', 'An expedition to uncover the secrets of the Lost Temple'),
('Battle of the Dragon Slayer', 'A heroic battle to slay the mighty dragon'),
('Mystery of the Ancient City', 'Unravel the mysteries of the ancient city hidden in the mountains');

-- Вставка данных в таблицу Backlog
INSERT INTO Backlog (name, type, difficult, manaStrat, manaMagic, manaBattle)
VALUES
('Locate the hidden temple', 'Quest', 'Hard', 60, 40, 20),
('Defeat the dragons minions', 'Battle', 'Medium', 70, 50, 40),
('Solve the temple puzzle', 'Puzzle', 'Medium', 50, 30, 20),
('Rescue the captured hero', 'Quest', 'Hard', 80, 60, 30),
('Explore the ruins of the ancient city', 'Exploration', 'Hard', 90, 40, 50),
('Gather magical artifacts', 'Quest', 'Medium', 30, 80, 40);

-- Вставка данных в таблицу Heroes
INSERT INTO Heroes (type, level, mana)
VALUES
('Warrior', 5, 100),
('Mage', 4, 120),
('Archer', 3, 80),
('Healer', 6, 150),
('Rogue', 4, 90);

-- Вставка данных в таблицу HeroResolution
-- Здесь устанавливаются связи между героями и экспедициями
INSERT INTO HeroResolution (idExpedition, idHero)
VALUES
(1, 1),  -- Expedition 1 (Lost Temple) и Hero 1 (Warrior)
(1, 2),  -- Expedition 1 (Lost Temple) и Hero 2 (Mage)
(2, 3),  -- Expedition 2 (Dragon Slayer) и Hero 3 (Archer)
(2, 4),  -- Expedition 2 (Dragon Slayer) и Hero 4 (Healer)
(3, 5);  -- Expedition 3 (Ancient City) и Hero 5 (Rogue)

-- Вставка данных в таблицу Tasks
-- Задачи для каждой экспедиции
INSERT INTO Tasks (idExpedition, name, description)
VALUES
(1, 'Enter the temple', 'Begin the journey to find the entrance of the Lost Temple'),
(1, 'Defeat the temple guardians', 'Fight the guardians to advance deeper into the temple'),
(2, 'Defeat the dragon', 'Slay the dragon that has terrorized the kingdom'),
(3, 'Explore the ancient city ruins', 'Investigate the secrets hidden within the ancient city');

-- Вставка данных в таблицу SubTasks
-- Задачи для каждой основной задачи
INSERT INTO SubTasks (idTask)
VALUES
(1),  -- Задача 1 (Enter the temple) для экспедиции 1
(1),  -- Задача 2 (Defeat the temple guardians) для экспедиции 1
(2),  -- Задача 3 (Defeat the dragon) для экспедиции 2
(3),  -- Задача 4 (Explore the ruins) для экспедиции 3
(2),  -- Задача 5 (Defeat the dragon) для экспедиции 2
(3);  -- Задача 6 (Explore the ruins) для экспедиции 3

-- Вставка данных в таблицу SubTaskResolution
-- Резолюции подзадач с ссылками на Backlog
INSERT INTO SubTaskResolution (id, idBackLogSubTask)
VALUES
(1, 1),  -- Подзадача 1 решена для Backlog 1 (Locate the hidden temple)
(2, 3),  -- Подзадача 2 решена для Backlog 3 (Solve the temple puzzle)
(3, 2),  -- Подзадача 3 решена для Backlog 2 (Defeat the dragons minions)
(4, 5),  -- Подзадача 4 решена для Backlog 5 (Explore the ruins of the ancient city)
(5, 6),  -- Подзадача 5 решена для Backlog 6 (Gather magical artifacts)
(6, 4);  -- Подзадача 6 решена для Backlog 4 (Rescue the captured hero)
