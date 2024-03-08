package com.authorizationapi.authorizationapi.controller.persona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilMessages;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.service.persona.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public final class PersonaController {
	@Autowired
	private final PersonaService service = new PersonaService();

	private final Logger log = LoggerFactory.getLogger(PersonaController.class);

	@PostMapping("/persona")
	public ResponseEntity<Response<Persona>> create(@RequestBody Persona persona) {


		var statusCode = HttpStatus.OK;
		Response<Persona> response = new Response<>();

		try {
			service.registrar(persona);
			response.getMessages().add(UtilMessages.ControllerPersona.PERSONA_REGISTRADA_FINAL);

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessages.ControllerPersona.PERSONA_NO_REGISTRADA_FINAL);
			log.error(UtilMessages.ControllerPersona.PERSONA_NO_REGISTRADA_TECNICO);
		}
		return new ResponseEntity<>(response, statusCode);
	}

	@GetMapping("/personaById")
	public ResponseEntity<Response<Persona>> listById(@RequestBody Persona persona) {

		var statusCode = HttpStatus.OK;
		Response<Persona> response = new Response<>();

		try {
			List<String> messages = new ArrayList<>();
			Persona personaConsultada = service.consultar(persona);

			List<Persona> list = List.of(personaConsultada);
			if (personaConsultada != null) {
				messages.add(UtilMessages.ControllerPersona.PERSONAS_CONSULTADAS_FINAL);

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				response.getMessages().add(UtilMessages.ControllerPersona.PERSONAS_NO_CONSULTADAS_FINAL);
				log.error((UtilMessages.ControllerPersona.PERSONAS_NO_CONSULTADAS_TECNICO));
			}

			response = new Response<>(list,messages);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add(UtilMessages.ControllerPersona.PERSONA_NO_CONSULTADA_INTERNO_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@GetMapping("/persona")
	public ResponseEntity<Response<Persona>> listAll() {

		var statusCode = HttpStatus.OK;
		Response<Persona> response;

		List<String> messages = null;
		try {
			messages = new ArrayList<>();
			List<Persona> list = service.consultarTodas();

			if (!list.isEmpty()) {
				messages.add("Personas consultadas exitosamente");

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				messages.add("No hay personas para consular");
			}

			response = new Response<>(list, messages);

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");

			log.error(exception.getMessage());
		}

		return new ResponseEntity<>(response, statusCode);
	}

	@PutMapping("/persona/{identificador}")
	public ResponseEntity<Response<Persona>> update(@PathVariable UUID identificador,@RequestBody Persona persona) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Persona>();

		try {
			service.editar(identificador,persona);
			response.getMessages().add("Se ha cambiado la informacion de la persona satisfactoriamente");

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido cambiar la informacion");
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@DeleteMapping("/persona")
	public ResponseEntity<Response<Persona>> drop(@RequestBody Persona persona) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Persona>();

		try {
			service.eliminar(persona);
			response.getMessages().add("La persona se ha podido eliminar satisfactoriamente");

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido eliminar a la persona");
		}

		return new ResponseEntity<>(response,statusCode);
	}

}
