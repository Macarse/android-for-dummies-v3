package com.dummies.tasks.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.dummies.tasks.fragment.TaskEditFragment;
import com.dummies.tasks.receiver.OnAlarmReceiver;

import java.util.Calendar;

/**
 * A helper class that knows how to set reminders using the AlarmManager
 */
public class ReminderManager {

    public static void setReminder(Context context, long taskId,
                                   String title, Calendar when) {

        AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);

        // Create an intent for our OnAlarmReceiver,
        // which will show the notification when it is called
        Intent i = new Intent(context, OnAlarmReceiver.class);
        i.putExtra(TaskEditFragment.TASK_ID, taskId);
        i.putExtra(TaskEditFragment.TASK_TITLE, title);

        // Create the PendingIntent that will wrap the
        // above intent.  All intents that are used in
        // the AlarmManager must be wrapped in a PendingIntent to "give
        // permission" to the AlarmManager to call back into our
        // application.
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i,
                PendingIntent.FLAG_ONE_SHOT);

        // Set the alarm
        alarmManager.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis(),
                pi);
    }
}
