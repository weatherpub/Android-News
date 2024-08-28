package edu.sfsu.times.ui.notifications;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {
    private final Handler handler;

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {
        Log.i("LOG", "NotificationViewModel constructor");

        HandlerThread workerThread = new HandlerThread("WorkerThread");
        workerThread.start();
        handler = new Handler(workerThread.getLooper());

        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    /* *
     * Non-static nested classes (inner classes) have access to other members of the enclosing class,
     * even if they are declared private.
     */
}