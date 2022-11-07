package uniandes.dpoo.taller4.interfaz;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class interfazjuego  extends JFrame implements ActionListener{

	private Tablero tablero;
	private tablerodejuego tablerodeJuego;
	private configuracionjuego configuracionesJuego;
	private reporte reporteJugadas;
	private opciones opcionesJuego;
	private Top10 top10;
	private int tamm = 4;
	private int dificultad;
	//El tamaño por defecto del tablero es 4x4
	
	private static final int FACIL = 3;
	private static final int MEDIO = 5;
	private static final int DIFICIL = 7;
	private static final File best = new File("src/uniandes/dpoo/taller4/best.txt"); //aqui se incluye el nombre del archivo .txt con los mejores resultados
	private static final Toolkit pantalla = Toolkit.getDefaultToolkit();
	private static final Image on = pantalla.getImage("src/uniandes/dpoo/taller4/imagenes/On.jpg"); //nombre de la imagen del bombillo encendido
	private static final Image off = pantalla.getImage("src/uniandes/dpoo/taller4/imagenes/Off.jpg"); //nombre de la imagen del bombillo apagado 
	private static final Image logo = pantalla.getImage("src/uniandes/dpoo/taller4/imagenes/LightsOutLogo.png");//logo de la aplicacion 
	
	public interfazjuego() {
		top10 = new Top10();
		top10.cargarRecords(best);
		tablero = new Tablero(tamm);
		tablerodeJuego = new tablerodejuego(on, this,tamm,off);
		setIconImage(logo);

		configuracionesJuego = new configuracionjuego(this);
		reporteJugadas = new reporte(this);
		opcionesJuego = new opciones(this);
		
		setTitle( "LightsOut Game Version 69 HD");//nombre del titulo del frame
		setSize( 820, 820 ); //tamaño del frame
		setResizable( false );// no permite que se agrande o minimice
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );//permite que se salga del ejecutable
		setVisible(true);//permite que el frame sea visible
		
		getContentPane().setBackground(Color.gray);//cambia el color del background del frame 
		
		add(configuracionesJuego, BorderLayout.NORTH);
		add(reporteJugadas, BorderLayout.SOUTH);
		add(tablerodeJuego, BorderLayout.CENTER);
		add(opcionesJuego, BorderLayout.EAST);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent x)
			{
				try {
					top10.salvarRecords(best);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//setVisible(true);
		
	}

	public static void main(String[] args) {
		new interfazjuego();
	}
		
	private void replayy() {
			tablero.reiniciar();
			tablerodeJuego.actualizarTablero();
			reporteJugadas.actualizarContador(tablero.darJugadas());
	}
	
	public void contadorjugadas() {
		reporteJugadas.actualizarContador(tablero.darJugadas());
	}

	public void finalizo() {
		if (tablero.tableroIluminado() && tablero.darJugadas()!=0) {
			JOptionPane.showMessageDialog(this,"¡Felicidades! "+reporteJugadas.getJugador().toUpperCase()+"\nCompletaste este tablero en "+tablero.darJugadas()+" jugadas.");
			top10.agregarRegistro(reporteJugadas.getJugador(), tablero.calcularPuntaje());
			replayy();
		}
	}
	
	//GETTERS
	
	public Tablero darTablero() {
		return this.tablero;
	}
	
	public tablerodejuego dartablerodejuego() {
		return this.tablerodeJuego;
	}
	
	public configuracionjuego darconfiguracionjuego() {
		return this.configuracionesJuego;
	}
	
	public Top10 darTop10() {
		return top10;
	}
	
	public Image darIcono() {
		return on;//esto se modifico uwu!!!
	}
	
	//SETTERS
	public void setTablero(Tablero newTab) {
		tablero = newTab;
		reporteJugadas.actualizarContador(tablero.darJugadas());
	}
	
	public void setTamano(int n) {
		tamm = n;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==opcionesJuego.getbttnRestart()) {
			replayy();
		}
		
		if (e.getSource()==configuracionesJuego.getDificultad_facil()) {
			dificultad = FACIL;
		}
		
		if (e.getSource()==configuracionesJuego.getDificultad_medio()) {
			dificultad = MEDIO;
		}
		
		if (e.getSource()==configuracionesJuego.getDificultad_dificil()) {
			dificultad = DIFICIL;
		}
		
		if (e.getSource()==opcionesJuego.getbttnNewGame()) {
			replayy();
			tablero.desordenar(dificultad);
			tablerodeJuego.actualizarTablero();
		}
		
		if (e.getSource()==opcionesJuego.getbttnChangePlayer()) {
			String jugador = JOptionPane.showInputDialog(this, "Ingresa el nombre del Jugador");
			reporteJugadas.actualizarJugador(jugador);
		}
		
		if (e.getSource()==opcionesJuego.getbttnTop10()) {
			top10jugadores ventanaEmergente = new top10jugadores(this);
		}
		
	}

}
