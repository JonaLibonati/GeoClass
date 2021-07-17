package com.example.geoclass;

public class ClasificacionSucs extends Suelo {

    public ClasificacionSucs(int pasaTamiz4, int pasaTamiz200, float diametroEfectivo,
                             float diametro30, float diametro60, int limiteLiquido,
                             int limitePlastico, int perdidaDeMasa) {

        super.pasaTamiz4 = pasaTamiz4;
        super.pasaTamiz200 = pasaTamiz200;
        super.diametroEfectivo = diametroEfectivo;
        super.diametro30 = diametro30;
        super.diametro60 = diametro60;
        super.limiteLiquido = limiteLiquido;
        super.limitePlastico = limitePlastico;
        super.perdidaDeMasa = perdidaDeMasa;
    }

    @Override
    public String [] clasificar () {
        String [] auxiliar = new String[2];
        auxiliar [0] = Nombre();
        auxiliar [1] = Codigo();
        return auxiliar;
    }

    //Metodo que determina el nombre

    public String Nombre () {

        String auxiliar = "";
        if (menor0mayor100() || LimiteSuelos() || LPmayorLL()) {
            auxiliar = "";
        }else if (FFEntre0a4()) {
            auxiliar = GravaArena()[1] + Graduado()[1] + ConGravaConArena()[0];

        } else if (FFEntre5a11()) {
            auxiliar = GravaArena()[1] + Graduado()[1] + LimoArcilla()[3] + ConGravaConArena()[1];

        } else if (FFEntre12a49()) {
            auxiliar = GravaArena()[1] + LimoArcilla()[2] + ConGravaConArena() [0];

        } else {
            if (EsArcilla()) {
                if (FGEntre0a14()) {
                    auxiliar = LimoArcilla()[1] + Plasticidad()[1];

                } else if (FGEntre15a29()) {
                    auxiliar = LimoArcilla()[1] + Plasticidad()[1] + GravaArena()[3];

                }else {
                    auxiliar = LimoArcilla()[1] + Plasticidad()[1] + GravaArena()[2] + ConGravaConArena()[0];
                }

            } if (EsLimo()) {
                if (FGEntre0a14()) {
                    auxiliar = LimoArcilla()[1] + Plasticidad()[2];

                } else if (FGEntre15a29()) {
                    auxiliar = LimoArcilla()[1] + Plasticidad()[2] + GravaArena()[3];

                }else {
                    auxiliar = LimoArcilla()[1] + Plasticidad()[2] + GravaArena()[2] + ConGravaConArena()[0];
                }

            } else if (EsLimoArcilla()) {
                if (FGEntre0a14()) {
                    auxiliar = LimoArcilla()[1];

                } else if (FGEntre15a29()) {
                    auxiliar = LimoArcilla()[1] + GravaArena()[3];

                }else {
                    auxiliar = LimoArcilla()[1] + GravaArena()[2] + ConGravaConArena()[0];
                }
            }
        }
        return auxiliar;
    }

    // Metodo que determina el codigo del suelo

    public String Codigo () {

        String auxiliar = "";

        if (menor0mayor100()) {
            auxiliar = "¡Error!\nPorcentajes fuera de 0 a 100";

        } else if (LimiteSuelos()) {
            auxiliar = "¡Error!\nFuera del límite de Casagrande";

        } else if (LPmayorLL()) {
            auxiliar = "¡Error!\nLímite plástico > Límite líquido";

        } else if (FFEntre0a4()) {
            auxiliar = GravaArena()[0] + Graduado()[0];

        }else if (FFEntre5a11()) {
            if (EsArcilla() || EsLimo()) {
                auxiliar = GravaArena()[0] + Graduado()[0] + "-" + GravaArena()[0] + LimoArcilla()[0];
            }
            if (EsLimoArcilla()) {
                auxiliar = GravaArena()[0] + Graduado()[0] + "-" + GravaArena()[0] + LimoArcilla()[0] + "-" + GravaArena()[0] + LimoArcilla()[3];
            }

        }else if (FFEntre12a49()) {
            if (EsArcilla() || EsLimo()) {
                auxiliar = GravaArena()[0] + LimoArcilla()[0];
            }
            if (EsLimoArcilla()) {
                auxiliar = GravaArena()[0] + LimoArcilla()[0] + "-" + GravaArena()[0] + LimoArcilla()[3];
            }

        } else {
            if (EsArcilla() || EsLimo()) {
                auxiliar = LimoArcilla()[0] + Plasticidad()[0];
            } else if (EsLimoArcilla()) {
                auxiliar = "CL-ML";
            }
        }
        return auxiliar;
    }

    //Metodo que determina si hay mas Grava que arena

    public String[] GravaArena() {

        String[] auxiliar = new String[4];

        if (GravasContenido() > ArenasContenido()) {
            auxiliar[0] = "G";
            auxiliar[1] = "Grava";
            auxiliar[2] = " tipo grava";
            auxiliar[3] = " con grava";
        } else {
            auxiliar[0] = "S";
            auxiliar[1] = "Arena";
            auxiliar[2] = " arenosa";
            auxiliar[3] = " con arena";
        }
        return auxiliar;
    }

    // Metodo que determina si es C o M

    public String [] LimoArcilla () {

        String[] auxiliar = new String[4];
        if (EsArcilla()){
            auxiliar [0] = "C";
            auxiliar [1] = "Arcilla";
            auxiliar [2] = " arcillosa";
            auxiliar [3] = " con arcilla";
        }
        if (EsLimo()) {
            auxiliar [0] = "M";
            auxiliar [1] = "Limo";
            auxiliar [2] = " limosa";
            auxiliar [3] = " con limo";
        }
        if (EsLimoArcilla()){
            auxiliar [0] = "C";
            auxiliar [1] = "Arcilla limosa";
            auxiliar [2] = " limo arcillosa";
            auxiliar [3] = "M";
        }
        return auxiliar;
    }

    //Metodo que determina si el suelo es bien o mal graduado

    public String [] Graduado () {

        String[] auxiliar = new String[2];

        if (GravaArena()[0].equals("G") && CoefUniformidad() >= 4  && CoefCurvatura() >= 1 && CoefCurvatura() <= 3) {
            auxiliar [0] = "W";
            auxiliar [1] = " bien graduada";
        }
        if (GravaArena()[0].equals("S") && CoefUniformidad() >= 6  && CoefCurvatura() >= 1 && CoefCurvatura() <= 3) {
            auxiliar [0] = "W";
            auxiliar [1] = " bien graduada";
        } else {
            auxiliar [0] = "P";
            auxiliar [1] = " mal graduada";
        }
        return auxiliar;
    }

    // Metodo que devuelve la plasticidad o elasticidad de limos o arcillas

    public String [] Plasticidad () {

        String[] auxiliar = new String[3];
        if (limiteLiquido >= 50) {
            auxiliar[0] = "H";
            auxiliar[1] = " densa";
            auxiliar[2] = " elástico";
        } else {
            auxiliar[0] = "L";
            auxiliar[1] = " ligera";
            auxiliar[2] = "";
        }
        return auxiliar;
    }

    //Metodo que determina si con arena o con grava

    public String[] ConGravaConArena() {

        String[] auxiliar = new String[2];
        if (GravasContenido() >= 15 && GravaArena()[0].equals("S")) {
            auxiliar[0] = " con grava";
            auxiliar[1] = " y grava";
        }else if (ArenasContenido() >= 15 && GravaArena()[0].equals("G")) {
            auxiliar[0] = " con arena";
            auxiliar[1] = " y arena";
        }else {
            auxiliar[0] = "";
            auxiliar[1] = "";
        }
        return auxiliar;
    }


}