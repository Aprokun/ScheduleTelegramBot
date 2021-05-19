package schedulebot.bot.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import schedulebot.parser.Lesson;
import schedulebot.parser.Page;
import schedulebot.parser.Parser;

import java.time.LocalDate;
import java.util.*;

@Component
public class LessonDAO {
    HashMap<String, Lesson> lessons;
    final Parser parser;

    @Autowired
    public LessonDAO(Parser parser) {
        this.parser = parser;
        lessons = parser.parse();
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
