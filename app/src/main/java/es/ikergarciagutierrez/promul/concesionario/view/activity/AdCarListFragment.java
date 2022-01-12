package es.ikergarciagutierrez.promul.concesionario.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.ikergarciagutierrez.promul.concesionario.R;
import es.ikergarciagutierrez.promul.concesionario.databinding.FragmentAdCarListBinding;
import es.ikergarciagutierrez.promul.concesionario.view.adapter.CarAdapter;

public class AdCarListFragment extends Fragment {

    private FragmentAdCarListBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdCarListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        RecyclerView recyclerView = binding.rvPokemon;
        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getContext()));
        /*
        VideoGameViewModel videoGameVM = new ViewModelProvider(this).get(VideoGameViewModel.class);
        CarAdapter videoGameAdapter = new CarAdapter(getContext());

        recyclerView.setAdapter(videoGameAdapter);

        LiveData<List<VideoGame_VideoGameConsole>> videoGameList = videoGameVM.getAllVideoGames();
        videoGameList.observe(getViewLifecycleOwner(), videoGames -> {
            videoGameAdapter.setVideoGameList(videoGames);
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}