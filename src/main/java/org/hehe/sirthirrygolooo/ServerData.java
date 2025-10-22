package org.hehe.sirthirrygolooo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


//Il s'agit des données globales au serveur, qui seront partagées entre les threads

public class ServerData {
    HashMap<String,UserData> data;
}



class UserData{
    private ArrayList<Double> reactionTime;
    private HashMap<Double,Date> accelerometerData;
    private HashMap<Double,Date> heartRateData;
    private HashMap<Double,Date> RightAccelerometerData;
    private HashMap<Double,Date> LeftAccelerometerData;
    private HashMap<Double,Date> bodyTemperatureData;
}