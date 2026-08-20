package cetam.projeto01grupo05.service;

import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Professor;
import cetam.projeto01grupo05.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor salvar(Professor professor) { return professorRepository.save(professor); }

    public List<Professor> listarTodos() {
        return null;
    }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Professor dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}