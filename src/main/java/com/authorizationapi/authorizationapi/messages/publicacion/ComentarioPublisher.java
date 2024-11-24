package com.authorizationapi.authorizationapi.messages.publicacion;

import com.authorizationapi.authorizationapi.config.RabbitConfig;
import com.authorizationapi.authorizationapi.domain.publicacion.Comentario;
import com.authorizationapi.authorizationapi.domain.publicacion.Publicacion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public HttpStatus comentar(Comentario comentario) {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.COMENTARIO_EXCHANGE, "comentar_routing_key", comentario, ParameterizedTypeReference.forType(HttpStatus.class));
    }
    public List<Comentario> listarPorPublicacion(Publicacion publicacion) {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.COMENTARIO_EXCHANGE, "listar_por_publicacion_routing_key", publicacion, ParameterizedTypeReference.forType(List.class));
    }
}