package quannkph29999.fpoly.assignment.Service;

import static quannkph29999.fpoly.assignment.Notification.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

import quannkph29999.fpoly.assignment.BroadCast.MusicReceiver;
import quannkph29999.fpoly.assignment.Fragment.Music_Fragment;
import quannkph29999.fpoly.assignment.Model.Music;
import quannkph29999.fpoly.assignment.R;

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
                music = mmusic;
                startMusic(musicURI);
                sendNotification(music);
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
                break;
            case ACTION_PREV:
                break;
        }
    }


    private void PauseMusic() {
        if (mediaPlayer != null && isPlaying == true) {
            mediaPlayer.pause();
            isPlaying = false;
            sendNotification(music);
            sendActivity(ACTION_PAUSE);
        }
    }

    private void ResumeMusic() {
        if (mediaPlayer != null && isPlaying == false) {
            mediaPlayer.start();
            isPlaying = true;
            sendNotification(music);
            sendActivity(ACTION_RESUME);
        }
    }


    private void sendNotification(Music music) {

        Intent intent = new Intent(this, Music_Fragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custome_notification);
        remoteViews.setTextViewText(R.id.cuno_title_song, music.getTennhac());
        remoteViews.setImageViewResource(R.id.cuno_img_pause, R.drawable.baseline_pause_circle_24);
        if (!isPlaying) {
            remoteViews.setOnClickPendingIntent(R.id.cuno_img_pause, getPendingintent(this, ACTION_RESUME));
            remoteViews.setImageViewResource(R.id.cuno_img_pause, R.drawable.baseline_play_circle_24);
        } else {
            remoteViews.setOnClickPendingIntent(R.id.cuno_img_pause, getPendingintent(this, ACTION_PAUSE));
            remoteViews.setImageViewResource(R.id.cuno_img_pause, R.drawable.baseline_pause_circle_24);
        }
        remoteViews.setOnClickPendingIntent(R.id.cuno_img_clear, getPendingintent(this, ACTION_STOP));
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_music_note_24)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .build();
        startForeground(1, notification);
    }

    private PendingIntent getPendingintent(Context context, int action) {
        Intent intent = new Intent(this, MusicReceiver.class);
        intent.putExtra("action_music", action);
        return PendingIntent.getBroadcast(context.getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
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
