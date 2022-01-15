package es.ikergarciagutierrez.promul.concesionario.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.view.adapter.viewholder.CarViewHolder;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> implements View.OnClickListener {

    private static final String URL = "jdbc:mysql://146.59.237.189:3306/dam208_iggconcesionario";
    private static final String USER = "dam208_igg";
    private static final String PASSWORD = "dam208_igg";

    private String title, price, location, images, fuel, km, year;

    private ArrayList<String> carList = new ArrayList<>();
    private Context context;

    private View.OnClickListener listener;

    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);

        view.setOnClickListener(this);

        //new InfoAsyncTask().execute();

        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {/*
        VideoGame_VideoGameConsole videoGame_videoGameConsole = videoGameList.get(position);
        VideoGame videoGame = videoGame_videoGameConsole.videoGame;
        VideoGameConsole videoGameConsole = videoGame_videoGameConsole.videoGameConsole;*/

        carList.add("KIA - eSoul");
        carList.add("Asturconsa, concesionario oficial kia en asturias.\n" +
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
        carList.add("435859465");
        carList.add("42708");
        carList.add("Asturias");
        carList.add("https://img.milanuncios.com/fg/4358/59/435859465_1.jpg");
        carList.add("https://www.milanuncios.com/kia-de-segunda-mano/kia-esoul-435859465.htm");
        carList.add("hybrid");
        carList.add("20");
        carList.add("Automático");
        carList.add("Gris/Plata");
        carList.add("204");
        carList.add("5");
        carList.add("2021");

        title = carList.get(0);
        price = carList.get(3);
        location = carList.get(4);
        images = carList.get(5);
        fuel = carList.get(7);
        km = carList.get(8);
        year = carList.get(13);

        holder.tvAdCarTitle.setText(title);
        holder.tvAdCarPrice.setText("Precio: " + price + " €");
        holder.etAdCarLocation.setText(location);
        holder.etAdCarFuel.setText(fuel);
        holder.etAdCarKm.setText(km);
        holder.etAdCarYear.setText(year);
        Picasso.get().load(images).into(holder.ivAdCar);
    }

    @Override
    public int getItemCount() {/*
        if (carList == null) {
            return 0;
        }
        return carList.size();*/
        return 5;
    }

    public void setVideoGameList(ArrayList<String> carList) {
        this.carList = carList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    /*public void update(List<VideoGame_VideoGameConsole> videoGames) {
        videoGameList = videoGames;
        notifyDataSetChanged();
    }

    public VideoGame_VideoGameConsole getItem(int position) {
        return videoGameList.get(position);
    }*/
/*
    // Obtener datos de la BBDD
    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT title, description, reference, price, location, images, linkPage," +
                        "fuel, km, transmission, color, power, numDoors, year FROM coches";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    info.put("title", resultSet.getString("title"));
                    info.put("price", resultSet.getString("price"));
                    info.put("location", resultSet.getString("location"));
                    info.put("images", resultSet.getString("images"));
                    info.put("fuel", resultSet.getString("fuel"));
                    info.put("km", resultSet.getString("km"));
                    info.put("year", resultSet.getString("year"));
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (!result.isEmpty()) {
                title = result.get("title");
                price = result.get("price");
                location = result.get("location");
                images = result.get("images");
                fuel = result.get("fuel");
                km = result.get("km");
                year = result.get("year");
            }
        }
    }*/

}
