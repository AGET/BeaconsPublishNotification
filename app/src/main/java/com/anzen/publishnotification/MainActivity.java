package com.anzen.publishnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIF_ALERTA_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                notification();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void notification() {
        Bitmap large = (((BitmapDrawable) getResources().getDrawable(R.drawable.notification)).getBitmap());
        Bitmap notSoLarge = (((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap());

        NotificationCompat.Builder builderNotification =
                new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setLargeIcon(notSoLarge)
                        .setContentTitle("setContentTitle")
                        .setContentText("setContentText setContentText setContentText setContentText.")
                        .setContentInfo("setContentInfo setContentInfo setContentInfo setContentInfo")
                        .setSubText("SubText SubText SubText SubText")
                        .setTicker("Tiker tiker")
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(large))
                        .addAction(android.R.drawable.ic_menu_myplaces, "Option1", getPendingAction(getBaseContext(), "Option"))
                        .addAction(android.R.drawable.ic_input_add, "Option2", getPendingAction(getBaseContext(), "Option2"))
                        .addAction(android.R.drawable.ic_menu_delete, "Option3", getPendingAction(getBaseContext(), "Option3"))

                ;

        Intent notIntent = new Intent(this, MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent, 0);
        builderNotification.setContentIntent(contIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIF_ALERTA_ID, builderNotification.build());
    }

    public PendingIntent getPendingAction(Context context,  String action) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("CLICK", true);
        intent.putExtra("ACTION", action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

}
