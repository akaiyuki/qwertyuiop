package com.av.dev.pyurluxuryandroid.Core;

/**
 * Created by CodeSyaona on 29/08/2017.
 */

public class PSingleton {

    public static int roomPosition;
    public static int paxPosition;

    //hotel booking
    public static String city;
    public static String hotelName;
    public static String checkIn;
    public static String checkOut;
    public static String roomType;
    public static String numRoom;
    public static String numPax;
    public static String notes;
    public static String time;
    public static String date;

    //cinema
    public static String cinema;
    public static String movie;

    public static String getMovie() {
        return movie;
    }

    public static void setMovie(String movie) {
        PSingleton.movie = movie;
    }

    public static String getCinema() {

        return cinema;
    }

    public static void setCinema(String cinema) {
        PSingleton.cinema = cinema;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        PSingleton.date = date;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        PSingleton.time = time;
    }

    public static String getNotes() {
        return notes;
    }

    public static void setNotes(String notes) {
        PSingleton.notes = notes;
    }

    public static String getNumPax() {

        return numPax;
    }

    public static void setNumPax(String numPax) {
        PSingleton.numPax = numPax;
    }

    public static String getNumRoom() {

        return numRoom;
    }

    public static void setNumRoom(String numRoom) {
        PSingleton.numRoom = numRoom;
    }

    public static String getRoomType() {

        return roomType;
    }

    public static void setRoomType(String roomType) {
        PSingleton.roomType = roomType;
    }

    public static String getCheckOut() {

        return checkOut;
    }

    public static void setCheckOut(String checkOut) {
        PSingleton.checkOut = checkOut;
    }

    public static String getCheckIn() {

        return checkIn;
    }

    public static void setCheckIn(String checkIn) {
        PSingleton.checkIn = checkIn;
    }

    public static String getHotelName() {

        return hotelName;
    }

    public static void setHotelName(String hotelName) {
        PSingleton.hotelName = hotelName;
    }

    public static String getCity() {

        return city;
    }

    public static void setCity(String city) {
        PSingleton.city = city;
    }

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
