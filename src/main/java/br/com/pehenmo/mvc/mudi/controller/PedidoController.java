package br.com.pehenmo.mvc.mudi.controller;

import br.com.pehenmo.mvc.mudi.dto.NovoPedidoDto;
import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {

    PedidoRepository repository;

    public PedidoController(PedidoRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "formulario", method = RequestMethod.GET)
    public ModelAndView formulario(NovoPedidoDto dto){
        ModelAndView modelAndView = new ModelAndView("pedido/formulario");
        return modelAndView;
    }

    @RequestMapping(value = "novo", method = RequestMethod.POST)
    public ModelAndView novo(@Valid NovoPedidoDto dto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("pedido/formulario");
        }
        Pedido pedido = dto.toPedido();
        repository.save(pedido);

        return new ModelAndView("redirect:/home");
    }
}
