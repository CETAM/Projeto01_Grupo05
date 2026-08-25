package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> atualizar(Long id, Livro dados) {
        return livroRepository.findById(id).map(livro -> {
            livro.setCodigo(dados.getCodigo());
            livro.setTitulo(dados.getTitulo());
            livro.setIsbn(dados.getIsbn());
            livro.setAno(dados.getAno());
            livro.setCategoria(dados.getCategoria());
            livro.setEditora(dados.getEditora());
            livro.setAutores(dados.getAutores());

            return livroRepository.save(livro);
        });
    }

    public boolean deletar(Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
