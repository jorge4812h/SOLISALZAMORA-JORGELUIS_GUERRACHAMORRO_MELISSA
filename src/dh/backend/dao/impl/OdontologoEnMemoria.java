package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoEnMemoria implements IDao<Odontologo> {

    public static Logger LOGGER = Logger.getLogger(OdontologoEnMemoria.class);

    List<Odontologo> listaOdontologos=new ArrayList<>();
    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        Integer id=listaOdontologos.size()+1;

        odontologo.setId(id);

        listaOdontologos.add(odontologo);

        LOGGER.info("Odontologo Registrado: "+odontologo);

        return odontologo;
    }

    @Override
    public List<Odontologo> listarOdontologo() {

        LOGGER.info("Listado de Pacientes:" +listaOdontologos);

        return listaOdontologos;
    }
}
