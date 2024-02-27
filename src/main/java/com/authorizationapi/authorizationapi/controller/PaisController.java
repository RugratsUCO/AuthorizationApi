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
import uco.doo.rugrats.uconnect.busisness.facade.PaisFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.PaisFacadeImpl;
import uco.doo.rugrats.uconnect.dto.PaisDTO;

@RestController
@RequestMapping("uconnect/api/v1/pais")
public final class PaisController {
	
	
	@GetMapping("/dummy")
	public PaisDTO dummy() {
		return PaisDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<PaisDTO>> list(@RequestBody PaisDTO dto) {
		PaisFacade facade = new PaisFacadeImpl();
		List<PaisDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Paises de teléfonos consultados exitosamente");
		
		Response<PaisDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public PaisDTO listById(@PathVariable UUID id) {
		return PaisDTO.create().setIdentificador(id);
	}
	
}
