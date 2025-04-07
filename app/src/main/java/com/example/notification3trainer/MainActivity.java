package com.example.notification3trainer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {
    Button button;
    private static final  String CHANNEL_ID = "default_channel";
    public static final String CHANNEL_ID_HIGH = "Channel_high_priority";
    private static final String CHANNEL_NAME = "Kanał Powiadomień222";

    private static int ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel(this);

        button = findViewById(R.id.notification);
        button.setOnClickListener(v ->{
            sendNotification(ID, this);
            ID++;
        });

    }
    public static void sendNotification(int NOTIFICATION_ID, AppCompatActivity activity) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }


        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(R.drawable.mi)
                .setContentTitle("My Tittle")
                .setContentText("Hi dear consumer!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_HIGH, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

/*


public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_channel_id";
    private static int ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationHelper.createNotificationChannels(this);

        Button btn = findViewById(R.id.notification);
        btn.setOnClickListener(v->{
            NotificationHelper.sendNotification(ID, this, "Custom",
                    "Custom posiwadomienie");
            ID++;
        });
        }

package com.example.notification;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper {
    private static final String CHANNEL_ID = "default_channel";
    private static final String CHANNEL_NAME = "Kanał Powiadomień";
    private static final int NOTIFICATION_ID = 1;
    public static final String CHANNEL_ID_LOW = "Channel_low_priority";
    public static final String CHANNEL_ID_DEFAULT = "Channel_default_priority";
    public static final String CHANNEL_ID_HIGH = "Channel_high_priority";
    // kanal ma importance wieksza niz same powiadomienia

    public static void createNotificationChannels(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channe = new NotificationChannel(CHANNEL_ID_HIGH, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channe);

        }

    }

    public static void sendNotification(int NOTIFICATION_ID, AppCompatActivity activity, String title, String message){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
               ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}

*/