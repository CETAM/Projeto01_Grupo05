package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Editora;
import cetam.projeto01grupo05.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public Editora salvar(Editora editora) {
        return editoraRepository.save(editora);
    }

    public List<Editora> listarTodos() {
        return editoraRepository.findAll();
    }

    public Optional<Editora> buscarPorId(Long id) {
        return editoraRepository.findById(id);
    }

    public Optional<Editora> atualizar(Long id, Editora dados) {
        return editoraRepository.findById(id).map(editora -> {
            editora.setNome(dados.getNome());
            editora.setEndereco(dados.getEndereco());
            editora.setTelefone(dados.getTelefone());
            editora.setEmail(dados.getEmail());

            return editoraRepository.save(editora);
        });
    }

    public boolean deletar(Long id) {
        if (editoraRepository.existsById(id)) {
            editoraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}