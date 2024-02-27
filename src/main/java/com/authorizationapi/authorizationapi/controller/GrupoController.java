package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.busisness.facade.GrupoFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.GrupoFacadeImpl;
import uco.doo.rugrats.uconnect.dto.GrupoDTO;

@RestController
@RequestMapping("uconnect/api/v1/grupo")
public final class GrupoController {
	
	
	
	@GetMapping("/dummy")
	public GrupoDTO dummy() {
		return GrupoDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<GrupoDTO>> list(@RequestBody GrupoDTO dto) {
		GrupoFacade facade = new GrupoFacadeImpl();
		List<GrupoDTO> list = facade.buscar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Grupos consultados exitosamente");
		
		Response<GrupoDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public GrupoDTO listById(@PathVariable UUID id) {
		return GrupoDTO.create().setIdentificador(id);
	}
	
}
