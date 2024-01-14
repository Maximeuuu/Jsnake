import iut.algo.*;

/* classe serpent
* Auteur : Maxime L
* Version : 1 du 18/12/22
*/

public class Serpent
{
	/*-------------------------------*/
	/* ATTRIBUTS                     */
	/*-------------------------------*/

	private Plateau plateau;
	private int[][] tabCooCorps; //tableau de coordonnées
	private int longueur; //taille actuelle du serpent
	private char dir; //direction du serpent (NOSE)

	/*-------------------------------*/
	/* CONSTRUCTEURS                 */
	/*-------------------------------*/

	public Serpent()
	/*placer un serpent aux coordonnées (5,5) sur un plateau de taille 8 sur 8*/
	{
		this( new Plateau(), 5, 5 );
	}

	public Serpent(Plateau plateau, int posX, int posY)
	/*placer un serpent aux coordonnées (posX,posY) sur un Plateau plateau*/
	{
		this.plateau     = plateau;
		this.tabCooCorps = new int[ plateau.getLongueur() *plateau.getHauteur() ][ plateau.getDimension() ];
		this.longueur    = 1;
		this.dir         = 'N';
		this.tabCooCorps[ this.longueur-1 ][ 0 ] = posX;
		this.tabCooCorps[ this.longueur-1 ][ 1 ] = posY;
	}

	/*-------------------------------*/
	/* ACCESSEURS                    */
	/*-------------------------------*/

	public int getX()
	{
		return this.tabCooCorps[ this.longueur-1 ][ 0 ];
	}

	public int getY()
	{
		return this.tabCooCorps[ this.longueur-1 ][ 1 ];
	}

	/*-------------------------------*/
	/* METHODES                      */
	/*-------------------------------*/

	public void manger( char coord )
	/*agrandir sans retrecir*/
	{
		agrandir( coord );
	}

	private void agrandir( char coord )
	/*agrandi le serpent avec une nouvelle partie de corps*/
	{
		int posX, posY;

		posX = this.tabCooCorps[ this.longueur-1 ][ 0 ];
		posY = this.tabCooCorps[ this.longueur-1 ][ 1 ];
		switch( coord )
		{
			case 'N' -> posY--;
			case 'O' -> posX--;
			case 'S' -> posY++;
			case 'E' -> posX++;
		}
		this.longueur++;
		this.tabCooCorps[ this.longueur-1 ][ 0 ] = posX;
		this.tabCooCorps[ this.longueur-1 ][ 1 ] = posY;
	}

	private void decaler()
	/*décale toutes les partie du corps du serpent*/
	{
		int cptLong, cptCoo;

		cptLong = 0;
		while( cptLong < this.longueur )
		{
			cptCoo = 0;
			while( cptCoo < this.plateau.getDimension() )
			{
				this.tabCooCorps[ cptLong ][ cptCoo ] = this.tabCooCorps[ cptLong+1 ][ cptCoo ];

				cptCoo++;
			}

			cptLong++;
		}
	}

	private void retrecir()
	/*supprimer l'index du dernier élément de la liste*/
	{
		this.longueur--;
	}

	public void deplacer( char coord )
	/*déplace tout le corps du serpent avec les même mouvements*/
	{
		agrandir( coord );
		retrecir();
		decaler();
	}

	public void mourir()
	/*tue le serpent*/
	{
		this.longueur = 0;
	}

	public boolean estVivant()
	{
		int cpt;
		int posX, posY;

		posX = this.tabCooCorps[ this.longueur-1 ][ 0 ];
		posY = this.tabCooCorps[ this.longueur-1 ][ 1 ];

		cpt = 0;
		while( cpt < this.longueur -1 ) //le -1 permet de ne pas prendre deux fois la tête du serpent
		{
			//tester si le serpent ne se mange pas le corps
			if( this.tabCooCorps[ cpt ][ 0 ] == posX &&
				this.tabCooCorps[ cpt ][ 1 ] == posY 	)
			{
				return false;
			}

			//tester si le serpent n'est pas sortit du plateau
			if( posX >= this.plateau.getLongueur() || posX < 0 ||
				posY >= this.plateau.getHauteur() || posY < 0 	)
			{
				return false;
			}

			cpt++;
		}

		return true;
	}

	private char getCara( int cptLig, int cptCol, Pomme pomme )
	/*retourne le caractère correspondant à l'élément à la case (cptLig, cptCol)*/
	{
		int cpt;
		int precX, posX, suivX;
		int precY, posY, suivY;

		if( cptCol == pomme.getX() && cptLig == pomme.getY() )
		{
			return '@';
		}

		cpt = 0;
		while( cpt < this.longueur )
		{

			posX  = this.tabCooCorps[ cpt ][ 0 ];
			suivX = this.tabCooCorps[ cpt+1 ][ 0 ];
			posY  = this.tabCooCorps[ cpt ][ 1 ];
			suivY = this.tabCooCorps[ cpt+1 ][ 1 ];

			if( posX == cptCol && posY == cptLig )
			{

				if( cpt == this.longueur-1 ) //si c'est la tête
				{
					return '\u022b'; //ȫ
				}

				if( cpt == 0 ) //si c'est la queue
				{
					if( suivX > posX )
					{
						return '\u255e'; // ╞
					}
					if( suivX < posX )
					{
						return '\u2561'; // ╡
					}
					if( suivY > posY )
					{
						return '\u2565'; // ╥
					}
					if( suivY < posY )
					{
						return '\u2568'; // ╨
					}

					return '-';
				}

				precX = this.tabCooCorps[ cpt-1 ][ 0 ];
				precY = this.tabCooCorps[ cpt-1 ][ 1 ];

				//sinon c'est le corps :
				if( precX < posX && suivY > posY ||
					suivX < posX && precY > posY 	)
				{
					return '\u2557'; // ╗
				}
				if( precX < posX && suivY < posY ||
					suivX < posX && precY < posY 	)
				{
					return '\u255d'; // ╝
				}
				if( precX > posX && suivY < posY ||
					suivX > posX && precY < posY 	)
				{
					return '\u255a'; // ╚
				}
				if( precX > posX && suivY > posY ||
					suivX > posX && precY > posY 	)
				{
					return '\u2554'; // ╔
				}
				if( precX < posX && suivX > posX ||
					suivX < posX && precX > posX 	)
				{
					return '\u2550'; // ═
				}
				if( precY < posY && suivY > posY ||
					suivY < posY && precY > posY 	)
				{
					return '\u2551'; // ║
				}


			}
			cpt++;
		}

		return '*'; //caractère représentant une case de grille
	}

	public String toString( Pomme pomme )
	/*retourne les propriétés du serpent*/
	{
		int cptLig, cptCol;
		String s;

		s="";

		cptLig = 0;
		while( cptLig < this.plateau.getHauteur() )
		{
			cptCol = 0;
			while( cptCol < this.plateau.getLongueur() )
			{
				s = s + getCara( cptLig, cptCol, pomme );

				cptCol++;
			}
			s = s + "\n";
			cptLig++;
		}

		return s;
	}

/*ȫ║╔╗╚╝═□₪*/
/* ╨ ╞ ╡ ╥ */
/*
ȫ
║╔══╗
╚╝  ╨
*/
}
