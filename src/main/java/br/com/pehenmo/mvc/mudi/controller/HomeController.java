package br.com.pehenmo.mvc.mudi.controller;

import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.StatusPedido;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    PedidoRepository repository;

    public HomeController(PedidoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public String home(Model model){

        //ModelAndView mv = new ModelAndView("home");
        //mv.addObject("pedidos", pedidos);

        List<Pedido> pedidos = repository.findAll();

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", "home");
        return "home";
    }

    @RequestMapping(value = "/{status}", method = RequestMethod.GET)
    public String porStatus(@PathVariable("status") String status, Model model){

        List<Pedido> pedidos = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status.toLowerCase());
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }


}
