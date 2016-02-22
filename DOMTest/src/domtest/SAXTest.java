/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domtest;

import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import java.io.FileReader;

/**
 *
 * @author Salman
 */

class QueryHandler extends DefaultHandler {
    int state;
    String title;

    public QueryHandler ( String file ) {
        super();
        state = 0;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(false);
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            xmlReader.setContentHandler(this);
            xmlReader.parse(file);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void startElement(String uri, String name, String tag, Attributes atts) {
        if(tag.equals("movie"))
            state = 1;
        else if(state==1 && tag.equals("title"))
            state = 2;
        else if(state==3 && tag.equals("year"))
            state=4;
        else if(state==5 && tag.equals("actor"))
            state=6;
    }

    public void endElement(String uri, String name, String tag) {
        if(tag.equals("movie"))
            state=0;
    }

    public void characters(char text[], int start, int length) {
        String s = new String(text,start,length);
        if(state == 2){title = s;state=3;}
        else if(state==4 && s.equals("2012"))
            state=5;
        else if(state==6 && s.equals("Brad Pit"))
            System.out.println(title);
    }
}

public class SAXTest {
 public static void main ( String args[] ) throws Exception {
        new QueryHandler("m.xml");
    }    
}
