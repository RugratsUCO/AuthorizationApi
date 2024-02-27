package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.api.validator.reaccion.CambiarReaccionValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.EliminarReaccionValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.ReaccionarValidation;
import uco.doo.rugrats.uconnect.busisness.facade.ReaccionFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.ReaccionFacadeImpl;
import uco.doo.rugrats.uconnect.crosscutting.exception.UconnectException;
import uco.doo.rugrats.uconnect.dto.ReaccionDTO;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

@RestController
@RequestMapping("uconnect/api/v1/reaccion")
public class ReaccionController {
	private Logger log = LoggerFactory.getLogger(ReaccionController.class);

	private ReaccionFacade facade;
	
	@GetMapping("/dummy")
	public ReaccionDTO dummy() {
		return ReaccionDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<ReaccionDTO>> list(@RequestParam ReaccionDTO dto) {
		facade = new ReaccionFacadeImpl();
		List<ReaccionDTO> list = facade.mostrar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Calificaciones consultadas exitosamente");
		
		Response<ReaccionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ReaccionDTO listById(@PathVariable UUID id) {
		return ReaccionDTO.create().setIdentificador(id);
	}
	@PostMapping
	public ResponseEntity<Response<ReaccionDTO>> create(@RequestParam ReaccionDTO dto) {
		facade = new ReaccionFacadeImpl();
		var statusCode = HttpStatus.OK;
		Response<ReaccionDTO> response = new Response<>();
		
		try {
			var result = ReaccionarValidation.validate(dto);
			if(result.getMessages().isEmpty()) {
				facade.reaccionar(dto);
				response.getMessages().add("La reacción fue registrada de forma satisfactoria");
			}else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}
		}catch (UconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat("-").concat(exception.getTechnicalMessage()),exception);

			
		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UconnectApiMessages.UNEXPECTED_USER_ERROR_MESSAGE);
			log.error(UconnectApiMessages.UNEXPECTED_TECHNYCAL_ERROR_MESSAGE);

		}
		
		return new ResponseEntity<>(response,statusCode);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Response<ReaccionDTO>> update(@RequestParam UUID id,@RequestBody ReaccionDTO dto) {
		facade = new ReaccionFacadeImpl();

		var statusCode = HttpStatus.OK;
		var response = new Response<ReaccionDTO>();
		
		try {
			dto.setIdentificador(id);
			var result = CambiarReaccionValidation.validate(dto);
			if(result.getMessages().isEmpty()) {
				facade.cambiarReaccion(dto);
				response.getMessages().add("El comentario fue modificado de forma satisfactoria");
			}else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}
		}catch (UconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat("-").concat(exception.getTechnicalMessage()),exception);

			
		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UconnectApiMessages.UNEXPECTED_USER_ERROR_MESSAGE);
			log.error(UconnectApiMessages.UNEXPECTED_TECHNYCAL_ERROR_MESSAGE);

		}
		
		return new ResponseEntity<>(response,statusCode);
	}
	@DeleteMapping
	public ResponseEntity<Response<ReaccionDTO>> drop(@PathVariable UUID id) {
		facade = new ReaccionFacadeImpl();
		var statusCode = HttpStatus.OK;
		var response = new Response<ReaccionDTO>();
		
		try {
			var result = EliminarReaccionValidation.validate(id);
			if(result.getMessages().isEmpty()) {
				facade.eliminar(id);
				response.getMessages().add("La reacción fue eliminada de forma satisfactoria");
			}else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
			}
		}catch (UconnectException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			log.error(exception.getType().toString().concat("-").concat(exception.getTechnicalMessage()),exception);

			
		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UconnectApiMessages.UNEXPECTED_USER_ERROR_MESSAGE);
			log.error(UconnectApiMessages.UNEXPECTED_TECHNYCAL_ERROR_MESSAGE);

		}
		
		return new ResponseEntity<>(response,statusCode);
	}
}
