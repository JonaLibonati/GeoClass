package com.example.geoclass;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAastho extends AppCompatActivity {

    private EditText et_n10;
    private EditText et_n40;
    private EditText et_n200;
    private EditText et_LL;
    private EditText et_LP;
    private TextView tv_grupo;
    private TextView tv_grupo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aastho);
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

        et_n10 = findViewById(R.id.et_pasaTamiz10);
        et_n40 = findViewById(R.id.et_pasaTamiz40);
        et_n200 = findViewById(R.id.et_pasaTamiz200);
        et_LL = findViewById(R.id.et_limiteLiquido);
        et_LP = findViewById(R.id.et_limitePlastico);
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

        try {
            int pasaTamiz10 = IntegerParse(et_n10);
            int pasaTamiz40 = IntegerParse(et_n40);
            int pasaTamiz200 = IntegerParse(et_n200);
            int limiteLiquido = IntegerParse(et_LL);
            int limitePlastico = IntegerParse(et_LP);

            Suelo Suelo = new ClasificacionAastho(pasaTamiz200, pasaTamiz40, pasaTamiz10,
                    limiteLiquido, limitePlastico);

            if (Suelo.LimiteSuelos() || Suelo.menor0mayor100() || Suelo.LPmayorLL()) {
                tv_grupo.setTextSize(22);
                tv_grupo.setText(Suelo.clasificar()[0]);
            } else {
                tv_grupo.setText(Suelo.clasificar()[0]);
                tv_grupo2.setText(Suelo.clasificar()[1]);
            }
        } catch (NumberFormatException e) {

            tv_grupo.setTextSize(22);
            tv_grupo.setText("¡Error!. Ingrese números enteros");
            }

        EtNotEnable();
        }

        public void limpiar (View view){

            EtEnable();

            et_n10.setText(null);
            et_n40.setText(null);
            et_n200.setText(null);
            et_LL.setText(null);
            et_LP.setText(null);
            tv_grupo.setText(null);
            tv_grupo2.setText(null);
        }

        public int IntegerParse (EditText editText){
            String et = editText.getText().toString();
            return Integer.parseInt(et);
        }

        public void EtNotEnable () {

            et_n10.setEnabled(false);
            et_n40.setEnabled(false);
            et_n200.setEnabled(false);
            et_LL.setEnabled(false);
            et_LP.setEnabled(false);
        }
        public void EtEnable () {

            et_n10.setEnabled(true);
            et_n40.setEnabled(true);
            et_n200.setEnabled(true);
            et_LL.setEnabled(true);
            et_LP.setEnabled(true);
        }
    }



