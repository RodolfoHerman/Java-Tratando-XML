package br.com.rodolfo.xml.handlers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.rodolfo.xml.models.Produto;

/**
 * Para ler o arquivo XML, precisamos ter uma classe que representa a 
 * nossa estratégia de leitura, onde vamos implementar a lógica de cada evento.
 * Esse é o papel da classe 'ProdutosHandler' que extende 'DefaultHandler'
 */

/**
 * Classe que executa a lógica para a leitura do XML
 */
public class ProdutosHandler extends DefaultHandler {
    
    private List<Produto> produtos = new ArrayList<Produto>();
    private Produto produto;
    private StringBuilder conteudo;

    //Evento que realza a leitura de todas as tegs do documento XML
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        //Se a TAG sendo lida for "produto" então podemos criar um novo para ser adicionado no Array
        if(qName.equals("produto")) {

            produto = new Produto();
        }

        //Todo inicio de TAG limpamos o seu conteúdo
        conteudo = new StringBuilder();
    }

    //Evento que varre o conteúdo das TAG's (ex.: <nome>Livro de Códigos</nome> conteúdo está entre o abre e o fecha TAG)  
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
        //Necessário fazer um append pois a string dentro da tag pode ser composta por uma frase (ex.: Livro OO)
        conteudo.append(new String(ch, start, length));
    }

    //Evento que identifica o final da TAG
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        //Verificar se a TAG 'produto' chegou ao fim. Se chegou podemos adicionar o produto no array
        if(qName.equals("produto")) {

            produtos.add(produto);
        
        //Verificar se a TAG nome hegou ao fim. se chegou podemos atribui-la ao produto
        } else if(qName.equals("nome")) {

            produto.setNome(conteudo.toString());

        //Verificar se a TAG preco hegou ao fim. se chegou podemos atribui-la ao produto
        } else if(qName.equals("preco")) {

            produto.setPreco(Double.parseDouble(conteudo.toString()));
        }


    }

    /**
     * @return List<Produto> return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

}