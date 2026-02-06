package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.Duration;
import java.util.List;

class MinSessionDurationTest {

    MinSessionDuration minSessionDuration = new MinSessionDuration();
    List<SleepSession> sleepSessions = List.of(
            new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
            new SleepSession("02.10.25 23:00;03.10.25 01:00;NORMAL"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD")
    );

    @Test
    void testMinSessionDurationTrue() {
        Duration duration = Duration.ofMinutes(120);
        Assertions.assertEquals(duration.toMinutes(), minSessionDuration.apply(sleepSessions).getResult());
    }

    @Test
    void testMinSessionDurationFalse() {
        Duration duration = Duration.ofMinutes(130);
        Assertions.assertNotEquals(duration.toMinutes(), minSessionDuration.apply(sleepSessions).getResult());
    }

}