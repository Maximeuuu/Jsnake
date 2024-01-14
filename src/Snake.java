import iut.algo.*;

/* test de la classe Serpent
* Auteur : Maxime L
* Version : 1 du 27/12/22
*/

public class Snake
{

	public static void main(String[] argv)
	{
		/*-------------------------------*/
		/* DONNEES                       */
		/*-------------------------------*/

		/*variables*/
		Serpent serpent;
        Pomme pomme;
        char dir;

		/*-------------------------------*/
		/* INSTRUCTIONS                  */
		/*-------------------------------*/

        /*générer un serpent*/
        serpent = new Serpent();

        /*agrandir le serpent par défaut*/
        serpent.manger( 'N' );
        serpent.manger( 'N' );
        serpent.manger( 'N' );
        serpent.manger( 'N' );

        /*générer une pomme*/
        pomme = new Pomme();

        /*déplacer tant que le serpent n'est pas mort*/
        while( serpent.estVivant() )
        {
            System.out.println( "\033[H\033[2J"); //vider le terminal
            System.out.flush(); //vider le buffer

            System.out.println( serpent.toString( pomme ) ); //afficher l'état du serpent
            dir = Clavier.lire_char(); //choix de la direction

			switch( dir )
			{
				case 'Z' -> dir = 'N';
				case 'Q' -> dir = 'O';
				case 'S' -> dir = 'S';
				case 'D' -> dir = 'E';
			}

            if( pomme.estMangee( serpent, dir ) )
            {
                serpent.manger( dir );
                pomme = new Pomme( new Plateau(), (int)( Math.random() * 8 ), (int)( Math.random() * 8 ));
            }
            else
            {
                serpent.deplacer( dir );
            }
        }

        /*fin de la partie*/
        serpent.mourir();

        System.out.println( "\033[H\033[2J"); //vider le terminal
        System.out.flush(); //vider le buffer

        System.out.println( serpent.toString( pomme ) ); //afficher l'état du serpent
        System.out.println("Bouh tu as perdu ! ");
	}

}
