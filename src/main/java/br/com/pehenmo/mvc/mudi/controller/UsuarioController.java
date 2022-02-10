package br.com.pehenmo.mvc.mudi.controller;

import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.StatusPedido;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    PedidoRepository repository;

    public UsuarioController(PedidoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    @RequestMapping(value = "/pedido", method = RequestMethod.GET)
    public String home(Model model, Principal principal){

        List<Pedido> pedidos = repository.findAllByUsuario(principal.getName());

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", "todos");
        return "usuario/home";
    }

    @RequestMapping(value = "/pedido/{status}", method = RequestMethod.GET)
    public String porStatus(@PathVariable("status") String status, Model model, Principal princial){

        List<Pedido> pedidos = repository.findByStatusAndUsername(princial.getName(), StatusPedido.valueOf(status.toUpperCase()));

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status.toLowerCase());
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/usuario/home";
    }


}
