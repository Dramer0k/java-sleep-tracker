package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AvgSessionDuration implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {


        OptionalDouble duration = sleepSessions.stream()
                .mapToDouble(time -> Duration.between(time.getStart(), time.getFinish()).toMinutes())
                .average();

        if (duration.isPresent()) {
            return new SleepAnalysisResult("Средняя продолжительность сессии", (int) duration.getAsDouble());

        }
        return new SleepAnalysisResult("Список пуст. Средняя продолжительность сессии", 0);
    }
}
