INSERT INTO `myletterbox`.`persona` (`name`) VALUES
('Joseph Gordon-Levitt'),
('Hans Zimmer'),
('Wally Pfister'),
('Anne Hathaway'),
('Hoyte van Hoytema'),
('Zoë Kravitz'),
('Michael Giacchino'),
('Greig Fraser');

-- Los siguientes ids de persona tienes que cambiarlos conforme los tengas en tu base de datos

-- Inception
INSERT INTO `myletterbox`.`crew` (`PERSONA_id`, `Movie_movie_id`, `crew_role`) VALUES
(11, 4, 'Actor Secundario'),       -- Joseph Gordon-Levitt
(12, 4, 'Compositor'),             -- Hans Zimmer
(13, 4, 'Director de Fotografía'); -- Wally Pfister

-- Interstellar
INSERT INTO `myletterbox`.`crew` (`PERSONA_id`, `Movie_movie_id`, `crew_role`) VALUES
(14, 5, 'Actriz Principal'),       -- Anne Hathaway
(12, 5, 'Compositor'),             -- Hans Zimmer
(15, 5, 'Director de Fotografía'); -- Hoyte van Hoytema

-- The Batman
INSERT INTO `myletterbox`.`crew` (`PERSONA_id`, `Movie_movie_id`, `crew_role`) VALUES
(16, 6, 'Actriz Secundaria'),     -- Zoë Kravitz
(17, 6, 'Compositor'),            -- Michael Giacchino
(18, 6, 'Director de Fotografía');-- Greig Fraser



