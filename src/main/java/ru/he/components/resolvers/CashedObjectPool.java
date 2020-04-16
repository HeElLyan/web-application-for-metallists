package ru.he.components.resolvers;

public interface CashedObjectPool<T> {

    T cashedOf(T subject);

    void dispose(T subject);

}
