package uniandes.dpoo.taller4.modelo;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Esta clase sirve para representar un tablero del juego LightsOut.
 * 
 * El juego gira alrededor de un tablero cuadrado con luces que pueden estar
 * prendidas o apagadas. En cada jugada, el jugador selecciona una luz y esta
 * cambia de estado (de prendida a apagada y viceversa). Lo mismo sucede con las
 * 8 luces vecinas a la luz seleccionada por el jugador.
 *
 * El objetivo del juego es lograr que todas las luces estén prendidas, en la
 * menor cantidad de jugadas posibles.
 */
public class Tablero
{
	public static void main(String [] args) {
		JFrame frame = new JFrame (); //crea el frame
		frame.setTitle("Lights Out Game Version 69 HD");// añade el titulo al frame	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permite salir del frame :)
		frame.setResizable(false);//evita que se pueda cambiar el tamanño de la app
		frame.setSize(694,694);//pone el tamaño del frame en x e y
		frame.setVisible(true); //hace que el frame sea visible
		ImageIcon image = new ImageIcon("LightsOutLogo.png");//crea la imagen del icono
		frame.setIconImage(image.getImage());//cambia la imagen del icono dedl frame
		//frame.getContentPane().setBackground(new Color(204,204,255)); //cambia el color del frame
	}
	// ********************************
	// Atributos
	// ********************************
	/**
	 * Una matriz con las casillas del tablero: true significa que está apagado
	 */
	private boolean[][] tablero;

	/**
	 * Una matriz con las casillas originales del tablero para poder repetir un
	 * juego
	 */
	private boolean[][] tablero_original;

	/**
	 * La cantidad de jugadas realizadas hasta el momento desde la última vez que se
	 * reinició el tablero.
	 */
	private int jugadas;

	// ********************************
	// Constructor
	// ********************************

	/**
	 * Crea un nuevo tablero del tamaño indicado. El tablero se crea con tamano x
	 * tamano casillas prendidas.
	 * 
	 * La cantidad de jugadas se inicializa en 0.
	 * 
	 * @param tamano El ancho y alto del tablero
	 */
	public Tablero(int tamano)
	{
		tablero = new boolean[tamano][tamano];
		tablero_original = new boolean[tamano][tamano];
		for (int i = 0; i < tablero.length; i++)
			for (int ii = 0; ii < tablero.length; ii++)
			{
				tablero[i][ii] = true;
				tablero_original[i][ii] = true;
			}
		jugadas = 0;
	}

	// ********************************
	// Métodos
	// ********************************

	/**
	 * Reinicia el tablero para que las casillas tengan los valores almacenados en
	 * tablero_original.
	 * 
	 * Deja la cantidad de jugadas con valor 0.
	 */
	public void reiniciar()
	{
		for (int i = 0; i < tablero.length; i++)
			for (int ii = 0; ii < tablero.length; ii++)
				tablero[i][ii] = tablero_original[i][ii];
		jugadas = 0;
	}

	/**
	 * Copia el tablero actual sobre la matriz tablero_original
	 */
	public void salvar_tablero()
	{
		for (int i = 0; i < tablero.length; i++)
			for (int ii = 0; ii < tablero.length; ii++)
				tablero_original[i][ii] = tablero[i][ii];
	}

	/**
	 * Desordena el tablero realizando una serie de jugadas sobre posiciones
	 * aleatorias. Si antes de ejecutar este método el tablero estaba todo
	 * iluminado, esto garantiza que se vaya a poder resolver después.
	 * 
	 * El número de jugadas realizadas se mantiene constante
	 * 
	 * @param dificultad La cantidad de jugadas aleatorias que se deben hacer.
	 */
	public void desordenar(int dificultad)
	{
		int temp = jugadas;
		for (int i = 0; i < dificultad; i++)
		{
			int tam = tablero.length;

			int fila = (int) (Math.random() * tam);
			int col = (int) (Math.random() * tam);
			jugar(fila, col);
		}
		salvar_tablero();
		jugadas = temp;
	}

	/**
	 * Retorna la cantidad de jugadas que se han hecho hasta el momento
	 * 
	 * @return jugadas
	 */
	public int darJugadas()
	{
		return jugadas;
	}

	/**
	 * Retorna el tablero completo en su estado actual
	 * 
	 * @return tablero
	 */
	public boolean[][] darTablero()
	{
		return tablero;
	}

	/**
	 * Cambia el estado de una casilla y el estado de las casillas que la rodean:
	 * las iluminadas se apagan y viceversa. Incrementa en 1 el número de jugadas
	 * realizadas hasta el momento.
	 * 
	 * @param fila    La fila de la casilla, contando desde 0
	 * @param columna La columna de la casilla, contando desde 0
	 */
	public void jugar(int fila, int columna)
	{
		int tam = tablero.length;

		for (int df = -1; df < 2; df++)
		{
			for (int dc = -1; dc < 2; dc++)
			{
				int f = fila + df;
				int c = columna + dc;
				if (f >= 0 && f < tam && c >= 0 && c < tam)
				{
					tablero[f][c] = !tablero[f][c];
				}
			}
		}
		jugadas++;
	}

	/**
	 * Informa si todas las casillas del tablero están iluminadas
	 * 
	 * @return
	 */
	public boolean tableroIluminado()
	{
		boolean iluminado = true;

		for (int i = 0; i < tablero.length && iluminado; i++)
			for (int ii = 0; ii < tablero.length && iluminado; ii++)
				iluminado = tablero[i][ii];

		return iluminado;
	}

	/**
	 * Calcula la cantidad de puntos que obtuvo el jugador
	 * 
	 * @return Una cantidad de puntos que depende del tamaño del tablero y de la
	 *         cantidad de jugadas que fueron necesarias
	 */
	public int calcularPuntaje()
	{
		int tam = tablero.length;
		int factorDificultad = tam * tam;

		return factorDificultad - jugadas;
	}
}
