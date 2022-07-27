package org.izv.igg.promul.garciagutierrez.interfazaerolinea.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.izv.igg.promul.garciagutierrez.interfazaerolinea.R;

/**
 * Esta es la clase que genera la factura con todos los datos del billete comprado por el usuario y
 * gestiona el layout definido mediante el recurso activity_bill.xml.
 */
public class BillActivity extends AppCompatActivity {
    /**
     * Campos de la clase.
     */
    private String strOrigen;
    private String strDestino;
    private String strFecha;

    private String strNombre;
    private String strApellidos;
    private String strDireccion;
    private String strTelefeno;
    private String strEmail;

    private String strMovilidad;

    private String strExPrimeraClase;
    private String strExVentanilla;
    private String strExMascota;
    private String strExAsientos;

    private String strSeguro;

    private String strPremium;

    private String strPrecio;

    private TextView tvFactura;
    private TextView tvPrecio;

    private Button btAceptar;

    private Context context;
    private Toast toast;
    private View toastView;

    /**
     * Método que inicializa el layout activity_bill.xml.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        context = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        generarFactura();

        /**
         * Esta es la acción que se realiza al pulsar el botón btAceptar.
         */
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
        strTelefeno = getIntent().getStringExtra("ID_TLF").toUpperCase();
        strEmail = getIntent().getStringExtra("ID_EMAIL").toUpperCase();

        strMovilidad = getIntent().getStringExtra("ID_MOVILIDAD").toUpperCase();

        strExPrimeraClase = getIntent().getStringExtra("ID_EXPRIMERACLASE").toUpperCase();
        strExVentanilla = getIntent().getStringExtra("ID_EXVENTANILLA").toUpperCase();
        strExMascota = getIntent().getStringExtra("ID_EXMASCOTA").toUpperCase();
        strExAsientos = getIntent().getStringExtra("ID_EXASIENTOS").toUpperCase();

        strSeguro = getIntent().getStringExtra("ID_SEGURO").toUpperCase();

        strPremium = getIntent().getStringExtra("ID_BTPREMIUM").toUpperCase();

        strPrecio = getIntent().getStringExtra("ID_PRECIO").toUpperCase();

        String factura = strNombre + " " + strApellidos + "\n"
                + strDireccion + "\n"
                + "Tlf: " + strTelefeno + "\n"
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
