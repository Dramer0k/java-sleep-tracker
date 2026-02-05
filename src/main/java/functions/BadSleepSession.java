package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class BadSleepSession implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {

        List<SleepSession> badSessions = sleepSessions.stream()
                .filter(quality -> quality.getQuality() == SleepQuality.BAD)
                .toList();
        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", badSessions.size());
    }
}
