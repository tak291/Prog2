package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.TipoUsuario;
import co.edu.unbosque.repository.TipoUsuarioRepository;
import co.edu.unbosque.service.api.TipoUsuarioServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class TipoUsuarioServiceImpl extends GenericServiceImpl<TipoUsuario, Short> implements TipoUsuarioServiceAPI{

	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	@Override
	public CrudRepository<TipoUsuario, Short> getDao() {
		// TODO Auto-generated method stub
		return tipoUsuarioRepository;
	}

}
