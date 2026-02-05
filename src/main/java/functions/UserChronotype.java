package functions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.lang.constant.Constable;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserChronotype implements Function<List<SleepSession>, SleepAnalysisResult> {
    Chronotype keyWithMaxValue;
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {

        //делаем мап с ключами хронотипа и значениями кол-ва ночей
        Map<? extends Constable, Long> map = sleepSessions.stream()
                .collect(Collectors.groupingBy(time -> {
                    if (time.getStart().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                            time.getFinish().toLocalTime().isAfter(LocalTime.of(9, 0))) {
                        return Chronotype.OWL;
                    } else if (time.getStart().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                            time.getFinish().toLocalTime().isBefore(LocalTime.of(7, 0))) {
                        return Chronotype.LARK;
                    }
                    return Chronotype.PIGEON;
                }, Collectors.counting()
                ));

        Optional<? extends Map.Entry<? extends Constable, Long>> MaxEntry = map.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (MaxEntry.isPresent()) {
            keyWithMaxValue = (Chronotype) MaxEntry.get().getKey();
        } else {
           return new SleepAnalysisResult("Хронотип", "не определен");
        }

        return new SleepAnalysisResult("Хронотип", keyWithMaxValue);
    }
}
