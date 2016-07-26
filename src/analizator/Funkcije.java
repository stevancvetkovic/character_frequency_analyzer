package analizator;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import org.xml.sax.*;

public class Funkcije
{

    public Azbuka[] vratiSlova()
    {
        Azbuka[] lista = new Azbuka[0];
        Sax handler = new Sax();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        {
            try
            {
                SAXParser saxParser = factory.newSAXParser();
                saxParser.parse(new File("rezultat.xml"), handler);
                Sax sm = (Sax)handler;

                lista = new Azbuka[sm.brojSlova];
                for (int i=0; i<sm.brojSlova; i++)
                {
                    lista[i] = new Azbuka();
                    lista[i] = sm.nizSlova[i];
                }
            }
            catch (SAXException spe)
            {
                System.out.println("\n** Parsing error" + ", line " + spe.getMessage() + ", uri " + spe.getMessage());
            }
            catch (Throwable t)
            {
                 t.printStackTrace();
            }
        }
        return lista;
    }

    public void azurirajVerovatnoce(String s, float v) throws NePostojiException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Dom dm = new Dom();
            dm.document = builder.parse(new File("rezultat.xml"));

            dm.promeniV(s, v);

            TransformerFactory xformFactory = TransformerFactory.newInstance();
            Transformer idTransform = xformFactory.newTransformer();
            Source input = new DOMSource(dm.document);
            Result output = new StreamResult("rezultat.xml");
            idTransform.transform(input, output);
        }
        catch(NePostojiException npe)
	{
            System.out.println(npe.getPoruka());
	}
        catch (SAXParseException spe)
        {
            System.out.println("\n** Parsing error"
                            + ", line " + spe.getLineNumber()
                            + ", uri " + spe.getSystemId());
            System.out.println(" " + spe.getMessage() );
            Exception x = spe;
            if (spe.getException() != null)
                    x = spe.getException();
            x.printStackTrace();
        }
        catch (SAXException sxe)
        {
            Exception x = sxe;
            if (sxe.getException() != null)
                    x = sxe.getException();
            x.printStackTrace();
        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (TransformerConfigurationException e)
        {
            System.out.println("This DOM does not support transforms.");
        }
        catch (TransformerException e)
        {
            System.out.println("Transform failed.");
        }
    }

    public void obrada(char[] ulaz)
    {
        Funkcije f = new Funkcije();
        Azbuka[] slova = new Azbuka[0];

        slova = f.vratiSlova();
        
        for(int j=0; j<ulaz.length; j++)
        {
            String c = String.valueOf(ulaz[j]);

            for(int i=0; i<slova.length; i++)
            {
                if(slova[i].getSlovo().equals(c.toLowerCase()))
                {
                    slova[i].incBrojPojavljivanja();
                }
            }
        }

        for(int i=0; i<slova.length; i++)
        {
            slova[i].setVerovatnoca((float)slova[i].getBrojPojavljivanja() / ulaz.length);

            try
            {
                if(slova[i].getBrojPojavljivanja() != 0)
                    f.azurirajVerovatnoce(slova[i].getSlovo(), slova[i].getVerovatnoca());
            }
            catch(NePostojiException npe)
            {
                System.out.println(npe.getPoruka());
            }
        }
    }

    public Azbuka[] vratiSortiranNiz()
    {
        Funkcije f = new Funkcije();
        Azbuka[] niz = new Azbuka[30];
        Azbuka temp = new Azbuka();
        niz = f.vratiSlova();

        for(int j=1; j<niz.length; j++)
            for(int i=niz.length-1; i>=j; i--)
            {
                if(niz[i-1].getVerovatnoca()<niz[i].getVerovatnoca())
                {
                    temp = niz[i-1];
                    niz[i-1] = niz[i];
                    niz[i] = temp;
                }
            }
        return niz;
    }
}
