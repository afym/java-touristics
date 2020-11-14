package com.personal.request.dto;

public class SearchRequest {
    private String nationality;
    private String language;
    private String token;
    private String destination;
    private String checkin;
    private String checkout;
    private String nights;
    private String rooms;
    private RoomRequest[] listrooms;
    private boolean onlyavailable;

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getNights() {
        return nights;
    }

    public void setNights(String nights) {
        this.nights = nights;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public RoomRequest[] getListrooms() {
        return listrooms;
    }

    public void setListrooms(RoomRequest[] listrooms) {
        this.listrooms = listrooms;
    }

    public boolean isOnlyavailable() {
        return onlyavailable;
    }

    public void setOnlyavailable(boolean onlyavailable) {
        this.onlyavailable = onlyavailable;
    }
}
