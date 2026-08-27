package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> listarPorUsuario(Usuario usuario) {
        return emprestimoRepository.findByUsuario(usuario);
    }

    public Emprestimo salvar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    public void deletar(Long id) {
        emprestimoRepository.deleteById(id);
    }
}