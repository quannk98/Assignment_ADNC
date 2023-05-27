package quannkph29999.fpoly.assignment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DAO.FavDAO;
import quannkph29999.fpoly.assignment.DAO.MusicDAO;
import quannkph29999.fpoly.assignment.DBHepler.DBHelper;
import quannkph29999.fpoly.assignment.Model.FavoriteMusic;
import quannkph29999.fpoly.assignment.R;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {
    private Context context;
    private ArrayList<FavoriteMusic> listmfav;
    private FavDAO favDAO;

    public AdapterFavorite(Context context, ArrayList<FavoriteMusic> listmfav, FavDAO favDAO) {
        this.context = context;
        this.listmfav = listmfav;
        this.favDAO = favDAO;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenfav;
        ImageButton xoafav;
        RelativeLayout itemfav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenfav = itemView.findViewById(R.id.itemfav_tenfav);
            xoafav = itemView.findViewById(R.id.itemfav_xoafav);
            itemfav = itemView.findViewById(R.id.itemfav);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenfav.setText(listmfav.get(position).getTennhac());
        holder.xoafav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idfav = listmfav.get(position).getIdfav();
                if (favDAO.XoaFav(idfav) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    listmfav.clear();
                    favDAO = new FavDAO(context);
                    listmfav = favDAO.getDSFM();
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listmfav.size();
    }


}
