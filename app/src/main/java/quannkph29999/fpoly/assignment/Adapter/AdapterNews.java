package quannkph29999.fpoly.assignment.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.ImageDecoder;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.DAO.MusicDAO;
import quannkph29999.fpoly.assignment.DAO.NewsDAO;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.Model.News;
import quannkph29999.fpoly.assignment.R;

public class AdapterNews  {
//    ArrayList<News> listnews;
//    Context context;
//    NewsDAO newsDAO;
//
//    public AdapterNews(ArrayList<News> listnews, Context context, NewsDAO newsDAO) {
//        this.listnews = listnews;
//        this.context = context;
//        this.newsDAO = newsDAO;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tenbaibao;
//        ImageView imgbao;
//        RelativeLayout itemnews;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tenbaibao = itemView.findViewById(R.id.itemnews_tenbaibao);
//            imgbao = itemView.findViewById(R.id.itemnews_imganh);
//            itemnews = itemView.findViewById(R.id.itemnews);
//
//        }
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_news, null);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.tenbaibao.setText(listnews.get(position).getTenbao());
//        holder.itemnews.setOnLongClickListener(new View.OnLongClickListener() {
//            @SuppressLint("RestrictedApi")
//            @Override
//            public boolean onLongClick(View v) {
//                @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
//                MenuInflater menuInflater = new MenuInflater(context);
//                menuInflater.inflate(R.menu.menu_news, menuBuilder);
//                @SuppressLint("RestrictedApi") MenuPopupHelper optionnews = new MenuPopupHelper(context, menuBuilder, v);
//                menuBuilder.setCallback(new MenuBuilder.Callback() {
//                    @Override
//                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
//                        if (item.getItemId() == R.id.opnews_showdata) {
//                            ShowdataNews(context, listnews.get(position));
//                            return true;
//                        } else if (item.getItemId() == R.id.opnews_edit) {
//                            SuaNews(context,listnews.get(position));
//                            return true;
//                        } else if (item.getItemId() == R.id.opnews_delete) {
//                            newsDAO = new NewsDAO(context);
//                            int idbao = listnews.get(position).getIdbao();
//                            if (newsDAO.XoaNews(idbao) > 0) {
//                                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
//                                listnews.clear();
//                                newsDAO = new NewsDAO(context);
//                                listnews = newsDAO.getDataNews();
//                                notifyDataSetChanged();
//
//                            } else {
//                                Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
//                            }
//                            return true;
//                        } else {
//                            return false;
//                        }
//
//                    }
//
//                    @Override
//                    public void onMenuModeChange(@NonNull MenuBuilder menu) {
//
//                    }
//                });
//                optionnews.show();
//
//                return false;
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return listnews.size();
//    }
//
//    public void ShowdataNews(Context context, News news) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Thông Tin Bài Báo");
//        builder.setMessage("Tên Bài Báo:" + news.getTenbao() + "\nLink Báo:" + news.getLinkbao()+"\nLink Ảnh:"+news.getLinkanh());
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                alertDialog.dismiss();
//            }
//        });
//    }
//
//    public void SuaNews(Context context, News news) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_sua_bao, null, false);
//        EditText suatenbao = view.findViewById(R.id.dialognews_suatenbaibao);
//        EditText sualinkbao = view.findViewById(R.id.dialognews_sualinkbao);
//        EditText sualinkanh = view.findViewById(R.id.dialognews_sualinkanh);
//        Button suabao = view.findViewById(R.id.dialognews_btnsua);
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        suatenbao.setText(news.getTenbao());
//        sualinkbao.setText(news.getLinkbao());
//        sualinkanh.setText(news.getLinkanh());
//        suabao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String ten = suatenbao.getText().toString();
//                String linkbao = sualinkbao.getText().toString();
//                String linkanh = sualinkanh.getText().toString();
//                int idbao = news.getIdbao();
//                if (suatenbao.length() == 0) {
//                    suatenbao.requestFocus();
//                    suatenbao.setError("Không để trống tên báo");
//                } else if (sualinkbao.length() == 0) {
//                    sualinkbao.requestFocus();
//                    sualinkbao.setError("Không để trống link báo");
//                } else if (sualinkanh.length() == 0) {
//                    sualinkanh.requestFocus();
//                    sualinkanh.setError("Không để trống link ảnh");
//                } else {
//                    newsDAO = new NewsDAO(context);
//                    News suanews = new News(idbao, ten, linkbao, linkanh);
//                    if (newsDAO.SuaNews(suanews) > 0) {
//                        Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
//                        listnews.clear();
//                        newsDAO = new NewsDAO(context);
//                        listnews = newsDAO.getDataNews();
//                        notifyDataSetChanged();
//                        alertDialog.dismiss();
//
//                    } else {
//                        Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//    }


}
