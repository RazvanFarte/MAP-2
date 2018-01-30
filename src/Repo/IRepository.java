package Repo;

import java.util.ArrayList;

public interface IRepository<E> {

    public void add(E entity);

    public ArrayList<E> getEntities();

}
