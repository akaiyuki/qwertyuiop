package com.av.dev.pyurluxuryandroid.Core;

/**
 * Created by CodeSyaona on 29/08/2017.
 */

public class PSingleton {

    public static int roomPosition;
    public static int paxPosition;

    public static int getPaxPosition() {
        return paxPosition;
    }

    public static void setPaxPosition(int paxPosition) {
        PSingleton.paxPosition = paxPosition;
    }

    public static int getRoomPosition() {
        return roomPosition;
    }

    public static void setRoomPosition(int roomPosition) {
        PSingleton.roomPosition = roomPosition;
    }


}
