package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import uniandes.dpoo.taller4.modelo.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;





//Interfaz del juego Lights Out Interfaz del juego Lights Out Interfaz del juego Lights Out Interfaz del juego Lights Out Interfaz del juego Lights Out
//public class paneldejuego extends JFrame{ //TENGO QUE CAMBIAR EL NOMBRE DEL ARCHIVO A Paneldejuego!!!!!
	
	//AQUI NO DEBO COMENTARprivate ControllerModeloMundo controller;
	
	
	///NOTA: LA IMAGEN DEL TABLERO SE PONE EN UN JPANEL CON MOUSELISTENER
	
	
	//AQUI NO DEBO COMENTARprivate PanelConfiguracion panelConfiguracion;
//AQUI NO DEBO COMENTARprivate PanelTablero panelTablero;
//AQUI NO DEBO COMENTARprivate PanelOpciones panelOpciones;
//AQUI NO DEBO COMENTARprivate PanelEstadisticas panelEstadisticas;
	
	
public class paneldejuego {
	public static void main(String[] args){
	//AQUI NO DEBO COMENTAR{
	
		JFrame frame = new JFrame ();
		frame.setTitle("Lights Out Game Version 69 HD"); //nombre del titulo del frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permite salir del frame :)
		frame.setResizable(false);//evita que se pueda cambiar el tamanño de la app
		frame.setSize(694,694);//pone el tamaño del frame en x e y
		frame.setVisible(true); //hace que el frame sea visible
		ImageIcon image = new ImageIcon("LightsOutLogo.png");//crea la imagen del icono
		frame.setIconImage(image.getImage());//cambia la imagen del icono del frame
		frame.getContentPane().setBackground(new Color(204,204,255)); //cambia el color del frame
		setLayout(new BorderLayout());
		}

	private static void setLayout(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}
}



	
	
		
		//JPanel norte = new JPanel( );
		//norte.setLayout( new BorderLayout( ) );
		//add( norte, BorderLayout.NORTH );
		//AQUI NO DEBO COMENTAR//panelConfiguracion = new PanelConfiguracion(this);
		//norte.add( panelConfiguracion, BorderLayout.NORTH);
		//AQUI NO DEBO COMENTARadd(panelConfiguracion, BorderLayout.NORTH);		
		
	
				
	

	//AQUI NO DEBO COMENTARpublic static void main(String[] pArgs)
	//AQUI NO DEBO COMENTAR{
		//AQUI NO DEBO COMENTARInterfazLightsOut interfaz = new InterfazLightsOut(); //TENGO QUE CAMBIAR EL NOMBRE DEL ARCHIVO A Paneldejuego!!!!!
		//AQUI NO DEBO COMENTARinterfaz.setVisible(true);

		//AQUI NO DEBO COMENTAR}
	

