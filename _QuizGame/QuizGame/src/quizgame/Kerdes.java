/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizgame;

import java.io.Serializable;

/**
 *
 * @author Németh & Sauerbrei
 */
public class Kerdes implements Serializable{
    private String kerdes,a,b,c,d,megoldas;
    static final long serialVersionUID = 2345678L;

    public Kerdes(String kerdes, String a, String b, String c, String d, String megoldas) {
        this.kerdes = kerdes;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.megoldas = megoldas;
}

    public String getKerdes() {
        return kerdes;
    }

    public void setKerdes(String kerdes) {
        this.kerdes = kerdes;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getMegoldas() {
        return megoldas;
    }

    public void setMegoldas(String megoldas) {
        this.megoldas = megoldas;
    }
}
