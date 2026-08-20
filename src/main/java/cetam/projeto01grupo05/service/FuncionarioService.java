package cetam.projeto01grupo05.service;


import aj.org.objectweb.asm.commons.Remapper;
import cetam.projeto01grupo05.model.Funcionario;
import cetam.projeto01grupo05.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario salvar(Funcionario funcionario) { return funcionarioRepository.save(funcionario); }

    public List<Funcionario> listarTodos() {
        return null;
    }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public Remapper atualizar(Long id, Funcionario dados) {
        return null;
    }

    public boolean deletar(Long id) {
        return false;
    }
}