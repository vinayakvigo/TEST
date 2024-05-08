package com.example.test.main.Task3;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.test.R;
import com.example.test.main.MainActivity;

public class ForgroundService extends Service {
    public ForgroundService() {
    }

    private static final int NOTIFICATION_ID = 123;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.sound); // Replace "your_music_file" with the name of your music file
    }

    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }

        try{
            // Create a notification for the foreground service
            Notification notification = createNotification();

            // Start the service in the foreground with the notification
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK);
            } else {
                startForeground(1, notification);
            }
        }catch (Error e){
            Log.e("ERROR", "onStartCommand: "+e.toString() );
        }


        // Return START_STICKY if the system kills the service after onStartCommand() returns
        return START_STICKY;
    }

    private Notification createNotification() {
        // Create a notification channel if targeting Android Oreo (API 26) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Create a pending intent to open the app when notification clicked
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, services.class), PendingIntent.FLAG_IMMUTABLE);

        // Create a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Music Service")
                .setContentText("Playing Music")
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentIntent(pendingIntent);

        return builder.build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }


}

