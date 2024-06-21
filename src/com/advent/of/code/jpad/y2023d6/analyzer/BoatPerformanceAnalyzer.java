package com.advent.of.code.jpad.y2023d6.analyzer;

import com.advent.of.code.jpad.y2023d6.actors.Boat;
import com.advent.of.code.jpad.y2023d6.actors.RaceRecord;

public class BoatPerformanceAnalyzer {

    public static Long computeRecordBreakingChargeDurations(RaceRecord raceRecord) {
        long durationCursor = raceRecord.duration() / 2;
        long boatDistance = Integer.MAX_VALUE;

        while (durationCursor > 0) {
            boatDistance = Boat.chargedWith(durationCursor)
                    .moveForDuration(raceRecord.duration() - durationCursor)
                    .getPosition();
            if (boatDistance <= raceRecord.maxDistance()) {
                break;
            }
            durationCursor--;
        }
        long recordBreakingDurations = (raceRecord.duration() / 2 - durationCursor) * 2;
        if (raceRecord.duration() % 2 == 0) {
            recordBreakingDurations--;
        }
        System.out.println(recordBreakingDurations);
        return recordBreakingDurations;
    }
}
