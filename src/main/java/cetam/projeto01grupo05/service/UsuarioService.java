package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusUsuario;
import cetam.projeto01grupo05.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> autenticar(String email, String senha) {

        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha))
                .filter(usuario -> usuario.getStatus() == StatusUsuario.ATIVO);
    }

    public Optional<Usuario> atualizar(Long id, Usuario dados) {

        return usuarioRepository.findById(id).map(usuario -> {

            usuario.setNome(dados.getNome());
            usuario.setCpf(dados.getCpf());
            usuario.setEmail(dados.getEmail());
            usuario.setSenha(dados.getSenha());
            usuario.setTipoUsuario(dados.getTipoUsuario());
            usuario.setStatus(dados.getStatus());

            return usuarioRepository.save(usuario);
        });
    }

    public boolean deletar(Long id) {

        if (usuarioRepository.existsById(id)) {

            usuarioRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public void alterarStatus(Long id, StatusUsuario status) {

        Usuario usuario = buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        usuario.setStatus(status);

        usuarioRepository.save(usuario);
    }
}
