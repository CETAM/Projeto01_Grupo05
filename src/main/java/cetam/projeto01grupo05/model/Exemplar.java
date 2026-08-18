package cetam.projeto01grupo05.model;

import cetam.projeto01grupo05.model.enums.StatusExemplar;
import jakarta.persistence.*;

@Entity
@Table(name = "exemplar")
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_exemplar;
    private String codigo_exemplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusExemplar status = StatusExemplar.DISPONIVEL;

    private String localizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    public Exemplar() {}

    public Long getId_exemplar() {
        return id_exemplar;
    }

    public void setId_exemplar(Long id_exemplar) {
        this.id_exemplar = id_exemplar;
    }

    public String getCodigo_exemplar() {
        return codigo_exemplar;
    }

    public void setCodigo_exemplar(String codigo_exemplar) {
        this.codigo_exemplar = codigo_exemplar;
    }

    public StatusExemplar getStatus() {
        return status;
    }

    public void setStatus(StatusExemplar status) {
        this.status = status;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
