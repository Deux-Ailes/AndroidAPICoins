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

import androidx.core.app.NotificationCompat;

import com.example.td_mvvm.activities.MainActivity;
import com.example.td_mvvm.R;
import com.example.td_mvvm.storage.PreferencesHelper;

public class ForegroundService extends Service {

    private final static int ID_COMM = 1;
    private static boolean instanciated = false;
    public static Boolean isRunning;
    private HandlerThread handlerThread;
    private Handler handler;
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
        if(!instanciated){ // Boolean qui indique si le service a déjà été instancié
            handlerThread = new HandlerThread("service_thread");
            handlerThread.start();
            Looper looper = handlerThread.getLooper();
            handler = new Handler(looper);
            nf = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE); // Nécessaire pour gérer les notifs
            instanciated = true; // Empêche une nouvelle instanciation
        }

    }

    /**
     * Si une coin est présente dans la database, lance la première notif en foreground
     */
    private void setupForeground() {

        if (checkFavCoinInDB()) startForeground(ID_COMM, creationNotification());
    }

    /**
     * Poste une notif toutes les 5 minutes si une coin est présente dans la DB.
     */
    private void startMonitoring() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Get la crypto et son petit prix
                if (checkFavCoinInDB())
                    nf.notify(ID_COMM, creationNotification()); // Hop petite notif
                // Boucle de 5 minutes
                handler.postDelayed(this, 5 * 60 * 1000);
            }
        });
    }

    /**
     * Récupère dans la DB la coin favorite
     * @return Coin favorite
     */
    private CoinTable getFavCoin() {
        // Aller dans les sharedPreferences
        return PreferencesHelper.getInstance().getFavCoinData();
    }


    /**
     * Créer une notification en se basant sur la coin stockée en DB
     * @return Notification construite
     */
    private Notification creationNotification() {
        CoinTable coinTable = getFavCoin();
        return new NotificationCompat.Builder(this, MainActivity.Channel1)
                .setSmallIcon(R.drawable.brake_disc)
                .setContentTitle(getString(R.string.notifTitle))
                .setContentText(String.format("Le %s est actuellement à %s$", coinTable.getName(), coinTable.getPrice().substring(0, 7)))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();
    }

    private boolean checkFavCoinInDB() {
        return PreferencesHelper.getInstance().getFavCoinData() != null;
    }
}