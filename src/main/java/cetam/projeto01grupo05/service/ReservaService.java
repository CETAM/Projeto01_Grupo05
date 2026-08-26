package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusReserva;
import cetam.projeto01grupo05.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }

    public Reserva salvar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public boolean deletar(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Reserva criarReserva(Usuario usuario, Livro livro) {
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setLivro(livro);
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setStatus(StatusReserva.PENDENTE);
        return reservaRepository.save(reserva);
    }
}