package br.com.pehenmo.mvc.mudi.controller;

import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.StatusPedido;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

        Sort sort = Sort.by("dataEntrega").descending();
        PageRequest page = PageRequest.of(0, 10, sort);


        List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, page);

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", "home");
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }


}
