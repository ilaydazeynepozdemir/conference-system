package com.conference.system.model;

import lombok.Data;

import java.util.*;

@Data
public class Track implements List<Presentation> {

    public Track() {
        this.presentationList = new LinkedList<>();
    }

    public Track(Integer id) {
        this.id = id;
        this.presentationList = new LinkedList<>();
    }

    private Integer id;
    private List<Presentation> presentationList;

    @Override
    public int size() {
        return presentationList.size();
    }

    @Override
    public boolean isEmpty() {
        return presentationList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return presentationList.contains(o);
    }

    @Override
    public Iterator<Presentation> iterator() {
        return presentationList.iterator();
    }

    @Override
    public Object[] toArray() {
        return presentationList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return presentationList.toArray(a);
    }

    @Override
    public boolean add(Presentation presentation) {
        return presentationList.add(presentation);
    }

    @Override
    public boolean remove(Object o) {
        return presentationList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return presentationList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Presentation> c) {
        return presentationList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Presentation> c) {
        return presentationList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return presentationList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return presentationList.retainAll(c);
    }

    @Override
    public void clear() {
        presentationList.clear();
    }

    @Override
    public Presentation get(int index) {
        return presentationList.get(index);
    }

    @Override
    public Presentation set(int index, Presentation element) {
        return presentationList.set(index, element);
    }

    @Override
    public void add(int index, Presentation element) {
        presentationList.add(index, element);
    }

    @Override
    public Presentation remove(int index) {
        return presentationList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return presentationList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return presentationList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Presentation> listIterator() {
        return presentationList.listIterator();
    }

    @Override
    public ListIterator<Presentation> listIterator(int index) {
        return presentationList.listIterator(index);
    }

    @Override
    public List<Presentation> subList(int fromIndex, int toIndex) {
        return presentationList.subList(fromIndex, toIndex);
    }
}
