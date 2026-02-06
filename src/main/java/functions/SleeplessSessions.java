package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

public class SleeplessSessions implements DataAnalyzer {
    private static final LocalTime SLEEP_NIGHT_START = LocalTime.of(0, 0); //старт периода сонной ночи
    private static final LocalTime SLEEP_NIGHT_FINISH = LocalTime.of(6, 0); //конец периода сонной ночи

    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepSessions) {

        if (sleepSessions == null || sleepSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        //фильтруем весь период сессий по дням
        List<SleepSession> sortedList = sleepSessions.stream()
                 .sorted(Comparator.comparing(SleepSession::getStart))
                .toList();

        final LocalDate startDate = sortedList.getFirst().getStart().toLocalDate();
        final LocalDate endDate = sortedList.getLast().getFinish().toLocalDate();

        //получаем сколько дней длился период замеров
        long period = ChronoUnit.DAYS.between(startDate, endDate);

        long sleepNightCounter = sortedList.stream()
                //фильтруем дни, когда сон приходил на период с 00:00 до 06:00 (сонные ночи)
                .filter(this::filterSleepSession)
                .count();

        //Количество бессонных ночей за период
        long sleeplessNightCount = period - sleepNightCounter;

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessNightCount);
    }

    public boolean filterSleepSession(SleepSession sleepSession) {
        if (sleepSession.getStart().toLocalDate().isBefore(sleepSession.getFinish().toLocalDate())) {
            return true;
        } else if (sleepSession.getStart().toLocalTime().isAfter(SLEEP_NIGHT_START) &&
                sleepSession.getStart().toLocalTime().isBefore(SLEEP_NIGHT_FINISH)) {
            return true;
        } else return sleepSession.getFinish().toLocalTime().isAfter(SLEEP_NIGHT_START) &&
                sleepSession.getFinish().toLocalTime().isBefore(SLEEP_NIGHT_FINISH);
    }
}
