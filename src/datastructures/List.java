package datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;

public class List<E> implements IList<E> {

    ArrayList<E> mList;

    public List() {
        mList = new ArrayList<>();
    }

    @Override
    public int size() {
        return mList.size();
    }

    @Override
    public boolean isEmpty() {
        return mList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return mList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return mList.iterator();
    }

    @Override
    public Object[] toArray() {
        return mList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return mList.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return mList.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return mList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return mList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return mList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return mList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return mList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return mList.retainAll(c);
    }

    @Override
    public void clear() {
        mList.clear();
    }

    @Override
    public E get(int index) {
        return mList.get(index);
    }

    @Override
    public E set(int index, E element) {
        return mList.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        mList.add(index, element);
    }

    @Override
    public E remove(int index) {
        return mList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return mList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return mList.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return mList.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return mList.listIterator(index);
    }

    @Override
    public java.util.List<E> subList(int fromIndex, int toIndex) {
        return mList.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        mList.forEach(new Consumer<E>() {
            public void accept(E element) {
                stringBuilder.append(element.toString() + ", ");
            }
        });

        stringBuilder.append("]\n");

        return stringBuilder.toString();
    }
}
