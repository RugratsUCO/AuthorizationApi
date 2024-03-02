package com.authorizationapi.authorizationapi.controller.persona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.service.persona.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/persona")
public final class PersonaController {
	private PersonaService service;
	@GetMapping("/dummy")
	public String dummy() {
		return "hola";
	}

	@PostMapping
	public ResponseEntity<Response<Persona>> create(@RequestBody Persona persona) {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		Response<Persona> response = new Response<>();

		try {
			service.registrar(persona);
			response.getMessages().add("La persona se ha registrado exitosamente");

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido registrar la persona");

		}
		return new ResponseEntity<>(response, statusCode);
	}
	@GetMapping
	public ResponseEntity<Response<Persona>> list(@RequestBody Persona persona) {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		Response<Persona> response;

		try {

			List<Persona> list = service.consultar(persona);
			List<String> messages = new ArrayList<>();
			messages.add("Personas consultadas exitosamente");

			response = new Response<>(list,messages);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
		}

		return new ResponseEntity<>(response,statusCode);
	}

	@GetMapping
	public ResponseEntity<Response<Persona>> listAll() {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		Response<Persona> response;

		try {
			List<String> messages = new ArrayList<>();
			List<Persona> list = service.consultarTodas();

			if (!list.isEmpty()) {
				messages.add("Personas consultadas exitosamente");

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				messages.add("No hay personas para consular");
			}

			response = new Response<>(list,messages);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
		}

		return new ResponseEntity<>(response,statusCode);
	}

	@PutMapping
	public ResponseEntity<Response<Persona>> update(@RequestBody Persona persona) {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		var response = new Response<Persona>();

		try {
			service.editar(persona);
			response.getMessages().add("Se ha cambiado la informacion de la persona satisfactoriamente");

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido cambiar la informacion");
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@DeleteMapping
	public ResponseEntity<Response<Persona>> drop(@PathVariable UUID id) {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		var response = new Response<Persona>();

		try {
			service.eliminar(id);
			response.getMessages().add("La persona se ha podido eliminar satisfactoriamente");

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido eliminar a la persona");
		}

		return new ResponseEntity<>(response,statusCode);
	}

}
