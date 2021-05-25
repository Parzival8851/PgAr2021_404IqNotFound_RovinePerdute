import java.util.ArrayList;

public class Spedizione
{
	private static ArrayList<Nodo> vertici = new ArrayList<>();
	private static double [][] archi;
	private static InputXml lettura = new InputXml();

	public static void main(String[] args)
	{
		// leggo le città
		vertici=lettura.getVert();
		// leggo quali città hanno collegamenti
		archi= lettura.getArc();
		// calcolo le distanze tra quelle che hanno collegamenti
		setDdistanze();
	}

	/**
	 * scorro la matrice in cerca di celle non zero per come l'ho costruita, quando le trovo
	 * calcolo il collegamento i->j con la distanza euclidea
	 */
	private static void setDdistanze()
	{
		for (int i = 0; i < archi.length; i++)
		{
			for (int j = 0; j < archi.length; j++)
			{
				if (archi[i][j]!=0)
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
	public double getArco(int a, int b)
	{
		return archi[a][b];
	}





}

