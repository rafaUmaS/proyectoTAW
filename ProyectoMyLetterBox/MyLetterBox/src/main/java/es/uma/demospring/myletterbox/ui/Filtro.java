package es.uma.demospring.myletterbox.ui;

import lombok.Data;
/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */
@Data
public class Filtro {
    protected String nombre;
    protected String columnaFiltro;

    protected String idioma;
    protected String genero;
}
