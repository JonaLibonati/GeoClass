package com.example.geoclass;

public abstract class Suelo {

    protected int limiteLiquido;
    protected int limitePlastico;
    protected int pasaTamiz200;
    protected int pasaTamiz40;
    protected int pasaTamiz10;
    protected int pasaTamiz4;
    protected float diametroEfectivo;
    protected float diametro30;
    protected float diametro60;
    protected int perdidaDeMasa;

    //
    public abstract String [] clasificar();


    //Metodo que retorna indice de plasticidad

    public int IndicePlastidad () {
        return limiteLiquido - limitePlastico;
    }

    //Este metodo representa el limite superior de la carta de plasticidad de Casagrande.

    public boolean LimiteSuelos () {
        return IndicePlastidad() > 0.9 * (limiteLiquido - 8) || limiteLiquido <= 8;
    }

    // Metodo que calcula Coeficiente de Uniformidad

    public float CoefUniformidad () {
        return diametro60 / diametroEfectivo;
    }

    // Metodo que calcula coeficiente de curvatura

    public float CoefCurvatura () {
        return (diametro30*diametro30) / (diametroEfectivo * diametro60);
    }

    // Metodo que calcula el contenido de gravas

    public int GravasContenido () {
        return 100 - pasaTamiz4;
    }

    // Metodo que calcula el contenido de arenas

    public int ArenasContenido () {
        return pasaTamiz4 - pasaTamiz200;
    }

    //Metodo que determina si el suelo es organico o inorganico

    public boolean SueloOrganico () {
        return perdidaDeMasa >= 25;
    }

    //Metodo que determina si es arcilla

    public boolean EsArcilla () {
        return IndicePlastidad() > 7 && IndicePlastidad() > (0.73 * (limiteLiquido - 20));
    }

    //Metodo que determina si es limo

    public boolean EsLimo () {
        return (limiteLiquido > 20 && IndicePlastidad() <= (0.73 * (limiteLiquido - 20))) || IndicePlastidad() < 4;
    }

    //Metodo que determina si es limo-arcilla

    public boolean EsLimoArcilla () {
        return IndicePlastidad() >= 4 && IndicePlastidad() <= 7 && IndicePlastidad() > (0.73 * (limiteLiquido - 20));
    }

    //Metodo si valores entre0a100

    public boolean menor0mayor100() {
        return pasaTamiz200 < 0 || pasaTamiz200 > 100 || pasaTamiz4 < 0 || pasaTamiz4 > 100 ||
                perdidaDeMasa < 0 || perdidaDeMasa > 100 || limiteLiquido < 0 || limiteLiquido > 100 ||
                limitePlastico < 0 || limitePlastico > 100;
    }

    // Metodo si limite plastco mayor limite liquido

    public boolean LPmayorLL () {
        return limitePlastico > limiteLiquido;
    }

    // Metodo si suelo fino entre 0a4

    public boolean FFEntre0a4() {
        return pasaTamiz200 < 5;
    }

    // Metodo si suelo fino entre 5a11

    public boolean FFEntre5a11() {
        return pasaTamiz200 < 12 && pasaTamiz200 >= 5;
    }

    // Metodo si suelo fino entre 12a49

    public boolean FFEntre12a49() {
        return pasaTamiz200 < 50 && pasaTamiz200 >= 12;
    }

    //metodo que determina si Fraccion gruesa entre 0a14

    public boolean FGEntre0a14 () {
        return (ArenasContenido() + GravasContenido()) < 15;
    }

    //metodo que determina si Fraccion gruesa entre 15a29

    public boolean FGEntre15a29 () {
        return (ArenasContenido() + GravasContenido()) >= 15 && (ArenasContenido() + GravasContenido()) < 30;
    }
}
