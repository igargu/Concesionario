package es.ikergarciagutierrez.promul.concesionario.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.promul.concesionario.R;

public class CarViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivAdCar;
    public TextView tvAdCarTitle, tvAdCarPrice, tvAdCarLocation,
            tvAdCarFuel, tvAdCarKm, tvAdCarYear;

    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        ivAdCar = itemView.findViewById(R.id.ivAdCar);
        tvAdCarTitle = itemView.findViewById(R.id.tvAdCarTitle);
        tvAdCarPrice = itemView.findViewById(R.id.tvAdCarPrice);
        tvAdCarLocation = itemView.findViewById(R.id.tvAdCarLocation);
        tvAdCarFuel = itemView.findViewById(R.id.tvAdCarFuel);
        tvAdCarKm = itemView.findViewById(R.id.tvAdCarKm);
        tvAdCarYear = itemView.findViewById(R.id.tvAdCarYear);
    }

}
