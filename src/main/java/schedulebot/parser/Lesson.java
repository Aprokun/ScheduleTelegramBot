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
        return switch (numOfDay) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "undefined";
        };
    }

    private String getStatus(byte status) {
        return switch (status) {
            case 1 -> "Numerator";
            case 0 -> "Usual";
            case -1 -> "Denominator";
            default -> "undefined";
        };
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
