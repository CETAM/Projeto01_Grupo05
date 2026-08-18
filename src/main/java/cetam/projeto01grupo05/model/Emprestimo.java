package cetam.projeto01grupo05.model;

import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_emprestimo;

    private LocalDateTime dataEmprestimo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEmprestimo status = StatusEmprestimo.ATIVO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplar", nullable = false)
    private Exemplar exemplar;

    public Emprestimo() {}

    public Long getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(Long id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
}
