package br.com.rodolfo.xml.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//utilizando o padrão e especificações do w3c
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.rodolfo.xml.models.Produto;


public class Sistema {

    public static void main(String[] args) throws Exception {
        //Instanciando uma fFábrica de documentos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        //Informa que queremos realizar a validação do documento XML através do arquivo XSD
        factory.setValidating(true);
        //Pegar o arquivo de validação atrave´s do atributo 'noNamespaceSchemaLocation' no arquivo XML
        factory.setNamespaceAware(true);
        //Temos que especificar qual a linguagem esta sendo utilizada para a validação
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
        "http://www.w3.org/2001/XMLSchema");

        //Construtor de documentos
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Carregamento do documento na memória e atribuído à variável. O 'document' é a TAG mais externa do xml, ou seja, é a raiz. Sendo assim, a TAG raiz do xml é a <venda></venda>
        Document documento = builder.parse("./src/main/java/br/com/rodolfo/xml/vendas.xml");
        
        //Para acessar a TAG raiz (<venda></venda>)
        Element raiz = documento.getDocumentElement();
        //Para acesar um atributo de uma TAG
        System.out.println(raiz.getAttribute("moeda"));

        //Coo no XML pode ter várias tags com o mesmo nome o método 'getElementsByTagName' trará uma lista de tags
        NodeList produtos = documento.getElementsByTagName("produto");

        for (int i = 0; i < produtos.getLength(); i++) {
            
            //O método 'item' retorn um tipo 'Node' mas este tipo não possui bons métodos para se trabalhar.
            //Utilizando a interface 'Element' fica melhor para tratamento dos dados do XML
            Element produto = (Element) produtos.item(i);
    
            String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
            Double preco = Double.parseDouble(
                                produto.getElementsByTagName("preco").item(0).getTextContent()
                            );

            Produto prod = new Produto(nome, preco);

            System.out.println(prod);

        }

    }

}