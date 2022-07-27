package org.izv.igg.promul.garciagutierrez.interfazaerolinea.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.izv.igg.promul.garciagutierrez.interfazaerolinea.R;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.customeMessages.MyUndoListener;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.CentroDeAyudaActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.CompannerosViajeActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.ConfigPrivacidadActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.ContactarAerolineaActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.DatosPersonalesActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.DocuViajesActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.InfoVueloActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.MisViajesActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.PoliticaPrivacidadActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.RevistaVueloActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.TarjetaEmbarqueActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.TerminosCondicionesActivity;
import org.izv.igg.promul.garciagutierrez.interfazaerolinea.menu.ValesRegaloActivity;

/**
 * Esta es la clase main de Interfaz aerolínea, en ella se gestiona el layout principal de la
 * aplicación definido en el recurso main_activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Campos de la clase.
     */
    private Spinner spOrigen;
    private Spinner spDestino;
    private Spinner spFecha;
    private CheckBox cbExtraPrimeraClase;
    private CheckBox cbExtraVentanilla;
    private CheckBox cbExtraMascota;
    private CheckBox cbExtraAsientos;
    private Switch swSeguro;

    private boolean withPremium = false;

    private ImageButton ibPremium;
    private Button btComprar;
    private CheckBox cbTerminos;
    private Context context;

    private EditText etNombre;
    private String nombre;
    private EditText etApellidos;
    private String apellidos;
    private EditText etDireccion;
    private String direccion;
    private EditText etTelefono;
    private String telefono;
    private EditText etEmail;
    private String email;

    private Switch swMovilidad;

    private Toast toast;
    private View toastView;

    /**
     * Método que inicializa el layout activity_main.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        spOrigen = findViewById(R.id.spOrigen);
        spDestino = findViewById(R.id.spDestino);
        spFecha = findViewById(R.id.spFecha);
        cbExtraPrimeraClase = findViewById(R.id.cbUsoDatos);
        cbExtraVentanilla = findViewById(R.id.cbExtra2);
        cbExtraMascota = findViewById(R.id.cbExtra3);
        cbExtraAsientos = findViewById(R.id.cbExtra4);
        swSeguro = findViewById(R.id.swSeguro);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etEmail = findViewById(R.id.etEmail);

        swMovilidad = findViewById(R.id.swMovilidad);

        /**
         * Esta es la acción que se realiza al pulsar el botón de opción premium. Se diferencia con
         * el botón de opción de compra estándar en que pone la variable withPremium a true para
         * poder añadir la opción premium al precio final del billete.
         */
        ibPremium = findViewById(R.id.ibPremium);
        ibPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = etNombre.getText().toString();
                apellidos = etApellidos.getText().toString();
                direccion = etDireccion.getText().toString();
                telefono = etTelefono.getText().toString();
                email = etEmail.getText().toString();

                /**
                 * Se controla que los campos de texto no queden vacíos.
                 *
                 * Avisamos al usuario mediante un Snackbar, el cual utiliza la clase MyUndoListener()
                 * para poder customizarlo.
                 */
                if (nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty()
                        || telefono.isEmpty() || email.isEmpty()) {
                    Snackbar.make(view, "Los campos con * deben ser rellenados", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(R.color.second_color))
                            .setAction("Aceptar", new MyUndoListener())
                            .show();
                } else {
                    /**
                     * Se controla que el usuario acepte los Términos y condiciones de la empresa.
                     *
                     * Avisamos al usuario mediante un Toast.
                     */
                    if (!cbTerminos.isChecked()) {
                        showToast("Debe aceptar los Términos y condiciones");
                    } else {
                        /**
                         * Se controla que el lugar de origen y el de destino no sean iguales
                         *
                         * Avisamos al usuario mediante un Toast.
                         */
                        if (spOrigen.getSelectedItem().toString() == spDestino.getSelectedItem().toString()) {
                            showToast("El lugar de origen y el de destino no pueden ser el mismo");
                        } else {
                            withPremium = true;
                            finish();
                            /**
                             * Iniciamos una determinada actividad según el dispositivo del usuario,
                             * en este caso un teléfono móvil o una tablet.
                             */
                            Configuration config = getResources().getConfiguration();
                            if (config.smallestScreenWidthDp < 600) {
                                openBuyActivity();
                            } else {
                                openBillBuyActivity();
                            }
                        }
                    }
                }
            }
        });

        /**
         * Esta es la acción que se realiza al pulsar el botón de compra estándar.
         */
        cbTerminos = findViewById(R.id.cbTerminos);
        btComprar = findViewById(R.id.btComprar);
        btComprar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                nombre = etNombre.getText().toString();
                apellidos = etApellidos.getText().toString();
                direccion = etDireccion.getText().toString();
                telefono = etTelefono.getText().toString();
                email = etEmail.getText().toString();

                /**
                 * Se controla que los campos de texto no queden vacíos.
                 *
                 * Avisamos al usuario mediante un Snackbar, el cual utiliza la clase MyUndoListener()
                 * para poder customizarlo.
                 */
                if (nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty()
                        || telefono.isEmpty() || email.isEmpty()) {
                    Snackbar.make(view, "Los campos con * deben ser rellenados", Snackbar.LENGTH_LONG)
                            .setActionTextColor(getResources().getColor(R.color.second_color))
                            .setAction("Aceptar", new MyUndoListener())
                            .show();
                } else {
                    /**
                     * Se controla que el usuario acepte los Términos y condiciones de la empresa.
                     *
                     * Avisamos al usuario mediante un Toast.
                     */
                    if (!cbTerminos.isChecked()) {
                        showToast("Debe aceptar los Términos y condiciones");
                    } else {
                        /**
                         * Se controla que el lugar de origen y el de destino no sean iguales
                         *
                         * Avisamos al usuario mediante un Toast.
                         */
                        if (spOrigen.getSelectedItem().toString() == spDestino.getSelectedItem().toString()) {
                            showToast("El lugar de origen y el de destino no pueden ser el mismo");
                        } else {
                            /**
                             * Iniciamos una determinada actividad según el dispositivo del usuario,
                             * en este caso un teléfono móvil o una tablet.
                             */
                            withPremium = false;
                            finish();
                            Configuration config = getResources().getConfiguration();
                            if (config.smallestScreenWidthDp < 600) {
                                openBuyActivity();
                            } else {
                                openBillBuyActivity();
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Método que coloca el menú customizado creado mediante el recurso menu.xml.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método que controla todas las opciones del menu. Al pulsar una de ellas se iniciará su
     * correspondiente activity, todas recogidas en el package menu.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_datosPersonales) {
            Intent intent = new Intent(this, DatosPersonalesActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_docuViaje) {
            Intent intent = new Intent(this, DocuViajesActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_compañerosViaje) {
            Intent intent = new Intent(this, CompannerosViajeActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_misViajes) {
            Intent intent = new Intent(this, MisViajesActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_tarjetasEmbarque) {
            Intent intent = new Intent(this, TarjetaEmbarqueActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_infoVuelo) {
            Intent intent = new Intent(this, InfoVueloActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_valesRegalo) {
            Intent intent = new Intent(this, ValesRegaloActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_revistaVuelo) {
            Intent intent = new Intent(this, RevistaVueloActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_terminosCondiciones) {
            Intent intent = new Intent(this, TerminosCondicionesActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_centroDeAyuda) {
            Intent intent = new Intent(this, CentroDeAyudaActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_contactarAerolinea) {
            Intent intent = new Intent(this, ContactarAerolineaActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_politicaPrivacidad) {
            Intent intent = new Intent(this, PoliticaPrivacidadActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_configPrivacidad) {
            Intent intent = new Intent(this, ConfigPrivacidadActivity.class);
            startActivity(intent);
        }
        return true;
    }

    /**
     * Método que inicia la actividad BuyActivity y le envía los datos, proporcionados en la actividad
     * MainActivity, mediante el objeto bundle.
     *
     * Esta actividad es la correspondiente a los dispositivos móviles.
     */
    private void openBuyActivity() {

        Bundle bundle = new Bundle();
        bundle.putString("ID_SPORIGEN", spOrigen.getSelectedItem().toString());
        bundle.putString("ID_SPDESTINO", spDestino.getSelectedItem().toString());
        bundle.putString("ID_SPFECHA", spFecha.getSelectedItem().toString());

        bundle.putString("ID_NOMBRE", nombre);
        bundle.putString("ID_APELLIDOS", apellidos);
        bundle.putString("ID_DIRECCION", direccion);
        bundle.putString("ID_TLF", telefono);
        bundle.putString("ID_EMAIL", email);

        if (swMovilidad.isChecked()) {
            bundle.putString("ID_MOVILIDAD", swMovilidad.getText().toString());
        }

        if (cbExtraPrimeraClase.isChecked()) {
            bundle.putString("ID_EXPRIMERACLASE", cbExtraPrimeraClase.getText().toString());
        }

        if (cbExtraVentanilla.isChecked()) {
            bundle.putString("ID_EXVENTANILLA", cbExtraVentanilla.getText().toString());
        }

        if (cbExtraMascota.isChecked()) {
            bundle.putString("ID_EXMASCOTA", cbExtraMascota.getText().toString());
        }

        if (cbExtraAsientos.isChecked()) {
            bundle.putString("ID_EXASIENTOS", cbExtraAsientos.getText().toString());
        }

        if (swSeguro.isChecked()) {
            bundle.putString("ID_SEGURO", swSeguro.getText().toString());
        }

        if (withPremium == true) {
            bundle.putString("ID_BTPREMIUM", "50");
        }

        Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Método que inicia la actividad BillBuyActivity y le envía los datos, proporcionados en
     * la actividad MainActivity, mediante el objeto bundle.
     *
     * Esta actividad es la correspondiente a las tablets.
     */
    private void openBillBuyActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("ID_SPORIGEN", spOrigen.getSelectedItem().toString());
        bundle.putString("ID_SPDESTINO", spDestino.getSelectedItem().toString());
        bundle.putString("ID_SPFECHA", spFecha.getSelectedItem().toString());

        bundle.putString("ID_NOMBRE", nombre);
        bundle.putString("ID_APELLIDOS", apellidos);
        bundle.putString("ID_DIRECCION", direccion);
        bundle.putString("ID_TLF", telefono);
        bundle.putString("ID_EMAIL", email);

        if (swMovilidad.isChecked()) {
            bundle.putString("ID_MOVILIDAD", swMovilidad.getText().toString());
        }

        if (cbExtraPrimeraClase.isChecked()) {
            bundle.putString("ID_EXPRIMERACLASE", cbExtraPrimeraClase.getText().toString());
        }

        if (cbExtraVentanilla.isChecked()) {
            bundle.putString("ID_EXVENTANILLA", cbExtraVentanilla.getText().toString());
        }

        if (cbExtraMascota.isChecked()) {
            bundle.putString("ID_EXMASCOTA", cbExtraMascota.getText().toString());
        }

        if (cbExtraAsientos.isChecked()) {
            bundle.putString("ID_EXASIENTOS", cbExtraAsientos.getText().toString());
        }

        if (swSeguro.isChecked()) {
            bundle.putString("ID_SEGURO", swSeguro.getText().toString());
        }

        if (withPremium == true) {
            bundle.putString("ID_BTPREMIUM", "50");
        }

        Intent intent = new Intent(this, BillBuyActivity.class);
        intent.putExtras(bundle);
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
