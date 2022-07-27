package org.izv.igg.promul.garciagutierrez.interfazaerolinea.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.izv.igg.promul.garciagutierrez.interfazaerolinea.R;

/**
 * Esta es la clase que calcula el precio del billete de avión y gestiona el layout definido
 * mediante el recurso activity_buy.xml.
 */
public class BuyActivity extends AppCompatActivity {
    /**
     * Campos de la clase.
     */
    private String strOrigen;
    private int origen;
    private String strDestino;
    private int destino;
    private String strFecha;
    private int fecha;

    private String strNombre;
    private String strApellidos;
    private String strDireccion;
    private String strTelefono;
    private String strEmail;

    private String strMovilidad;

    private String strExPrimeraClase;
    private int extraPrimeraClase;
    private String strExVentanilla;
    private int extraVentanilla;
    private String strExMascota;
    private String strExAsientos;
    private int extraAsientos;

    private String strSeguro;
    private int seguroAdicional;

    private String strPremium;
    private int intPremium;

    private TextView tvPrecio;
    private Button btPrecio;

    private String strPrecio;

    private Context context;
    private ImageButton ibConfirmar;
    private ImageButton ibCancelar;
    private Toast toast;
    private View toastView;

    /**
     * Método que inicializa el layout activity_buy.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        context = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        calcularPrecioBillete();

        /**
         * Esta es la acción que se realiza al pulsar el botón btPrecio.
         */
        btPrecio = findViewById(R.id.btPrecio);
        btPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Se muestra un AlertDialog customizado mediante el layout definido en el recurso
                 * custome_alert_dialog.
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(BuyActivity.this, R.style.MyAlertTheme);
                View alertView = getLayoutInflater().inflate(R.layout.custome_alert_dialog, null);
                builder.setView(alertView);
                AlertDialog dialog = builder.create();
                /**
                 * Si se pulsa el botón Confirmar del AlertDialog se inicia la actividad BillActivity.
                 */
                ibConfirmar = (ImageButton) alertView.findViewById(R.id.ibConfirmar);
                ibConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("Compra realizada");
                        dialog.cancel();
                        finish();
                        openBillActivity();
                    }
                });
                /**
                 * Si se pulsa el botón Cancelar del AlterDialog se cierra la actividad BuyActivity
                 * y volvemos a MainActivity.
                 */
                ibCancelar = (ImageButton) alertView.findViewById(R.id.ibCancelar);
                ibCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("Compra cancelada");
                        dialog.cancel();
                        finish();
                        openMainActivity();
                    }
                });
                dialog.show();
            }
        });

    }

    /**
     * Este método calcula el precio de billete de avión mediante los datos que le pasa a la clase
     * el objeto bundle de la clase MainActivity.
     */
    public void calcularPrecioBillete() {

        strOrigen = getIntent().getStringExtra("ID_SPORIGEN");
        for (int i = 0; i < strOrigen.length(); i++) {
            origen += strOrigen.charAt(i);
        }

        strDestino = getIntent().getStringExtra("ID_SPDESTINO");
        for (int i = 0; i < strDestino.length(); i++) {
            destino += strDestino.charAt(i);
        }

        strFecha = getIntent().getStringExtra("ID_SPFECHA");
        for (int i = 0; i < strFecha.length(); i++) {
            fecha += strFecha.charAt(i);
        }

        strMovilidad = getIntent().getStringExtra("ID_MOVILIDAD");
        if (strMovilidad != null) {
            strMovilidad = "SI";
        } else {
            strMovilidad = "NO";
        }

        strExPrimeraClase = getIntent().getStringExtra("ID_EXPRIMERACLASE");
        if (strExPrimeraClase != null) {
            extraPrimeraClase = 50;
            strExPrimeraClase = "SI";
        } else {
            strExPrimeraClase = "NO";
        }

        strExVentanilla = getIntent().getStringExtra("ID_EXVENTANILLA");
        if (strExVentanilla != null) {
            extraVentanilla = 20;
            strExVentanilla = "SI";
        } else {
            strExVentanilla = "NO";
        }

        strExMascota = getIntent().getStringExtra("ID_EXMASCOTA");
        if (strExMascota != null) {
            strExMascota = "SI";
        } else {
            strExMascota = "NO";
        }

        strExAsientos = getIntent().getStringExtra("ID_EXASIENTOS");
        if (strExAsientos != null) {
            extraAsientos = 30;
            strExAsientos = "SI";
        } else {
            strExAsientos = "NO";
        }

        strSeguro = getIntent().getStringExtra("ID_SEGURO");
        if (strSeguro != null) {
            seguroAdicional = 60;
            strSeguro = "SI";
        } else {
            strSeguro = "NO";
        }

        if (getIntent().getStringExtra("ID_BTPREMIUM") != null) {
            strPremium = "SI";
            intPremium = Integer.parseInt(getIntent().getStringExtra("ID_BTPREMIUM"));
        } else {
            strPremium = "NO";
        }

        int precio = (origen + destino + fecha + extraPrimeraClase + extraVentanilla + extraAsientos + seguroAdicional + intPremium) / 5;
        tvPrecio = findViewById(R.id.tvPrecio);
        strPrecio = String.valueOf(precio) + " €";
        tvPrecio.setText(strPrecio);
    }

    /**
     * Método que inicia la actividad BillActivity y le envía los datos, proporcionados en
     * la actividad BuyActivity, mediante el objeto bundle.
     */
    private void openBillActivity() {
        Bundle bundle = new Bundle();

        bundle.putString("ID_SPORIGEN", strOrigen);
        bundle.putString("ID_SPDESTINO", strDestino);
        bundle.putString("ID_SPFECHA", strFecha);

        strNombre = getIntent().getStringExtra("ID_NOMBRE");
        bundle.putString("ID_NOMBRE", strNombre);

        strApellidos = getIntent().getStringExtra("ID_APELLIDOS");
        bundle.putString("ID_APELLIDOS", strApellidos);

        strDireccion = getIntent().getStringExtra("ID_DIRECCION");
        bundle.putString("ID_DIRECCION", strDireccion);

        strTelefono = getIntent().getStringExtra("ID_TLF");
        bundle.putString("ID_TLF", strTelefono);

        strEmail = getIntent().getStringExtra("ID_EMAIL");
        bundle.putString("ID_EMAIL", strEmail);

        bundle.putString("ID_EXPRIMERACLASE", strExPrimeraClase);
        bundle.putString("ID_EXVENTANILLA", strExVentanilla);
        bundle.putString("ID_EXMASCOTA", strExMascota);
        bundle.putString("ID_EXASIENTOS", strExAsientos);
        bundle.putString("ID_MOVILIDAD", strMovilidad);
        bundle.putString("ID_SEGURO", strSeguro);
        bundle.putString("ID_BTPREMIUM", strPremium);
        bundle.putString("ID_PRECIO", strPrecio);

        Intent intent = new Intent(this, BillActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    /**
     * Método que vuelva a iniciar la actividad MainActivity.
     */
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Método que muestra un Toast personalizado
     *
     * @param message Mensaje que queremos que aparezca en el Toast
     */
    private void showToast(String message) {
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView tvToast = view.findViewById(R.id.tvMessage);
        tvToast.setText(message);
        toast.setView(view);
        toast.show();
    }
}