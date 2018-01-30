package Repo;

import java.util.ArrayList;

public class MemoryRepository<E> implements IRepository<E>{
    private ArrayList<E> entities;

    public MemoryRepository(int size) {
        this.entities = new ArrayList<E>(size);
    }

    public void add(E car){
        this.entities.add(car);
    }

    public ArrayList<E> getEntities() {
        return entities;
    }
}
