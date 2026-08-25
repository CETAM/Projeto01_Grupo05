package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Devolucao;
import cetam.projeto01grupo05.service.DevolucaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/devolucoes")
public class DevolucaoController {

    private final DevolucaoService devolucaoService;

    public DevolucaoController(DevolucaoService devolucaoService) {
        this.devolucaoService = devolucaoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("devolucoes", devolucaoService.listarTodos());
        return "devolucoes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("devolucao", new Devolucao());
        return "devolucao-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Devolucao devolucao) {
        devolucaoService.salvar(devolucao);
        return "redirect:/devolucoes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("devolucao", devolucaoService.buscarPorId(id).orElseThrow());
        return "devolucao-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        devolucaoService.deletar(id);
        return "redirect:/devolucoes";
    }
}