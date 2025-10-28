package org.hehe.sirthirrygolooo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


//Il s'agit des données globales au serveur, qui seront partagées entre les threads

public class ServerData {

    private final HashMap<String,UserData> data;

    ServerData(){
        this.data = new HashMap<>();
    }

    public UserData getUserData(String id) {
        return this.data.get(id);
    }

    public String addUser(String id) {
        this.data.put(id, new UserData());
        return id;
    }


}
