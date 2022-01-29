package es.ikergarciagutierrez.promul.concesionario.view.adapter.viewholder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

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
    public TextInputLayout tilAdCarLocation, tilAdCarFuel, tilAdCarKm, tilAdCarYear;

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
        tilAdCarLocation = itemView.findViewById(R.id.tilAdCarLocation);
        etAdCarFuel = itemView.findViewById(R.id.etAdCarFuel);
        tilAdCarFuel = itemView.findViewById(R.id.tilAdCarFuel);
        etAdCarKm = itemView.findViewById(R.id.etAdCarKm);
        tilAdCarKm = itemView.findViewById(R.id.tilAdCarKm);
        etAdCarYear = itemView.findViewById(R.id.etAdCarYear);
        tilAdCarYear = itemView.findViewById(R.id.tilAdCarYear);
    }

}
