package cetam.projeto01grupo05.service;

import cetam.projeto01grupo05.model.Categoria;
import cetam.projeto01grupo05.model.Editora;
import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.repository.CategoriaRepository;
import cetam.projeto01grupo05.repository.EditoraRepository;
import cetam.projeto01grupo05.repository.ExemplarRepository;
import cetam.projeto01grupo05.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaRepository categoriaRepository;
    private final EditoraRepository editoraRepository;
    private final ExemplarRepository exemplarRepository;

    public LivroService(LivroRepository livroRepository, CategoriaRepository categoriaRepository, EditoraRepository editoraRepository, ExemplarRepository exemplarRepository) {
        this.livroRepository = livroRepository;
        this.categoriaRepository = categoriaRepository;
        this.editoraRepository = editoraRepository;
        this.exemplarRepository = exemplarRepository;
    }

    public Livro salvar(Livro livro) {
        if (livro.getCategoria() != null && livro.getCategoria().getNome() != null && !livro.getCategoria().getNome().trim().isEmpty()) {
            String nomeCategoria = livro.getCategoria().getNome().trim();
            Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nomeCategoria)
                    .orElseGet(() -> {
                        Categoria novaCat = new Categoria();
                        novaCat.setNome(nomeCategoria);
                        return categoriaRepository.save(novaCat);
                    });
            livro.setCategoria(categoria);
        }

        if (livro.getEditora() != null && livro.getEditora().getNome() != null && !livro.getEditora().getNome().trim().isEmpty()) {
            String nomeEditora = livro.getEditora().getNome().trim();
            Editora editora = editoraRepository.findByNomeIgnoreCase(nomeEditora)
                    .orElseGet(() -> {
                        Editora novaEd = new Editora();
                        novaEd.setNome(nomeEditora);
                        return editoraRepository.save(novaEd);
                    });
            livro.setEditora(editora);
        }

        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public int contarExemplaresDisponiveis(Long idLivro) {
        List<Exemplar> disponiveis = exemplarRepository.findByLivroIdLivroAndStatus(idLivro, StatusExemplar.DISPONIVEL);
        return disponiveis.size();
    }

    public Optional<Livro> atualizar(Long id, Livro dados) {
        return livroRepository.findById(id).map(livro -> {
            livro.setCodigo(dados.getCodigo());
            livro.setTitulo(dados.getTitulo());
            livro.setIsbn(dados.getIsbn());
            livro.setAno(dados.getAno());
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