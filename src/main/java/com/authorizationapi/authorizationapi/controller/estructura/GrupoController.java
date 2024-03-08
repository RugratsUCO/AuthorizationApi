package com.authorizationapi.authorizationapi.controller.estructura;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.domain.estructura.Grupo;
import com.authorizationapi.authorizationapi.service.estructura.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authorization/api/v1")
public final class GrupoController {

    @Autowired
    private final GrupoService service = new GrupoService();
    @PostMapping("/grupo")
    public ResponseEntity<Response<Grupo>> crear(@RequestBody Grupo grupo) {


        var statusCode = HttpStatus.OK;
        Response<Grupo> response = new Response<>();

        try {
            service.crear(grupo);
            response.getMessages().add("El grupo se ha registrado exitosamente");

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido registrar el grupo");

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/grupo")
    public ResponseEntity<Response<Grupo>> buscar() {

        var statusCode = HttpStatus.OK;
        Response<Grupo> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Grupo> list = service.buscar();

            if (!list.isEmpty()) {
                messages.add("Grupos consultados exitosamente");

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add("No hay grupos para consultar");
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/grupoActivo/{identificador}")
    public ResponseEntity<Response<Grupo>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Grupo grupo) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Grupo>();

        try {
            service.cambiarEstado(identificador, grupo);
            response.getMessages().add("Se ha cambiado el estado del grupo satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar el estado");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/grupo/{identificador}")
    public ResponseEntity<Response<Grupo>> editar(@PathVariable UUID identificador,@RequestBody Grupo grupo) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Grupo>();

        try {
            service.editar(identificador, grupo);
            response.getMessages().add("Se han cambiado los datos del grupo satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar los datos del grupo");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/grupo/{identificador}")
    public ResponseEntity<Response<Grupo>> eliminar(@RequestBody Grupo grupo) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Grupo>();

        try {
            service.eliminar(grupo);
            response.getMessages().add("El grupo se ha podido eliminar satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido eliminar al grupo");
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
