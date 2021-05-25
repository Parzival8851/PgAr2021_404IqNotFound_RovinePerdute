import java.util.ArrayList;

public class PercorsoPossibile
{
	private ArrayList<Integer> ID_min = new ArrayList<>();
	double cammino = 0;

	/**
	 * tengo traccia del possibile percorso passante in questi nodi
	 * @param a id del nodo da aggiungere
	 * @param arcoPerA distanza da nodo di partenza ad a
	 */
	public void aggiungiNodoPossibile(int a, int arcoPerA)
	{
		ID_min.add(a);
		cammino+=arcoPerA;
	}

	public ArrayList<Integer> getID_min() {
		return ID_min;
	}

	public double getCammino()
	{
		return cammino;
	}
}
