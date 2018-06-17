package br.com.rodolfo.xml.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.rodolfo.xml.models.Produto;

/**
 * O XPath nos permite selecionar apenas uma parte do nosso documento, 
 * usando uma sintaxe bem parecida com a estrutura de pastas do nosso 
 * sistema operacional. O que facilita a busca de dados do nosso documento.
 */
public class ConsultasComXPathNoDom {

    public static void main(String[] args) throws Exception {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.parse("./src/main/java/br/com/rodolfo/xml/vendas.xml");

        //Construtor de cunsultas no DOM do XML
        XPath path = XPathFactory.newInstance().newXPath();

        //Expreesão (SQL do XML) para buscar produtos
        //String expressao = "/venda/produtos/produto";

        //Consulta para pegar apenas o segundo produto no XML
        //String expressao = "/venda/produtos/produto[2]";

        //Consulta para trazer o elemento que possui exatamente o que está escrito no atributo
        //String expressao = "/venda/produtos/produto[nome='Livro de OO']";

        //Consulta que traz todos os produtos que possuem a palavra 'Livro' no atributo 'nome'
        String expressao = "/venda/produtos/produto[contains(nome,'Livro')]";

        //Transformar a String emuma consulta válida
        XPathExpression compile = path.compile(expressao);

        //Executa a consulta no DOCUMENTO e retorna como resposta os nós (NODESET) do XML
        NodeList lista = (NodeList) compile.evaluate(documento, XPathConstants.NODESET);

        for (int x = 0; x < lista.getLength(); x++) {
            
            Element produto = (Element) lista.item(x);

            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            Double preco = Double.parseDouble(
                                produto.getElementsByTagName("preco").item(0).getTextContent()
                            );

            Produto prod = new Produto(nome, preco);

            System.out.println(prod);
        }


    }


}