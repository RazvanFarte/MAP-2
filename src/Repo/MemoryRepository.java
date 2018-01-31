package Repo;

import datastructures.IList;
import datastructures.List;

import java.util.ArrayList;

public class MemoryRepository<E> implements IRepository<E>{
    private IList<E> entities;

    public MemoryRepository() {
        this.entities = new List<>();
    }

    public void add(E car){
        this.entities.add(car);
    }

    public IList<E> getEntities() {
        return entities;
    }

    @Override
    public E getCurrentEntity() {
        return entities.get(0);
    }
}
