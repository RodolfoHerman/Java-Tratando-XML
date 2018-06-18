package br.com.rodolfo.xml.test;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class ConversorParaHTML {

    
    public static void main(String[] args) throws Exception {
        
        InputStream xls = new FileInputStream("./src/main/java/br/com/rodolfo/xml/xmlParaHtml.xsl");
        StreamSource xlsSource = new StreamSource(xls);


        InputStream xml = new FileInputStream("./src/main/java/br/com/rodolfo/xml/vendas.xml");
        StreamSource xmlSource = new StreamSource(xml);

        StreamResult saida = new StreamResult("./src/main/java/br/com/rodolfo/xml/vendas.html");

        //Responsável por transformoar o arquivo XML para o formato desejado
        Transformer transformer = TransformerFactory.newInstance().newTransformer(xlsSource);

        transformer.transform(xmlSource, saida);
    }


}