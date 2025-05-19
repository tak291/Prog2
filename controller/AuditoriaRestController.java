package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.entity.Auditoria;
import co.edu.unbosque.service.api.AuditoriaServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/auditoria")
public class AuditoriaRestController {
	@Autowired
    private AuditoriaServiceAPI auditoriaServiceAPI;

    @GetMapping(value="/getAll")
    //ResponseEntity List<Usuario> getAll(){
    public List<Auditoria> getAll(){
        return auditoriaServiceAPI.getAll();
    }


    @PostMapping(value="/saveAuditoria")
    public ResponseEntity<Auditoria> save(@RequestBody Auditoria auditoria){
        Auditoria obj = auditoriaServiceAPI.save(auditoria);
        return new ResponseEntity<Auditoria>(obj, HttpStatus.OK); // 200
    }

    @GetMapping(value="/findRecord/{id}")
    public ResponseEntity<Auditoria> getAuditoriaById(@PathVariable long id)
            throws ResourceNotFoundException {
        Auditoria auditoria = auditoriaServiceAPI.get(id);
        if (auditoria == null){
            new ResourceNotFoundException("Record not found for <Auditoria>"+id);
        }
        return ResponseEntity.ok().body(auditoria);
    }
    /*
    @DeleteMapping(value="/deleteUsuario/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Integer id){
        Usuario usuario = usuarioServiceAPI.get(id);
        if (usuario != null){
            usuarioServiceAPI.delete(id);
        }else{
            return new ResponseEntity<Usuario>(usuario, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    */

}
