package schedulebot.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        //make bot api
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        //make bot
        ScheduleBot scheduleBot = new ScheduleBot();

        //register bot
        botsApi.registerBot(scheduleBot);
    }
}
