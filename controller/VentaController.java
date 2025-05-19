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

import co.edu.unbosque.entity.Venta;
import co.edu.unbosque.service.api.VentaServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

@RestController
@RequestMapping("/venta")
public class VentaController {
	@Autowired
    private VentaServiceAPI ventaServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Usuario> getAll(){
    public List<Venta> getAll(){
        return ventaServiceAPI.getAll();
    }


    @PostMapping(value="/saveVenta")
    public ResponseEntity<Venta> save(@RequestBody Venta venta){
        Venta obj = ventaServiceAPI.save(venta);
        return new ResponseEntity<Venta>(obj, HttpStatus.OK); // 200
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id)
            throws ResourceNotFoundException {
        Venta venta = ventaServiceAPI.get(id);
        if (venta == null){
            new ResourceNotFoundException("Record not found for <Venta>"+id);
        }
        return ResponseEntity.ok().body(venta);
    }

    @DeleteMapping(value="/deleteVenta/{id}")
    public ResponseEntity<Venta> delete(@PathVariable Integer id){
        Venta venta = ventaServiceAPI.get(id);
        if (venta != null){
            ventaServiceAPI.delete(id);
        }else{
            return new ResponseEntity<Venta>(venta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Venta>(venta, HttpStatus.OK);
    }


	

}
