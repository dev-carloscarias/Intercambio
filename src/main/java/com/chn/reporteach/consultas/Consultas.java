package com.chn.reporteach.consultas;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consultas
{
    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate jdbcTemplate2;

    public Consultas(@Qualifier("Second") DataSource dataSource, @Qualifier("Third")DataSource dataSource2 )
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcTemplate2 = new JdbcTemplate(dataSource2);
    }

    public List<Map<String, Object>> listarAchRecibidas(String fechaInicial, String fechaFinal, String cuenta) {
        List<Map<String, Object>> list = null;
        try {
            String consulta = " EXEC SP_CONSULTA_ACH_RECIBIDAS '" + fechaInicial + "', '" + fechaFinal + "','" + cuenta + "'";
            list = this.jdbcTemplate.queryForList(consulta);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Map<String, Object>> listarAchEnviadas(String fechaInicial, String fechaFinal, String cuenta) {
        List<Map<String, Object>> list = null;
        try {
            String consulta = " EXEC SP_CONSULTA_ACH_SALIENTES '" + fechaInicial + "', '" + fechaFinal + "','" + cuenta + "'";
            System.out.println(consulta);
            list = this.jdbcTemplate.queryForList(consulta);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Map<String, Object>> listarAllAchEnviadas(String fecha) {
        List<Map<String, Object>> list = null;
        try {
            String consulta = " EXEC SP_LISTA_TRAN_ACH_ENVIADAS '" + fecha + "'";
            System.out.println(consulta);
            list = this.jdbcTemplate2.queryForList(consulta);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Map<String, Object>> listarAllAchRecibidas(String fecha) {
        List<Map<String, Object>> list = null;
        try {
            String consulta = " EXEC SP_LISTA_TRAN_ACH_RECIBIDAS '" + fecha + "'";
            System.out.println(consulta);
            list = this.jdbcTemplate2.queryForList(consulta);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }



    public List<Map<String, Object>> listar(String fecha, int tipo) {
        List<Map<String, Object>> list = null;
        try {
            String consulta = " EXEC SP_TRANSACCIONES_ENVIADAS_CCA '" + fecha + "'," + tipo + "";
            System.out.println(consulta);
            list = this.jdbcTemplate2.queryForList(consulta);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }


    public List<Map<String, Object>> transaccionesEnviadas(String fecha) {
        List<Map<String, Object>> list = null;
        try {
            String consulta = " EXEC SP_REPORTE_TRANSACCIONES_ACH '" + fecha +  "'";
            System.out.println(consulta);
            list = this.jdbcTemplate2.queryForList(consulta);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

}
