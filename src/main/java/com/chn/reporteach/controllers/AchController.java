package com.chn.reporteach.controllers;

import com.chn.reporteach.consultas.Consultas;
import com.chn.reporteach.model.AchAllConsulta;
import com.chn.reporteach.model.AchEnviada;
import com.chn.reporteach.model.AchRecibida;
import com.chn.reporteach.model.ConsultaAch;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AchController {
    @Autowired
    Consultas consultas;

    private final HttpServletRequest request;

    public AchController(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET})
    public String index(Model model) {
        model.addAttribute("index", null);
        return "index";
    }

    @RequestMapping(value = {"/formachrecibida"}, method = {RequestMethod.GET})
    public String consultaoperaciones(Model model) {
        System.out.println("ingresando a formulario para consultar recibidas");
        model.addAttribute("achRecibida", new AchRecibida());
        return "formachrecibida";
    }

    @PostMapping({"/listarecibidas"})
    public ModelAndView consulta(@Valid AchRecibida achRecibida, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("formachrecibida");
            return modelAndView;
        }
        modelAndView.addObject("listAch", this.consultas.listarAchRecibidas(achRecibida.getFechainicio(), achRecibida.getFechafin(), achRecibida.getCuenta()));
        modelAndView.setViewName("listaachrecibidas");
        return modelAndView;
    }

    @RequestMapping(value = {"/formachenviada"}, method = {RequestMethod.GET})
    public String consultaoperacionesenviadas(Model model) {
        model.addAttribute("achEnviada", new AchEnviada());
        return "formachenviada";
    }

    @PostMapping({"/consultaachenviadas"})
    public ModelAndView consultaachenviadas(@Valid AchEnviada achEnviada, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("formachenviada");
            return modelAndView;
        }
        modelAndView.addObject("listAch", this.consultas.listarAchEnviadas(achEnviada.getFechainicio(), achEnviada.getFechafin(), achEnviada.getCuenta()));
        modelAndView.setViewName("listaachenviadas");
        return modelAndView;
    }



    /*
    @PostMapping({"/transacciones"})
    public ModelAndView consultaach(@Valid ConsultaAch consultaAch, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("formconsultaach");
            return modelAndView;
        }
        String consulta = " EXEC SP_REPORTE_TRANSACCIONES_ACH '" + consultaAch.getFechainicio() + "'";
        List<Map<String, Object>> list = null;
        list = null;
        modelAndView.addObject("transaccionesach", list);
        modelAndView.setViewName("transaccionesach");
        return modelAndView;
    }

     */

    @PostMapping({"/transaccionesach"})
    public ModelAndView transaccionesach(@Valid ConsultaAch consultaAch, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("reportetransacciones");
            return modelAndView;
        }
        modelAndView.setViewName("");
        return modelAndView;
    }


    @RequestMapping(value = {"/formconsultaenviadastodo"}, method = {RequestMethod.GET})
    public String formconsultaenviadastodo(Model model)
    {
        model.addAttribute("filtroconsulta", new AchAllConsulta());
        return "formconsultaenviadastodo";
    }


    @PostMapping({"/listataachenviadasall"})
    public ModelAndView consultaachenviadas(@Valid AchAllConsulta achAllEnviada, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("formconsultaenviadastodo");
            return modelAndView;
        }
        modelAndView.addObject("listAchEnviadas", this.consultas.listarAllAchEnviadas(achAllEnviada.getFecha()));
        modelAndView.setViewName("listaachenviadasall");
        return modelAndView;
    }



    @RequestMapping(value = {"/formconsultarecibidastodo"}, method = {RequestMethod.GET})
    public String formconsultarecibidastodo(Model model)
    {
        model.addAttribute("filtroconsulta", new AchAllConsulta());
        return "formconsultarecibidastodo";
    }


    @PostMapping({"/listarecibidasll"})
    public ModelAndView consultarecibidas(@Valid AchAllConsulta achAllEnviada, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("formconsultarecibidastodo");
            return modelAndView;
        }
        modelAndView.addObject("listAchRecibidas", this.consultas.listarAllAchRecibidas(achAllEnviada.getFecha()));
        modelAndView.setViewName("listaachrecibidassall");
        return modelAndView;
    }


    /* otro sistema */


/*
    @RequestMapping(value = {"/form"}, method = {RequestMethod.GET})
    public String form(Model model) {
        model.addAttribute("ach", new Ach());
        return "ach";
    }

    @PostMapping({"/consulta"})
    public ModelAndView consulta(@Valid Ach ach, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("ach");
            return modelAndView;
        }

        modelAndView.addObject("listAch",  this.consultas.listar(ach.getFecha(), ach.getTipo()));
        modelAndView.setViewName("lista");
        return modelAndView;
    }

 */


    @RequestMapping(value = {"/consultaach"}, method = {RequestMethod.GET})
    public String consultaach(Model model) {
        model.addAttribute("consultaAch", new ConsultaAch());
        return "consultaach";
    }


    @PostMapping({"/transacciones"})
    public ModelAndView transacciones(@Valid ConsultaAch consultaAch, BindingResult bindigResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindigResult.hasErrors()) {
            modelAndView.setViewName("consultaach");
            return modelAndView;
        }

        List<Map<String, Object>> list = null;
        list = this.consultas.transaccionesEnviadas  (consultaAch.getFechainicio());
        modelAndView.addObject("transaccionesach", list);
        modelAndView.setViewName("transaccionesach");
        return modelAndView;
    }
}