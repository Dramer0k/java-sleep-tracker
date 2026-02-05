package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class MinSessionDuration implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {
        Duration duration = sleepSessions.stream()
                .map(time -> {
                    return Duration.between(time.getStart(), time.getFinish());
                })
                .min(Comparator.naturalOrder())
                .orElse(Duration.ZERO);

        return new SleepAnalysisResult("Минимальная продолжительность сессии", duration.toMinutes());
    }
}
