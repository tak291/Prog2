package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.MetodoPago;
import co.edu.unbosque.repository.MetodoPagoRepository;
import co.edu.unbosque.service.api.MetodoPagoServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class MetodoPagoServiceImpl extends GenericServiceImpl<MetodoPago, Short> implements MetodoPagoServiceAPI {
    
	@Autowired
	private MetodoPagoRepository metodoPagoRepository;
	
	@Override
	public CrudRepository<MetodoPago, Short> getDao() {
		// TODO Auto-generated method stub
		return metodoPagoRepository;
	}

}
