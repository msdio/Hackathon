package com.example.hackathon;

public class Shop_list {

    public int index;
    public String name;
    public String addr;
    public int zipcode;
    public double longitude;
    public double latitude;

    //set function
    public void setIndex(int index) {this.index = index;}
    public void setName(String name) { this.name = name; }
    public void setAddr(String addr) { this.addr = addr; }
    public void setZipcode(int zipcode) { this.zipcode = zipcode; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }


    //get function
    public int getIndex() { return this.index; }
    public String getName() { return this.name; }
    public String getAddr() { return this.addr; }
    public int getZipcode() { return this.zipcode; }
    public double getLongitude() { return this.longitude; }
    public double getLatitude() { return this.latitude; }

}
