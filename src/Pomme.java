import iut.algo.*;

/* classe serpent
* Auteur : Maxime L
* Version : 1 du 18/12/22
*/

public class Pomme
{
    /*-------------------------------*/
	/* ATTRIBUTS                     */
	/*-------------------------------*/

    private Plateau plateau;
    private int posX; //coordonnées de la pomme
    private int posY;

    /*-------------------------------*/
	/* CONSTRUCTEURS                 */
	/*-------------------------------*/

    public Pomme()
    /*placer une pomme aux coordonnées (7,5) sur un plateau de taille 8 sur 8*/
    {
        this( new Plateau(), 7, 5 );
    }

    public Pomme(Plateau plateau, int posX, int posY)
    /*placer une pomme aux coordonnées (posX,posY) sur un plateau Plateau*/
    {
        this.plateau = plateau;
        this.posX    = posX;
        this.posY    = posY;
    }

    /*-------------------------------*/
	/* ACCESSEURS                    */
	/*-------------------------------*/

    public int getX()
    {
        return this.posX;
    }

    public int getY()
    {
        return this.posY;
    }

    /*-------------------------------*/
	/* METHODES                      */
	/*-------------------------------*/

    public boolean estMangee( Serpent serpent, char coord )
    /*test si la pomme est mangée par le Serpent serpent en allant dans la direction "coord"*/
    {
        int posX, posY;

        posX = serpent.getX();
        posY = serpent.getY();

        switch( coord )
        {
            case 'N' -> posY--;
            case 'O' -> posX--;
            case 'S' -> posY++;
            case 'E' -> posX++;
        }

        if( this.posX == posX && this.posY == posY )
        {
            return true;
        }

        return false;
    }


}
