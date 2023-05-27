package quannkph29999.fpoly.assignment.Fragment;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Adapter.AdapterMusic;
import quannkph29999.fpoly.assignment.DAO.MusicDAO;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.R;


public class Music_Fragment extends Fragment {
     MusicDAO musicDAO;
     AdapterMusic adapterMusic;
     ArrayList<Music> listmusic;
     RecyclerView recyclerView;
     FloatingActionButton floatingthem;
    TextView tenbaihat;


    public Music_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.music_recycler);
        tenbaihat = view.findViewById(R.id.music_edtensong);
        floatingthem = view.findViewById(R.id.music_btnfloatingthembaihat);
        realoaddata();
        floatingthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemMusic();
            }
        });

    }




    public void realoaddata() {
        musicDAO = new MusicDAO(getContext());
        listmusic = musicDAO.getDataMusic();
        adapterMusic = new AdapterMusic(listmusic, getContext(), musicDAO,tenbaihat);
        recyclerView.setAdapter(adapterMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    @Override
    public void onResume() {
        super.onResume();
        realoaddata();

    }

    public void ThemMusic() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_music, null, false);
        EditText themtenmusic = view.findViewById(R.id.dialogmusic_themtenbaihat);
        EditText themlinknhac = view.findViewById(R.id.dialogmusic_themlinknhac);
        Button themnhac = view.findViewById(R.id.dialogmusic_btnthem);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        themnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themten = themtenmusic.getText().toString();
                String themlink = themlinknhac.getText().toString();
                Music themmusic = new Music(themten, themlink);
                if (themtenmusic.length() == 0) {
                    themtenmusic.requestFocus();
                    themtenmusic.setError("Không để trống phần tên");
                } else if (themlinknhac.length() == 0) {
                    themlinknhac.requestFocus();
                    themlinknhac.setError("Không để trống phần link");
                } else {

                    if (musicDAO.ThemMusic(themmusic) > 0) {
                        realoaddata();
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}