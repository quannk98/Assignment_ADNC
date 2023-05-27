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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Adapter.AdapterNews;
import quannkph29999.fpoly.assignment.DAO.NewsDAO;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.Model.News;
import quannkph29999.fpoly.assignment.R;


public class News_Fragment extends Fragment {
        NewsDAO newsDAO;
        AdapterNews adapterNews;
        ArrayList<News> listnews;
        RecyclerView recyclerView;
        FloatingActionButton floatingbao;


    public News_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_, container, false);
        recyclerView = view.findViewById(R.id.news_recycler);
        floatingbao = view.findViewById(R.id.news_btnfloatingthembao);
        reloadata();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadata();
    }
    public void reloadata(){
        newsDAO = new NewsDAO(getContext());
        listnews = newsDAO.getDataNews();
        adapterNews = new AdapterNews(listnews,getContext(),newsDAO);
        recyclerView.setAdapter(adapterNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemBao();
            }
        });
    }

    public void ThemBao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_bao, null, false);
        EditText themtenbao = view.findViewById(R.id.dialognews_themtenbaibao);
        EditText themlinkbao = view.findViewById(R.id.dialognews_themlinkbao);
        EditText themlinkanh = view.findViewById(R.id.dialognews_themlinkanh);
        Button thembao = view.findViewById(R.id.dialognews_btnthem);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        thembao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addten = themtenbao.getText().toString();
                String addlinkbao = themlinkbao.getText().toString();
                String addlinkanh = themlinkanh.getText().toString();
                News themnews = new News(addten,addlinkbao,addlinkanh);
                if (themtenbao.length() == 0) {
                    themtenbao.requestFocus();
                    themtenbao.setError("Không để trống phần tên");
                }
                else if (themlinkbao.length() == 0) {
                    themlinkbao.requestFocus();
                    themlinkbao.setError("Không để trống phần link báo");
                }
                else if (themlinkanh.length() == 0) {
                    themlinkanh.requestFocus();
                    themlinkanh.setError("Không để trống phần link ảnh");
                }

                else {
                      newsDAO = new NewsDAO(getContext());
                    if (newsDAO.ThemNews(themnews) > 0) {
                        reloadata();
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