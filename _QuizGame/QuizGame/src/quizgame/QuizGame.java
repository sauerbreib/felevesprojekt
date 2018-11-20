/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Németh & Sauerbrei
 */
public class QuizGame extends JFrame implements ActionListener, KeyListener{
    private JPanel input, add;
    private JButton ujJatekGomb, ujKerdesGomb, szabalyGomb;

    public static void main(String[] args) {
        QuizGame kviz = new QuizGame("Quiz Game");
    }
    
    public QuizGame (String title) {
        super(title);
        mainGraphics();
    }
    
    private void mainGraphics() {
        this.setLayout(new GridLayout(5, 5));
        Container a = this.getContentPane();
        
        input = new JPanel(new GridLayout(5, 5));
        a.add(input);
        add = new JPanel();
        a.add(add);
        ujJatekGomb = new JButton("Új játék kezdése");
        add.add(ujJatekGomb);
        ujJatekGomb.addActionListener(this);
        ujJatekGomb.addKeyListener(this);
        ujKerdesGomb = new JButton("Új kérdés felvitele");
        add.add(ujKerdesGomb);
        ujKerdesGomb.addActionListener(this);
        ujKerdesGomb.addKeyListener(this);
        szabalyGomb = new JButton("Súgó");
        add.add(szabalyGomb);
        szabalyGomb.addActionListener(this);
        szabalyGomb.addKeyListener(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.centerWindow(this);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public static void centerWindow(java.awt.Window frame) {
	java.awt.Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	frame.setLocation(x, y);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ujJatekGomb)){  
            newgame();
        }
        if (e.getSource().equals(ujKerdesGomb)){   
            KerdesDialog kd = new KerdesDialog(this, Dialog.ModalityType.APPLICATION_MODAL);
        } 
        if (e.getSource().equals(szabalyGomb)){
            szabaly();
        }        
    }    
    
    
    private void szabaly(){
        
        JOptionPane.showMessageDialog(rootPane,"1 kérdés, 1 jó válasz, 4 lehetőség\n\n\n"
        + "Játékmódok \n\n"
                + "Normál: 2 segítség, a válaszlehetőségek számát lehet felezni \n"
                + "Nehéz: a kérdések megválaszolásához nem áll rendelkezésre segítség \n");
    }

    
    private void newgame() {
        Beallit beallit = new Beallit(this, Dialog.ModalityType.APPLICATION_MODAL);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
   private void RandNumber(){
        int nr= (int)(Math.random()*1000000000*+1);
        JOptionPane.showMessageDialog(rootPane,nr);
    }

    @Override
    public void keyTyped(KeyEvent e) {        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode()==KeyEvent.VK_F1){
            szabaly();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {        
    }
    
}
