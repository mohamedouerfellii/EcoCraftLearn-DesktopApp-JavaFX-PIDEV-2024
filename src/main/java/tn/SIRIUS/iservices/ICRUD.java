package tn.SIRIUS.iservices;

import java.util.List;

public interface ICRUD<T> {
    public int add(T entity);
    public List<T> getAll();
    public boolean update(T entity);
    public boolean delete(int id);


}