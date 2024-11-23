package com.authorizationapi.authorizationapi.domain.publicacion;

import com.authorizationapi.authorizationapi.crosscutting.utils.*;

import com.authorizationapi.authorizationapi.domain.estructura.ParticipanteGrupo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "Publicacion")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Publicacion {
    @Id
    @Column(name = "identificador", nullable = false)
    private UUID identificador;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion;
    @ManyToOne
    @JoinColumn(name = "participanteGrupo")
    private ParticipanteGrupo autor;


    public Publicacion() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
        setContenido(UtilText.getDefaultValue());
        setFechaPublicacion(UtilDate.getDefaultValueDate());
        setAutor(ParticipanteGrupo.create());

    }
    @JsonCreator
    public Publicacion(
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("titulo") String titulo,
            @JsonProperty("activo") boolean activo,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("fechaPublicacion") Date fechaPublicacion,
            @JsonProperty("autor") ParticipanteGrupo autor

    ) {
        setIdentificador(identificador);
        setTitulo(titulo);
        setActivo(activo);
        setContenido(contenido);
        setFechaPublicacion(fechaPublicacion);
        setAutor(autor);

    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Publicacion setIdentificador(UUID identificador) {
        this.identificador = UtilObject.getDefault(identificador,UtilUUID.getDefaultValue());
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Publicacion setTitulo(String titulo) {
        this.titulo = UtilObject.getDefault(titulo,UtilText.getDefaultValue());
        return this;
    }


    public String getContenido() {
        return contenido;
    }

    public Publicacion setContenido(String contenido) {
        this.contenido = UtilObject.getDefault(contenido,UtilText.getDefaultValue());
        return this;
    }

    public boolean isActivo() {
        return activo;
    }

    public Publicacion setActivo(boolean activo) {
        this.activo = UtilObject.getDefault(activo,UtilBoolean.getDefaultValue());
        return this;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Publicacion setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public ParticipanteGrupo getAutor() {
        return autor;
    }

    public Publicacion setAutor(ParticipanteGrupo autor) {
        this.autor = UtilObject.getDefault(autor,ParticipanteGrupo.create());
        return this;
    }

    public static Publicacion create() {
        return new Publicacion();
    }

}

