package quannkph29999.fpoly.assignment.ScreenNews;

import static android.R.layout.simple_list_item_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import quannkph29999.fpoly.assignment.Adapter.AdapterNews;
import quannkph29999.fpoly.assignment.ContentNews;
import quannkph29999.fpoly.assignment.Model.News;
import quannkph29999.fpoly.assignment.R;
import quannkph29999.fpoly.assignment.XML.ThoiSuXML;


public class ThoiSuFragment extends Fragment {
    RecyclerView recyclerView;
    News news;
    AdapterNews adapterNews;



    public ThoiSuFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thoi_su, container, false);
        recyclerView= view.findViewById(R.id.ThoiSu_reycc);
        String urlRss = "https://vnexpress.net/rss/thoi-su.rss";
        DownLoadThoiSu downLoadThoiSu = new DownLoadThoiSu();
        downLoadThoiSu.execute(urlRss);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class DownLoadThoiSu extends AsyncTask<String, Void, List<News>> {


        @Override
        protected List<News> doInBackground(String... strings) {
            List<News> list = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    InputStream inputStream = urlConnection.getInputStream();
                    list = new ThoiSuXML().getlistthoisu(inputStream);

                }
                urlConnection.disconnect();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<News> thoisus) {
            super.onPostExecute(thoisus);
            adapterNews = new AdapterNews((ArrayList<News>) thoisus,getContext());
            recyclerView.setAdapter(adapterNews);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }
    }
}