package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepSession {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    LocalDateTime start;
    LocalDateTime finish;
    SleepQuality quality;

    public SleepSession(String str) {
        String[] array = str.trim().split(";");
        this.start = LocalDateTime.parse(array[0], formatter);
        this.finish = LocalDateTime.parse(array[1], formatter);
        this.quality = SleepQuality.valueOf(array[2].trim().toUpperCase());
    }

    public LocalDateTime getStart() {
        return start;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    @Override
    public String toString() {
        return "SleepSession{" +
                "start=" + start.format(formatter) +
                ", finish=" + finish.format(formatter) +
                ", quality=" + quality +
                '}';
    }

}
