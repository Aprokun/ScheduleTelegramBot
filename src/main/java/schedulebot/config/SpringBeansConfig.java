package schedulebot.config;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import schedulebot.bot.DAO.LessonDAO;
import schedulebot.bot.ScheduleBot;
import schedulebot.parser.Page;
import schedulebot.parser.Parser;

import java.io.IOException;

@Configuration
@ComponentScan("schedulebot")
public class SpringBeansConfig {
    @Bean
    public Parser parser() {
        return new Parser(document());
    }

    @Bean
    public LessonDAO lessonDAO() {
        Parser parser = parser();
        return new LessonDAO(parser());
    }

    @Bean
    public ScheduleBot scheduleBot() {
        return new ScheduleBot(lessonDAO());
    }

    @Bean
    public Page page() {
        return new Page("https://www.bstu.ru/static/themes/bstu/schedule/index.php?gid=10640&");
    }

    @Bean
    public Document document() {
        try {
            return Jsoup.connect(page().getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
            return new Document("");
        }
    }

}
