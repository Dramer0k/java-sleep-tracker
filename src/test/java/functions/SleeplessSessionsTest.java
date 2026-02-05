package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;

class SleeplessSessionsTest {

    SleeplessSessions sleeplessSessions = new SleeplessSessions();
    List<SleepSession> sleepSessions = List.of(
            new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
            new SleepSession("02.10.25 07:00;02.10.25 19:00;NORMAL"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
            new SleepSession("09.10.25 19:00;09.10.25 23:00;BAD")
    );

    long sleeplessSessionsCount = 6L;

    @Test
    void testSleeplessSessionsCountTrue() {
        Assertions.assertEquals(sleeplessSessionsCount, sleeplessSessions.apply(sleepSessions).getResult());
    }

    @Test
    void testSleeplessSessionsCountFalse() {
        Assertions.assertNotEquals(sleeplessSessionsCount + 1, sleeplessSessions.apply(sleepSessions).getResult());
    }

}