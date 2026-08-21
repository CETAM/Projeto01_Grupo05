package cetam.projeto01grupo05.service;

import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Aluno;
import cetam.projeto01grupo05.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvar(Aluno aluno) { return alunoRepository.save(aluno); }
    public Aluno buscarPorMatricula(String matricula) { return alunoRepository.findByMatricula(matricula); }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public List<Aluno> listarTodos() {
        return null;
    }

    public Remapper atualizar(Long id, Aluno dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}