package schedulebot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import schedulebot.config.SpringBeansConfig;

import javax.inject.Inject;

@Component
public class Main {
    public static void main(String[] args) throws TelegramApiException {
        SpringBeansConfig springBeansConfig = new SpringBeansConfig();

        //make bot api
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        ScheduleBot scheduleBot = springBeansConfig.scheduleBot();

        //register bot
        botsApi.registerBot(scheduleBot);
    }
}
