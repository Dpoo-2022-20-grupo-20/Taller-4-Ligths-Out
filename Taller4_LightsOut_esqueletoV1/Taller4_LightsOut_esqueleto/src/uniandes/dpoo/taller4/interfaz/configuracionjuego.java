package uniandes.dpoo.taller4.interfaz;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.event.*;
import uniandes.dpoo.taller4.interfaz.*;

public class configuracionjuego extends JPanel{
		private JComboBox<String> combo1;
		private JLabel txt_tamanio;
		private JLabel txt_dificultad;
		private JRadioButton dificultad_facil;
		private JRadioButton dificultad_medio;
		private JRadioButton dificultad_dificil;
		public configuracionjuego(interfazjuego interfaz){
		combo1=new JComboBox<String>();
        combo1.setBounds(10,10,80,20);
        combo1.addItem("4x4");
        combo1.addItem("5x5");
        combo1.addItem("6x6");
        combo1.addItem("7x7");
       
        
        setDificultad_facil(new JRadioButton("Fácil"));
		setDificultad_medio(new JRadioButton("Medio"));
		setDificultad_dificil(new JRadioButton("Dificíl"));
		ButtonGroup grupo_botones = new ButtonGroup();
		grupo_botones.add(getDificultad_dificil());
		grupo_botones.add(getDificultad_medio());
		grupo_botones.add(getDificultad_facil());
		txt_dificultad = new JLabel("Dificultad: ");
		txt_tamanio = new JLabel("Tamaño: ");
		
		add(txt_tamanio);
		add(combo1);
		add(txt_dificultad);
		add(getDificultad_facil());
		add(getDificultad_medio());
		add(getDificultad_dificil());
        
        /*Hace que el PanelTablero sea un ActionListener del ComboBox, es decir, cuando un evento suceda en combo1, esto podrá afectar al 
        PanelTablero*/
        combo1.addActionListener(interfaz.dartablerodejuego());
        getDificultad_facil().addActionListener(interfaz);
        getDificultad_medio().addActionListener(interfaz);
        getDificultad_dificil().addActionListener(interfaz);
		}
		
		 public void itemStateChanged(ItemEvent e) {
		       /*if (e.getSource()==combo1) {
		            String seleccionado=(String)combo1.getSelectedItem();
		     		        }*/
		 }
		 
		 public String obtenerTamanoTablero() {
			 return combo1.getSelectedItem().toString();
		 }

		public JRadioButton getDificultad_facil() {
			return dificultad_facil;
		}

		public void setDificultad_facil(JRadioButton dificultad_facil) {
			this.dificultad_facil = dificultad_facil;
		}

		public JRadioButton getDificultad_medio() {
			return dificultad_medio;
		}

		public void setDificultad_medio(JRadioButton dificultad_medio) {
			this.dificultad_medio = dificultad_medio;
		}

		public JRadioButton getDificultad_dificil() {
			return dificultad_dificil;
		}

		public void setDificultad_dificil(JRadioButton dificultad_dificil) {
			this.dificultad_dificil = dificultad_dificil;
		}
		 
	
}
