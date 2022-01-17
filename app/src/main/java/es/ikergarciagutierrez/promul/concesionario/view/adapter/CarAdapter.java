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
import java.util.Map;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.view.adapter.viewholder.CarViewHolder;

/**
 * Clase que define el Adapter del RecyclerView. Aquí se llenan cada uno de los items
 * con los datos de la BD
 */
public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> implements View.OnClickListener {

    /**
     * Campos de la clase
     */
    private static final String URL = "jdbc:mysql://146.59.237.189:3306/dam208_iggconcesionario";
    private static final String USER = "dam208_igg";
    private static final String PASSWORD = "dam208_igg";

    private String title, description, reference, price, location, images,
            linkPage, fuel, km, transmission, color, power, numDoors, year;

    private ArrayList<String> carList = new ArrayList<>();
    private Context context;

    private View.OnClickListener listener;

    /**
     * Constructor pra el Adapter
     *
     * @param context
     */
    public CarAdapter(Context context) {
        this.context = context;
    }

    /**
     * Método que infla el layout con el ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        view.setOnClickListener(this);
        return new CarViewHolder(view);
    }

    /**
     * Método que da valores a los datos del ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        new InfoAsyncTask().execute();

        if (!carList.isEmpty()) {

            String carAdDetail = carList.get(position);
            String[] carAdDetails = carAdDetail.split("=");

            title = carAdDetails[0];
            price = "Precio: " + carAdDetails[3] + " €";
            location = carAdDetails[4];
            fuel = carAdDetails[7];
            km = carAdDetails[8];
            year = carAdDetails[13];
            images = carAdDetails[5];

            holder.tvAdCarTitle.setText(title);
            holder.tvAdCarPrice.setText(price);
            holder.etAdCarLocation.setText(location);
            holder.etAdCarFuel.setText(fuel);
            holder.etAdCarKm.setText(km);
            holder.etAdCarYear.setText(year);
            String[] img = images.split(";");
            Picasso.get().load(img[0]).into(holder.ivAdCar);

        } else {

            if (position == 0) {

                holder.tvAdCarTitle.setText("KIA - eSoul");
                holder.tvAdCarPrice.setText("Precio: 42708 €");
                Picasso.get().load("https://img.milanuncios.com/fg/4358/59/435859465_1.jpg").into(holder.ivAdCar);
                holder.etAdCarLocation.setText("Asturias");
                holder.etAdCarFuel.setText("hybrid");
                holder.etAdCarKm.setText("20");
                holder.etAdCarYear.setText("2021");

            } else {

                holder.tvAdCarTitle.setText("KIA - Niro 1.6 GDi HEV 104kW 141CV");
                holder.tvAdCarPrice.setText("Precio: 25300 €");
                Picasso.get().load("https://img.milanuncios.com/fg/4358/59/435859467_1.jpg").into(holder.ivAdCar);
                holder.etAdCarLocation.setText("Asturias");
                holder.etAdCarFuel.setText("hybrid");
                holder.etAdCarKm.setText("18700");
                holder.etAdCarYear.setText("2021");

            }
        }
    }

    /**
     * Método que devuelve el número de items que se mostrarán en el RecyclerView
     *
     * @return El número de items que se mostrarán en el RecyclerView
     */
    @Override
    public int getItemCount() {
        if (carList == null) {
            return 0;
        }
        return 20;
    }

    /**
     * Método que establece el Listener de los items del RecyclerView
     *
     * @param listener
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    /**
     * Método que obtiene los datos de la BD y llena un ArrayList con ellos
     */
    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                String sql = "SELECT title, description, reference, price, location, images, linkPage," +
                        "fuel, km, transmission, color, power, numDoors, year FROM coches";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                int numColumn = statement.getMetaData().getColumnCount();

                if (carList.size() < numColumn) {
                    while (resultSet.next()) {

                        carList.add(resultSet.getString("title") + "=" + resultSet.getString("description") + "=" + resultSet.getString("reference") + "="
                                + resultSet.getString("price") + "=" + resultSet.getString("location") + "=" + resultSet.getString("images") + "=" + resultSet.getString("linkPage")
                                + "=" + resultSet.getString("fuel") + "=" + resultSet.getString("km") + "=" + resultSet.getString("transmission") + "=" + resultSet.getString("color")
                                + "=" + resultSet.getString("power") + "=" + resultSet.getString("numDoors") + "=" + resultSet.getString("year"));

                    }
                }

            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {

        }
    }

}
