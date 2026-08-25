    package cetam.projeto01grupo05.controller;

    import cetam.projeto01grupo05.model.Livro;
    import cetam.projeto01grupo05.service.LivroService;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/livros")
    public class LivroController {

        private final LivroService livroService;

        public LivroController(LivroService livroService) {
            this.livroService = livroService;
        }

        @GetMapping
        public String listar(Model model) {
            model.addAttribute("livros", livroService.listarTodos());
            return "livros";
        }

        @GetMapping("/novo")
        public String novo(Model model) {
            model.addAttribute("livro", new Livro());
            return "livro-form";
        }

        @PostMapping("/salvar")
        public String salvar(@ModelAttribute Livro livro) {
            livroService.salvar(livro);
            return "redirect:/livros";
        }

        @GetMapping("/editar/{id}")
        public String editar(@PathVariable Long id, Model model) {
            model.addAttribute("livro", livroService.buscarPorId(id).orElseThrow());
            return "livro-form";
        }

        @GetMapping("/excluir/{id}")
        public String excluir(@PathVariable Long id) {
            livroService.deletar(id);
            return "redirect:/livros";
        }
    }