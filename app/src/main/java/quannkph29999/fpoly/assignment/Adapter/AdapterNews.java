package quannkph29999.fpoly.assignment.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import quannkph29999.fpoly.assignment.ContentNews;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.Model.News;
import quannkph29999.fpoly.assignment.R;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {
    private ArrayList<News> list;
    private Context context;

    public AdapterNews(ArrayList<News> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenbaibao;
        ImageView img;
        RelativeLayout itembao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenbaibao = itemView.findViewById(R.id.news_title);
            img = itemView.findViewById(R.id.news_img);
            itembao = itemView.findViewById(R.id.news_relative);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tenbaibao.setText(list.get(position).getTitle());
        String description = list.get(position).getDescription();


        Pattern pattern = Pattern.compile("src=\\\"(.*?)\\\"");
        Matcher matcher = pattern.matcher(description);
        String url_image = "";
        if (matcher.find()) {
            url_image = matcher.group(1);

        }
        Picasso.get().load(url_image).into(holder.img);

        holder.itembao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContentNews.class);
                intent.putExtra("link", list.get(position).getLink().trim());
                context.startActivity(intent);
            }
        });
        holder.itembao.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ShowdataNews(context, list.get(position));
                return false;
            }
        });
    }

    public void ShowdataNews(Context context, News news) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông Tin Bài Báo");
        builder.setMessage("Tên Bài Báo:" + news.getTitle() + "\n\nLink Bài Báo:" + news.getLink());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
