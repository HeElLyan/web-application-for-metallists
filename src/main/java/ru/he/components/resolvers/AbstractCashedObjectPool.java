package ru.he.components.resolvers;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCashedObjectPool<T> implements CashedObjectPool<T> {

    private final List<T> cash;

    public AbstractCashedObjectPool() {
        cash = new ArrayList<>();
    }

    @Override
    public void dispose(T subject) {
        synchronized (cash) {
            for (T t : cash) {
                if (t.equals(subject)) {
                    cash.remove(subject);
                }
            }
        }
    }

    @Override
    public T cashedOf(T subject) {
        synchronized (cash) {
            for (T t : cash) {
                if (t.equals(subject)) {
                    return t;
                }
            }
            cash.add(subject);
            return subject;
        }
    }

}
