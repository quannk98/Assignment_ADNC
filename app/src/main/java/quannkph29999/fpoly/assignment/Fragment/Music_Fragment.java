package quannkph29999.fpoly.assignment.Fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Adapter.AdapterMusic;
import quannkph29999.fpoly.assignment.DAO.MusicDAO;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.R;
import quannkph29999.fpoly.assignment.Service.Service_Music;


public class Music_Fragment extends Fragment {
    MusicDAO musicDAO;
    AdapterMusic adapterMusic;
    RecyclerView recyclerView;
    FloatingActionButton floatingthem;
    TextView tenbaihat;
    ImageButton btn_next, btn_prev, btn_pause, btn_clear;
    Music music;
    private boolean isplaying;
    boolean trangthai;
    ArrayList<Music> listmusic;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            } else {
                music = (Music) bundle.get("object_music");
                isplaying = (boolean) bundle.get("status_music");
                int actionMusic = bundle.getInt("action_music");
//                handleLayoutMusic(actionMusic)
            }
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.music_recycler);
        tenbaihat = view.findViewById(R.id.music_edtensong);
        floatingthem = view.findViewById(R.id.music_btnfloatingthembaihat);
        btn_next = view.findViewById(R.id.music_imbtnnext);
        btn_prev = view.findViewById(R.id.music_imbtnpre);
        btn_pause = view.findViewById(R.id.music_imbtnpause);
        btn_clear = view.findViewById(R.id.music_imbtclear);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver,
                new IntentFilter("sendDataActivity"));
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Service_Music.class);
                getActivity().stopService(intent);
            }
        });
            btn_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isplaying == true) {
                        sendService(Service_Music.ACTION_PAUSE);
                        btn_pause.setImageResource(R.drawable.baseline_play_circle_24);
                        Toast.makeText(getActivity(), "Tam Dung Bai Hat", Toast.LENGTH_SHORT).show();
                    } else if (isplaying == false) {
                        sendService(Service_Music.ACTION_RESUME);
                        btn_pause.setImageResource(R.drawable.baseline_pause_circle_24);
                        Toast.makeText(getActivity(), "Tiep Tuc Phat Nhac ", Toast.LENGTH_SHORT).show();
                        isplaying = true;
                    }
                }

            });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendService(Service_Music.ACTION_NEXT);
            }
        });


    }

//    private void handleLayoutMusic(int actionMusic) {
//        switch (actionMusic) {
//            case Service_Music.ACTION_START:
//                ShowinfoMusic();
//                setLayoutPause();
//                break;
//            case Service_Music.ACTION_PAUSE:
//                setLayoutPause();
//                break;
//            case Service_Music.ACTION_RESUME:
//                setLayoutPause();
//                break;
//            case Service_Music.ACTION_STOP:
//                break;
//            case Service_Music.ACTION_NEXT:
//                break;
//            case Service_Music.ACTION_PREV:
//                break;
//        }
//    }
//
//
//    private void ShowinfoMusic() {
//        if (music == null) {
//            return;
//        }
//        btn_pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isplaying) {
//                    sendService(Service_Music.ACTION_PAUSE);
//                } else {
//                    sendService(Service_Music.ACTION_RESUME);
//                }
//            }
//        });
//        btn_clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendService(Service_Music.ACTION_STOP);
//            }
//        });
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendService(Service_Music.ACTION_NEXT);
//            }
//        });
//        btn_prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendService(Service_Music.ACTION_PREV);
//            }
//        });
//    }


    private void sendService(int action) {
        Intent intent = new Intent(getActivity(), Service_Music.class);
        intent.putExtra("action_broadcast", action);
        getActivity().startService(intent);
    }


    public Music_Fragment() {

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


    public void realoaddata() {

        musicDAO = new MusicDAO(getContext());
        listmusic = musicDAO.getDataMusic();
        adapterMusic = new AdapterMusic(listmusic, getContext(), musicDAO, tenbaihat);
        recyclerView.setAdapter(adapterMusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        floatingthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemMusic();
            }
        });
        realoaddata();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
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