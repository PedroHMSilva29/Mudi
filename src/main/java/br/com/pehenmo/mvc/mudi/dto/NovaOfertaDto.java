package br.com.pehenmo.mvc.mudi.dto;

import br.com.pehenmo.mvc.mudi.model.Oferta;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NovaOfertaDto {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer idPedido;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotNull
    private String valor;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
    @NotNull
    private String dataEntrega;

    private String comentario;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }



    public Oferta criaOferta() {
        Oferta oferta = new Oferta();
        oferta.setComentario(this.getComentario());
        oferta.setDataEntrega(LocalDate.parse(this.getDataEntrega(), formatter));
        oferta.setValor(new BigDecimal(this.getValor()));
       return oferta;

    }
}
