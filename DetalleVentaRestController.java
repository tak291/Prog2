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

import co.edu.unbosque.entity.DetalleVenta;
import co.edu.unbosque.service.api.DetalleVentaServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;



//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaRestController {
	@Autowired
    private DetalleVentaServiceAPI detalleVentaServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Usuario> getAll(){
    public List<DetalleVenta> getAll(){
        return detalleVentaServiceAPI.getAll();
    }


    @PostMapping(value="/saveDetalleVenta")
    public ResponseEntity<DetalleVenta> save(@RequestBody DetalleVenta detalleVenta){
        DetalleVenta obj = detalleVentaServiceAPI.save(detalleVenta);
        return new ResponseEntity<DetalleVenta>(obj, HttpStatus.OK); // 200
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<DetalleVenta> getDetalleVentaById(@PathVariable Integer id)
            throws ResourceNotFoundException {
        DetalleVenta detalleVenta = detalleVentaServiceAPI.get(id);
        if (detalleVenta == null){
            new ResourceNotFoundException("Record not found for <DetalleVenta>"+id);
        }
        return ResponseEntity.ok().body(detalleVenta);
    }

    @DeleteMapping(value="/deleteDetalleVenta/{id}")
    public ResponseEntity<DetalleVenta> delete(@PathVariable Integer id){
        DetalleVenta detalleVenta = detalleVentaServiceAPI.get(id);
        if (detalleVenta != null){
            detalleVentaServiceAPI.delete(id);
        }else{
            return new ResponseEntity<DetalleVenta>(detalleVenta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<DetalleVenta>(detalleVenta, HttpStatus.OK);
    }
	

}
