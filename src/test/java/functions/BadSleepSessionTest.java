package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;

class BadSleepSessionTest {

    BadSleepSession badSleepSession = new BadSleepSession();
    List<SleepSession> sleepSessions = List.of(
            new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
            new SleepSession("02.10.25 23:00;03.10.25 01:00;NORMAL"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD")
    );

    @Test
    void testBadSleepSessionCountTrue() {
        Assertions.assertEquals(2, badSleepSession.apply(sleepSessions).getResult());
    }

    @Test
    void testBadSleepSessionCountFalse() {
        Assertions.assertNotEquals(3, badSleepSession.apply(sleepSessions).getResult());
    }
}