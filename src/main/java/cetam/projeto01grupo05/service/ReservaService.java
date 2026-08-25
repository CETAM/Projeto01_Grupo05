package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusReserva;
import cetam.projeto01grupo05.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva criarReserva(Usuario usuario, Livro livro) {

        Reserva reserva = new Reserva();

        reserva.setUsuario(usuario);
        reserva.setLivro(livro);
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setStatus(StatusReserva.PENDENTE);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }
}