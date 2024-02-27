package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.busisness.facade.PublicacionFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.PublicacionFacadeImpl;
import uco.doo.rugrats.uconnect.dto.PublicacionDTO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("uconnect/api/v1/publicacion")
public final class PublicacionController {
	
	
	
	@GetMapping("/dummy")
	public PublicacionDTO dummy() {
		return PublicacionDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<PublicacionDTO>> list() {
		var dto = PublicacionDTO.create();
		PublicacionFacade facade = new PublicacionFacadeImpl();
		List<PublicacionDTO> list = facade.listar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Publicaciones consultadas exitosamente");
		
		Response<PublicacionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public PublicacionDTO listById(@PathVariable UUID id) {
		return PublicacionDTO.create().setIdentificador(id);
	}
	
}
