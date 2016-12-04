package com.happycoding.uniquehust.accountplus.RemindKeepAccountsService;

import android.accounts.Account;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.add_item.AddEditAccountActivity;
import com.happycoding.uniquehust.accountplus.global.AccountPlusApp;

/**
 * Created by qimeng on 16-12-1.
 */

public class RemindKeepAccountsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) AccountPlusApp.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent notiIntent = PendingIntent.getActivity(AccountPlusApp.getInstance(),
                0,new Intent(AccountPlusApp.getInstance(), AddEditAccountActivity.class),0);
        Notification notification = new Notification.Builder(AccountPlusApp.getInstance())
                .setSmallIcon(R.mipmap.webwxgetmsgimg)
                .setTicker("记账提醒")
                .setContentTitle("该记账啦")
                .setContentText("记账时间到啦")
                .setContentIntent(notiIntent)
                .setNumber(1).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(1, notification);

    }
}
