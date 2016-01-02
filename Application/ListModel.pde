import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

abstract class ListModel<T> implements List<T> {

    protected List<T> list;

    ListModel(List<T> list) {
        this.list = Collections.synchronizedList(list);
    }

    void preEnumerate() {

    }

    String labelFor(T element){
        return element.toString();
    }

    @Override
    public synchronized int size() {
        return list.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public synchronized Object[] toArray() {
        return list.toArray();
    }

    @Override
    public synchronized <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public synchronized boolean add(T t) {
        return list.add(t);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public synchronized boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public synchronized boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    @Override
    public synchronized boolean addAll(int index, Collection<? extends T> c) {
        return list.addAll(index, c);
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public synchronized boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public synchronized void replaceAll(UnaryOperator<T> operator) {
        list.replaceAll(operator);
    }

    @Override
    public synchronized void sort(Comparator<? super T> c) {
        list.sort(c);
    }

    @Override
    public synchronized void clear() {
        list.clear();
    }

    @Override
    public synchronized boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public synchronized int hashCode() {
        return list.hashCode();
    }

    @Override
    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized T set(int index, T element) {
        return list.set(index, element);
    }

    @Override
    public synchronized void add(int index, T element) {
        list.add(index, element);
    }

    @Override
    public synchronized T remove(int index) {
        return list.remove(index);
    }

    @Override
    public synchronized int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public synchronized int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public synchronized ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public synchronized ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public synchronized List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public synchronized Spliterator<T> spliterator() {
        return list.spliterator();
    }

    @Override
    public synchronized boolean removeIf(Predicate<? super T> filter) {
        return list.removeIf(filter);
    }

    @Override
    public synchronized Stream<T> stream() {
        return list.stream();
    }

    @Override
    public synchronized Stream<T> parallelStream() {
        return list.parallelStream();
    }

    @Override
    public synchronized void forEach(Consumer<? super T> action) {
        list.forEach(action);
    }
}