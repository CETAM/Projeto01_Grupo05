package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Aluno {
    private String matricula;
    private String curso;
    private long id_responsavel;

    public Aluno() {}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public long getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(long id_responsavel) {
        this.id_responsavel = id_responsavel;
    }
}
