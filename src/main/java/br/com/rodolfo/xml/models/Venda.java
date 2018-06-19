package br.com.rodolfo.xml.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

//A anotação 'XmlRootElement' informa que a classe é um mapeamento do aruivo XML
@XmlRootElement
//Informar como deve ser preenchido o atributo
@XmlAccessorType(XmlAccessType.FIELD)
public class Venda {

    private String formaDePagamento;

    //A anotação 'XmlElementWrapper' informa que esse atributo é uma TAG de 'produtos' através do parâmetro 'name'
    //que engloba uma lista
    @XmlElementWrapper(name="produtos")
    //Informa que a TAG para acessar dentro de 'produtos' é a TAG 'produto', que indica cada TAG dentro da lista
    @XmlElement(name="produto")
    private List<Produto> produtos;


    /**
     * @return String return the formaDePagamento
     */
    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    /**
     * @param formaDePagamento the formaDePagamento to set
     */
    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    /**
     * @return List<Produto> return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Forma de pagamento : " + this.formaDePagamento + "\n" + produtos;
    }

}