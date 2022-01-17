package es.ikergarciagutierrez.promul.concesionario.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.databinding.FragmentAdCarBinding;

/**
 * Esta clase define el fragmento de cada anuncio del concesionario
 */
public class AdCarFragment extends Fragment {

    /**
     * Campos de la clase
     */
    private static final String URL = "jdbc:mysql://146.59.237.189:3306/dam208_iggconcesionario";
    private static final String USER = "dam208_igg";
    private static final String PASSWORD = "dam208_igg";

    private FragmentAdCarBinding binding;

    private TextView tvAdTitle, tvAdPrice, tvAdReference, tvAdDescription, tvAdLinkPage;
    private ImageView ivAd;
    private String imageLink;
    private EditText etAdLocation, etAdFuel, etAdKm, etAdYear,
            etAdTransmission, etAdColor, etAdPower, etAdNumDoors;
    private Button btLeft, btRight;

    private String title, description, reference, price, location, images = "",
            linkPage, fuel, km, transmission, color, power, numDoors, year;

    private int numImg = 1;

    /**
     * Constructor para la vista del fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdCarBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * Método que inicializa la vista del fragmento
     *
     * @param view
     * @param savedInstanceState
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        new InfoAsyncTask().execute();
    }

    /**
     * Método que inicializa los componentes del layout
     */
    private void initialize() {

        tvAdTitle = binding.tvAdTitle;
        tvAdPrice = binding.tvAdPrice;
        tvAdReference = binding.tvAdReference;
        tvAdDescription = binding.tvAdDescription;
        tvAdLinkPage = binding.tvAdLinkPage;
        ivAd = binding.ivAd;
        etAdLocation = binding.etAdLocation;
        etAdFuel = binding.etAdFuel;
        etAdKm = binding.etAdKm;
        etAdYear = binding.etAdYear;
        etAdTransmission = binding.etAdTransmission;
        etAdColor = binding.etAdColor;
        etAdPower = binding.etAdPower;
        etAdNumDoors = binding.etAdNumDoors;

        btLeft = binding.btLeft;
        btRight = binding.btRight;

    }

    /**
     * Método que define el evento del TextView tvAdLinkPage. Al pulsare en él se abriré una
     * ventana del navegador con el anunció en cuestión
     *
     * @param pageLink El parámetro pageLink contiene la url del anuncio
     */
    private void defineLinkListener(String pageLink) {
        tvAdLinkPage.setOnClickListener(view -> {
            Uri uri = Uri.parse(pageLink);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

    /**
     * Método que define el evento del Button btLeft. Al pulsarlo la imagen del anuncio cambia,
     * mostrando las anteriores. Si la imagen es cuestión es la primera, se desactiva el botón y se
     * muestra un Toast en pantalla
     */
    private void defineLeftListener() {

        btLeft.setOnClickListener(view -> {

            if (imageLink.contains("_1.jpg")) {

                btLeft.setEnabled(false);

                Toast toast = Toast.makeText(getContext(), "No hay más imágenes", Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toastView.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_IN);
                TextView text = (TextView) view.findViewById(android.R.id.message);
                toast.show();

            } else {

                btLeft.setEnabled(true);
                btRight.setEnabled(true);

                int newNumImg = numImg - 1;

                imageLink = imageLink.replace("_" + numImg + ".jpg", "_" + newNumImg + ".jpg");
                Picasso.get().load(imageLink).into(ivAd);

                numImg = newNumImg;

            }

        });
    }

    /**
     * Método que define el evento del Button btRight. Al pulsarlo la imagen del anuncio cambia,
     * mostrando las siguientes. Si la imagen es cuestión es la última, se desactiva el botón y se
     * muestra un Toast en pantalla
     */
    private void defineRightListener() {

        btRight.setOnClickListener(view -> {

            String[] img = images.split(";");
            int totalImgs = img.length;

            if (imageLink.contains("_" + totalImgs + ".jpg")) {

                btRight.setEnabled(false);

                Toast toast = Toast.makeText(getContext(), "No hay más imágenes", Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toastView.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_IN);
                TextView text = (TextView) view.findViewById(android.R.id.message);
                toast.show();

            } else {

                btLeft.setEnabled(true);
                btRight.setEnabled(true);

                int newNumImg = numImg + 1;

                imageLink = imageLink.replace("_" + numImg + ".jpg", "_" + newNumImg + ".jpg");
                Picasso.get().load(imageLink).into(ivAd);

                numImg = newNumImg;

            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Método que obtiene los datos de la BD y llena un ArrayList con ellos
     */
    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            String ad = String.valueOf((Integer) getArguments().getSerializable("idAd"));

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String sql = "SELECT title, description, reference, price, location, images, linkPage," +
                        "fuel, km, transmission, color, power, numDoors, year FROM coches LIMIT " + ad + ",1";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    title = resultSet.getString("title");
                    description = resultSet.getString("description");
                    reference = resultSet.getString("reference");
                    price = resultSet.getString("price");
                    location = resultSet.getString("location");
                    images = resultSet.getString("images");
                    linkPage = resultSet.getString("linkPage");
                    fuel = resultSet.getString("fuel");
                    km = resultSet.getString("km");
                    transmission = resultSet.getString("transmission");
                    color = resultSet.getString("color");
                    power = resultSet.getString("power");
                    numDoors = resultSet.getString("numDoors");
                    year = resultSet.getString("year");
                }

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;

        }


        @Override
        protected void onPostExecute(Map<String, String> result) {
            setAd();
        }
    }

    /**
     * Método que muestra los datos recogidos en el ArrayList, en los componentes del layout.
     * También se inicializan los eventos de los botones y link del anuncio
     */
    public void setAd() {

        tvAdTitle.setText(title);
        tvAdPrice.setText("Precio: " + price + " €");
        tvAdReference.setText("Referencia: " + reference);
        tvAdDescription.setText(getString(R.string.descripcion) + "\n\n" + description);
        tvAdLinkPage.setText("Ir al anuncio en nuestra web");
        String[] img = images.split(";");
        Picasso.get().load(img[0]).into(ivAd);
        imageLink = img[0];
        etAdLocation.setText(location);
        etAdFuel.setText(fuel);
        etAdKm.setText(km);
        etAdYear.setText(year);
        etAdTransmission.setText(transmission);
        etAdColor.setText(color);
        etAdPower.setText(power);
        etAdNumDoors.setText(numDoors);

        defineLeftListener();
        defineRightListener();
        defineLinkListener(linkPage);

    }

}