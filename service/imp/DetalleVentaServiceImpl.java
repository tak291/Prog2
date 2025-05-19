package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.DetalleVenta;
import co.edu.unbosque.repository.DetalleVentaRepository;
import co.edu.unbosque.service.api.DetalleVentaServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;
@Service
public class DetalleVentaServiceImpl extends GenericServiceImpl<DetalleVenta, Integer>implements DetalleVentaServiceAPI {
    
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	@Override
	public CrudRepository<DetalleVenta, Integer> getDao() {
		// TODO Auto-generated method stub
		return detalleVentaRepository;
	}
	

}
