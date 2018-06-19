package br.com.rodolfo.xml.test;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.rodolfo.xml.models.Produto;
import br.com.rodolfo.xml.models.Venda;


/**
 * Como ler arquivos XML usando o JAXB, 
 * que é a especificação do Java que nos permite associar diretamente uma classe a um arquivo XML. 
 * Para isso é necessário criar uma classe que representa o XML, adicionar 
 * a anotação @XmlRootElement e por fim usar a classe JAXBContext para parsear o documento.
 * A principal desvantagem do JAX-B é que os arquivos também são interpretados usando árvores, ou seja, 
 * possuímos os mesmos problemas do DOM. Quando o arquivo é muito grande, acabamos gastando muita memória
 */
public class MapeiaXMLComJAXB {
    
    public static void main(String[] args) throws Exception{
        
        //Iniciar um contexto falando qual é a classe que deverá receber os dados do nosso arquivo XML, 
        //executar o processo de ler o arquivo XML e transformar em um objeto (unmarshall)
        JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);

        //xmlParaObjeto(jaxbContext);

        objetoParaXml(jaxbContext);
    }


    public static void objetoParaXml(JAXBContext jaxbContext) throws Exception {
        
        Marshaller marshaller = jaxbContext.createMarshaller();

        Venda venda = new Venda();
        venda.setFormaDePagamento("Crediario");

        List<Produto> produtos = new ArrayList<Produto>();

        produtos.add(new Produto("Mesa redonda", 55.90));
        produtos.add(new Produto("Mesa quadrada", 25.90));
        produtos.add(new Produto("Cadeira", 35.90));

        venda.setProdutos(produtos);

        //Em vez de criar um arquivo para e escrever dentro dele o XML criamos uma StringWriter para receber as informações
        StringWriter stringWriter = new StringWriter();

        marshaller.marshal(venda, stringWriter);

        System.out.println(stringWriter.toString());
    }


    public static void xmlParaObjeto(JAXBContext jaxbContext) throws Exception {
        
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Venda venda = (Venda) unmarshaller.unmarshal(new File("./src/main/java/br/com/rodolfo/xml/vendas.xml"));

        System.out.println(venda);
    }


}