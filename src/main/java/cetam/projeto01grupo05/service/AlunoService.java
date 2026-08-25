package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Aluno;
import cetam.projeto01grupo05.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> atualizar(Long id, Aluno dados) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(dados.getNome());
            aluno.setCpf(dados.getCpf());
            aluno.setEmail(dados.getEmail());
            aluno.setSenha(dados.getSenha());
            aluno.setTipoUsuario(dados.getTipoUsuario());
            aluno.setStatus(dados.getStatus());
            aluno.setMatricula(dados.getMatricula());
            aluno.setCurso(dados.getCurso());
            aluno.setResponsavel(dados.getResponsavel());

            return alunoRepository.save(aluno);
        });
    }

    public boolean deletar(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}