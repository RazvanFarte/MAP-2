package repo;

import datastructures.IList;
import datastructures.List;

public class MemoryRepository<E> implements IRepository<E>{
    private java.util.List<E> entities;

    public MemoryRepository() {
        this.entities = new List<>();
    }

    public void add(E car){
        this.entities.add(car);
    }

    @Override
    public java.util.List<E> getEntities() {
        return entities;
    }

    @Override
    public void setEntities(java.util.List<E> entities) {
        this.entities = entities;
    }
}
