package br.com.pehenmo.mvc.mudi.api;

import br.com.pehenmo.mvc.mudi.dto.NovaOfertaDto;
import br.com.pehenmo.mvc.mudi.model.Oferta;
import br.com.pehenmo.mvc.mudi.model.Pedido;
import br.com.pehenmo.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api/ofertas")
public class OfertaRestController {

    @Autowired
    PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<Oferta> criaOferta(@Valid @RequestBody NovaOfertaDto request){
        Oferta oferta = request.criaOferta();

        Optional<Pedido> optPedido = pedidoRepository.findById(request.getIdPedido());

        if(!optPedido.isPresent()){
           return null;
        }
        Pedido pedido = optPedido.get();
        oferta.setPedido(pedido);
        pedido.getOfertas().add(oferta);

        pedidoRepository.save(pedido);

        return ResponseEntity.ok().body(oferta);
    }
}
