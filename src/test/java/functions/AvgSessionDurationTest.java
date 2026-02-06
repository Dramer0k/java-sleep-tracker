package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;


class AvgSessionDurationTest {

    AvgSessionDuration avgSessionDuration = new AvgSessionDuration();
    List<SleepSession> sleepSessions = List.of(
            new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
            new SleepSession("02.10.25 23:00;03.10.25 01:00;NORMAL"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD")
    );

    @Test
    @DisplayName("Средняя продолжительность сна: 200 (true)")
    void testAvgSessionDurationTrue() {
        Assertions.assertEquals(200, avgSessionDuration.apply(sleepSessions).getResult());
    }

    @Test
    @DisplayName("Средняя продолжительность сна: 300 (false)")
    void testAvgSessionDurationFalse() {
        Assertions.assertNotEquals(300, avgSessionDuration.apply(sleepSessions).getResult());
    }




}