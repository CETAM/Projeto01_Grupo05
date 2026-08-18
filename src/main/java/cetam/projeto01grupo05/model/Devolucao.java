package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "devolucao")
public class Devolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_devolucao;
    private LocalDateTime data_devolucao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emprestimo", nullable = false, unique = true)
    private Emprestimo emprestimo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;

    public Devolucao() {}

    public int getId_devolucao() {
        return id_devolucao;
    }

    public void setId_devolucao(int id_devolucao) {
        this.id_devolucao = id_devolucao;
    }

    public LocalDateTime getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDateTime data_devolucao) {
        this.data_devolucao = data_devolucao;
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
