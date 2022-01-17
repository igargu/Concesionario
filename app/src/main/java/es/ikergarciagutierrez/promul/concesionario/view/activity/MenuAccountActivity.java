package es.ikergarciagutierrez.promul.concesionario.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import es.ikergarciagutierrez.promul.concesionario.R;

/**
 * Clase que define el layout de la opción del menu Mi cuenta, además del evento del botón
 * btCreateAccount. Al pulsar este botón se abre una página del navegador para poder registrarse en
 * la página del concesionario
 */
public class MenuAccountActivity extends AppCompatActivity {

    private Button btCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_account);

        btCreateAccount = findViewById(R.id.btMenuAccount);

        btCreateAccount.setOnClickListener(view -> {
            Uri uri = Uri.parse("https://www.milanuncios.com/?stc=sem-google-9328697459-Search_" +
                                "Marca_Web_Click_Milanuncios-Milanuncios_Espa%C3%B1a-%2Bmilanuncios&" +
                                "gclid=CjwKCAiAxJSPBhAoEiwAeO_fPy-dB5-u8NmHVxcpbsz4X-AouSP4wA0FitAyh" +
                                "TAL1LBH5lzmHY1K5RoCQrQQAvD_BwE");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

    }
}