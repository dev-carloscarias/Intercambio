package com.chn.reporteach.controllers;

import com.chn.reporteach.model.RegistroUsuario;
import com.chn.reporteach.model.dao.IRoleDao;
import com.chn.reporteach.model.dao.IUsuarioDao;
import com.chn.reporteach.model.entity.Role;
import com.chn.reporteach.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class UsuarioController
{
    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MessageSource messageSource;

    @GetMapping({"/usuarios"})
    public ModelAndView usuarios() {

        ModelAndView modelAndView = new ModelAndView();

        List<Usuario> usuarios = new ArrayList<>();
        usuarios = (List<Usuario>) usuarioDao.findAll();

        modelAndView.addObject("usuarios", usuarios);
        modelAndView.setViewName("listausuarios");

        return modelAndView;
    }

    @GetMapping({"/registrarusuario"})
    public String registrarUsuario(Model model) {

        model.addAttribute("registroUsuario", new RegistroUsuario());

        return "registrarusuario";
    }

    @PostMapping({"/saveuser"})
    public ModelAndView saveuser(@Valid RegistroUsuario registroUsuario,  BindingResult bindigResult, Locale locale) {

        ModelAndView modelAndView = new ModelAndView();

        if(bindigResult.hasErrors())
        {
            modelAndView.setViewName("registrarusuario");
            return modelAndView;
        }

        Usuario usuario = usuarioDao.findByUsername(registroUsuario.getUsername());

        if(usuario!= null)
        {
            modelAndView.setViewName("registrarusuario");
            modelAndView.addObject("error", messageSource.getMessage("text.usuario.error.existe", null, locale) );
            return modelAndView;
        }

        Usuario nuevoUsuario = new Usuario();

        try
        {
            Role role = new Role();
            role.setAuthority(registroUsuario.getRole());
            List<Role> roles =new ArrayList<>();
            roles.add(role);

            nuevoUsuario.setUsername(registroUsuario.getUsername());
            nuevoUsuario.setPassword( bCryptPasswordEncoder.encode( registroUsuario.getPassword()) );
            nuevoUsuario.setEnabled(true);
            nuevoUsuario.setRoles(roles);
            usuarioDao.save(nuevoUsuario);

        }

        catch (Exception ex )
        {
            System.out.println(ex.getCause().toString());
            modelAndView.setViewName("registrarusuario");
            modelAndView.addObject("error", messageSource.getMessage("text.usuario.error.crear", null, locale) );
            return modelAndView;
        }

        List<Usuario> usuarios = new ArrayList<>();
        usuarios = (List<Usuario>) usuarioDao.findAll();

        modelAndView.addObject("usuarios", usuarios);
        modelAndView.setViewName("listausuarios");

        return modelAndView;
    }
}
