package functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;

public interface DataAnalyzer {

    SleepAnalysisResult apply(List<SleepSession> data);

}
