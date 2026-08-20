package cetam.projeto01grupo05.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devolucao")
public class Devolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucao")
    private Long idDevolucao;

    @Column(name = "data_devolucao", nullable = false)
    private LocalDateTime dataDevolucao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emprestimo", nullable = false, unique = true)
    private Emprestimo emprestimo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    public Devolucao() {
    }

    public Long getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(Long idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}