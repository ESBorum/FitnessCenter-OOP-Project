package service;

import java.util.ArrayList;

// Generic repository class to manage collections of items
public class Repository<T> {
    private ArrayList<T> items;

    public Repository() {
        items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public ArrayList<T> getAll() {
        return items;
    }
}