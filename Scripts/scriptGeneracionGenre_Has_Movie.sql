-- Inception (ID película 1) => Ciencia Ficción, Acción, Aventura
ALTER TABLE `myletterbox`.`genre_has_movie`
CONVERT TO CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

INSERT INTO `myletterbox`.`genre_has_movie` (`Genre_id`, `Movie_id`) VALUES
(1, 1), -- Ciencia Ficción
(2, 1), -- Aventura
(3, 1); -- Acción

-- Interstellar (ID película 2) => Ciencia Ficción, Aventura, Drama
INSERT INTO `myletterbox`.`genre_has_movie` (`Genre_id`, `Movie_id`) VALUES
(1, 2), -- Ciencia Ficción
(2, 2), -- Aventura
(4, 2); -- Drama

-- The Batman (ID película 3) => Crimen, Drama, Acción
INSERT INTO `myletterbox`.`genre_has_movie` (`Genre_id`, `Movie_id`) VALUES
(5, 3), -- Crimen
(4, 3), -- Drama
(3, 3); -- Acción
