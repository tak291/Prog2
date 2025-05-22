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

import co.edu.unbosque.entity.MetodoPago;
import co.edu.unbosque.service.api.MetodoPagoServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/metodoPago")
public class MetodoPagoRestController {
	@Autowired
    private MetodoPagoServiceAPI metodoPagoServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Usuario> getAll(){
    public List<MetodoPago> getAll(){
        return metodoPagoServiceAPI.getAll();
    }


    @PostMapping(value="/saveMetodoPago")
    public ResponseEntity<MetodoPago> save(@RequestBody MetodoPago metodoPago){
        MetodoPago obj = metodoPagoServiceAPI.save(metodoPago);
        return new ResponseEntity<MetodoPago>(obj, HttpStatus.OK); // 200
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<MetodoPago> getMetodoPagoById(@PathVariable Short id)
            throws ResourceNotFoundException {
        MetodoPago metodoPago = metodoPagoServiceAPI.get(id);
        if (metodoPago == null){
            new ResourceNotFoundException("Record not found for <MetodoPago>"+id);
        }
        return ResponseEntity.ok().body(metodoPago);
    }

    @DeleteMapping(value="/deleteMetodoPago/{id}")
    public ResponseEntity<MetodoPago> delete(@PathVariable Short id){
        MetodoPago metodoPago = metodoPagoServiceAPI.get(id);
        if (metodoPago != null){
            metodoPagoServiceAPI.delete(id);
        }else{
            return new ResponseEntity<MetodoPago>(metodoPago, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<MetodoPago>(metodoPago, HttpStatus.OK);
    }


}
