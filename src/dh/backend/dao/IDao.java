package dh.backend.dao;

import java.util.List;

public interface IDao <T>{
    T registrarOdontologo (T t);
    List<T> listarOdontologo ();

}
