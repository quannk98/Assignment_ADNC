package quannkph29999.fpoly.assignment.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Adapter.AdapterNews;
import quannkph29999.fpoly.assignment.Adapter.ViewPager2Adapter;
import quannkph29999.fpoly.assignment.DAO.NewsDAO;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.Model.News;
import quannkph29999.fpoly.assignment.R;
import quannkph29999.fpoly.assignment.ScreenNews.NewsViewPager2Adapter;


public class News_Fragment extends Fragment {
    NewsViewPager2Adapter newsViewPager2Adapter;

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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.news_tablayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.news_viewpager);
        newsViewPager2Adapter = new NewsViewPager2Adapter(News_Fragment.this);
        viewPager2.setAdapter(newsViewPager2Adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Thời Sự");
                        break;
                    case 1:
                        tab.setText("Bóng Đá");
                        break;
                    case 2:
                        tab.setText("Du Lịch");
                        break;
                }
            }
        }).attach();

    }


}