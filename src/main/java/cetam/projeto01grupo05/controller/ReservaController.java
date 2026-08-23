package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarTodos());
        return "reservas";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("reserva", new Reserva());
        return "reserva-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Reserva reserva) {
        reservaService.salvar(reserva);
        return "redirect:/reservas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("reserva", reservaService.buscarPorId(id).orElseThrow());
        return "reserva-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        reservaService.deletar(id);
        return "redirect:/reservas";
    }
}