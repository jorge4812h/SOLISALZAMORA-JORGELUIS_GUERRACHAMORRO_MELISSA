package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.db.H2Connection;
import dh.backend.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoIDao implements IDao<Odontologo> {

    public static Logger LOGGER = Logger.getLogger(OdontologoIDao.class);
    public static String SQL_INSERT="INSERT INTO ODONTOLOGOS VALUES (DEFAULT,?,?,?)";
    public static String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {

        Connection connection=null;
        Odontologo odontologoARetornar=null;

        try{
            connection= H2Connection.getConnection();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());

            preparedStatement.executeUpdate();
            ResultSet resultSet= preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                Integer id=resultSet.getInt(1);

                odontologoARetornar = new Odontologo(id, odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());


            }

            LOGGER.info("Odontologo Registrado: "+odontologoARetornar);

            connection.commit();
            connection.setAutoCommit(true);

        }catch (Exception e){
            if(connection!=null){
                try{
                    connection.rollback();
                }catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                    ex.printStackTrace();
                }
            }
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return odontologoARetornar;
    }

    @Override
    public List<Odontologo> listarOdontologo() {
        Connection connection = null;
        Odontologo odontologoARetornar = null;

        List<Odontologo> listadoOdontologos=new ArrayList<>();
        try{

            connection=H2Connection.getConnection();

            Statement statement= connection.createStatement();

            ResultSet resultSet=statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()){

                Odontologo odontologo1=new Odontologo(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4));

                listadoOdontologos.add(odontologo1);
            }
            LOGGER.info("Listado de Odontologos:"+listadoOdontologos);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return listadoOdontologos;

    }
}
