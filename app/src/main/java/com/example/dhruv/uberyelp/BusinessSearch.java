package com.example.dhruv.uberyelp;

public class BusinessSearch{
	public Business[] businesses;
}

class Business {
	public int review_count;
	public double rating;
	public String name; 
	public String is_closed;
	public String phone;
	public double distance;
	
	public Location location;
	public String image_url;
	
	public Coordinates coordinates;
}

class Location{
	public String[] display_address;
}

class Coordinates{
	public double latitude;
	public double longitude;
}