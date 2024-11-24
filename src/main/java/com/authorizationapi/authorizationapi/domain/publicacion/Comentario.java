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
@Table(name = "comentario")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comentario {
    private static final Comentario PADRE = null;
    @Id
    @Column(name = "identificador", nullable = false)
    private UUID identificador;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"publicacion\"")
    private Publicacion publicacion;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"comentarioPadre\"")
    private Comentario comentarioPadre;
    @Column(name = "fechaComentario")
    private Date fechaComentario;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"autor\"")
    private ParticipanteGrupo autor;
    @Column(name = "contenido", nullable = false)
    private String contenido;
    @Column(name = "tienePadre")
    private boolean tienePadre;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    public Comentario() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
        setContenido(UtilText.getDefaultValue());
        setFechaComentario(UtilDate.getNowValue());
        setAutor(ParticipanteGrupo.create());
        setComentarioPadre(PADRE);
    }
    @JsonCreator
    public Comentario(
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("comentarioPadre") Comentario comentarioPadre,
            @JsonProperty("activo") boolean activo,
            @JsonProperty("autor") ParticipanteGrupo autor,
            @JsonProperty("tienePadre") boolean tienePadre) {

        setIdentificador(identificador);
        setTienePadre(tienePadre);
        setContenido(contenido);
        setComentarioPadre(comentarioPadre);
        setFechaComentario(UtilDate.getNowValue());
        setAutor(autor);
        setActivo(activo);
        setTienePadre(tienePadre);
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Comentario getComentarioPadre() {
        return comentarioPadre;
    }

    public void setComentarioPadre(Comentario comentarioPadre) {
        if (isTienePadre()) {
            this.comentarioPadre = UtilObject.getDefault(comentarioPadre, Comentario.create());
        } else {
            this.comentarioPadre = PADRE;
        }
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public ParticipanteGrupo getAutor() {
        return autor;
    }

    public void setAutor(ParticipanteGrupo autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isTienePadre() {
        return tienePadre;
    }

    public void setTienePadre(boolean tienePadre) {
        this.tienePadre = UtilBoolean.isNull(tienePadre);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public static Comentario create(){
        return new Comentario();
    }
}
