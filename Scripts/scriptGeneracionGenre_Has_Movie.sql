-- Inception (ID película 1) => Ciencia Ficción, Acción, Aventura
ALTER TABLE `myletterbox`.`genre_has_movie`
CONVERT TO CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

INSERT INTO `myletterbox`.`genre_has_movie` (`Genre_id`, `Movie_id`) VALUES
(22, 4), -- Ciencia Ficción
(20, 4), -- Aventura
(19, 4); -- Acción

-- Interstellar (ID película 2) => Ciencia Ficción, Aventura, Drama
INSERT INTO `myletterbox`.`genre_has_movie` (`Genre_id`, `Movie_id`) VALUES
(22, 5), -- Ciencia Ficción
(20, 5), -- Aventura
(26, 5); -- Drama

-- The Batman (ID película 3) => Crimen, Drama, Acción
INSERT INTO `myletterbox`.`genre_has_movie` (`Genre_id`, `Movie_id`) VALUES
(24, 6), -- Crimen
(26, 6), -- Drama
(19, 6); -- Acción
