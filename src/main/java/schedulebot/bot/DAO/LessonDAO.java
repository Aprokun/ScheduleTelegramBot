package schedulebot.bot.DAO;

import schedulebot.parser.Lesson;
import schedulebot.parser.Page;
import schedulebot.parser.Parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class LessonDAO {
    HashMap<String, Lesson> lessons;

    public LessonDAO(Parser parser) {
        lessons = parser.parse(new Page("https://www.bstu.ru/static/themes/bstu/schedule/index.php?gid=10640&"));
    }

    public List<Lesson> getTodayLessonList() {
        List<Lesson> lessonsList = new ArrayList<>();

        int today = LocalDate.now().getDayOfWeek().getValue();

        for (int i = 1; i <= 6; i++) {
            final Lesson currLesson = lessons.get(String.valueOf(i) + today);

            if (currLesson != null) {
                lessonsList.add(currLesson);
            }
        }

        return lessonsList;
    }
}
