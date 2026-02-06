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

    @Test
    void testSleeplessSessionsCountEmpty() {
        List<SleepSession> sleepSessions = List.of();
        Assertions.assertEquals(0, sleeplessSessions.apply(sleepSessions).getResult());
    }

    @Test
    void testSleeplessSessionsCountAnotherMonth() {
        List<SleepSession> sleepSessionsNew = List.of(
                new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
                new SleepSession("02.10.25 07:00;02.10.25 19:00;NORMAL"),
                new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
                new SleepSession("01.11.25 19:00;01.11.25 23:00;BAD")
        );
        Assertions.assertEquals( 29L, sleeplessSessions.apply(sleepSessionsNew).getResult());
    }

    @Test
    void testSleeplessSessionsFirstSessionAfter00() {
        List<SleepSession> sleepSessionsNew = List.of(
                new SleepSession("01.10.25 00:00;01.10.25 10:00;GOOD"),
                new SleepSession("02.10.25 07:00;02.10.25 19:00;NORMAL"),
                new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
                new SleepSession("01.11.25 19:00;01.11.25 23:00;BAD")
        );
        Assertions.assertEquals( 30L, sleeplessSessions.apply(sleepSessionsNew).getResult());
    }

}