package com.authorizationapi.authorizationapi.controller.estructura;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.service.estructura.EstructuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("authorization/api/v1")
public final class EstructuraController {
    @Autowired
    private final EstructuraService service = new EstructuraService();
    @PostMapping("/estructura")
    public ResponseEntity<Response<Estructura>> crearNueva(@RequestBody Estructura estructura) {


        var statusCode = HttpStatus.OK;
        Response<Estructura> response = new Response<>();

        try {
            service.crearNueva(estructura);
            response.getMessages().add("La estructura se ha registrado exitosamente");

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido registrar la estructura");

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/estructura")
    public ResponseEntity<Response<Estructura>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<Estructura> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Estructura> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add("Estructuras consultadas exitosamente");

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add("No hay estructuras para consultar");
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/estructuraActiva/{identificador}")
    public ResponseEntity<Response<Estructura>> cambiarEstado(@PathVariable UUID identificador, @RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            service.cambiarEstado(identificador, estructura);
            response.getMessages().add("Se ha cambiado el estado de la estructura satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar el estado");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/estructura/{identificador}")
    public ResponseEntity<Response<Estructura>> editar(@PathVariable UUID identificador,@RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            service.cambiarNombre(identificador, estructura);
            response.getMessages().add("Se ha cambiado el nombre de la estructura satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar los datos de la estructura");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/estructura")
    public ResponseEntity<Response<Estructura>> eliminar(@RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            service.eliminar(estructura);
            response.getMessages().add("La estructura se ha podido eliminar satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido eliminar la estructura");
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
