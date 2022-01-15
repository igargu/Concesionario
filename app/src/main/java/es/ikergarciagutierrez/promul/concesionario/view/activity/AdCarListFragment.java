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
        RecyclerView recyclerView = binding.rvCar;
        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getContext()));

        //VideoGameViewModel videoGameVM = new ViewModelProvider(this).get(VideoGameViewModel.class);
        CarAdapter carAdapter = new CarAdapter(getContext());

        recyclerView.setAdapter(carAdapter);
        /*
        LiveData<List<VideoGame_VideoGameConsole>> videoGameList = videoGameVM.getAllVideoGames();
        videoGameList.observe(getViewLifecycleOwner(), videoGames -> {
            videoGameAdapter.setVideoGameList(videoGames);
        });*/

        carAdapter.setOnClickListener(view -> {
            Bundle bundle = new Bundle();/*
            bundle.putSerializable("id_VideoGameName",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.name);
            bundle.putSerializable("id_VideoGameDeveloper",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.developer);
            bundle.putSerializable("id_VideoGameConsole",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.idVideoGameConsole);
            bundle.putSerializable("id_VideoGameGenre",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.genre);
            bundle.putSerializable("id_VideoGameReleaseDate",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.releaseDate);
            bundle.putSerializable("id_VideoGameImageUrl",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.imageUrl);*/
            NavHostFragment.findNavController(AdCarListFragment.this).navigate(R.id.action_AdCarListFragment_to_AdCarFragment,bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}