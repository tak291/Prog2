package co.edu.unbosque.utils;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getDao().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> resultList = new ArrayList<>();
		getDao().findAll().forEach(obj -> resultList.add(obj));
		return resultList;
	}

	public abstract CrudRepository<T, ID> getDao();
}
