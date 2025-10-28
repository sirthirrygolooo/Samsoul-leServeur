package org.hehe.sirthirrygolooo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class UserData{
    private final ArrayList<Double> reactionTime;
    private final HashMap<Double,Long> heartRateData;
    private final HashMap<Double,Long> RightAccelerometerData;
    private final HashMap<Double,Long> LeftAccelerometerData;
    private final HashMap<Double,Long> bodyTemperatureData;

    UserData() {
        this.heartRateData = new HashMap<>();
        this.RightAccelerometerData = new HashMap<>();
        this.LeftAccelerometerData = new HashMap<>();
        this.bodyTemperatureData = new HashMap<>();
        this.reactionTime = new ArrayList<>();
    }

    public ArrayList<Double> addReactionTime(Double rt) {
        reactionTime.add(rt);
        return reactionTime;
    }

    public HashMap<Double, Long> addHeartRateData(Double rt) {
        heartRateData.put(rt,new Date().getTime());
        return heartRateData;
    }


    public HashMap<Double, Long> getHeartRateData() {
        return heartRateData;
    }

    public HashMap<Double, Long> getRightAccelerometerData() {
        return RightAccelerometerData;
    }

    public HashMap<Double, Long> getLeftAccelerometerData() {
        return LeftAccelerometerData;
    }
    public HashMap<Double, Long> getBodyTemperatureData() {
        return bodyTemperatureData;
    }

}


