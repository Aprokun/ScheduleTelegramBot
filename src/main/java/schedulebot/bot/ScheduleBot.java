package schedulebot.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import schedulebot.bot.DAO.LessonDAO;
import schedulebot.parser.Lesson;
import schedulebot.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class ScheduleBot extends TelegramLongPollingBot {
    LessonDAO dao = new LessonDAO(new Parser());

    @Override
    public String getBotUsername() {
        return "BSTUScheduleBot";
    }

    @Override
    public String getBotToken() {
        return "1662346531:AAFTL9t2Zr_9QvBiXjiL2r4nJWUc4c_PGfI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().getText().equals("/today")) {
            List<String> todayLessons = getTodayLessons();

            for (String l : todayLessons) {
                try {
                    execute(new SendMessage(update.getMessage().getChatId().toString(), l));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<String> getTodayLessons() {
        List<Lesson> lessons = dao.getTodayLessonList();
        return toLessonStringList(lessons);
    }

    private List<String> toLessonStringList(List<Lesson> lessons) {
        List<String> res = new ArrayList<>();

        for (Lesson l : lessons) {
            res.add(l.toString());
        }

        return res;
    }
}
