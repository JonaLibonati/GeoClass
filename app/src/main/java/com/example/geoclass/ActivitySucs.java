package com.example.geoclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


public class ActivitySucs extends AppCompatActivity {

    private EditText et_n4;
    private EditText et_n200;
    private EditText et_LL;
    private EditText et_LP;
    private EditText et_D10;
    private EditText et_D30;
    private EditText et_D60;
    private EditText et_PM;
    private TextView tv_grupo;
    private TextView tv_grupo2;
    private TextInputLayout lay_D10;
    private TextInputLayout lay_D30;
    private TextInputLayout lay_D60;
    private TextInputLayout lay_LP;
    private TextInputLayout lay_LL;
    private TextInputLayout lay_PM;
    private int pasaTamiz4;
    private int pasaTamiz200;
    private int limiteLiquido;
    private int limitePlastico;
    private float DiamEfectivo;
    private float Diam30;
    private float Diam60;
    private int PerdidaMasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucs);
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
        // La actividad está Creada.

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de hacerse visible.
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
        // La actividad se ha vuelto visible (ahora se "reanuda").

        et_n4 = findViewById(R.id.et_pasaTamiz4);
        et_n200 = findViewById(R.id.et_pasaTamiz200);
        et_LL = findViewById(R.id.et_limiteLiquido);
        et_LP = findViewById(R.id.et_limitePlastico);
        et_D10 = findViewById(R.id.et_diametroEfec);
        et_D30 = findViewById(R.id.et_diametro30);
        et_D60 = findViewById(R.id.et_diametro60);
        et_PM = findViewById(R.id.et_PerdidaDeMasa);
        tv_grupo = findViewById(R.id.tx_grupo);
        tv_grupo2 = findViewById(R.id.tx_grupo2);
        lay_D10 = findViewById(R.id.label_Defec);
        lay_D30 = findViewById(R.id.label_D30);
        lay_D60 = findViewById(R.id.label_D60);
        lay_LL = findViewById(R.id.label_LL);
        lay_LP = findViewById(R.id.label_LP);
        lay_PM = findViewById(R.id.label_PM);

        datosNecesarios();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora está "detenida")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de ser destruida.
    }

    public void clasificar (View view) {

        try {
            pasaTamiz4 = IntegerParse(et_n4);
            pasaTamiz200 = IntegerParse(et_n200);

            if (pasaTamiz200 >= 0 && pasaTamiz200 < 12) {
                DiamEfectivo = FloatParse(et_D10);
                Diam30 = FloatParse(et_D30);
                Diam60 = FloatParse(et_D60);

            } else if (pasaTamiz200 >= 12 && pasaTamiz200 < 50) {
                limiteLiquido = IntegerParse(et_LL);
                limitePlastico = IntegerParse(et_LP);

            } else {
                limiteLiquido = IntegerParse(et_LL);
                limitePlastico = IntegerParse(et_LP);
                PerdidaMasa = IntegerParse(et_PM);
            }

            Suelo Suelo = new ClasificacionSucs(pasaTamiz4, pasaTamiz200, DiamEfectivo,
                    Diam30, Diam60, limiteLiquido, limitePlastico, PerdidaMasa );

            if (Suelo.menor0mayor100() || Suelo.LimiteSuelos() || Suelo.LPmayorLL()) {
                tv_grupo.setTextSize(22);
            }
                tv_grupo.setText(Suelo.clasificar()[1]);
                tv_grupo2.setText(Suelo.clasificar()[0]);
        } catch (NumberFormatException e) {

            tv_grupo.setTextSize(22);
            tv_grupo.setText("¡Error!. Formato de dato/s incorrecto");
        }

        EtNotEnable();
    }

    public void limpiar (View view){

        et_n4.setText(null);
        et_n200.setText(null);
        et_LL.setText(null);
        et_LP.setText(null);
        et_D10.setText(null);
        et_D30.setText(null);
        et_D60.setText(null);
        et_PM.setText(null);
        tv_grupo.setText(null);
        tv_grupo2.setText(null);

        et_n4.setEnabled(true);
        et_n200.setEnabled(true);
        et_D10.setEnabled(false);
        et_D30.setEnabled(false);
        et_D60.setEnabled(false);
        et_LL.setEnabled(false);
        et_LP.setEnabled(false);
        et_PM.setEnabled(false);
        lay_D10.setEnabled(false);
        lay_D30.setEnabled(false);
        lay_D60.setEnabled(false);
        lay_LL.setEnabled(false);
        lay_LP.setEnabled(false);
        lay_PM.setEnabled(false);
    }

    public void EtNotEnable () {

        et_n4.setEnabled(false);
        et_n200.setEnabled(false);
        et_LL.setEnabled(false);
        et_LP.setEnabled(false);
        et_D10.setEnabled(false);
        et_D30.setEnabled(false);
        et_D60.setEnabled(false);
        et_PM.setEnabled(false);
    }

    // Metodo para solamente pedir los datos necesarios

    public void datosNecesarios () {
        et_n200.addTextChangedListener(new TextWatcher () {

            public void afterTextChanged(Editable s) {

                try {
                    pasaTamiz200 = IntegerParse(et_n200);
                } catch (NumberFormatException e){}

                if (pasaTamiz200 >= 0 && pasaTamiz200 < 12) {
                    et_D10.setEnabled(true);
                    et_D30.setEnabled(true);
                    et_D60.setEnabled(true);
                    et_LL.setEnabled(false);
                    et_LP.setEnabled(false);
                    et_PM.setEnabled(false);
                    lay_D10.setEnabled(true);
                    lay_D30.setEnabled(true);
                    lay_D60.setEnabled(true);
                    lay_LL.setEnabled(false);
                    lay_LP.setEnabled(false);
                    lay_PM.setEnabled(false);
                    et_LL.setText(null);
                    et_LP.setText(null);
                    et_PM.setText(null);

                } else if (pasaTamiz200 >= 12 && pasaTamiz200 < 50){
                    et_D10.setEnabled(false);
                    et_D30.setEnabled(false);
                    et_D60.setEnabled(false);
                    et_LL.setEnabled(true);
                    et_LP.setEnabled(true);
                    et_PM.setEnabled(false);
                    lay_D10.setEnabled(false);
                    lay_D30.setEnabled(false);
                    lay_D60.setEnabled(false);
                    lay_LL.setEnabled(true);
                    lay_LP.setEnabled(true);
                    lay_PM.setEnabled(false);
                    et_D10.setText(null);
                    et_D30.setText(null);
                    et_D60.setText(null);
                    et_PM.setText(null);

                } else {
                    et_D10.setEnabled(false);
                    et_D30.setEnabled(false);
                    et_D60.setEnabled(false);
                    et_LL.setEnabled(true);
                    et_LP.setEnabled(true);
                    et_PM.setEnabled(true);
                    lay_D10.setEnabled(false);
                    lay_D30.setEnabled(false);
                    lay_D60.setEnabled(false);
                    lay_LL.setEnabled(true);
                    lay_LP.setEnabled(true);
                    lay_PM.setEnabled(true);
                    et_D10.setText(null);
                    et_D30.setText(null);
                    et_D60.setText(null);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    pasaTamiz200 = IntegerParse(et_n200);
                } catch (NumberFormatException e){}
            }
        });
    }


    public int IntegerParse (EditText editText){
        String et = editText.getText().toString();
        return Integer.parseInt(et);

    }

    public float FloatParse (EditText editText){
        String et = editText.getText().toString();
        return Float.parseFloat(et);
    }
}
