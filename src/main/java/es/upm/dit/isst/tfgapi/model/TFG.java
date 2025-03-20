package es.upm.dit.isst.tfgapi.model;

import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class TFG {

    @Id @Email private String alumno;
    @Email @NotEmpty private String tutor;
    @NotEmpty private String titulo;
    private String resumen;
    private Estado estado;

    //array de bytes donde se almacena el archivo con la memoria del TFG
    @JsonIgnore @Lob private byte[] memoria;
    @PositiveOrZero @DecimalMax("10.0") private Double calificacion;
    private Boolean matriculaHonor;

    //relacion con la entidad Sesion: un TFG tiene una sesion, una sesion tiene varios TFG.
    @ManyToOne private Sesion sesion;

    public TFG() {
    }

    /*
     * Constructor de la clase TFG
    */

    public TFG(@Email String alumno,
               @Email @NotEmpty String tutor,
               @NotEmpty String titulo,
               String resumen,
               Estado estado,
               byte[] memoria,
               @PositiveOrZero @DecimalMax("10.0") Double calificacion,
               Boolean matriculaHonor,
               Sesion sesion) {

        this.alumno = alumno;
        this.tutor = tutor;
        this.titulo = titulo;
        this.resumen = resumen;
        this.estado = estado;
        this.memoria = memoria;
        this.calificacion = calificacion;
        this.matriculaHonor = matriculaHonor;
        this.sesion = sesion;
    }



    /*
     * Getters y Setters
     */

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public byte[] getMemoria() {
        return memoria;
    }

    @JsonProperty
    public void setMemoria(byte[] memoria) {
        this.memoria = memoria;
    }

    //devuelve la URI de la memoria del TFG
    //Por simplicidad, usaremos una URL relativa al TFG, que nos permitirá usar siempre la misma.
    @JsonGetter
    public URI getDireccionMemoria() throws URISyntaxException {
        return new URI("./memoria");
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Boolean getMatriculaHonor() {
        return matriculaHonor;
    }

    public void setMatriculaHonor(Boolean matriculaHonor) {
        this.matriculaHonor = matriculaHonor;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    /*
     * Metodo toString
     */

    @Override
    public String toString() {
        return "TFG [alumno=" + alumno + ", calificacion=" + calificacion + ", estado=" + estado + ", matriculaHonor="
                + matriculaHonor + ", resumen=" + resumen + ", sesion=" + sesion + ", titulo=" + titulo + ", tutor="
                + tutor + "]";
    }


    /*
     * hashCode y equals
     */

    //hashCode solo utiliza el atributo @Id 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
        return result;
    }

    //equals solo utiliza el atributo @Id
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TFG other = (TFG) obj;
        if (alumno == null) {
            if (other.alumno != null)
                return false;
        } else if (!alumno.equals(other.alumno))
            return false;
        return true;
    }


}
