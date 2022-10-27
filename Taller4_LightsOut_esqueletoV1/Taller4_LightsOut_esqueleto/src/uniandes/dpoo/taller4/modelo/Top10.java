package uniandes.dpoo.taller4.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Esta clase sirve para representar la lista de los records que se encuentran
 * en el Top10.
 * 
 * Esta clase utiliza una cola de prioridades para mantener los records
 * ordenados, y usa un Comparator para decidir el orden relativo entre records.
 * 
 * No es una implementación particularmente eficiente (sería más eficiente haber
 * usado una lista) pero permite ilustrar el uso de otras clases del framework
 * de colecciones y demostrar el uso de comparadores.
 * 
 * Esta clase también incluye mecanismos de persistencia sobre archivos CSV
 */
public class Top10
{
	// ********************************
	// Atributos
	// ********************************

	/**
	 * La cola de prioridades donde se almacenan los registros.
	 */
	private PriorityQueue<RegistroTop10> registros;

	/**
	 * El peor puntaje almacenado hasta el momento. Este valor se almacena para no
	 * tener que recorrer la cola completa para poder saber si un nuevo record debe
	 * entrar o no al top-10 (conocer el último elementos de una Cola de Prioridades
	 * es O(n) )
	 */
	private int peorPuntaje;

	// ********************************
	// Constructores
	// ********************************

	/**
	 * Construye un nuevo Top10 donde todas las posiciones empiezan ocupadas con un
	 * registro de 0 puntos.
	 */
	public Top10()
	{
		// Construye una nueva cola de prioridades y la inicializa con un
		// comparador para Registros. Este comparador se usará cada vez
		// que se agregue un elemento a la cola y se basará en los puntos.
		// El comparador se implementa como una clase interna anónima porque
		// no se usará en ninguna otra parte del programa y no tiene sentido
		// reutilizarla.
		registros = new PriorityQueue<>(new Comparator<RegistroTop10>()
		{
			@Override
			public int compare(RegistroTop10 o1, RegistroTop10 o2)
			{
				int p1 = o1.darPuntos();
				int p2 = o2.darPuntos();
				return p1 > p2 ? -1 : (p1 == p2 ? 0 : 1);
			}
		});

		for (int i = 0; i < 10; i++)
		{
			RegistroTop10 r = new RegistroTop10("AAA", 0);
			registros.add(r);
		}
		peorPuntaje = 0;
	}

	// ********************************
	// Métodos
	// ********************************

	/**
	 * Indica si el puntaje indicado es suficiente para entrar al top10
	 * 
	 * @param puntaje El puntaje que se va a revisar
	 * @return True si el puntaje debería estar en el top10
	 */
	public boolean esTop10(int puntaje)
	{
		return puntaje > peorPuntaje;
	}

	/**
	 * Agrega un nuevo registro al top10, en la posición correcta de acuerdo a la
	 * cantidad de puntos.
	 * 
	 * @param nombre  El nombre que debe quedar asociado al nuevo puntaje
	 * @param puntaje El puntaje que va a entrar al top10
	 */
	public void agregarRegistro(String nombre, int puntaje)
	{
		RegistroTop10 nuevoRegistro = new RegistroTop10(nombre, puntaje);
		registros.add(nuevoRegistro);

		// Si hay más de 10 registros en el top 10 después de agregar el nuevo, debe
		// eliminarse el peor.
		// En una cola de prioridades no se pueden eliminar elementos de una posición
		// particular, así que es necesario sacar los 10 mejores registros a una lista
		// temporal y luego reconstruir la cola con estos elementos
		if (registros.size() > 10)
		{
			ArrayList<RegistroTop10> temp = new ArrayList<>();
			for (int i = 0; i < 10; i++)
			{
				temp.add(registros.poll());
			}
			peorPuntaje = temp.get(9).darPuntos();

			registros.clear();
			registros.addAll(temp);
		}
	}

	/**
	 * Retorna los registros de una manera en la que se puedan recorrer pero no
	 * modificar
	 * 
	 * @return Los registros del top 10, vistos como una colección.
	 */
	public Collection<RegistroTop10> darRegistros()
	{
		return registros;
	}

	/**
	 * Salva los registros del top 10 en un archivo de texto UTF-8 y usando el
	 * siguiente formato:
	 * 
	 * - En cada línea aparece un registro
	 * 
	 * - Cada registro tiene el nombre y los puntos separados por el caracter ';'
	 * 
	 * @param archivo El archivo donde se debe almacenar la información
	 * @throws FileNotFoundException        Esta excepción se lanza si hay problemas
	 *                                      creando el archivo
	 * @throws UnsupportedEncodingException Esta excepción se lanza si por algún
	 *                                      motivo no puede usarse el encoding utf-8
	 *                                      para guardar la información dentro del
	 *                                      archivo.
	 */
	public void salvarRecords(File archivo) throws FileNotFoundException, UnsupportedEncodingException
	{
		OutputStream os = new FileOutputStream(archivo);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		Collection<RegistroTop10> temp = darRegistros();
		for (RegistroTop10 reg : temp)
		{
			String nombre = reg.darNombre();
			int puntos = reg.darPuntos();
			pw.println(nombre + ";" + puntos);
		}
		pw.close();
	}

	/**
	 * Carga la información de los records a partir de un archivo de texto que debe
	 * estar en el siguiente formato:
	 * 
	 * - En cada línea aparece un registro
	 * 
	 * - Cada registro tiene el nombre y los puntos separados por el caracter ';'
	 * 
	 * @param archivo El archivo donde se encuentra almacenada la información de los
	 *                records. Si el archivo no existe no se hace nada pero no se
	 *                produce un error.
	 */
	public void cargarRecords(File archivo)
	{
		if (archivo.exists())
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				String linea = br.readLine();
				while (linea != null)
				{
					String[] partes = linea.split(";");
					String nombre = partes[0];
					int puntos = Integer.parseInt(partes[1]);
					agregarRegistro(nombre, puntos);

					linea = br.readLine();
				}
				br.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("No encontré el archivo ...");
				e.printStackTrace();
			}
			catch (IOException e)
			{
				System.out.println("Error de lectura ...");
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				System.out.println("Error en los datos: un número se pudo convertir a int ...");
				e.printStackTrace();
			}

		}
	}
}
