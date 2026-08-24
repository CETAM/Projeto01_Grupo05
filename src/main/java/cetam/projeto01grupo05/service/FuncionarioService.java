package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Funcionario;
import cetam.projeto01grupo05.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> atualizar(Long id, Funcionario dados) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(dados.getNome());
            funcionario.setCpf(dados.getCpf());
            funcionario.setEmail(dados.getEmail());
            funcionario.setSenha(dados.getSenha());
            funcionario.setTipoUsuario(dados.getTipoUsuario());
            funcionario.setStatus(dados.getStatus());
            funcionario.setCargo(dados.getCargo());

            return funcionarioRepository.save(funcionario);
        });
    }

    public boolean deletar(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}