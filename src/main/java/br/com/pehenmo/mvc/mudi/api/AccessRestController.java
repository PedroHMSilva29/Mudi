package br.com.pehenmo.mvc.mudi.api;

import br.com.pehenmo.mvc.mudi.interceptor.AccessInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("acessos")
@RestController
public class AccessRestController {
    @GetMapping
    public List<AccessInterceptor.Acesso> getAcessos(){
        return AccessInterceptor.acessos;
    }
}
