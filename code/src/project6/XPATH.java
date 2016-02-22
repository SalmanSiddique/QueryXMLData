/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

import javax.xml.xpath.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

/**
 *
 * @author Salman
 */
public class XPATH {
static void print ( Node e ) {
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
		print(c.item(k));
	    System.out.print("</"+e.getNodeName()+">");
	}
    }

    static void eval ( String query, String document ) throws Exception {
	XPathFactory xpathFactory = XPathFactory.newInstance();
	XPath xpath = xpathFactory.newXPath();
	InputSource inputSource = new InputSource(document);
	NodeList result = (NodeList) xpath.evaluate(query,inputSource,XPathConstants.NODESET);
	System.out.println("XPath query: "+query);
	for (int i = 0; i < result.getLength(); i++)
	    print(result.item(i));
	System.out.println();
    }

    public static void main ( String[] args ) throws Exception {
        //1.1        
        eval("//article[authors/author='David Maier']/title","SigmodRecord.xml");
     
        //1.2        
        eval("//article[authors/author[@position='00']='David Maier']/title","SigmodRecord.xml");
        
        //1.3
        eval("//article[authors/author='David Maier' and authors/author='Stanley B. Zdonik']/title","SigmodRecord.xml");

        //1.4
        eval("//issue[volume='19' or number='2']/articles/article/title","SigmodRecord.xml");
        
        //1.5
        eval("//issue[contains(volume,'19') or contains(number,'2')]/articles/article[authors/author='Jim Gray']/*[self::title or self::initPage or self::endPage]","SigmodRecord.xml");
        
        //1.6 
        eval("//issue[articles/article/authors/author='David Maier']/*[self::volume or self::number]","SigmodRecord.xml");        
    }    
}
