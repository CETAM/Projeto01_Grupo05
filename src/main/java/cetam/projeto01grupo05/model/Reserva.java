package cetam.projeto01grupo05.model;


import cetam.projeto01grupo05.model.enums.StatusReserva;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private LocalDateTime data_reserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusReserva status = StatusReserva.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    public Reserva() {}

    public LocalDateTime getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(LocalDateTime data_reserva) {
        this.data_reserva = data_reserva;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
