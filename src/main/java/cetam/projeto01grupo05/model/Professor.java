package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Professor extends Usuario {

    @Column(name = "matricula", nullable = false, unique = true, length = 20)
    private String matricula;

    @Column(name = "departamento", nullable = false, length = 100)
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