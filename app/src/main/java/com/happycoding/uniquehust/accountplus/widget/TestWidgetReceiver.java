package com.happycoding.uniquehust.accountplus.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.RemoteViews;

import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.details.MainActivity;
import com.happycoding.uniquehust.accountplus.details.SuccessInformActivity;

/**
 * Created by yifan on 2016/11/30.
 */

public class TestWidgetReceiver extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetID = appWidgetIds[i];

            Intent intent = new Intent(context, SuccessInformActivity.class);
            intent.putExtra("flag", true);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            views.setOnClickPendingIntent(R.id.widget_ok, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetID, views);
        }
    }
}