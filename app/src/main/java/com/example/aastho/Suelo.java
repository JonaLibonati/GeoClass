package com.example.aastho;

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
        return IndicePlastidad() > (0.9 * (limiteLiquido - 8)) && limiteLiquido > 8;
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

}
