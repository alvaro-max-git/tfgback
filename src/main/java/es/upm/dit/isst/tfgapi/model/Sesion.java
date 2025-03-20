package es.upm.dit.isst.tfgapi.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
public class Sesion {

    @Id @GeneratedValue(strategy= GenerationType.AUTO) private Long id;
    @Future private Date fecha;
    private String lugar;
    @Size(min = 3, max = 3) private List <@Email @NotEmpty String> tribunal;
    @JsonIgnore @OneToMany(mappedBy = "sesion") private List <@Valid TFG> tfgs;

    public Sesion() {
    }

    public Sesion(@Future Date fecha, String lugar, 
        @Size(min = 3, max = 3) List<@Email @NotEmpty String> tribunal, 
            List<@Valid TFG> tfgs) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.tribunal = tribunal;
        this.tfgs = tfgs;
    }

    /*
     * Getters y Setters de la clase Sesion
     */

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public List<String> getTribunal() {
        return tribunal;
    }
    public void setTribunal(List<String> tribunal) {
        this.tribunal = tribunal;
    }

    public List<TFG> getTfgs() {
        return tfgs;
    }

    @JsonGetter("tfgs")
    public String[] getEmailsTfgs() {
        if (tfgs != null) {
            return tfgs.stream().map(TFG::getAlumno).toArray(String[]::new);
        } else {
            return new String[0];
        }
    }

    @JsonProperty("tfgs")
    public void setTfgs(List<TFG> tfgs) {
        this.tfgs = tfgs;
    }

    /*
     * Método toString de la clase Sesion. Omitimos las referencias a propiedad tfgs
     */

    @Override
    public String toString() {
        return "Sesion [id=" + id + ", fecha=" + fecha + ", lugar=" + lugar + ", tribunal=" + tribunal + "]";
    }

    /*
     * Método hashCode de la clase Sesion. Usamos solo @id
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /*
     * Método equals de la clase Sesion. Usamos solo @id
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sesion other = (Sesion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
