package br.com.rodolfo.xml.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import br.com.rodolfo.xml.handlers.ProdutosHandler;

/**
 * Quando temos um arquivo muito grande no qual apenas uma parte interessa, 
 * não faz sentido carregá-lo inteiro na memória para usar apenas um pedaço. 
 * Portanto, ficar ouvindo todos os eventos, mas apenas tratar os que interessam, 
 * passa a ser uma abordagem interessante, pois economiza memória, já que 
 * baseado em eventos nós decidimos o que deve permanecer nela.
 */


/**
 * A vantagem que a leitura é feita por eventos e escolhemos o que guardar na memória, ou seja, não precisamos
 * ter todo o XML em memória como acontecia na abordagem pelo DOM
 */
public class LeXMLComSax {

    public static void main(String[] args) throws Exception{
        
        XMLReader leitor = XMLReaderFactory.createXMLReader();

        ProdutosHandler logica = new ProdutosHandler();
        
        //Associar o ProdutosHandler à fábrica, utilizando o método setContentHandler
        leitor.setContentHandler(logica);

        InputStream inputStream = new FileInputStream("./src/main/java/br/com/rodolfo/xml/vendas.xml");
        InputSource inputSource = new InputSource(inputStream);

        leitor.parse(inputSource);


        System.out.println(logica.getProdutos());
    }


}