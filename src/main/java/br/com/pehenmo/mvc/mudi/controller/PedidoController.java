package br.com.pehenmo.mvc.mudi.controller;

import br.com.pehenmo.mvc.mudi.dto.NovoPedidoDto;
import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.User;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import br.com.pehenmo.mvc.mudi.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {

    PedidoRepository pedidoRepository;
    UserRepository userRepository;

    public PedidoController(PedidoRepository pedidoRepository, UserRepository userRepository){
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "formulario", method = RequestMethod.GET)
    public ModelAndView formulario(NovoPedidoDto dto){
        ModelAndView modelAndView = new ModelAndView("pedido/formulario");
        return modelAndView;
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ModelAndView novo(@Valid NovoPedidoDto dto, BindingResult result, Principal princial){
        if(result.hasErrors()){
            return new ModelAndView("pedido/formulario");
        }

        User user = userRepository.findByUsername(princial.getName());

        Pedido pedido = dto.toPedido();
        pedido.setUser(user);
        System.out.println(user);

        pedidoRepository.save(pedido);

        return new ModelAndView("redirect:/home");
    }
}
