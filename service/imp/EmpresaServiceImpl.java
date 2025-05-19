package co.edu.unbosque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.entity.Empresa;
import co.edu.unbosque.repository.EmpresaRepository;
import co.edu.unbosque.service.api.EmpresaServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class EmpresaServiceImpl extends GenericServiceImpl<Empresa, Short> implements EmpresaServiceAPI{

	@Autowired
	private EmpresaRepository empresaRepository;
	@Override
	public CrudRepository<Empresa, Short> getDao() {
		// TODO Auto-generated method stub
		return empresaRepository;
	}

}
