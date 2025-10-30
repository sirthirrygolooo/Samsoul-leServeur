package org.hehe.sirthirrygolooo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;



class UserData{

    private String formatDateNow(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }


    private final ArrayList<Double> reactionTime;
    private final HashMap<Double,String> heartRateData;
    private final HashMap<Double,String> RightAccelerometerData;
    private final HashMap<Double,String> LeftAccelerometerData;
    private final HashMap<Double,String> bodyTemperatureData;

    public String toString(){
        return "Reaction Time: " + this.reactionTime +
                ",\nHeart Rate Data: " + this.heartRateData +
                ",\nRight Accelerometre: "+this.RightAccelerometerData +
                ",\nLeft Accelerometre: "+this.LeftAccelerometerData +
                ",\nBody Temperature: " + this.bodyTemperatureData + "\n";
    }

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

    public ArrayList<Double> getReactionTime() {
        return reactionTime;
    }

    public Double addHeartRateData(Double rt) {
        heartRateData.put(rt,formatDateNow());
        return rt;
    }


    public HashMap<Double, String> getHeartRateData() {
        return heartRateData;
    }

    public HashMap<Double, String> getRightAccelerometerData() {
        return RightAccelerometerData;
    }

    public void addRightAccelerometerData(Double rt) {
        RightAccelerometerData.put(rt,formatDateNow());
    }

    public HashMap<Double, String> getLeftAccelerometerData() {
        return LeftAccelerometerData;
    }

    public void addLeftAccelerometerData(Double rt) {
        LeftAccelerometerData.put(rt,formatDateNow());
    }

    public HashMap<Double, String> getBodyTemperatureData() {
        return bodyTemperatureData;
    }
    public void addBodyTemperatureData(Double rt) {
        bodyTemperatureData.put(rt,formatDateNow());
    }

}


