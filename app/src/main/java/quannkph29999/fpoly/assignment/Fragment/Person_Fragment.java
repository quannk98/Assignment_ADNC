package quannkph29999.fpoly.assignment.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Adapter.AdapterFavorite;
import quannkph29999.fpoly.assignment.DAO.FavDAO;
import quannkph29999.fpoly.assignment.Model.FavoriteMusic;
import quannkph29999.fpoly.assignment.R;


public class Person_Fragment extends Fragment {
    FavDAO favDAO;
    AdapterFavorite adapterFavorite;
    ArrayList<FavoriteMusic> listfav;
    RecyclerView recyclerView;


    public Person_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_, container, false);
        recyclerView = view.findViewById(R.id.person_recycler_yeuthichnhac);
        return view;
    }
    public void realoandata(){
        favDAO = new FavDAO(getContext());
        listfav = favDAO.getDSFM();
        adapterFavorite = new AdapterFavorite(getContext(),listfav,favDAO);
        recyclerView.setAdapter(adapterFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        realoandata();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}