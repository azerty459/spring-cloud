package fr.nextoo.micro.common.transformer;


import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Transformer<E, D, K> {

    public abstract D transformToDto(E entity);

    public abstract K extractKey(E entity);

    public abstract E transformToEntity(D dto);

    public abstract E holdKey(K key);

    public final Collection<D> transformToDto(Collection<E> entities) {
        return entities == null ? null :
                entities.stream()
                        .map(this::transformToDto)
                        .collect(Collectors.toList());
    }

    public final Collection<K> extractKey(Collection<E> entities) {
        return entities == null ? null :
                entities.stream()
                        .map(this::extractKey)
                        .collect(Collectors.toList());
    }

    public final Collection<E> transformToEntity(Collection<D> dto) {
        return dto == null ? null :
                dto.stream()
                        .map(this::transformToEntity)
                        .collect(Collectors.toList());
    }

    public final Collection<E> holdKey(Collection<K> keys) {
        return keys == null ? null :
                keys.stream()
                        .map(this::holdKey)
                        .collect(Collectors.toList());
    }
}
