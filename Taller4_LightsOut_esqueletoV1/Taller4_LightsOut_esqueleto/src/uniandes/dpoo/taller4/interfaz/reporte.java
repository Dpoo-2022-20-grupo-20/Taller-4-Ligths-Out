package uniandes.dpoo.taller4.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class reporte extends JPanel implements ActionListener{
    
    private JLabel labelPlayerName;
    private JTextField labelCurrentPlayer;
    private JLabel labelMoves;
    private JTextField labelMovements;
    private interfazjuego interfaz;
    
    public reporte(interfazjuego interf){
    	interfaz = interf;
        setPreferredSize(new Dimension(700, 100));
        FlowLayout fl = new FlowLayout();
        setLayout(fl);

        labelPlayerName = new JLabel("Jugador: ");
        labelCurrentPlayer = new JTextField();
        labelCurrentPlayer.setPreferredSize(new Dimension(150,30));
        labelCurrentPlayer.setEditable(false);
        labelMoves = new JLabel("Jugadas: ");
        labelMovements = new JTextField();
        labelMovements.setPreferredSize(new Dimension(30,30));
        labelMovements.setEditable(false);
        
        add(labelPlayerName);
        add(labelCurrentPlayer);
        add(labelMovements);
        add(labelMoves);

    }
    
    public void actualizarContador(int n) {
    	String jugadas = String.valueOf(n);
    	labelMovements.setText(jugadas);
    }
    
    public void actualizarJugador(String name) {
    	labelCurrentPlayer.setText(name);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    public String getJugador() {
    	return labelCurrentPlayer.getText();
    }


}
