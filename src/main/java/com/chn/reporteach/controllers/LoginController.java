package com.chn.reporteach.controllers;


import java.security.Principal;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private MessageSource messageSource;

    @RequestMapping({"/login"})
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model, Principal principal, RedirectAttributes flash, Locale locale) {
        System.out.println("Ingresando al login");
        if (principal != null) {
            System.out.println("Datos de principal: " + principal.getName());
            flash.addFlashAttribute("info", this.messageSource.getMessage("text.login.already", null, locale));
            return "redirect:/";
        }
        if (error != null) {
            System.out.println("No hay error");
            model.addAttribute("error", this.messageSource.getMessage("text.login.error", null, locale));
        }
        if (logout != null) {
            System.out.println("No es logout");
            model.addAttribute("success", this.messageSource.getMessage("text.login.logout", null, locale));
        }
        return "login";
    }
}
