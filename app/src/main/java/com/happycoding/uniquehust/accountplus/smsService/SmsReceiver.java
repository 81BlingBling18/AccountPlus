package com.happycoding.uniquehust.accountplus.smsService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.details.MainActivity;

import static android.support.v4.app.NotificationCompat.DEFAULT_ALL;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        Bundle myBundle = intent.getExtras();
        SmsMessage[] messages = null;
        String strMessage = "";

        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");

            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = myBundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                strMessage += " : ";
                strMessage += messages[i].getMessageBody();
                strMessage += "\n";
                //if(messages[i].getOriginatingAddress().length() == 5) {
                    String msg = messages[i].getMessageBody();
                    showNotification(context, msg);
                //}
            }



        }
    }

    public void showNotification(Context context, String msg) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.book_pressed)
                        .setContentTitle("检测到消费短信")
                        .setContentText("点击此通知自动添加到账本")
                        .setPriority(PRIORITY_HIGH)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setTicker("检测到消费短信")
                        .setAutoCancel(true);


        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.putExtra("sms", msg);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }

}