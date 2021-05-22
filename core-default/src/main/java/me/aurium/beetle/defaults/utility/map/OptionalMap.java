package me.aurium.beetle.defaults.utility.map;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * move this to beetle
 * @param <T>
 * @param <E>
 */
public interface OptionalMap<T,E> {

    Optional<E> remove(T t);

    Optional<E> get(T t);

    void removeIfPresent(T key, Consumer<E> consumer);

    void consumeIfPresent(T key, Consumer<E> consumer);

    Map<T,E> delegate();
}
