package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Top10;

public class opciones extends JPanel implements ActionListener{
	private JButton bttnRestart;
	private JButton bttnNewGame;
	private JButton bttnChangePlayer;
	private JButton bttnTop10;
	private interfazjuego interfaz;


	public opciones(interfazjuego inter) {
		interfaz = inter;
		
		setPreferredSize(new Dimension(300, 200));
		FlowLayout bl = new FlowLayout(100, 100, 100);
		setLayout(bl);

		bttnRestart = new JButton("Reiniciar Juego");
		bttnRestart.addActionListener(interfaz);
		
		bttnNewGame = new JButton("Nuevo Juego");
		bttnNewGame.addActionListener(interfaz);
		
		bttnChangePlayer = new JButton("Cambiar Jugador");
		bttnChangePlayer.addActionListener(interfaz);
			
		bttnTop10 = new JButton("Mejores 10");
		bttnTop10.addActionListener(interfaz);
		
		add(bttnRestart);
		add(bttnNewGame);
		add(bttnChangePlayer);
		add(bttnTop10);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}

	public JButton getbttnRestart() {
		return bttnRestart;
	}

	public void setbttnRestart(JButton bttnRestart) {
		this.bttnRestart = bttnRestart;
	}

	public JButton getbttnNewGame() {
		return bttnNewGame;
	}

	public void setbttnNewGame(JButton bttnNewGame) {
		this.bttnNewGame = bttnNewGame;
	}
	
	public JButton getbttnChangePlayer() {
		return bttnChangePlayer;
	}

	public void setbttnChangePlayer(JButton bttnChangePlayer) {
		this.bttnChangePlayer = bttnChangePlayer;
	}
	
	public JButton getbttnTop10() {
		return bttnTop10;
	}
}
