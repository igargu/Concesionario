package es.ikergarciagutierrez.promul.concesionario.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.databinding.FragmentAdCarListBinding;
import es.ikergarciagutierrez.promul.concesionario.view.adapter.CarAdapter;

/**
 * Clase que inicializa el RecyclerView que contiene que cada uno de los anuncios de la BD
 */
public class AdCarListFragment extends Fragment {

    /**
     * Campos de la clase
     */
    private FragmentAdCarListBinding binding;

    /**
     * Constructor para el layout del fragmento
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

        binding = FragmentAdCarListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * Método que inicializa el layout del fragmento
     *
     * @param view
     * @param savedInstanceState
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    /**
     * Método que inicializa el RecyclerView y el evento que se produce al pulsar sus items.
     * Al pulsar se mostrará un fragmento que la información detallada del anuncio
     */
    private void initialize() {

        RecyclerView recyclerView = binding.rvCar;
        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getContext()));
        CarAdapter carAdapter = new CarAdapter(getContext());
        recyclerView.setAdapter(carAdapter);

        carAdapter.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            int ad = recyclerView.getChildAdapterPosition(view);
            bundle.putSerializable("idAd", ad);
            NavHostFragment.findNavController(AdCarListFragment.this).navigate(R.id.action_AdCarListFragment_to_AdCarFragment, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}