package es.uma.demospring.myletterbox.controller;

import jakarta.servlet.http.HttpSession;

public class BaseController {

    protected boolean estaAutenticado(HttpSession session) {return session.getAttribute("user") != null;}

}
