
package modelo.dao;

import java.util.List;

public interface DAO<T> {
   
    public void insertar(T t);
    public void modificar(T t);
    public void eliminar(int id);
    public List<T> obtenerTodos(); 
    public T getById(int id);
    
}
