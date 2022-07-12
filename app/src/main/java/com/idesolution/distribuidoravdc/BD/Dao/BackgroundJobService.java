package com.idesolution.distribuidoravdc.BD.Dao;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.util.Log;

public class BackgroundJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(this.getClass().getSimpleName(),"onStartJob");
        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getApplicationContext(),"CONSUMO JOB", Toast.LENGTH_SHORT).show();
                //jobFinished(params, false);
            }
        });
        BootReceiverTP.scheduleJob(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
