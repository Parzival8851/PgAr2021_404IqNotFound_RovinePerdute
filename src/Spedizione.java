import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class Spedizione
{
	public static final String SPAZIO = "  ";
	private static ArrayList<Nodo> vertici = new ArrayList<>();
	private static double [][] archi;
	private static InputXml lettura=new InputXml();
	private static RicercaCammino navigatoreXY;
	private static RicercaCammino navigatoreZ;
	private static ArrayList<Integer> camminoXY;
	private static ArrayList<Integer> camminoZ;



	public static void main(String[] args)
	{
		// leggo le città
		vertici=lettura.getVert();
		// leggo quali città hanno collegamenti
		archi= lettura.getArc();
		// calcolo le distanze tra quelle che hanno collegamenti
		setDistanzeXY();

		/*
		//test città
		testNodi();
		testArchi();
		*/

		// ricerca cammino XY
		navigatoreXY = new RicercaCammino(vertici);
		camminoXY = navigatoreXY.camminoMinimo();

		// modifico la matrice per la Z
		modificaArchiZ();

		// ricerca cammino Z
		navigatoreZ = new RicercaCammino(vertici);
		camminoZ = navigatoreZ.camminoMinimo();

		// test cammini
		System.out.println("CAMMINO XY:");
		stampaCammino(camminoXY);
		System.out.println("\n\nCAMMINO Z:");//mi sento inutile
		stampaCammino(camminoZ);

	}

	private static void stampaCammino(ArrayList<Integer> cammino)
	{
		System.out.print(cammino.get(0));
		for (int i = 1; i < cammino.size(); i++)
		{
			System.out.print(" --> " + cammino.get(i));
		}
	}

	/**
	 * scorro la matrice in cerca di celle non zero per come l'ho costruita, quando le trovo
	 * calcolo il collegamento i->j con la distanza euclidea
	 */
	private static void setDistanzeXY()
	{
		for (int i = 0; i < archi.length; i++)
		{
			for (int j = 0; j < archi.length; j++)
			{
				if (archi[i][j]>0) // per gli archi con 1 setto la distanza, lascio gli 0 e gli archi non esistenti con -1
				{
					archi[i][j]=Math.hypot(vertici.get(i).getX()-vertici.get(j).getX(), vertici.get(i).getY()-vertici.get(j).getY());
				}
			}
		}
	}



	/**
	 * getter dell'arco a->b
	 * @param a id partenza
	 * @param b id arrivo
	 * @return lunghezza arco a->b
	 */
	public static double getArco(int a, int b)
	{
		return archi[a][b];
	}

	private static void testNodi()
	{
		for (Nodo n : vertici)
		{
			System.out.println(n.getNome()+ SPAZIO +n.getId()+SPAZIO+n.getX()+SPAZIO+n.getY()+SPAZIO+n.getH());
		}
		System.out.println("\n\n");
	}

	private static void testArchi()
	{
		for (Nodo n : vertici)
		{
			System.out.println(n.getNome()+SPAZIO+n.getId());
			for (int i = 0; i < archi.length; i++)
			{
				System.out.print("to "+i+":   ");
				System.out.println(archi[n.getId()][i]);
			}
		}
		System.out.println("\n\n");
	}

	/**
	 * modifico la tabella: i valori già positivi (quindi dove esiste un arco)
	 * li converto alla distanza vertyicale H(a) - H(b)
	 */
	private static void modificaArchiZ()
	{
		for (int i = 0; i < archi.length; i++)
		{
			for (int j = 0; j < archi.length; j++)
			{
				if (archi[i][j]>0) // dove esiste arco
				{
					archi[i][j]=Math.abs(vertici.get(i).getH()-vertici.get(j).getH()); // guardo abs del delta quota
				}
			}
		}
	}





}

