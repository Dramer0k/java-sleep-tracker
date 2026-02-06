package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;

public class SessionCounter implements DataAnalyzer {

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        Integer count = sleepSessions.size();
        return new SleepAnalysisResult("Количество сессий сна", count);
    }
}
