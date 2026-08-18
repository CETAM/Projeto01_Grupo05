package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {
    private String matricula;
    private String departamento;

    public Professor() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
