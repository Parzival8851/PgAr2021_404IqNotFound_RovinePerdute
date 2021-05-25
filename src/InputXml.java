import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class InputXml {

    private InputStream xmlInputStream;
    private XMLInputFactory xmlif;
    private XMLStreamReader xmlr;


    public Nodo readNext(){

        int id=0;
        ArrayList<Integer> link = new ArrayList<Integer>();
        String nome = null, x = null, y = null, h = null;
        try{
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(LINK, new FileInputStream(LINK));
        }catch(Exception e){
            System.out.println("Errore nell'inizializzazione del reader: ");
            System.out.println(e.getMessage());
    }

        try
        {
            while(xmlr.hasNext())
            {
                // cerco il tag "city"
                if (xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("city"))
                {

                    id=Integer.parseInt(xmlr.getAttributeValue(0));
                    nome = xmlr.getAttributeValue(1);
                    x = xmlr.getAttributeValue(2);
                    y = xmlr.getAttributeValue(3);
                    h = xmlr.getAttributeValue(4);

                    while(!(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("link")))
                    {
                        xmlr.next();

                    }
                    xmlr.next();
                    link.add(Integer.parseInt(xmlr.getText()));

                    System.out.println("nodo aggiunto");
                    return new Nodo(id, nome, x, y , h, link);

                }
                else xmlr.next();
            }
        }
        catch (Exception e)
        {
            System.out.println("Errore: non esiste una nuova riga da leggere\n");
        }
}


