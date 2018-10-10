package com.lukemi.android.tutorial.volum;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.lukemi.android.tutorial.R;


/**
 * 音乐服务
 */
public class MusicService extends Service {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String action = intent.getAction();
        switch (action) {
            case Constants.Action.ACTION_START_FOREGROUND:


                Intent detailIntent = new Intent(this, VolumeActivity.class);
                detailIntent.setAction(Constants.Action.ACTION_MUSIC_PLAY);
                PendingIntent detailPendingIntent
                        = PendingIntent.getActivity(this, Constants.NotificationID.NOTIFICATION_ID, detailIntent, PendingIntent.FLAG_CANCEL_CURRENT);

                Intent playIntent = new Intent(this, MusicService.class);
                playIntent.setAction(Constants.Action.ACTION_MUSIC_PLAY);
                PendingIntent playPendingIntent
                        = PendingIntent.getService(this, Constants.NotificationID.NOTIFICATION_ID, playIntent, 0);

                Intent pauseIntent = new Intent(this, MusicService.class);
                pauseIntent.setAction(Constants.Action.ACTION_MUSIC_PLAY);
                PendingIntent pausePendingIntent
                        = PendingIntent.getActivity(this, Constants.NotificationID.NOTIFICATION_ID, pauseIntent, 0);


                String channel_id = "id";
                String name = "name";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    RemoteViews normalRemoteViews = new RemoteViews(getPackageName(), R.layout.remote_view_music_normal);
                    RemoteViews bigRemoteViews = new RemoteViews(getPackageName(), R.layout.remote_view_music_big);


                    String CHANNEL_ID = "my_channel_01";
//                    CharSequence name = "my_channel";
                    String Description = "This is my channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    mChannel.setDescription(Description);
                    mChannel.enableLights(true);
                    mChannel.setLightColor(Color.RED);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100/*, 200, 300, 400, 500, 400, 300, 200, 400*/});
                    mChannel.setShowBadge(false);
                    notificationManager.createNotificationChannel(mChannel);
                    Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Title")
                            .setContentText("哈哈")
                            .setContent(normalRemoteViews)
//                            .setCustomBigContentView(bigRemoteViews)
                            .setPriority(NotificationManager.IMPORTANCE_MAX)
                            .build();

//                    notification.flags=Notification.FLAG_FOREGROUND_SERVICE;
                    startForeground(Constants.NotificationID.NOTIFICATION_ID,
                            notification);
                }

                break;
            case Constants.Action.ACTION_MUSIC_MUSIC_DETAIL:

                break;
            case Constants.Action.ACTION_MUSIC_PLAY:

                break;
            case Constants.Action.ACTION_MUSIC_PAUSE:

                break;
            case Constants.Action.ACTION_STOP_FOREGROUND:

                stopForeground(true);
                stopSelf();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
