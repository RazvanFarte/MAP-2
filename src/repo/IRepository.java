package repo;

import datastructures.IList;

public interface IRepository<E> {

    public void add(E entity);

    public IList<E> getEntities();

    public void setEntities(IList<E> entities);
}
