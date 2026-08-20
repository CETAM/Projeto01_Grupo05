package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusUsuario;
import cetam.projeto01grupo05.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() { return usuarioRepository.findAll(); }
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public void alterarStatus(Long id, StatusUsuario status) {
        Usuario u = buscarPorId(id);
        u.setStatus(status);
        usuarioRepository.save(u);
    }
}