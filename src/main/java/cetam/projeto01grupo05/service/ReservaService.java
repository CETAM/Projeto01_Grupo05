package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.*;
import cetam.projeto01grupo05.model.enums.StatusReserva;
import cetam.projeto01grupo05.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva criarReserva(Usuario usuario, Livro livro) {
        Reserva res = new Reserva();
        res.setUsuario(usuario);
        res.setLivro(livro);
        res.setDataReserva(LocalDateTime.now());
        res.setStatus(StatusReserva.PENDENTE);
        return reservaRepository.save(res);
    }
}