package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.service.EmprestimoService;
import cetam.projeto01grupo05.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;
    private final UsuarioService usuarioService;

    // Construtor atualizado com os dois Services
    public EmprestimoController(EmprestimoService emprestimoService, UsuarioService usuarioService) {
        this.emprestimoService = emprestimoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("emprestimo", new Emprestimo());

        // Puxa a lista de usuários para o menu Select no HTML
        model.addAttribute("listaUsuarios", usuarioService.listarTodos());

        if (usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            model.addAttribute("emprestimos", emprestimoService.listarTodos());
        } else {
            model.addAttribute("emprestimos", emprestimoService.listarPorUsuario(usuario));
        }

        return "emprestimo-form";
    }

    @GetMapping("/novo")
    public String novo(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (!usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/emprestimos";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("emprestimos", emprestimoService.listarTodos());

        // Passa a lista também para o modo de "novo" empréstimo
        model.addAttribute("listaUsuarios", usuarioService.listarTodos());

        return "emprestimo-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Emprestimo emprestimo, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null || !usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/login";
        }

        emprestimoService.salvar(emprestimo);
        redirectAttributes.addFlashAttribute("sucesso", "Empréstimo registrado com sucesso!");

        return "redirect:/emprestimos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null || !usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("emprestimo", emprestimoService.buscarPorId(id).orElseThrow());
        model.addAttribute("emprestimos", emprestimoService.listarTodos());

        // Passa a lista também para quando for editar
        model.addAttribute("listaUsuarios", usuarioService.listarTodos());

        return "emprestimo-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null || !usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/login";
        }

        emprestimoService.deletar(id);
        redirectAttributes.addFlashAttribute("sucesso", "Empréstimo excluído do sistema!");

        return "redirect:/emprestimos";
    }
}