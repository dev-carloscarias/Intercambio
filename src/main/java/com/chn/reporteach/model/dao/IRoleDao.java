package com.chn.reporteach.model.dao;

import com.chn.reporteach.model.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleDao extends CrudRepository<Role, Long> {

    List<Role> findAll();

    @Transactional(readOnly = false)
    @Query(value = "insert into authorities (authority,user_id) VALUES (:authority,:user_id)", nativeQuery = true)
    void saveRol(@Param("authority") String authority, @Param("user_id") Long id);

}
