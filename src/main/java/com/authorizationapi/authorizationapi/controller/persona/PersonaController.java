package com.authorizationapi.authorizationapi.controller.persona;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationException;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.service.persona.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authorization/api/v1")
public final class PersonaController {

	private final PersonaService service;

	private final Logger log = LoggerFactory.getLogger(PersonaController.class);

	public PersonaController(PersonaService service){
		this.service = service;
	}
	@PostMapping("/persona")
	public ResponseEntity<Response<Persona>> create(@RequestBody Persona persona) {


		var statusCode = HttpStatus.OK;
		Response<Persona> response = new Response<>();

		try {
			service.registrar(persona);
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_REGISTRADA_FINAL);

		} catch (AuthorizationException exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_NO_REGISTRADA_FINAL);
			log.error(exception.getTechnicalMessage());
		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(exception.getMessage());
			log.error(UtilMessagesController.ControllerPersona.PERSONA_NO_REGISTRADA_TECNICO);
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
				messages.add(UtilMessagesController.ControllerPersona.PERSONAS_CONSULTADAS_FINAL);

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_FINAL);
				log.error((UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_TECNICO));
			}

			response = new Response<>(list,messages);

		} catch (AuthorizationException exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_NO_CONSULTADA_INTERNO_FINAL);
			log.error(exception.getTechnicalMessage());
		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(exception.getMessage());
			log.error(UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_TECNICO);
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@GetMapping("/persona")
	public ResponseEntity<Response<Persona>> listAll() {

		var statusCode = HttpStatus.OK;
		Response<Persona> response;

		List<String> messages;
		try {
			messages = new ArrayList<>();
			List<Persona> list = service.consultarTodas();

			if (!list.isEmpty()) {
				messages.add(UtilMessagesController.ControllerPersona.PERSONAS_CONSULTADAS_FINAL);

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				messages.add(UtilMessagesController.ControllerPersona.NO_PERSONAS_PARA_CONSULTAR_FINAL);
			}

			response = new Response<>(list, messages);

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_INTERNO_FINAL);
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
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_ACTUALIZADA_FINAL);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_NO_ACTUALIZADA_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}

	@PatchMapping("/persona/{identificador}")
	public ResponseEntity<Response<Persona>> changeStatus(@PathVariable UUID identificador) {
		var statusCode = HttpStatus.OK;
		var response = new Response<Persona>();
		//UUID identificadorUUID = UtilUUID.geneateUUIDFromString(identificador);

		try {
			service.cambiarEstado(identificador);
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_ACTUALIZADA_FINAL);

		}catch (Exception exception) {
			log.error(exception.getMessage());
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_NO_ELIMINADA_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}

	@DeleteMapping("/persona")
	public ResponseEntity<Response<Persona>> drop(@RequestBody Persona persona) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Persona>();

		try {
			service.eliminar(persona);
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_ELIMINADA_FINAL);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_NO_ELIMINADA_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}

}