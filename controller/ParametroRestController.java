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

import co.edu.unbosque.entity.Parametro;
import co.edu.unbosque.service.api.ParametroServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

@RestController
@RequestMapping("/parametro")
public class ParametroRestController {
	 @Autowired
	    private ParametroServiceAPI parametroServiceAPI;

	    @GetMapping(value="/getAll")
	    //ResponseEntity List<Usuario> getAll(){
	    public List<Parametro> getAll(){
	        return parametroServiceAPI.getAll();
	    }


	    @PostMapping(value="/saveParametro")
	    public ResponseEntity<Parametro> save(@RequestBody Parametro parametro){
	        Parametro obj = parametroServiceAPI.save(parametro);
	        return new ResponseEntity<Parametro>(obj, HttpStatus.OK); // 200
	    }

	    @GetMapping(value="/findRecord/{id}")
	    public ResponseEntity<Parametro> getParametroById(@PathVariable Short id)
	            throws ResourceNotFoundException {
	        Parametro parametro = parametroServiceAPI.get(id);
	        if (parametro == null){
	            new ResourceNotFoundException("Record not found for <Parametro>"+id);
	        }
	        return ResponseEntity.ok().body(parametro);
	    }

	    @DeleteMapping(value="/deleteParametro/{id}")
	    public ResponseEntity<Parametro> delete(@PathVariable Short id){
	        Parametro parametro = parametroServiceAPI.get(id);
	        if (parametro != null){
	            parametroServiceAPI.delete(id);
	        }else{
	            return new ResponseEntity<Parametro>(parametro, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<Parametro>(parametro, HttpStatus.OK);
	    }

}
