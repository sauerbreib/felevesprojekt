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
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Németh & Sauerbrei
 */
public class KerdesDialog extends JDialog implements ActionListener {

    private JPanel panel, urlap, megold;
    private JButton vissza, felvitel;
    private JLabel kerdes, aValasz, bValasz, cValasz, dValasz, megoldas, id;
    private JTextField kerdesMezo, aValaszMezo, bValaszMezo, cValaszMezo, dValaszMezo;
    private CheckboxGroup cbg;
    private Checkbox a,b,c,d;
    private int kid;
    
    private BufferedWriter bw;
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public static String file = "question.dat";
    

    public KerdesDialog(Window window, Dialog.ModalityType mt) {
        super(window, "Új kérdés felvétele", mt);
        init();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //this.addWindowListener(this);

    }

    private void init() {
        this.setLayout(new GridLayout(3, 2));

        Container lap = this.getContentPane();
        urlap = new JPanel(new GridLayout(7, 2));
        lap.add(urlap);

        kerdes = new JLabel("Kérdés");
        kerdesMezo = new JTextField(25);
        urlap.add(kerdes);
        urlap.add(kerdesMezo);

        aValasz = new JLabel("A válasz");
        aValaszMezo = new JTextField(25);
        urlap.add(aValasz);
        urlap.add(aValaszMezo);

        bValasz = new JLabel("B válasz");
        bValaszMezo = new JTextField(25);
        urlap.add(bValasz);
        urlap.add(bValaszMezo);

        cValasz = new JLabel("C válasz");
        cValaszMezo = new JTextField(25);
        urlap.add(cValasz);
        urlap.add(cValaszMezo);

        dValasz = new JLabel("D válasz");
        dValaszMezo = new JTextField(25);
        urlap.add(dValasz);
        urlap.add(dValaszMezo);

        int kid = (int)(1000000000*Math.random())+1;
        id = new JLabel("Kérdés azonosító: "+kid);
        urlap.add(id);

        Container valasz = this.getContentPane();
        megold = new JPanel();
        valasz.add(megold);
        
        megoldas = new JLabel("Megoldás: ");
        megold.add(megoldas);
        cbg = new CheckboxGroup();
        a = new Checkbox("A", cbg, false);
        megold.add(a);
        b = new Checkbox("B", cbg, false);
        megold.add(b);
        c = new Checkbox("C", cbg, false);
        megold.add(c);
        d = new Checkbox("D", cbg, false);
        megold.add(d);

        
        
        Container gomb = this.getContentPane();
        panel = new JPanel();
        gomb.add(panel);
        
        vissza = new JButton("Vissza");
        panel.add(vissza);
        vissza.addActionListener(this);
        
        felvitel = new JButton("Felvitel");
        panel.add(felvitel);
        felvitel.addActionListener(this);
        
        
        this.setSize(500, 350);
        this.centerWindow(this);
        setResizable(false);
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
        if (e.getSource().equals(vissza)) {
            visszalepes();
        }
        if (e.getSource().equals(felvitel)) {
          if(kerdesMezo.getText().isEmpty() || aValaszMezo.getText().isEmpty() || bValaszMezo.getText().isEmpty() ||
                  cValaszMezo.getText().isEmpty() || dValaszMezo.getText().isEmpty()){
              JOptionPane.showMessageDialog(rootPane,"Minden mező kitöltése kötelező!");
          }
          else try {
              feltolt();
          } catch (IOException ex) {
              JOptionPane.showMessageDialog(rootPane,"Fájl hiba");
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(rootPane,"Választ is meg kell adni!");
          }
        }
    }

    public void visszalepes() {
        KerdesDialog.this.setVisible(false);
    }

    private void feltolt() throws IOException {
        kid = (int)(Math.random()*1000000000*+1);
        id.setText("Kérdés azonosító: "+kid); 
        String m = null;
        if (cbg.getSelectedCheckbox().equals(a)) { m="A";}
        if (cbg.getSelectedCheckbox().equals(b)) { m="B";}
        if (cbg.getSelectedCheckbox().equals(c)) { m="C";}
        if (cbg.getSelectedCheckbox().equals(d)) { m="D";}
        
        bw = new BufferedWriter(new FileWriter(file, true));
        bw.newLine();
        bw.write(kerdesMezo.getText()+"#"+aValaszMezo.getText()+"#"+bValaszMezo.getText()+"#"+cValaszMezo.getText()+"#"
                +dValaszMezo.getText()+"#"+m+kid+";");
        bw.close();
        kerdesMezo.setText("");
        aValaszMezo.setText("");
        bValaszMezo.setText("");
        cValaszMezo.setText("");
        dValaszMezo.setText("");
        cbg.setSelectedCheckbox(null);
        JOptionPane.showMessageDialog(rootPane, "A kérdés rögzitésre került");
    }
}
