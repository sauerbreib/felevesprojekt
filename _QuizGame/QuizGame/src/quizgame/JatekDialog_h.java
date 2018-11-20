/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import static quizgame.KerdesDialog.file;

/**
 *
 * @author Németh & Sauerbrei
 */
public class JatekDialog_h extends JDialog implements ActionListener{
    
    
    private JPanel kerdespanel, abvalaszpanel, cdvalaszpanel, help;
    private JButton agomb, bgomb, cgomb, dgomb, f;
    private JLabel kerdes, aValasz, bValasz, cValasz, dValasz;
    
    private BufferedReader br;
    
    private String megoldas;
    private ArrayList<Integer>kerdesszamlista=new ArrayList<>();
    private int szamlalo=0;
    


    public JatekDialog_h(Window window, Dialog.ModalityType mt){
        super(window, "Játék_nehéz", mt);
            init();
            game();
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            //this.addWindowListener(this);

    }

    private void init(){
        
        this.setLayout(new GridLayout(4, 3));
        
        Container lap = this.getContentPane();
        kerdespanel = new JPanel();
        lap.add(kerdespanel);
        kerdes = new JLabel("Kérdés");
        kerdespanel.add(kerdes);
        
        Container abvalaszlap = this.getContentPane();
        abvalaszpanel = new JPanel();
        abvalaszlap.add(abvalaszpanel);
        aValasz = new JLabel("A");
        abvalaszpanel.add(aValasz);
        agomb = new JButton("A");
        abvalaszpanel.add(agomb);        
        agomb.addActionListener(this);
        agomb.setVisible(false);
        bValasz = new JLabel("B");
        abvalaszpanel.add(bValasz);
        bgomb = new JButton("B");
        abvalaszpanel.add(bgomb);
        bgomb.addActionListener(this);
        bgomb.setVisible(false);
        
        Container cdvalaszlap = this.getContentPane();
        cdvalaszpanel = new JPanel();
        cdvalaszlap.add(cdvalaszpanel);        
        cValasz = new JLabel("C");
        cdvalaszpanel.add(cValasz);
        cgomb = new JButton("C");
        cdvalaszpanel.add(cgomb);
        cgomb.addActionListener(this);
        cgomb.setVisible(false);
        dValasz = new JLabel("D");
        cdvalaszpanel.add(dValasz);
        dgomb = new JButton("D");
        cdvalaszpanel.add(dgomb);
        dgomb.addActionListener(this);
        dgomb.setVisible(false);
        
        Container page = this.getContentPane();
        help = new JPanel();
        page.add(help);
        f = new JButton("START");
        help.add(f);
        f.addActionListener(this);
       
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
        if (e.getSource().equals(agomb)) {
                //            System.out.println("A");
                eredmeny(aValasz);
        }
        if (e.getSource().equals(bgomb)) {
                //            System.out.println("B");
                eredmeny(bValasz);
        }
        if (e.getSource().equals(cgomb)) {
                //            System.out.println("C");
                eredmeny(cValasz);
        }
        if (e.getSource().equals(dgomb)) {
                //            System.out.println("D");
                eredmeny(dValasz);
        }
        
        if (e.getSource().equals(f)) {
            game();
            f.setVisible(false);
        }
    }
    
//  ArrayList<Kerdes>auto=kiolvas();//a függvény eredményének átadása egy listának
    public ArrayList<Kerdes> kiolvas()throws IOException, ClassNotFoundException{
        System.setProperty("file.encoding", "UTF8");
        br = new BufferedReader(new FileReader(file));
        String ker;
        ArrayList<String> lista = new ArrayList<>();
        while ((ker=br.readLine()) != null){
            lista.add(ker);
        }
        br.close();//a kérdéseket tartalmazó fájl olvasása, a kérdések kigyűjtése egy String listába
        
        ArrayList<String> kerdeslista = new ArrayList<>();
        for (int i=0; i<10; i++){
            int szam=(int) (Math.random() * lista.size());
            kerdeslista.add(lista.get(szam));
        } //10 kérdés kiválasztása
        
        ArrayList<Kerdes>almafa=new ArrayList<>();
        for (int i=0; i<kerdeslista.size(); i++){
            String korte = kerdeslista.get(i);
            String[] kortefa = korte.split("#");
            Kerdes e= new Kerdes(kortefa[0],kortefa[1],kortefa[2],kortefa[3],kortefa[4],kortefa[5].substring(0,1));
            almafa.add(e);
        }//a kiválasztott kérdések Kerdes típusú listába rendezése
        return almafa;
    }
    
    public void eredmeny(JLabel x){
//        System.out.println(x.getText());
//        System.out.println("OKÉS");
        if(megoldas.equals(x.getText())){
            JOptionPane.showMessageDialog(rootPane,"A válasz helyes");
            game();
            szamlalo++;
        }else{
            JOptionPane.showMessageDialog(rootPane,"Sajnos nem, a helyes válasz a(z): "+megoldas+" lett volna");
            JOptionPane.showMessageDialog(rootPane,"A játéknak vége, helyes válaszok száma: "+szamlalo);
            this.setVisible(false);
        }
    }
    
    
    public void game(){
        try {
            ArrayList<Kerdes>auto=kiolvas();
            int szam=auto.size();
            while (szam != 0) {
                szam--;
                kerdes.setText(auto.get(szam).getKerdes());
                agomb.setVisible(true);agomb.setText(auto.get(szam).getA());
                bgomb.setVisible(true);bgomb.setText(auto.get(szam).getB());
                cgomb.setVisible(true);cgomb.setText(auto.get(szam).getC());
                dgomb.setVisible(true);dgomb.setText(auto.get(szam).getD());
                megoldas=auto.get(szam).getMegoldas();
//              System.out.println(megoldas);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JatekDialog_h.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    
    }
}