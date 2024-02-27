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
import uco.doo.rugrats.uconnect.busisness.facade.TipoReaccionFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.TipoReaccionFacadeImpl;
import uco.doo.rugrats.uconnect.dto.TipoReaccionDTO;

@RestController
@RequestMapping("uconnect/api/v1/tiporeaccion")
public final class TipoReaccionController {
	
	
	
	@GetMapping("/dummy")
	public TipoReaccionDTO dummy() {
		return TipoReaccionDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<TipoReaccionDTO>> list(@RequestBody TipoReaccionDTO dto) {
		TipoReaccionFacade facade = new TipoReaccionFacadeImpl();
		List<TipoReaccionDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Tipos de reacción consultados exitosamente");
		
		Response<TipoReaccionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public TipoReaccionDTO listById(@PathVariable UUID id) {
		return TipoReaccionDTO.create().setIdentificador(id);
	}
	
}
