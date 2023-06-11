package quannkph29999.fpoly.assignment.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import quannkph29999.fpoly.assignment.Service.Service_Music;

public class MusicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int actonMusic =  intent.getIntExtra("action_music",0);
       Intent intent1 = new Intent(context, Service_Music.class);
       intent1.putExtra("action_broadcast",actonMusic);
       context.startService(intent1);
    }
}
