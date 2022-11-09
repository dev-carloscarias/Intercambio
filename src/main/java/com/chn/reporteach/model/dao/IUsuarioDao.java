package com.chn.reporteach.model.dao;


import com.chn.reporteach.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    Usuario findByUsername(String paramString);

    List<Usuario> findAll();

}
