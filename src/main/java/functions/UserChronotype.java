package functions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserChronotype implements DataAnalyzer {
    Chronotype keyWithMaxValue;
    private static final LocalTime LATE_NIGHT_23 = LocalTime.of(23, 0);
    private static final LocalTime EARLY_MORNING_9 = LocalTime.of(9, 0);
    private static final LocalTime EARLIEST_MORNING_7 = LocalTime.of(7, 0);
    private static final LocalTime MID_EVENING_22 = LocalTime.of(22, 0);

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {

        //делаем мап с ключами хронотипа и значениями кол-ва ночей
        Map<Chronotype, Long> map = sleepSessions.stream()
                .collect(Collectors.groupingBy(this::groupByChronotype, Collectors.counting()
                ));

        Optional<Map.Entry<Chronotype, Long>> maxEntry = map.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (maxEntry.isPresent()) {
            if (Objects.equals(map.get(Chronotype.OWL), map.get(Chronotype.LARK))){
                keyWithMaxValue = Chronotype.PIGEON;
            } else {
                keyWithMaxValue = maxEntry.get().getKey();
            }
        } else {
           return new SleepAnalysisResult("Хронотип", "не определен");
        }

        return new SleepAnalysisResult("Хронотип", keyWithMaxValue);
    }

    public Chronotype groupByChronotype(SleepSession sleepSession) {
        if (sleepSession.getStart().toLocalTime().isAfter(LATE_NIGHT_23) &&
                sleepSession.getFinish().toLocalTime().isAfter(EARLY_MORNING_9)) {
            return Chronotype.OWL;
        } else if (sleepSession.getStart().toLocalTime().isBefore(MID_EVENING_22) &&
                sleepSession.getFinish().toLocalTime().isBefore(EARLIEST_MORNING_7)) {
            return Chronotype.LARK;
        }
        return Chronotype.PIGEON;
    }
}
