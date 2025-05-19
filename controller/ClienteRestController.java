package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.entity.Cliente;
import co.edu.unbosque.service.api.ClienteServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {
	 @Autowired
	    private ClienteServiceAPI clienteServiceAPI;

	    @GetMapping(value="/getAll")
	    //ResponseEntity List<Usuario> getAll(){
	    public List<Cliente> getAll(){
	        return clienteServiceAPI.getAll();
	    }


	    @PostMapping(value="/saveCliente")
	    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
	        Cliente obj = clienteServiceAPI.save(cliente);
	        return new ResponseEntity<Cliente>(obj, HttpStatus.OK); // 200
	    }

	    @GetMapping(value="/findRecord/{id}")
	    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id)
	            throws ResourceNotFoundException {
	        Cliente cliente = clienteServiceAPI.get(id);
	        if (cliente == null){
	            new ResourceNotFoundException("Record not found for <Cliente>"+id);
	        }
	        return ResponseEntity.ok().body(cliente);
	    }

	    @DeleteMapping(value="/deleteCliente/{id}")
	    public ResponseEntity<Cliente> delete(@PathVariable Integer id){
	        Cliente cliente = clienteServiceAPI.get(id);
	        if (cliente != null){
	            clienteServiceAPI.delete(id);
	        }else{
	            return new ResponseEntity<Cliente>(cliente, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	    }

}
