package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalTime;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class SleeplessSessions implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {

        LocalTime sleepNightStart = LocalTime.of(0, 0); //старт периода сонной ночи
        LocalTime sleepNightFinish = LocalTime.of(6, 0); //конец периода сонной ночи
        long sleeplessNightCount; //Количество бессонных ночей за период


        if (sleepSessions == null || sleepSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        //фильтруем весь период сессий по дням
        List<SleepSession> sortedList = sleepSessions.stream()
                 .sorted(Comparator.comparing(SleepSession::getStart))
                .toList();

        //получаем сколько дней длился период замеров
        int period = Period.between(
                sortedList.getFirst().getStart().toLocalDate(),
                sortedList.getLast().getFinish().toLocalDate()
                ).getDays();

        long sleepNightCounter = sleepSessions.stream()
                //фильтруем дни, когда сон приходил на период с 00:00 до 06:00 (сонные ночи)
                .filter(nightSleep -> {
                    if (nightSleep.getStart().toLocalDate().isBefore(nightSleep.getFinish().toLocalDate())) {
                        return true;
                    } else if (nightSleep.getStart().toLocalTime().isAfter(sleepNightStart) &&
                            nightSleep.getStart().toLocalTime().isBefore(sleepNightFinish)) {
                        return true;
                    } else if (nightSleep.getFinish().toLocalTime().isAfter(sleepNightStart) &&
                            nightSleep.getFinish().toLocalTime().isBefore(sleepNightFinish)) {
                        return true;
                    }
                    return false;
                })
                .count();

        sleeplessNightCount = period - sleepNightCounter;

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessNightCount);
    }
}
