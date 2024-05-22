package dh.backend.service;

import dh.backend.dao.IDao;
import dh.backend.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologos;

    public OdontologoService(IDao<Odontologo> odontologos) {
        this.odontologos = odontologos;
    }

    public IDao<Odontologo> getOdontologos() {
        return odontologos;
    }

    public void setOdontologos(IDao<Odontologo> odontologos) {
        this.odontologos = odontologos;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologos.registrarOdontologo(odontologo);
    }

    public List<Odontologo> listarOdontologos(){

        return odontologos.listarOdontologo();
    }


}
