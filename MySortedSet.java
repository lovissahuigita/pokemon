import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An implementation of the FunctionalSortedSet interface that uses an ArrayList
 * as the backing data structure.
 *
 * @author Joe Rossi, Lovissa Winyoto
 * @version 1.1
 * @param <E> A comparable object that is contained within this sorted set.
 */
public class MySortedSet<E extends Comparable<? super E>>
    implements FunctionalSortedSet<E> {

    private ArrayList<E> list;
    private Comparator<E> c;

    /**
     * Creates a MySortedSet using the Comparable's compareTo as Comparator
     */
    public MySortedSet() {
        this((E e1, E e2) -> (e1.compareTo(e2)));
        // this(E::compareTo);
    }

    /**
     * Creates a MySortedSet using a specific Comparator
     *
     * @param c a Comparator that either "has" or "is" one int valued method
     */
    public MySortedSet(Comparator<E> c) {
        this.c = c;
        list = new ArrayList<E>();
    }

    //-----------FunctionalSortedSet METHODS - ONLY MODIFY filter!!------------

    /**
     * The filter method takes in a Predicate, which has a single boolean
     * valued method. This Predicate can be passed in using a lambda expression,
     * or "anonymous method" (one that you don't need to put inside a class).
     *
     *
     * @param p a Predicate that either "has" or "is" a boolean valued method
     * @return  a new FunctionalSortedSet with elements whose predicate calls
     *          returned false removed
     */
    @Override
    public MySortedSet<E> filter(Predicate<E> p) {
        return list.stream().filter(p).collect(Collectors
                            .toCollection(() -> new MySortedSet<>(c)));
    }

    /**
     * This method takes in a Comparator, which either "has" or "is" a method
     * that takes in two parameters and returns an int (similar to compareTo in
     * the Comparable interface).
     *
     * @param c a Comparator that either "has" or "is" an int valued method
     * @return  a new FunctionalSortedSet with the elements sorted based on c
     */
    @Override
    public MySortedSet<E> sort(Comparator<E> comparator) {
        MySortedSet<E> ret = new MySortedSet<E>(comparator);
        ret.addAll(this.list);
        return ret;
    }

    //------SortedSet METHODS - ONLY MODIFY subSet and tailSet!!---------------

    /**
     * This method compares items in the sorted set based on the specified
     * comparison
     *
     * @param no parameter needed
     * @return c a Comparator
     */
    @Override
    public Comparator<? super E> comparator() {
        return c;
    }

    /**
     * Returns the first item in the collection
     *
     * @param no parameter needed
     * @return the first item of type E
     */
    @Override
    public E first() {
        return list.get(0);
    }

    /**
     * Returns the last item in the collection
     *
     * @param no parameter needed
     * @return the last item of type E
     */
    @Override
    public E last() {
        return list.get(list.size() - 1);
    }

    /**
     * Returns a MySortedSet object with all elements [first, toElement) using a
     * functional programming expression.
     *
     * @param toElement The element the returned set should stop before.
     * @return A sorted set containing elements strictly less than toElement.
     */
    @Override
    public MySortedSet<E> headSet(E toElement) {
        return list.subList(0, list.indexOf(toElement)).stream().collect(
                            Collectors.toCollection(()
                            -> new MySortedSet<>(c)));
    }

    /**
     * Return a MySortedSet object with all elements [fromElement, toElement)
     * using a functional programming expression.
     *
     * @param fromElement The first element the returned set should contain.
     * @param toElement The element the returned set should stop before.
     * @return A sorted set containing elements fromElement to toElement.
     */
    @Override
    public MySortedSet<E> subSet(E fromElement, E toElement) {
        return list.subList(list.indexOf(fromElement), list.indexOf(toElement))
                            .stream().collect(Collectors.toCollection(()
                            -> new MySortedSet<>(c)));
    }

    /**
     * Return a MySortedSet object with all elements [fromElement, last]
     * using a functional programming expression.
     *
     * @param fromElement The first element the returned set should contain.
     * @return A sorted set containing elements fromElement and onwards.
     */
    @Override
    public MySortedSet<E> tailSet(E fromElement) {
        return list.subList(list.indexOf(fromElement), list.indexOf(
                            this.last())).stream().collect(Collectors
                            .toCollection(()-> new MySortedSet<>(c)));
    }

    //-----------Set METHODS - TODO---------------------------------------------

    /**
     * Adds an element of type e to the sorted set while maintaining the set to
     * be sorted and to have no duplicate
     *
     * @param e the element that needs to be added
     * @return boolean true if the element is successfully added
     * false otherwise
     */
    @Override
    public boolean add(E e) {
        boolean added = false;
        if (!(list.contains(e))) {
            added = list.add(e);
            list.sort(c);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> col) {
        boolean added = true;
        for (E element : col) {
            added = added && this.add(element); //this or list?
        }
        return added;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(Object e) {
        return list.contains(e);
    }

    @Override
    public boolean containsAll(Collection<?> col) {
        boolean containsAll = true;
        for (E element : list) {
            containsAll = (containsAll && list.contains(element));
        }
        return containsAll;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean remove(Object e) {
        return list.remove(e);
    }

    //is this the right syntax
    @Override
    public boolean removeAll(Collection<?> col) {
        return list.removeAll(col);
    }

    //is this the right syntax
    @Override
    public boolean retainAll(Collection<?> col) {
        return list.retainAll(col);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) { //T or E?
        return list.toArray(a);
    }

    @Override
    public String toString() {
        String strList = "";
        for (int index = 0; index < list.size(); index++) {
            strList += list.get(index).toString();

        }
        return strList;
    }
}
