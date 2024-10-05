package com.authorizationapi.authorizationapi.messages.publicacion;

import com.authorizationapi.authorizationapi.config.RabbitConfig;
import com.authorizationapi.authorizationapi.domain.estructura.Grupo;
import com.authorizationapi.authorizationapi.domain.publicacion.Publicacion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PublicacionPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public HttpStatus crearNueva(Publicacion publicacion){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "publicar_key", publicacion, ParameterizedTypeReference.forType(HttpStatus.class));
    }
    public List<Publicacion> listarPorGrupo(Grupo grupo) {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "listar_key", grupo, ParameterizedTypeReference.forType(List.class));
    }
    public List<Publicacion> consultarTodas() {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "listar_todas_key", Publicacion.create(), ParameterizedTypeReference.forType(List.class));
    }
    public HttpStatus editar(Publicacion publicacion){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "editar_key", publicacion, ParameterizedTypeReference.forType(HttpStatus.class));
    }

    public HttpStatus cambiarEstado(UUID identificador){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "cambiar_estado_key", identificador, ParameterizedTypeReference.forType(HttpStatus.class));
    }

    public HttpStatus eliminar(Publicacion publicacion){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "eliminar_key", publicacion, ParameterizedTypeReference.forType(HttpStatus.class));
    }

}
