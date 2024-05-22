package dh.backend.test;

import dh.backend.dao.impl.OdontologoIDao;
import dh.backend.model.Odontologo;
import dh.backend.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoTest {

    public static Logger LOGGER=Logger.getLogger(OdontologoTest.class);

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_examen_parcial;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    OdontologoService odontologoService=new OdontologoService(new OdontologoIDao());

    @Test
    @DisplayName("Registro de Odontolog")

    void Test1(){
        Odontologo odontologo1=new Odontologo("Pepito","Limaña","123455");

        Odontologo odontologoRegistrado=odontologoService.registrarOdontologo(odontologo1);

        assertNotNull(odontologoRegistrado);
    }

    @Test
    @DisplayName("Testo listado de Odontologos")

    void Test2(){
        Odontologo odontologo1=new Odontologo("Pepito","Limaña","123455");

        Odontologo od2ontologoRegistrado=odontologoService.registrarOdontologo(odontologo1);

        List<Odontologo> odontologos=odontologoService.listarOdontologos();

        assertEquals(2,odontologos.size());
    }
}
