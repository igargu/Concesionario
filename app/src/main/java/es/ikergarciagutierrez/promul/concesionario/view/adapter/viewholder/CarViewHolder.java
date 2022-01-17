package es.ikergarciagutierrez.promul.concesionario.view.adapter.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.promul.concesionario.R;

/**
 * Esta clase define cada item que formarán parte del RecyclerView
 */
public class CarViewHolder extends RecyclerView.ViewHolder {

    /**
     * Campos de la clase
     */
    public ImageView ivAdCar;
    public TextView tvAdCarTitle, tvAdCarPrice;
    public EditText etAdCarLocation, etAdCarFuel, etAdCarKm, etAdCarYear;

    /**
     * Constructor para definir el item
     *
     * @param itemView Este parámetro es la vista del item
     */
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        ivAdCar = itemView.findViewById(R.id.ivAdCar);
        tvAdCarTitle = itemView.findViewById(R.id.tvAdCarTitle);
        tvAdCarPrice = itemView.findViewById(R.id.tvAdCarPrice);
        etAdCarLocation = itemView.findViewById(R.id.etAdCarLocation);
        etAdCarFuel = itemView.findViewById(R.id.etAdCarFuel);
        etAdCarKm = itemView.findViewById(R.id.etAdCarKm);
        etAdCarYear = itemView.findViewById(R.id.etAdCarYear);
    }

}
