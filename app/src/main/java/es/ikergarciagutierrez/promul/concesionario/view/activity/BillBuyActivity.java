package org.izv.igg.promul.garciagutierrez.interfazaerolinea.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.izv.igg.promul.garciagutierrez.interfazaerolinea.R;

/**
 * Esta clase realiza las mismas funciones que las clases BuyActivity  y BillActivity, pero en este
 * caso se inicializa el recurso activity_bill_buy_activity. Este layout contiene dos fragment,
 * uno con un layout igual de la actividad BuyActivity y otro fragment con un layout igual de
 * la actividad BillActivity.
 */
public class BillBuyActivity extends AppCompatActivity {
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

    private TextView tvFactura;
    private Button btAceptar;

    private View fragmentBill;

    /**
     * Método que inicializa el layout activity_bill_buy.xml. Inicialmente el fragment que contiene
     * el layout correspondiente a la actividad BillActivity estará oculto hasta que pulsemos
     * el botón de confirmación de compra.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_buy);

        context = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        calcularPrecioBillete();
        accionBuyActivity();

        generarFactura();
        accionBillActivity();
    }

    /**
     * Método que controla la acción del botón btPrecio. Este botón se encuentra en el fragment que
     * contiene el layout correspondiente a la actividad BuyActivity.
     */
    public void accionBuyActivity() {
        btPrecio = findViewById(R.id.btPrecio);
        btPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Se muestra un AlertDialog customizado mediante el layout definido en el recurso
                 * custome_alert_dialog.
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(BillBuyActivity.this, R.style.MyAlertTheme);
                View alertView = getLayoutInflater().inflate(R.layout.custome_alert_dialog, null);
                builder.setView(alertView);
                AlertDialog dialog = builder.create();
                /**
                 * Si el usuario pulsa el botón Confirmar del AlertDialog, se mostrará el fragment
                 * que contiene el layout correspondiente a la actividad BillActivity.
                 */
                ibConfirmar = (ImageButton) alertView.findViewById(R.id.ibConfirmar);
                ibConfirmar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("Compra realizada");
                        dialog.cancel();

                        fragmentBill = findViewById(R.id.idFragmentBill);
                        fragmentBill.setVisibility(View.VISIBLE);

                        /**
                         * El botón btPrecio se deshabilita cuando ya hemos aceptado la compra.
                         */
                        btPrecio.setEnabled(false);
                    }
                });
                /**
                 * Si el usuario pulsa el botón Cancelar del AlertDialog, se le redirigirá a la
                 * actividad MainActivity.
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
     * Método que controla la acción del botón btAceptar. Este botón se encuentra en el fragment que
     * contiene el layout correspondiente a la actividad BillActivity.
     */
    public void accionBillActivity() {
        btAceptar = findViewById(R.id.btAceptar);
        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Mostramos un Toast agradeciendo la compra al usuario y le redirigimos a la
                 * actividad MainActivity.
                 */
                showToast("Gracias por confiar en nosotros");
                finish();
                openMainActivity();
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
     * Método que genera la factura con toda la información de la compra del usuario. Usamos los
     * datos que le pasa a la clase el objeto bundle de la clase BuyActivity.
     */
    public void generarFactura() {
        strOrigen = getIntent().getStringExtra("ID_SPORIGEN").toUpperCase();
        strDestino = getIntent().getStringExtra("ID_SPDESTINO").toUpperCase();
        strFecha = getIntent().getStringExtra("ID_SPFECHA").toUpperCase();

        strNombre = getIntent().getStringExtra("ID_NOMBRE").toUpperCase();
        strApellidos = getIntent().getStringExtra("ID_APELLIDOS").toUpperCase();
        strDireccion = getIntent().getStringExtra("ID_DIRECCION").toUpperCase();
        strTelefono = getIntent().getStringExtra("ID_TLF").toUpperCase();
        strEmail = getIntent().getStringExtra("ID_EMAIL").toUpperCase();

        strMovilidad.toUpperCase();

        strExPrimeraClase.toUpperCase();
        strExVentanilla.toUpperCase();
        strExMascota.toUpperCase();
        strExAsientos.toUpperCase();

        strSeguro.toUpperCase();

        strPremium.toUpperCase();

        strPrecio.toUpperCase();

        String factura = strNombre + " " + strApellidos + "\n"
                + strDireccion + "\n"
                + "Tlf: " + strTelefono + "\n"
                + "Email: " + strEmail + "\n\n"
                + "Origen: " + strOrigen + "\n"
                + "Destino: " + strDestino + "\n"
                + "Fecha: " + strFecha + "\n\n"
                + "DRAGON AIRLINES S.A." + "\n"
                + "C/HIROYTO Nº13" + "\n\n"
                + "MOVILIDAD REDUCIDA: " + strMovilidad + "\n\n"
                + "\t\t\t\t\t\t\t\t" + "EXTRAS" + "\n\n"
                + "VIAJAR EN PRIMERA CLASE: " + strExPrimeraClase + "\n"
                + "ASIENTO CON VENANILLA: " + strExVentanilla + "\n"
                + "VIAJAR CON UNA MASCOTA: " + strExMascota + "\n"
                + "BLOQUEAR ASIENTOS CONTIGUOS: " + strExAsientos + "\n\n"
                + "SEGURO ADICIONAL: " + strSeguro + "\n\n"
                + "OPCIÓN PREMIUM: " + strPremium + "\n";

        tvFactura = findViewById(R.id.tvFactura);
        tvFactura.setText(factura);

        String precio = "PRECIO FINAL: " + strPrecio + "\n";
        tvPrecio = findViewById(R.id.tvPrecioBill);
        tvPrecio.setText(precio);
    }

    /**
     * Método que vuelve a iniciar la actividad MainActivity.
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