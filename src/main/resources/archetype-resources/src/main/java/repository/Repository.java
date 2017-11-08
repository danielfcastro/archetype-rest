package ${groupId}.${artifactId}.repository;

import java.util.List;

public interface Repository<T> {
	
    void add(T item);

    void update(T item);

    void remove(T item);

    void remove(String  id);

    List<T> query(String  id);
}
