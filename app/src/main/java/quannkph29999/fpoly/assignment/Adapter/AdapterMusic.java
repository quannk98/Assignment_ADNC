package quannkph29999.fpoly.assignment.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DAO.FavDAO;
import quannkph29999.fpoly.assignment.DAO.MusicDAO;
import quannkph29999.fpoly.assignment.Model.FavoriteMusic;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.R;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder> {
    ArrayList<Music> listmusic;
    ArrayList<FavoriteMusic> listfav;
    Context context;
    MusicDAO musicDAO;
    FavoriteMusic favoriteMusic;
    TextView title;
    FavDAO favDAO;
    boolean check = false;

    public AdapterMusic(ArrayList<Music> listmusic, Context context, MusicDAO musicDAO, TextView title) {
        this.listmusic = listmusic;
        this.context = context;
        this.musicDAO = musicDAO;
        this.title = title;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenbh;
        ImageView yeuthich;
        RelativeLayout itemsong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenbh = itemView.findViewById(R.id.itemsong_tenbaihat);
            yeuthich = itemView.findViewById(R.id.itemsong_yeuthich);
            itemsong = itemView.findViewById(R.id.itemsong);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenbh.setText(listmusic.get(position).getTennhac());
        holder.yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteMusic = new FavoriteMusic();
                String tenfav = listmusic.get(position).getTennhac();
                favDAO = new FavDAO(context);
                FavoriteMusic themfav = new FavoriteMusic(tenfav);
                if (check == false) {
                    if (favDAO.ThemFav(themfav) > 0) {
                        try {
                            favoriteMusic.setTennhac(listmusic.get(position).getTennhac());
                            holder.yeuthich.setImageResource(R.drawable.baseline_favorite_red_24);
                            Toast.makeText(context, "Đã Thêm Vào Danh Sách Yêu Thích", Toast.LENGTH_SHORT).show();
                            check = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(context, "Đã Tồn Tại Bài Hát Trong Danh Sách Yêu Thích", Toast.LENGTH_SHORT).show();
                    }

                } else if (check == true) {
                    holder.yeuthich.setImageResource(R.drawable.baseline_favorite_24);
                    Toast.makeText(context, "Đã Xóa Khỏi Danh Sách Yêu Thích", Toast.LENGTH_SHORT).show();
                    check = false;
                }

            }
        });
        holder.itemsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chuyenten = listmusic.get(position).getTennhac();
                title.setText(chuyenten);
            }
        });
        holder.itemsong.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
                MenuInflater inflater = new MenuInflater(context);
                inflater.inflate(R.menu.menu_music, menuBuilder);
                @SuppressLint("RestrictedApi") MenuPopupHelper optionmusic = new MenuPopupHelper(context, menuBuilder, v);
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.opmusic_showdata) {
                            ShowdataMusic(context, listmusic.get(position));
                            return true;
                        } else if (item.getItemId() == R.id.opmusic_edit) {
                            SuaMuic(context, listmusic.get(position));
                            return true;
                        } else if (item.getItemId() == R.id.opmusic_delete) {
                            int idnhac = listmusic.get(position).getIdnhac();
                            if (musicDAO.XoaMusic(idnhac) > 0) {
                                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                                listmusic.clear();
                                musicDAO = new MusicDAO(context);
                                listmusic = musicDAO.getDataMusic();
                                notifyDataSetChanged();

                            } else {
                                Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        } else {
                            return false;
                        }

                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });
                optionmusic.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listmusic.size();
    }

    public void ShowdataMusic(Context context, Music music) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Bài Hát");
        builder.setMessage("Tên Bài Hát:" + music.getTennhac() + "\nLink Nhạc:" + music.getLinknhac());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }

    public void SuaMuic(Context context, Music music) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_music, null, false);
        EditText suatennhac = view.findViewById(R.id.dialogmusic_suatenbaihat);
        EditText sualinknhac = view.findViewById(R.id.dialogmusic_sualinknhac);
        Button suanhac = view.findViewById(R.id.dialogmusic_btnsua);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        suatennhac.setText(music.getTennhac());
        sualinknhac.setText(music.getLinknhac());
        suanhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suaten = suatennhac.getText().toString();
                String sualink = sualinknhac.getText().toString();
                int idnhac = music.getIdnhac();
                if (suatennhac.length() == 0) {
                    suatennhac.requestFocus();
                    suatennhac.setError("Không để trống tên nhạc");
                } else if (sualinknhac.length() == 0) {
                    sualinknhac.requestFocus();
                    sualinknhac.setError("Không để trống link nhạc");
                } else {
                    Music suamusic = new Music(idnhac, suaten, sualink);
                    if (musicDAO.SuaMusic(suamusic) > 0) {
                        Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                        listmusic.clear();
                        musicDAO = new MusicDAO(context);
                        listmusic = musicDAO.getDataMusic();
                        notifyDataSetChanged();
                        alertDialog.dismiss();

                    } else {
                        Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}
