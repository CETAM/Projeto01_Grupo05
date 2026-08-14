package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aluno")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Aluno {
    private String matricula;
    private String curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsavel")
    private Aluno responsavel;

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

    public Aluno getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Aluno responsavel) {
        this.responsavel = responsavel;
    }
}
