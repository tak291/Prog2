package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.repository.ClienteRepository;
import co.edu.unbosque.service.api.ClienteServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;
@Service
public class ClienteServiceImpl  extends GenericServiceImpl<Cliente, Integer>implements ClienteServiceAPI{
    
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public CrudRepository<Cliente, Integer> getDao() {
		return clienteRepository;
	}

}
