package repo;

import datastructures.IList;
import datastructures.List;

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
    public void setEntities(IList<E> entities) {
        this.entities = entities;
    }
}
