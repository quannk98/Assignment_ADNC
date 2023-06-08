package quannkph29999.fpoly.assignment.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

import quannkph29999.fpoly.assignment.Model.Music;

public class Service_Music extends Service {
    public static final int ACTION_START = 1;
    public static final int ACTION_PAUSE = 2;
    public static final int ACTION_RESUME = 3;
    public static final int ACTION_STOP = 4;
    public static final int ACTION_NEXT = 5;
    public static final int ACTION_PREV = 6;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying;
    private Music music;


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String musicURI = intent.getStringExtra("linknhac");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Music mmusic = (Music) bundle.get("object_music");
            if (mmusic != null) {
                mmusic = music;
                startMusic(musicURI);
            }

        }
        int action_broadcast = intent.getIntExtra("action_broadcast", 0);
        handleActionMusic(action_broadcast);
        return START_NOT_STICKY;
    }

    private void startMusic(String musicURI) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(musicURI);
            mediaPlayer.prepare();
            mediaPlayer.start();
            isPlaying = true;
            sendActivity(ACTION_START);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(String.valueOf(musicURI)));
//        }
//        mediaPlayer.start();
//        isPlaying = true;
//        sendActivity(ACTION_START);


    }

    private void handleActionMusic(int action) {
        switch (action) {
            case ACTION_PAUSE:
                PauseMusic();
                break;
            case ACTION_RESUME:
                ResumeMusic();
                break;
            case ACTION_STOP:
                stopSelf();
                sendActivity(ACTION_STOP);
                break;
            case ACTION_NEXT:
                NextMusic();
                sendActivity(ACTION_NEXT);
                break;
            case ACTION_PREV:
                PrevMusic();
                sendActivity(ACTION_PREV);
                break;
        }
    }


    private void PauseMusic() {
        if (mediaPlayer != null && isPlaying == true) {
            mediaPlayer.pause();
            isPlaying = false;
            sendActivity(ACTION_PAUSE);
        }
    }

    private void ResumeMusic() {
        if (mediaPlayer != null && isPlaying == false) {
            mediaPlayer.start();
            isPlaying = true;
            sendActivity(ACTION_RESUME);
        }
    }

    private void NextMusic() {
        startMusic(music.getLinknhac());

    }

    private void PrevMusic() {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void sendActivity(int action) {
        Intent intentActivity = new Intent("sendDataActivity");
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_music", music);
        bundle.putBoolean("status_music", isPlaying);
        bundle.putInt("action_music", action);
        intentActivity.putExtras(bundle);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentActivity);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
