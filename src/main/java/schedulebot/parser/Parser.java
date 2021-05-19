package schedulebot.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Parser {
    private final Document schedule;

    public Parser(Document schedule) {
        this.schedule = schedule;
    }

    public HashMap<String, Lesson> parse() {
        Elements cells = getCells(schedule);
        return getLessonsHashMap(cells);
    }

    private HashMap<String, Lesson> getLessonsHashMap(Elements cells) {
        HashMap<String, Lesson> lessons = new HashMap<>();

        Timer currTimer = null;
        byte day = 0, lessonNum = 0;
        final int LESSON_DURATION = 5700000;

        for (Element subject : cells) {
            if (subject.hasClass("time")) {
                String begin = subject.select("p:nth-child(1)").text();
                currTimer = new Timer(begin, LESSON_DURATION);
                lessonNum++;
            }

            if (subject.hasClass("schedule_std")) {
                if (subject.children().hasClass("inner")) {

                    if (subject.select("table > tbody > tr:nth-child(1) > td.schedule_half").hasText()) {
                        String teacherName = subject.select("table > tbody > tr:nth-child(1) > td.schedule_half > div.sp_half").text();
                        String subjectName = subject.select("table > tbody > tr:nth-child(1) > td.schedule_half > div.subject_half").text();
                        String place = subject.select("table > tbody > tr:nth-child(1) > td.schedule_half > div.place_half").text();

                        if (!((teacherName + subjectName + place).isEmpty())) {
                            Lesson lesson = new Lesson(
                                    teacherName,
                                    subjectName,
                                    place,
                                    day, currTimer, (byte) 1);
                            lessons.put(String.valueOf(lessonNum) + day, lesson);
                        }
                    }

                    if (subject.select("table > tbody > tr:nth-child(2) > td.schedule_half").hasText()) {
                        String teacherName = subject.select("table > tbody > tr:nth-child(2) > td.schedule_half > div.sp_half").text();
                        String subjectName = subject.select("table > tbody > tr:nth-child(2) > td.schedule_half > div.subject_half").text();
                        String place = subject.select("table > tbody > tr:nth-child(2) > td.schedule_half > div.place_half").text();

                        if (!((teacherName + subjectName + place).isEmpty())) {
                            Lesson lesson = new Lesson(
                                    teacherName,
                                    subjectName,
                                    place,
                                    day, currTimer, (byte) -1);
                            lessons.put(String.valueOf(lessonNum) + day, lesson);
                        }
                    }

                } else {
                    String teacherName = subject.select("div.sp_std").text();
                    String subjectName = subject.select("div.subject_std").text();
                    String place = subject.select("div.place_std").text();

                    if (!((teacherName + subjectName + place).isEmpty())) {
                        Lesson lesson = new Lesson(
                                teacherName,
                                subjectName,
                                place,
                                day, currTimer, (byte) 0);
                        lessons.put(String.valueOf(lessonNum) + day, lesson);
                    }
                }


            }

            day++; day %= 7;
        }

        return lessons;
    }

    private Elements getCells(Document schedule_) {
        return schedule_.select(
                "body > table:nth-child(4) > tbody > tr > td"
        );
    }
}
