import iut.algo.*;

/* classe Plateau de Snake.java
* Auteur : Maxime L
* Version : 1 du 18/12/22
*/

public class Plateau
{
    /*-------------------------------*/
	/* ATTRIBUTS                     */
	/*-------------------------------*/

    private int longueur;
    private int hauteur;
    private int dimension;

    /*-------------------------------*/
	/* CONSTRUCTEURS                 */
	/*-------------------------------*/

    public Plateau()
    {
        this(8,8,2);
    }

	public Plateau( int longueur, int hauteur, int dimension)
	{
		this.longueur  = longueur;
        this.hauteur   = hauteur;
        this.dimension = dimension;
	}

    /*-------------------------------*/
	/* ACCESSEURS                    */
	/*-------------------------------*/

    public int getLongueur()
    {
        return this.longueur;
    }

    public int getHauteur()
    {
        return this.hauteur;
    }

    public int getDimension()
    {
        return this.dimension;
    }


}
