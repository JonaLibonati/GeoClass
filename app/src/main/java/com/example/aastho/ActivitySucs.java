package com.example.aastho;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


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

        EtEnable();


        try {

            int pasaTamiz4 = IntegerParse(et_n4);
            int pasaTamiz200 = IntegerParse(et_n200);
            int limiteLiquido = IntegerParse(et_LL);
            int limitePlastico = IntegerParse(et_LP);
            float DiamEfectivo = FloatParse(et_D10);
            float Diam30 = FloatParse(et_D30);
            float Diam60 = FloatParse(et_D60);
            int PerdidaMasa = IntegerParse(et_PM);

            ClasificacionSucs Suelo = new ClasificacionSucs(pasaTamiz4, pasaTamiz200, DiamEfectivo,
                    Diam30, Diam60, limiteLiquido, limitePlastico, PerdidaMasa );

            if (Suelo.LimiteSuelos() || Suelo.menor0mayor100()) {
                tv_grupo.setTextSize(22);
                tv_grupo.setText(Suelo.clasificar()[1]);
            } else {
                tv_grupo.setText(Suelo.clasificar()[1]);
                tv_grupo2.setText(Suelo.clasificar()[0]);
            }
        } catch (NumberFormatException e) {

            tv_grupo.setTextSize(22);
            tv_grupo.setText("¡Error!. Formato de dato/s incorrecto");
        }

        //EtNotEnable();
    }

    public void limpiar (View view){

        EtEnable();

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

    public void EtEnable () {

        et_n4.setEnabled(true);
        et_n200.setEnabled(true);
        et_LL.setEnabled(true);
        et_LP.setEnabled(true);
        et_D10.setEnabled(true);
        et_D30.setEnabled(true);
        et_D60.setEnabled(true);
        et_PM.setEnabled(true);
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
