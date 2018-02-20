package repo;

public interface IRepository<E> {

    public void add(E entity);

    public java.util.List<E> getEntities();

    public void setEntities(java.util.List<E> entities);
}
