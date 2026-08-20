package cetam.projeto01grupo05.model;

import cetam.projeto01grupo05.model.enums.StatusExemplar;
import jakarta.persistence.*;

@Entity
@Table(name = "exemplar")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplar")
    private Long idExemplar;

    @Column(name = "codigo_exemplar", nullable = false, unique = true, length = 30)
    private String codigoExemplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusExemplar status = StatusExemplar.DISPONIVEL;

    @Column(name = "localizacao", length = 50)
    private String localizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    public Exemplar() {
    }

    public Long getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Long idExemplar) {
        this.idExemplar = idExemplar;
    }

    public String getCodigoExemplar() {
        return codigoExemplar;
    }

    public void setCodigoExemplar(String codigoExemplar) {
        this.codigoExemplar = codigoExemplar;
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