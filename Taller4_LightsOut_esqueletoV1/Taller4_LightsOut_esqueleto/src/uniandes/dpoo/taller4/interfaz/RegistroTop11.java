package uniandes.dpoo.taller4.interfaz;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import uniandes.dpoo.taller4.modelo.*;


//Interfaz del juego Lights Out
public class InterfazLightsOut extends JFrame
{
	
	private ControllerModeloMundo controller;
	
	
	///NOTA: LA IMAGEN DEL TABLERO SE PONE EN UN JPANEL CON MOUSELISTENER
	
	
	private PanelConfiguracion panelConfiguracion;
	private PanelTablero panelTablero;
	private PanelOpciones panelOpciones;
	private PanelEstadisticas panelEstadisticas;
	
	
	public InterfazLightsOut()
	{
		setSize(600,600);
		setResizable(false);
		setTitle("Lights Out");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new BorderLayout());
		
		//JPanel norte = new JPanel( );
		//norte.setLayout( new BorderLayout( ) );
		//add( norte, BorderLayout.NORTH );
		panelConfiguracion = new PanelConfiguracion(this);
		//norte.add( panelConfiguracion, BorderLayout.NORTH);
		add(panelConfiguracion, BorderLayout.NORTH);		
		
		
				
	}

	public static void main(String[] pArgs)
	{
		InterfazLightsOut interfaz = new InterfazLightsOut();
		interfaz.setVisible(true);

	}
	

}
