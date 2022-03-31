package model;
import java.util.HashMap;

public interface Persistable<T> {
    public T add(T obj);

    public T delete (Integer id);
    
    public T search (Integer id);
    
    public HashMap<Integer, T> getMap();
}