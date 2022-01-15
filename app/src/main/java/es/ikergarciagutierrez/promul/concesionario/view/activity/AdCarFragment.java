package es.ikergarciagutierrez.promul.concesionario.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.databinding.FragmentAdCarBinding;

public class AdCarFragment extends Fragment {

    private FragmentAdCarBinding binding;

    private TextView tvAdTitle, tvAdPrice, tvAdReference, tvAdDescription, tvAdLinkPage;
    private ImageView ivAd;
    private String imageLink, pageLink;
    private EditText etAdLocation, etAdFuel, etAdKm, etAdYear,
                        etAdTransmission, etAdColor, etAdPower, etAdNumDoors;
    private Button btLeft, btRight;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdCarBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize();
    }

    private void initialize() {

        tvAdTitle = binding.tvAdTitle;
        tvAdTitle.setText("KIA - eSoul");

        tvAdPrice = binding.tvAdPrice;
        tvAdPrice.setText("Precio: 42708 €");

        tvAdReference = binding.tvAdReference;
        tvAdReference.setText("Referencia: 435859465");

        tvAdDescription = binding.tvAdDescription;
        tvAdDescription.setText("Asturconsa, concesionario oficial kia en asturias.\n" +
                                "vehículo kia okasion. compromisos: 1) garantía de 3 a 6 años 2) certificado de revisión 105 puntos\n" +
                                "3)asistencia europea 24 horas 4) financiación especial con kia finance 5) garantía de cambio\n" +
                                "(14 días/2.000km)  6) inspección gratuita tras 2.000 km o 30 días\n" +
                                "7) prueba del vehículo sin compromiso.\n" +
                                "precio todo incluido: iva, transferencia.\n" +
                                "hacemos entregas en todo el territorio nacional. consultar tarifas.\n" +
                                "ven a vernos a nuestras instalaciones de oviedo, gijón, avilés, mieres, tapia de casariego y llanes.\n" +
                                "el precio financiado, además, incluye descuento vinculado a la financiación.\n" +
                                "la oferta es válida salvo error tipográfico en el precio o en la ficha. \n" +
                                "imágenes no contractuales.*precio indicado en milanuncios sujeto a financiación");

        tvAdLinkPage = binding.tvAdLinkPage;
        tvAdLinkPage.setText("Ir al anuncio en nuestra web");
        pageLink = "https://www.milanuncios.com/kia-de-segunda-mano/kia-esoul-435859465.htm";

        ivAd = binding.ivAd;
        Picasso.get().load("https://img.milanuncios.com/fg/4358/59/435859465_1.jpg").into(ivAd);
        imageLink = "https://img.milanuncios.com/fg/4358/59/435859465_1.jpg";

        etAdLocation = binding.etAdLocation;
        etAdLocation.setText("Asturias");

        etAdFuel = binding.etAdFuel;
        etAdFuel.setText("hybrid");

        etAdKm = binding.etAdKm;
        etAdKm.setText("20");

        etAdYear = binding.etAdYear;
        etAdYear.setText("2021");

        etAdTransmission = binding.etAdTransmission;
        etAdTransmission.setText("Automático");

        etAdColor = binding.etAdColor;
        etAdColor.setText("Gris/Plata");

        etAdPower = binding.etAdPower;
        etAdPower.setText("204");

        etAdNumDoors = binding.etAdNumDoors;
        etAdNumDoors.setText("5");

        btLeft = binding.btLeft;
        btRight = binding.btRight;

        defineLeftListener();
        defineRightListener();
        defineLinkListener();
    }

    private void defineLinkListener() {
        tvAdLinkPage.setOnClickListener(view -> {
            Uri uri = Uri.parse(pageLink);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

    private void defineLeftListener() {

        btLeft.setOnClickListener(view -> {

            char currentNumImg = imageLink.charAt(imageLink.indexOf("_")+1);
            char numImg = currentNumImg;

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
                numImg--;
                imageLink = imageLink.replace("_"+currentNumImg+".jpg","_"+numImg+".jpg");
                Picasso.get().load(imageLink).into(ivAd);

            }

        });
    }

    private void defineRightListener() {

        btRight.setOnClickListener(view -> {

            int totalImgs = 9;
            char numImg;
            char currentNumImg;

            if (totalImgs > 9) {

                char[] aux = new char[2];

                currentNumImg = imageLink.charAt(imageLink.indexOf("_")+1);
                aux[0] = currentNumImg;

                currentNumImg = imageLink.charAt(imageLink.indexOf("_")+2);
                aux[1] = currentNumImg;

                numImg = currentNumImg;

            } else {

                currentNumImg = imageLink.charAt(imageLink.indexOf("_")+1);
                numImg = currentNumImg;

            }

            if (imageLink.contains("_"+totalImgs+".jpg")) {

                btRight.setEnabled(false);

                Toast toast = Toast.makeText(getContext(), "No hay más imágenes", Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toastView.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_IN);
                TextView text = (TextView) view.findViewById(android.R.id.message);
                toast.show();

            } else {

                btLeft.setEnabled(true);
                btRight.setEnabled(true);
                numImg++;
                imageLink = imageLink.replace("_"+currentNumImg+".jpg","_"+numImg+".jpg");
                Picasso.get().load(imageLink).into(ivAd);

            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}