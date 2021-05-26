import java.util.ArrayList;
import java.util.Vector;

public class RicercaCammino
{
	//ArrayList<PercorsoPossibile> scelte = new ArrayList<>();
	private static Vector<Double> d = new Vector<Double>(); // vettore distanze minime da i a sorgente
	private static Vector<Boolean> v = new Vector<>(); // vettore booleano per sapere se i è stato visitato
	private static Vector<Integer> p = new Vector<>(); // vettore degli id dei nodi precedenti nel cammino tra i e x iniziale
	private static int x=0; // nodo iniziale
	private static int n=0; // numero dei nodi

	public RicercaCammino(ArrayList<Nodo> paese)
	{
		x=0; // set nodo iniziale, è sempre 0 "campo base"
		n= paese.size(); // set numero nodi
		setVettori(); // set dei vettori distanza da origine, nodi precedenti e visita

		d.set(x,0.0); // set d(x)=0
		double m;
		int j=0;
		do
		{
			m = Double.POSITIVE_INFINITY;

			// trovo il nodo che ha distanza minima da x a quelli non ancora visitati
			for (int i = 0; i < n; i++)
			{
				if (!v.get(i)) // se v(i)=false
				{
					if (d.get(i)<=m) // se d(i)<=(+inf)
					{
						m=d.get(i);
						j=i;
					}
				}
			}
			if(m!=Double.POSITIVE_INFINITY) // se ci sono ancora nodi da visitare
			{
				v.set(j,true); // marca il nodo come visitato
				// esamina i nodi frontiera (neighbours) per j
				for (int i = 0; i < n; i++)
				{
					if (G(i,j)>0) // se esiste l'arco i->j
					{
						if (d.get(i)>(d.get(j)+G(i,j)))
						{
							d.set(i,d.get(j)+G(i,j));
							p.set(i,j);
						}
					}
				}
			}
		}while(m==Double.POSITIVE_INFINITY); // tutti i nodi sono stati visitati
	}

	/**
	 * inizializzo la distanza da x a +inf,
	 * visita a falso,
	 * nodi precedenti a 0 (nessun nodo precedente)
	 */
	private void setVettori()
	{
		for (int i = 0; i < n; i++)
		{
			d.add(i, Double.POSITIVE_INFINITY);
			v.add(i, false);
			p.add(i, 0);
		}
	}

	/**
	 *
	 * @param a nodo partenza
	 * @param b nodo arrivo
	 * @return arco a->b
	 */
	private static double G(int a, int b)
	{
		return Spedizione.getArco(a, b);
	}

	public ArrayList<Integer> camminoMinimo()
	{
		ArrayList<Integer> camminoInverso = new ArrayList<>();
		int j=4; // id di Rovine Perdute
		do // cammino da i a x
		{
			camminoInverso.add(p.get(j));
			j=p.get(j);
		}while(j!=0); // finché j non è l'origine (0)
		/*ArrayList<Integer> camminoCorretto = new ArrayList<>();
		for (int i = camminoInverso.size()-1; i >=0; i--)
		{
			// inverto l'array per andare da x a i
			camminoCorretto.add(camminoInverso.get(i));
		}
		return camminoCorretto;*/
		return camminoInverso;
	}


}
