package schedulebot.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Timer {
    SimpleDateFormat dateFormat;

    String begin;
    String end;

    private Timer() {
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    public Timer(String begin, long duration ) {
        this();

        this.begin = begin;
        long beginTime = getTimeFromString(begin);
        end = dateFormat.format(beginTime + duration);
    }

    private long getTimeFromString(String begin) {
        try {
            return dateFormat.parse(begin).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
