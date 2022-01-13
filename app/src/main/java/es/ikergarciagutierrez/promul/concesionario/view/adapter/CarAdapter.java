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

    private List<String> carList;
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

        new InfoAsyncTask().execute();

        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {/*
        VideoGame_VideoGameConsole videoGame_videoGameConsole = videoGameList.get(position);
        VideoGame videoGame = videoGame_videoGameConsole.videoGame;
        VideoGameConsole videoGameConsole = videoGame_videoGameConsole.videoGameConsole;*/

        holder.tvAdCarTitle.setText(title);
        holder.tvAdCarPrice.setText(price);
        holder.etAdCarLocation.setText(location);
        holder.etAdCarFuel.setText(fuel);
        holder.etAdCarKm.setText(km);
        holder.etAdCarYear.setText(year);
        Picasso.get().load(images).into(holder.ivAdCar);
    }

    @Override
    public int getItemCount() {
        if (carList == null) {
            return 0;
        }
        return carList.size();
    }

    public void setVideoGameList(List<String> carList) {
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
    }

}
