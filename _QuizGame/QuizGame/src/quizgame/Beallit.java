/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

/**
 *
 * @author Németh & Sauerbrei
 */

import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Németh & Sauerbrei
 */
public class Beallit extends JDialog implements ActionListener{

    private JPanel input;
    private JLabel kerdes;
    private JButton normal, hard;

    
    public Beallit(Window window, Dialog.ModalityType mt) {
        super(window, "Nehézségi szint", mt);
        init();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //this.addWindowListener(this);

    }
    
    private void init() {
        this.setLayout(new GridLayout(5, 5));
        Container lap = this.getContentPane();
        
        kerdes = new JLabel("Válassz nehézségi fokozatot");
        lap.add(kerdes);
        
        input = new JPanel(new GridLayout(0, 2));
        lap.add(input);
        
        normal = new JButton("Normál");
        input.add(normal);
        normal.addActionListener(this);
        
        hard = new JButton("Nehéz");
        input.add(hard);
        hard.addActionListener(this);
        
        
        this.setSize(640, 480);
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
        if (e.getSource().equals(normal)) {
            JatekDialog_n jd= new JatekDialog_n(this, Dialog.ModalityType.APPLICATION_MODAL);
            this.setVisible(false);
        }
        if (e.getSource().equals(hard)) {
            JatekDialog_h jd = new JatekDialog_h(this, Dialog.ModalityType.APPLICATION_MODAL);
            this.setVisible(false);
        }

    }
    
}