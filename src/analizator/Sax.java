package analizator;

//import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
//import javax.xml.parsers.SAXParserFactory;
//import javax.xml.parsers.SAXParser;

public class Sax extends DefaultHandler
{
    StringBuffer textBuffer = null;

    public Azbuka trenutnoSlovo = null;
    public Azbuka[] nizSlova = new Azbuka[30];
    public int brojSlova = 0;

    @Override
    public void startDocument() throws SAXException
    {
        try
	{
            //System.out.println("Pocinje obrada!");
	}
	catch (Exception e)
	{
            throw new SAXException("U/I greska!", e);
	}
    }

    @Override
    public void endDocument() throws SAXException
    {
        try
        {
            //System.out.println("Obrada je zavrsena!");
        }
        catch (Exception e)
        {
            throw new SAXException("U/I greska!", e);
	}
    }

    @Override
    public void startElement(String namespaceURI,
                String sName,
		String qName,
		Attributes attrs) throws SAXException
    {

        if (qName.equals("Slovo"))
	{
                this.trenutnoSlovo = new Azbuka();
	}
    }

    @Override
    public void endElement(String namespaceURI,
		String sName,
		String qName
		)throws SAXException
    {
	if(qName.equals("Slovo"))
        {
            nizSlova[brojSlova] = new Azbuka();
            nizSlova[brojSlova++] = trenutnoSlovo;
	}

        if (textBuffer != null)
	{
            String contentValue = textBuffer.toString().trim();
            if (qName.equals("Slovo"))
                trenutnoSlovo.setSlovo(contentValue);
            else if (qName.equals("Verovatnoca"))
                trenutnoSlovo.setVerovatnoca(Float.parseFloat(contentValue));
            
            textBuffer = null;
        }
    }

    public void characters(char buf[], int offset, int len) throws SAXException
    {
        String s = new String(buf, offset, len);
	if (textBuffer == null)
	{
            textBuffer = new StringBuffer(s);
	}
	else
	{
            textBuffer.append(s.trim());
        }
    }
}