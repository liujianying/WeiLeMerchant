package com.ydh.weile.uitl;

/**
 * Singleton helper class for lazily initialization.
 * 
 * @param <T>
 */
public abstract class SingletonUtils<T> {
    private T instance;

    protected abstract T newInstance();

    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
