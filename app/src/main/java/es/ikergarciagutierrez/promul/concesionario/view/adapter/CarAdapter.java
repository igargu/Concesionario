package es.ikergarciagutierrez.promul.concesionario.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.view.adapter.viewholder.CarViewHolder;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> implements View.OnClickListener {

    //private List<VideoGame_VideoGameConsole> videoGameList;
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

        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {/*
        VideoGame_VideoGameConsole videoGame_videoGameConsole = videoGameList.get(position);
        VideoGame videoGame = videoGame_videoGameConsole.videoGame;
        VideoGameConsole videoGameConsole = videoGame_videoGameConsole.videoGameConsole;
        holder.tvVideoGameName.setText(videoGame.name);
        holder.tvVideoGameDeveloper.setText(videoGame.developer);

        holder.tvVideoGameConsole.setText(videoGameConsoleNames[(int) videoGame.idVideoGameConsole - 1]);

        holder.tvVideoGameGenre.setText(videoGame.genre);
        holder.tvVideoGameReleaseDate.setText(videoGame.releaseDate);
        holder.tvVideoGameImageUrl.setText(videoGame.imageUrl);
        Picasso.get().load(videoGame.imageUrl).into(holder.ivVideoGame);*/
    }

    @Override
    public int getItemCount() {/*
        if (videoGameList == null) {
            return 0;
        }
        return videoGameList.size();*/
        return 0;
    }
/*
    public void setVideoGameList(List<VideoGame_VideoGameConsole> videoGameList) {
        this.videoGameList = videoGameList;
        notifyDataSetChanged();
    }*/

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }
/*
    public void update(List<VideoGame_VideoGameConsole> videoGames) {
        videoGameList = videoGames;
        notifyDataSetChanged();
    }

    public VideoGame_VideoGameConsole getItem(int position) {
        return videoGameList.get(position);
    }*/
}
