package com.authorizationapi.authorizationapi.controller.estructura;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
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
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_CREADO_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_NO_CREADO_FINAL);

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
                messages.add(UtilMessagesController.ControllerGrupo.GRUPOS_CONSULTADOS_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerGrupo.GRUPOS_NO_CONSULTADOS_FINAL);
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPOS_NO_CONSULTADOS_INTERNO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/grupoActivo/{identificador}")
    public ResponseEntity<Response<Grupo>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Grupo grupo) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Grupo>();

        try {
            service.cambiarEstado(identificador, grupo);
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_ESTADO_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_ESTADO_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/grupo/{identificador}")
    public ResponseEntity<Response<Grupo>> editar(@PathVariable UUID identificador,@RequestBody Grupo grupo) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Grupo>();

        try {
            service.editar(identificador, grupo);
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_DATOS_EDITADOS_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_DATOS_NO_EDITADOS_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/grupo/{identificador}")
    public ResponseEntity<Response<Grupo>> eliminar(@RequestBody Grupo grupo) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Grupo>();

        try {
            service.eliminar(grupo);
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_ELIMINADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerGrupo.GRUPO_NO_ELIMINADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
