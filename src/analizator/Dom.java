package analizator;

import org.w3c.dom.*;

public class Dom
{
    public Document document;

    public void promeniV(String slovo, float verovatnoca) throws NePostojiException
    {
         NodeList lista = this.document.getElementsByTagName("Slovo");
         boolean flag = false;

         for(int i=0; i<lista.getLength(); i++)
            if (lista.item(i).getTextContent().equals(slovo.toLowerCase()))
                flag = true;

         if (flag == true)
         {
             for(int i=0; i<lista.getLength(); i++)
             {
                 if (lista.item(i).getTextContent().equals(slovo.toLowerCase()))
                 {
                    Element element = (Element) document.getElementsByTagName("Verovatnoca").item(i);
                    float f = Float.parseFloat(element.getTextContent());
                    
                    if (f != 0.0)
                        element.setTextContent(Float.toString((float)(verovatnoca+f)/2));
                    else if (f == 0.0)
                        element.setTextContent(Float.toString((verovatnoca)));
                 }
             }
         }
         else
         {
            throw new NePostojiException("Trazeno slovo ne postoji!");
         }
    }
}