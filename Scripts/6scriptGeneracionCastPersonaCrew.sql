INSERT INTO `myletterbox`.`persona` (`name`) VALUES 
('Leonardo DiCaprio'),         -- actor
('Matthew McConaughey'),       -- actor
('Robert Pattinson'),          -- actor
('Christopher Nolan'),         -- director de Inception e Interstellar
('Matt Reeves');               -- director de The Batman

-- Actores
INSERT INTO `myletterbox`.`crew` (`PERSONA_id`, `Movie_movie_id`, `crew_role`) VALUES
(1, 4, 'Actor'),   -- DiCaprio en Inception
(2, 5, 'Actor'),   -- McConaughey en Interstellar
(3, 6, 'Actor');   -- Pattinson en The Batman

-- Directores
INSERT INTO `myletterbox`.`crew` (`PERSONA_id`, `Movie_movie_id`, `crew_role`) VALUES
(4, 4, 'Director'),  -- Nolan en Inception
(4, 5, 'Director'),  -- Nolan en Interstellar
(5, 6, 'Director');  -- Matt Reeves en The Batman

INSERT INTO `myletterbox`.`cast` (`character`, `gender`, `name`, `Crew_id`) VALUES
('Dom Cobb', 2, 'Leonardo DiCaprio', 1),
('Cooper', 2, 'Matthew McConaughey', 2),
('Bruce Wayne', 2, 'Robert Pattinson', 3);

