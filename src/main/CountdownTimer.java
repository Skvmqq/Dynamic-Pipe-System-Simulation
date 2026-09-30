package main;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    public int seconds = 90;

    public CountdownTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (seconds >= 0) {
                    seconds--;
                } else {
                    System.out.println("Time's up!");
                    timer.cancel();
                }
            }
        };

        // Schedule the task to run every second (1000 milliseconds)
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
}