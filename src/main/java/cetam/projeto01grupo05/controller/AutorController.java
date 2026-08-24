package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Autor;
import cetam.projeto01grupo05.service.AutorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", autorService.listarTodos());
        return "autores";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Autor autor) {
        autorService.salvar(autor);
        return "redirect:/autores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("autor", autorService.buscarPorId(id).orElseThrow());
        return "autores-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        autorService.deletar(id);
        return "redirect:/autores";
    }
}