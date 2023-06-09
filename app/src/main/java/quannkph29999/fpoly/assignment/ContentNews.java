package quannkph29999.fpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import quannkph29999.fpoly.assignment.Fragment.News_Fragment;
import quannkph29999.fpoly.assignment.ScreenNews.ThoiSuFragment;

public class ContentNews extends AppCompatActivity {
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_news);
//        back = findViewById(R.id.back);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent back = new Intent(ContentNews.this, News_Fragment.class);
//                startActivity(back);
//                finish();
//            }
//        });


        WebView Webview = findViewById(R.id.wedview);
        Webview.loadUrl(link);
        Webview.setWebViewClient(new WebViewClient());
    }
}