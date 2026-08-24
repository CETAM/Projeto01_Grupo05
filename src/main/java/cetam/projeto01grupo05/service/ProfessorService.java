package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Professor;
import cetam.projeto01grupo05.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public Optional<Professor> atualizar(Long id, Professor dados) {
        return professorRepository.findById(id).map(professor -> {
            professor.setNome(dados.getNome());
            professor.setCpf(dados.getCpf());
            professor.setEmail(dados.getEmail());
            professor.setSenha(dados.getSenha());
            professor.setTipoUsuario(dados.getTipoUsuario());
            professor.setStatus(dados.getStatus());
            professor.setMatricula(dados.getMatricula());
            professor.setDepartamento(dados.getDepartamento());

            return professorRepository.save(professor);
        });
    }

    public boolean deletar(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
