package es.ikergarciagutierrez.promul.concesionario.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Clase que define la actividad principal de la aplicación
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Campos de la clase
     */
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    /**
     * Método que inicializa el layout principal
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

    }

    /**
     * Método que inicializa el menú de la aplicación
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Método que inicia las actividades de cada opción del menú al ser pulsada
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuAccount) {
            Intent intent = new Intent(this, MenuAccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.menuFavs) {
            Intent intent = new Intent(this, MenuFavsActivity.class);
            startActivity(intent);
        } else if (id == R.id.menuAboutUs) {
            Intent intent = new Intent(this, MenuAboutUsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que inicializa la navegación entre fragmentos
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}