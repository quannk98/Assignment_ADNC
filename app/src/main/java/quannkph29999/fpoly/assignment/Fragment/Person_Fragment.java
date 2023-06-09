package quannkph29999.fpoly.assignment.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Adapter.AdapterFavorite;
import quannkph29999.fpoly.assignment.DAO.FavDAO;
import quannkph29999.fpoly.assignment.Model.FavoriteMusic;
import quannkph29999.fpoly.assignment.R;
import quannkph29999.fpoly.assignment.Screen_Login;


public class Person_Fragment extends Fragment {
    FavDAO favDAO;
    AdapterFavorite adapterFavorite;
    ArrayList<FavoriteMusic> listfav;
    RecyclerView recyclerView;
    Button dangxuat;


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
        dangxuat = view.findViewById(R.id.person_btndangxuat);
        realoandata();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Screen_Login.class);
                getContext().startActivity(intent);
                getActivity().finish();

            }
        });


    }


}