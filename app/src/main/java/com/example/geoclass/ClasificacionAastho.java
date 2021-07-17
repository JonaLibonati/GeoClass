package com.example.geoclass;

import java.text.DecimalFormat;

public class ClasificacionAastho extends Suelo {

    public ClasificacionAastho(int pasaTamiz200, int pasaTamiz40, int pasaTamiz10, int limiteLiquido, int limitePlastico ) {
        super.pasaTamiz200 = pasaTamiz200 ;
        super.pasaTamiz40 = pasaTamiz40;
        super.pasaTamiz10 = pasaTamiz10;
        super.limiteLiquido = limiteLiquido;
        super.limitePlastico = limitePlastico;
    }

    // Metodo que devuelve el nombre completo

    @Override
    public String [] clasificar () {

        String[] auxiliar = new String [2];
        auxiliar [0] = Grupo() [0] + IndiceGrupo();
        auxiliar [1] = Grupo() [1];

        return auxiliar;
    }

    // Metodo que devuelve el nombre del grupo

    public String [] Grupo() {

        String[] auxiliar = new String [2];

        if (menor0mayor100()) {
            auxiliar [0] = "¡Error!\nLos valores indicados no son válidos";

        } else if(LimiteSuelos()) {
            auxiliar [0] = "Revisar. Suelo encima de Linea U [Casagrande]";

        } else if (grupoA1a()) {
            auxiliar [0] = "Grupo A1.a";
            auxiliar [1] = "Gravas y arenas";

        } else if (grupoA1b()) {
            auxiliar [0] = "Grupo A1.b";
            auxiliar [1] = "Grava y arena";

        } else if (grupoA3()) {
            auxiliar [0] = "Grupo A3";
            auxiliar [1] = "Arena Fina";

        } else if (grupoA24()) {
            auxiliar [0] = "Grupo A2.4";
            auxiliar [1] = "Grava y arena limosa";

        } else if (grupoA25()) {
            auxiliar [0] = "Grupo A2.5";
            auxiliar [1] = "Grava y arena limosa";

        } else if (grupoA26()) {
            auxiliar [0] = "Grupo A2.6";
            auxiliar [1] = "Grava y arena arcillosa";

        } else if (grupoA27()) {
            auxiliar [0] = "Grupo A2.7";
            auxiliar [1] = "Grava y arena arcillosa";

        } else if (grupoA4()) {
            auxiliar [0] = "Grupo A4";
            auxiliar [1] = "Suelo limoso";

        } else if (grupoA5()) {
            auxiliar [0] = "Grupo A5";
            auxiliar [1] = "Suelo limoso";

        } else if (grupoA6()) {
            auxiliar [0] = "Grupo A6";
            auxiliar [1] = "Suelo arcilloso";

        } else if (grupoA75()) {
            auxiliar [0] = "Grupo A7.5";
            auxiliar [1] = "Suelo arcilloso";

        } else if (grupoA76()) {
            auxiliar [0] = "Grupo A7.6";
            auxiliar [1] = "Suelo arcilloso";

        } else {
            auxiliar [0] = "¡Error! Los valores indicados no son válidos";
        }
        return auxiliar;
    }

    //Metodo que devuelve el indice de grupo

    public String IndiceGrupo () {
        DecimalFormat df = new DecimalFormat("#");
        double IG;
        String auxiliar = "";

        if (grupoA1a() || grupoA1b() || grupoA3() || grupoA24() || grupoA25()) {
            auxiliar = " [0]";

        } else if (grupoA26() || grupoA27()) {
            IG = (pasaTamiz200 - 15) * (IndicePlastidad() - 10) * 0.01;
            auxiliar = " [" + df.format(IG) + "]";

        } else if (grupoA4() || grupoA5() || grupoA6() || grupoA75() || grupoA76()) {
            IG = ((pasaTamiz200-35) * (0.2 + 0.005 * (limiteLiquido - 40)) + ((pasaTamiz200 - 15) * (IndicePlastidad() - 10) * 0.01));
            auxiliar = " [" + df.format(IG) + "]";
        }
        return auxiliar;
    }

   // Metodos que devuelve condicion de cada grupo

    public boolean grupoA1a() {
        return pasaTamiz10 <= 50 && pasaTamiz40 <= 30 && pasaTamiz200 <= 15 && IndicePlastidad() <= 6;
    }

    public boolean grupoA1b() {
        return pasaTamiz40 <= 50 && pasaTamiz200 <= 25;
    }

    public boolean grupoA3() {
        return pasaTamiz40 > 50 && pasaTamiz200 <= 10;
    }

    public boolean grupoA24() {
        return pasaTamiz200 <= 35 && IndicePlastidad() <= 10 && limiteLiquido <= 40;
    }

    public boolean grupoA25() {
        return pasaTamiz200 <= 35 && IndicePlastidad() <= 10 && limiteLiquido > 40;
    }

    public boolean grupoA26() {
        return pasaTamiz200 <= 35 && IndicePlastidad() > 10 && limiteLiquido <= 40;
    }

    public boolean grupoA27() {
        return pasaTamiz200 <= 35 && IndicePlastidad() > 10 && limiteLiquido > 40;
    }

    public boolean grupoA4() {
        return pasaTamiz200 > 35 && IndicePlastidad() <= 10 && limiteLiquido <= 40;
    }

    public boolean grupoA5() {
        return pasaTamiz200 > 35 && IndicePlastidad() <= 10 && limiteLiquido > 40;
    }

    public boolean grupoA6() {
        return pasaTamiz200 > 35 && IndicePlastidad() > 10 && limiteLiquido <= 40;
    }

    public boolean grupoA75() {
        return pasaTamiz200 > 35 && IndicePlastidad() > 10 && limiteLiquido > 40 && IndicePlastidad() <= limiteLiquido - 30;
    }

    public boolean grupoA76() {
        return pasaTamiz200 > 35 && IndicePlastidad() > 10 && limiteLiquido > 40 && IndicePlastidad() > limiteLiquido - 30;
    }

    // Metodo para validad que los datos se encuentren entre 0 y 100 %

    public boolean menor0mayor100() {
        return pasaTamiz200 < 0 || pasaTamiz200 > 100 || pasaTamiz40 < 0 || pasaTamiz40 > 100 ||
                pasaTamiz10 < 0 || pasaTamiz10 > 100 || limiteLiquido <0 || limiteLiquido > 100 ||
                limitePlastico < 0 || limitePlastico > 100;
    }
}

