package co.edu.unbosque.controller;

import co.edu.unbosque.entity.Parametro;
import co.edu.unbosque.entity.Usuario;
import co.edu.unbosque.service.api.ParametroServiceAPI;
import co.edu.unbosque.service.api.UsuarioServiceAPI;
import co.edu.unbosque.utils.HashGenerator;
import co.edu.unbosque.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8181",maxAge = 3600)
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioServiceAPI usuarioServiceAPI;
	@Autowired
	private ParametroServiceAPI parametroServiceAPI;

	@GetMapping(value = "/getAll")
	// ResponseEntity List<Usuario> getAll(){
	public List<Usuario> getAll() {
		return usuarioServiceAPI.getAll();
	}

	@PostMapping(value = "/saveUsuario")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		procesarClave(usuario);
		Usuario obj = usuarioServiceAPI.save(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK); // 200
	}

	@GetMapping(value = "/findRecord/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) throws ResourceNotFoundException {
		Usuario usuario = usuarioServiceAPI.get(id);
		if (usuario == null) {
			new ResourceNotFoundException("Record not found for <Usuario>" + id);
		}
		return ResponseEntity.ok().body(usuario);
	}

	@DeleteMapping(value = "/deleteUsuario/{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
		Usuario usuario = usuarioServiceAPI.get(id);
		if (usuario != null) {
			usuarioServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	private void procesarClave(Usuario usuario) {
		if (usuario.getClaveUsrio() != null && !usuario.getClaveUsrio().isEmpty()) {
	        Usuario usuarioExistente = (usuario.getId() != 0) ? usuarioServiceAPI.get(usuario.getId()) : null;

	        if (usuarioExistente == null || !usuario.getClaveUsrio().equals(usuarioExistente.getClaveUsrio())) {
	            usuario.setClaveUsrio(HashGenerator.generarHash(usuario.getClaveUsrio()));
	            usuario.setFchaUltmaClave(new Date()); // Actualiza con la fecha actual
	        } else {
	            usuario.setClaveUsrio(usuarioExistente.getClaveUsrio());
	        }
	    }
	}
	@GetMapping(value="/login/{correo}/{clave}")
	public String login(@PathVariable String correo,@PathVariable String clave) {
		List<Usuario> usuarios=usuarioServiceAPI.getAll();
		Usuario usuario=usuarios.stream().filter(u->u.getCorreoUsuario().equals(correo)).findFirst().orElse(null);
		if(usuario==null) {
			return "usuario no encontrado";
		}
		if(usuario.getIdTipoUsuario().equals("1")){
			return"Inicio de sesion Exitoso";
			
		}
		if(usuario.getEstado()==0) {
			return "Cuenta Bloqueada";
		}
		if(!HashGenerator.verificarHash(clave, usuario.getClaveUsrio())) {
			usuario.setIntentos(usuario.getIntentos()+1);
			usuarioServiceAPI.save(usuario);
			
			short idParametro=(short)1;
			Parametro parametro=parametroServiceAPI.get(idParametro);
			int intentosMaximos=parametro.getValorNumero();
			
			if(usuario.getIntentos()>=intentosMaximos) {
				usuario.setEstado((byte)0);
				usuarioServiceAPI.save(usuario);
				return "Cuenta bloqueada por intentos maximos";
			}
			usuarioServiceAPI.save(usuario);
			return "Credenciales Incorrectas.Intento"+usuario.getIntentos();
		}
		Date fechaActual= new Date();
		short idCambioClave=(short)2;
		Parametro parametroCambioClave=parametroServiceAPI.get(idCambioClave);
		if(parametroCambioClave==null) {
			return"Error,no se ha configurado la periocidad del cambio de contraseña";
		}
		Date fechaInicial=parametroCambioClave.getFechaInicial();
		Date fechaFinal=parametroCambioClave.getFechaFinal();
		
		if (fechaActual.before(fechaInicial)) {
		    return "El parámetro de cambio de contraseña aún no está activo.";
		}

		if (fechaFinal != null && fechaActual.after(fechaFinal)) {
		    return "El período de cambio de contraseña ha expirado.";
		}
		
		int diasPermitidos=parametroCambioClave.getValorNumero();
		
		long diferenciaTiempo=fechaActual.getTime()-usuario.getFchaUltmaClave().getTime();
		long diferenciasDias=diferenciaTiempo/(1000*60*60*24);
		
		if(diferenciasDias>=diasPermitidos) {
			usuario.setEstado((byte)0);
			usuarioServiceAPI.save(usuario);
			return"Cuenta bloqueada , no actualizo  su contraseña";
		}
		long diasRestantes=diasPermitidos-diferenciasDias;
		usuario.setIntentos(0);
		usuarioServiceAPI.save(usuario);
		return" inicio de sesion Exitoso. Recuerda que debes cambiar tu contraseña dentro de"+diasRestantes;
		
		
	}
	
	

}