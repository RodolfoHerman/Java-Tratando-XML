package br.com.rodolfo.xml.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import br.com.rodolfo.xml.models.Produto;

/**
 * O STAX Também é baseado em eventos, porém enquanto no SAX a própria API invoca os métodos que 
 * definimos no handler, no StAX conseguimos pegar uma "lista" de todos os eventos e percorrê-los um a um. 
 * A vantagem disso é que, por ser uma "lista", é possível voltar para o evento anterior, caso necessário.
 */
public class LeXMLComStax {

    public static void main(String[] args) throws Exception {
        
        InputStream inputStream = new FileInputStream("./src/main/java/br/com/rodolfo/xml/vendas.xml");

        XMLInputFactory factory = XMLInputFactory.newInstance();

        //Classe responsável por possuir os eventos de leitura do XML, ex.: os eventos do ProdutosHandler
        XMLEventReader eventos = factory.createXMLEventReader(inputStream);

        Produto produto = new Produto();
        List<Produto> produtos = new ArrayList<Produto>();

        while(eventos.hasNext()) {

            XMLEvent evento = eventos.nextEvent();

            // //Quando identifica que está na TAG de abertura 'produto' 
            // if(evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {

            //     produto = new Produto();

            // //Quando identifica que está na TAG de abertura 'nome'  
            // } else if(evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {

            //     //Necessário avançar o evento para a leitura do conteúdo da TAG
            //     evento = eventos.nextEvent();
            //     //Realiza a leitura do conteúdo da TAG
            //     String nome = evento.asCharacters().getData();
            //     produto.setNome(nome);

            // //Quando identifica que está na TAG de abertura 'preco'  
            // } else if(evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("preco")) {

            //     //Necessário avançar o evento para a leitura do conteúdo da TAG
            //     evento = eventos.nextEvent();
            //     //Realiza a leitura do conteúdo da TAG
            //     Double preco = Double.parseDouble(evento.asCharacters().getData());
            //     produto.setPreco(preco);

            // //Quando identifica que está na TAG de fechamento 'produto'  
            // } else if(evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("produto")) {

            //     produtos.add(produto);
            // }

            //Chamada do método extraído abaixo
            if(evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {

                Produto produto2 = criaProduto(eventos);
                produtos.add(produto2);

            }

        }

        System.out.println(produtos);
    }

    //Extração a complexidade da criação do produto declarada no método main
    public static Produto criaProduto(XMLEventReader eventos) throws Exception {
        
        Produto produto = new Produto();

        while(eventos.hasNext()) {

            XMLEvent evento = eventos.nextEvent();

            if(evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {

                evento = eventos.nextEvent();
                String nome = evento.asCharacters().getData();
                produto.setNome(nome);

            } else if(evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("preco")) {

                evento = eventos.nextEvent();
                Double preco = Double.parseDouble(evento.asCharacters().getData());
                produto.setPreco(preco);

            } else if(evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("produto")) {

                break;
            }

        }

        return produto;
    }

}