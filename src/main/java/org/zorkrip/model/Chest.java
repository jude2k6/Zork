package org.zorkrip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chest<T> implements Serializable {
    private final List<T> contents = new ArrayList<>();

    public void add(T item) {
        contents.add(item);
    }

    public List<T> getContents() {
        return contents;
    }
}
