package es.uma.demospring.myletterbox.controller;

import jakarta.servlet.http.HttpSession;

/*
 * Autor(es): Ivan Pedraza Díez (100%)
 */

public class BaseController {

    protected boolean estaAutenticado(HttpSession session) {return session.getAttribute("user") != null;}

}
