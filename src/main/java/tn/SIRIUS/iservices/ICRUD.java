package tn.SIRIUS.iservices;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICRUD<T,U> {
    public int add(T entity);
   public List<T> getAll();
    public int update(T entity);
    public int delete(T entity);

}
