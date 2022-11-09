package com.chn.reporteach.model.service;


import com.chn.reporteach.model.dao.IUsuarioDao;
import com.chn.reporteach.model.entity.Role;
import com.chn.reporteach.model.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class JpaUserDetailsService implements UserDetailsService
{
    @Autowired
    private IUsuarioDao usuarioDao;

    private Logger logger = LoggerFactory.getLogger(com.chn.reporteach.model.service.JpaUserDetailsService.class);

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("ingresando a consulta de usuario");
        Usuario usuario = this.usuarioDao.findByUsername(username);
        if (usuario == null) {
            this.logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : usuario.getRoles()) {
            this.logger.info("Role: ".concat(role.getAuthority()));
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        if (authorities.isEmpty()) {
            System.out.println("No tiene rol");
            this.logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        return (UserDetails)new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled().booleanValue(), true, true, true, authorities);
    }
}
