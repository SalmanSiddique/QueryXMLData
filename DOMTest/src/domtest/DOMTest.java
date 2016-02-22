/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domtest;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;


/**
 *
 * @author Salman
 */
public class DOMTest {
static void query (Node root)
{
    NodeList nl  = ((Element) root).getElementsByTagName("movie");
    for (int i = 0; i < nl.getLength(); i++) {
        Element e = (Element) (nl.item(i));
        if(e.getElementsByTagName("year").item(0).getFirstChild().getNodeValue().equals("2012"))
        {
            NodeList ac = ((Element) e).getElementsByTagName("actor");
            for (int j = 0; j < ac.getLength(); j++) {
                Element c = (Element) (ac.item(j));
                if(c.getChildNodes().item(0).getNodeValue().equals("Brad Pit"))
                {
                    printNode(e.getElementsByTagName("title").item(0).getFirstChild());
                }
            }
        }
    }
}

static void printNode ( Node e ) {
	if (e instanceof Text)
	    System.out.print(((Text) e).getData());
	else {
	    NodeList c = e.getChildNodes();
	    System.out.print("<"+e.getNodeName());
	    NamedNodeMap attributes = e.getAttributes();
	    for (int i = 0; i < attributes.getLength(); i++)
		System.out.print(" "+attributes.item(i).getNodeName()
				 +"=\""+attributes.item(i).getNodeValue()+"\"");
	    System.out.print(">");
	    for (int k = 0; k < c.getLength(); k++)
		printNode(c.item(k));
	    System.out.print("</"+e.getNodeName()+">");
	}
    }    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc  = db.parse(new File("m.xml"));
        Node root = doc.getDocumentElement();
        query(root);
    }
    
}
