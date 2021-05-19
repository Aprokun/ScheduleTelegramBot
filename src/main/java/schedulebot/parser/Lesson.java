package schedulebot.parser;

import lombok.Data;

public class Lesson {

    String  teacherName,
            subjectName,
            place,
            day,
            status;
    Timer lessonDuration;

    public Lesson(
            String teacherName, String subjectName,
            String place, byte numOfDay, Timer lessonDuration,
            byte status
    ) {
        this.teacherName = teacherName;
        this.subjectName = subjectName;
        this.place = place;
        this.status = getStatus(status);
        this.lessonDuration = lessonDuration;
        day = getDay(numOfDay);
    }

    private String getDay(byte numOfDay) {
        switch (numOfDay) {
            case 1: return  "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            default: return "undefined";
        }
    }

    private String getStatus(byte status) {
        switch (status) {
            case 1: return "Numerator";
            case 0: return "Usual";
            case -1: return "Denominator";
            default: return "undefined";
        }
    }

    @Override
    public String toString() {
        return "Урок:" + '\n' +
                subjectName + '\n' +
                teacherName + '\n' +
                place + '\n' +
                lessonDuration.begin + " - " + lessonDuration.end;
    }

}
