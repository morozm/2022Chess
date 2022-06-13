package pl.pw.edu.ee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerLabel extends JLabel {
    // Add in your code for 'format' and 'remainingTime'.
    // Note that the first time that 'getText' is called, it's called from the
    // constructor
    // if the superclass, so your own class is not fully initialized at this point.
    // Hence the 'if (format != null)' check
    String format;
    int remainingTime;
    Timer timer;

    public TimerLabel(int time) {
        remainingTime = time;
        setText(getRemainingTime());
        // Thread thread = new Thread(new Runnable() {
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setText(getRemainingTime());
                repaint();
            }
        });
        // timer.start();
        // timer.stop();
    }

    public String getRemainingTime() {
        if (remainingTime <= 100) {
            remainingTime = 0;
        } else {
            remainingTime -= 100;
        }
        int hours = (int) ((this.remainingTime / 3600000) % 60);
        int minutes = (int) ((this.remainingTime / 60000) % 60);
        int seconds = (int) (((this.remainingTime) / 1000) % 60);
        int deciseconds = (int) (((this.remainingTime) / 100) % 10);
        format =
                // String.format("%02d", hours) + " " +
                String.format("%02d", minutes) + " " + String.format("%02d", seconds) + " "
                        + String.format("%01d", deciseconds);
        if (format != null) {
            return format;
        } else {
            return "00 00 00";
        }
    }

    public void stopTimer() {
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }

    public void resetTimer() {
        remainingTime = 10 * 60 * 1000 + 100;
        setText(getRemainingTime());
        repaint();
    }

    public boolean isRunOutOfTime() {
        if (remainingTime == 0) {
            return true;
        } else {
            return false;
        }
    }
    // @Override
    // public String getText() {
    // if (format != null) {
    // return getRemainingTime();
    // } else {
    // return "";
    // }
    // }
}