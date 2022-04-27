package com.example.td_mvvm.models;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.td_mvvm.MainActivity;
import com.example.td_mvvm.R;
import com.example.td_mvvm.storage.PreferencesHelper;

public class ForegroundService extends Service {
    private HandlerThread handlerThread;
    private Handler handler;
    public static Boolean isRunning;
    private final static int ID_COMM = 1;
    private NotificationManager nf;
    public ForegroundService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("May be implemented in a near future");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        setupForeground();
        startMonitoring();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        handlerThread.quit();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread= new HandlerThread("service_thread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);
        //notifMana = new NotificationManagerCompat();
        // Faire un timer ou un truc du genre pour trigger toutes les 5 minutes startMonitoring ?
        nf = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void setupForeground(){
        // Lancer la notif
        if(checkFavCoinInDB()) startForeground(ID_COMM,creationNotif());
    }

    private void startMonitoring(){
        // Get la crypto favorite
        int a = 2;
       handler.post(new Runnable() {
           @Override
           public void run() {
                // Get la crypto et son petit prix

                if(checkFavCoinInDB())
                    nf.notify(ID_COMM,creationNotif());

                //notifMana.notify(ID_COMM,creationNotif());
               // ON BOUCLE ICI
               handler.postDelayed(this, 5*60*1000);
           }
       });
    }

    private Coin getFavCoin(){
        // Aller dans les sharedPreferences
        return PreferencesHelper.getInstance().getFavCoinData();
    }


    private Notification creationNotif(){
        Coin coin = getFavCoin();
        Notification notification = new NotificationCompat.Builder(this, MainActivity.Channel1)
                .setSmallIcon(R.drawable.brake_disc)
                .setContentTitle("Tu es aussi beau qu'un camion")
                .setContentText("Le "+ coin.getName() + " est actuellement à " + coin.getPrice().substring(0,7)+ "$")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();
        return notification;
    }

    private boolean checkFavCoinInDB(){
        if (PreferencesHelper.getInstance().getFavCoinData()!=null) return true;
        return false;
    }
}