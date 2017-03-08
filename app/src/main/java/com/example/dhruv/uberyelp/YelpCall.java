package com.example.dhruv.uberyelp;
import com.google.gson.Gson;

public class YelpCall {

	public static void main(String[] str){
		String response = findTerm("food","dallas");

		Gson gson = new Gson();
		BusinessSearch businessSearch = gson.fromJson(response, BusinessSearch.class);

		for(Business bus : businessSearch.businesses){
			System.out.println(bus.name);

			System.out.println("cordinates:");

			System.out.println(bus.coordinates.latitude + " " + bus.coordinates.longitude);

			String responseEstimate = findEstimate(32.9858,bus.coordinates.latitude , -96.7501, bus.coordinates.longitude,SendRequest.UBER_API);
			Gson gsonEstimate = new Gson();
			Pricess price = gsonEstimate.fromJson(responseEstimate, Pricess.class);

			System.out.println("Uber Estiamtes: ");

			for(Prices p : price.getPrices()){
				System.out.println(p.getDisplay_name() + " " + p.getEstimate());

			}

			responseEstimate = findEstimate(32.9858,bus.coordinates.latitude , -96.7501, bus.coordinates.longitude,SendRequest.LYFT_API);

			Cost_estimatess costEstimate = gsonEstimate.fromJson(responseEstimate, Cost_estimatess.class);

			System.out.println("Lyft Estimate");

			for(Cost_estimates c : costEstimate.getCost_estimates()){
				System.out.println(c.getDisplay_name() + " " + c.getEstimated_cost_cents_min()/100 + "-" + c.getEstimated_cost_cents_max()/100);
			}
		}
	}

	public static String findTerm(String term,String location){

		String url = "https://api.yelp.com/v3/businesses/search?term=" + term + "&location=" + location;

		return SendRequest.sendGet(url,SendRequest.YELP_API);
	}

	public static String findEstimate(double startLatitude,double endLatitude,double startLongitude,double endLongtide,String API){

		String url = "";

		if(API.equals(SendRequest.UBER_API))
			url = "https://api.uber.com/v1.2/estimates/price?start_latitude="+startLatitude+"&start_longitude="+startLongitude+"&end_latitude="+endLatitude+"&end_longitude="+endLongtide;
		else if(API.equals(SendRequest.LYFT_API))
			url = "https://api.lyft.com/v1/cost?start_lat="+startLatitude+"&start_lng="+startLongitude+"&end_lat="+endLatitude+"&end_lng="+endLongtide;

		return SendRequest.sendGet(url,API);
	}
}