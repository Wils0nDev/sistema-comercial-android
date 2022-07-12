package com.idesolution.distribuidoravdc.BD.Dao;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class BootReceiverTP extends BroadcastReceiver {
    private static final int PERIOD_MS = 5555000;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"BootReceiverTP", Toast.LENGTH_SHORT).show();
        /*
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Intent newIntent = new Intent(context, BackgroundIntentService.class);
            PendingIntent pendingIntent = PendingIntent.getService(context, 1, newIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis(), PERIOD_MS,
                    pendingIntent);
        } else {

         */
            scheduleJob(context);
        //}
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, BackgroundJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        //builder.setPeriodic(PERIOD_MS);
        builder.setMinimumLatency(PERIOD_MS);
        builder.setOverrideDeadline(PERIOD_MS);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}
