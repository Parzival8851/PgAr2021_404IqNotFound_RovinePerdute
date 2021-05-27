import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class OutputXml {


	public static final String UTF = "utf-8";
	public static final String VERSION = "1.0";
	private ArrayList<Integer> camminoXY;
	private ArrayList<Integer> camminoZ;
	private static final String TEAM1 = "Tonathiu";
	private static final String TEAM2 = "Metztli";
	private static final int NUMTEAM = 2;

	public OutputXml(RicercaCammino risultatoXY, RicercaCammino  risultatoZ)
	{


        try
        {

			XMLOutputFactory outputter = XMLOutputFactory.newInstance();

     	    XMLStreamWriter xmlw = outputter.createXMLStreamWriter(new FileOutputStream("Routes.xml"), UTF);

            xmlw.writeStartDocument(UTF, VERSION);
            xmlw.writeStartElement("routes");//scrivo tag radice "routes"

            //xmlw.writeComment("Inizio Lista Città");//scrivo commento





	        stampaCammino(xmlw, risultatoXY, TEAM1);
	        stampaCammino(xmlw, risultatoZ, TEAM2);



	            xmlw.writeEndElement();//chiusura routes

	            xmlw.writeEndDocument();//scrittura fine documento
	            xmlw.flush(); //svuota buffer e procede alla scrittura
	            xmlw.close(); //chiusura del documento e delle risorse utilizzate

        }
        catch(Exception e)
        {
            System.out.println("Errore!");
        }

    }


        private static void stampaCammino(XMLStreamWriter xmlw, RicercaCammino cammino, String team)
        {
			try
			{
				xmlw.writeStartElement("route");//scrittura tag route
				xmlw.writeAttribute("team", team);
				xmlw.writeAttribute("cost", Long.toString(Math.round(cammino.getCarburante())));
				ArrayList<Integer> nodiToccati = cammino.getCamminoMinimo();
				xmlw.writeAttribute("cities", Integer.toString(nodiToccati.size()));

				for (int j = 0; j < nodiToccati.size() ; j++)
				{
					xmlw.writeStartElement("city");//scrittura tag route
					xmlw.writeAttribute("id", Integer.toString(nodiToccati.get(j)));

					xmlw.writeAttribute("name", Spedizione.getNomeDaID(nodiToccati.get(j)));

					xmlw.writeEndElement();//chiusura city
				}
				xmlw.writeEndElement(); //chiusura route
			}
			catch(Exception e)
	        {
		        System.out.println("error");
	        }



		}


}
