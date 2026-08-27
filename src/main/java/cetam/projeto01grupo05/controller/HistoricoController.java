package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.service.EmprestimoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/historico")
public class HistoricoController {

    private final EmprestimoService emprestimoService;

    public HistoricoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public String verHistorico(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        // Trava de segurança: se não estiver logado, manda pro login
        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        // Regra de perfil baseada no DRS:
        // Funcionário vê todo o histórico do sistema; Aluno/Professor vê apenas o seu histórico pessoal.
        if (usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            model.addAttribute("historicos", emprestimoService.listarTodos());
        } else {
            model.addAttribute("historicos", emprestimoService.listarPorUsuario(usuario));
        }

        return "historico";
    }
}