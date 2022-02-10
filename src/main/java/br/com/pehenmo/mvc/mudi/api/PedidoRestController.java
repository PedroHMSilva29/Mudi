package br.com.pehenmo.mvc.mudi.api;

import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.model.StatusPedido;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {

    @Autowired
    private PedidoRepository pedidoReposity;

    @RequestMapping("/aguardando")
    public List<Pedido> getPedidosAguardando(){

        Sort sort = Sort.by("id").descending();
        PageRequest page = PageRequest.of(0, 10, sort);
        List<Pedido> pedidos = pedidoReposity.findByStatus(StatusPedido.AGUARDANDO, page);
        System.out.println("TOSTRING: "+pedidos);
        return pedidos;


    }

}
