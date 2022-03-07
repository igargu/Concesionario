package es.ikergarciagutierrez.promul.concesionario.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import es.ikergarciagutierrez.promul.concesionario.R;

/**
 * Clase que define el layout de la opción del menu Sobre nosotros
 */
public class MenuAboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_about_us);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}