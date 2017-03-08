package com.example.dhruv.uberyelp;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendRequest
{
	private static final int TIME_OUT = 5000;

	private static String YELP_AUTH = "authorization";
	private static String YELP_TOKEN = "Bearer hTKSqU9aFDmHRBECkUMsBCQArBsrJCEJAeBf5C8FVrwjztjTCA5X9wghnwOnHYuIqGW7vFsjLLKxs86aZWT-5Hjp3_C9n3OmfjkyhAyqEB6GYf2ktTsIPJL1hCq7WHYx";
	private static String UBER_AUTH = "authorization";
	private static String UBER_TOKEN = "Token dQq-Rxk00yokfz3uzJjLjFm2_748UkHTZeUBzv3x";
	private static String LYFT_AUTH = "Authorization";
	private static String LYFT_TOKEN = "Bearer gAAAAABYu2C3YCO0DGj5vVVnpalk3Vi7yPwV9j3eHjIcBvDKbpux2pcA7xr91Sri57Lcuwmyqd9bglPPgIzT1hHxoN-GsTQ0iZvRrSl_5x7gxZTbJRCt8JLMkFAhY3Gri4CisXFdzxdr1LBmKEbFAt_YybNA_GuOWr-jj8_KBa_IBxaVKLVpiDo=";

	public static String YELP_API = "YELP";
	public static String UBER_API = "UBER";
	public static String LYFT_API = "LYFT";

	public SendRequest()
	{
	}

	public static String sendPost(String url,String parameter,String API)
	{
		try
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");

			if(API.equals(YELP_API))
				con.setRequestProperty(YELP_AUTH, YELP_TOKEN);
			else if(API.equals(UBER_API))
				con.setRequestProperty(UBER_AUTH, UBER_TOKEN);
			else if(API.equals("LYFT_API"))
				con.setRequestProperty(LYFT_AUTH, LYFT_TOKEN);

			con.setRequestProperty("Accept-Language","en-US,en;q=0.5");

			con.setConnectTimeout(TIME_OUT);
			con.setReadTimeout(TIME_OUT);

			con.setDoOutput(true);

			DataOutputStream  wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(parameter);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			if(responseCode == 404)
				return null;

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//System.out.println(response.toString());		
			return response.toString();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}

	public static String sendGet(String url,String API)
	{
		try
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept-Language","en-US,en;q=0.5");

			if(API.equals(YELP_API))
				con.setRequestProperty(YELP_AUTH, YELP_TOKEN);
			else if(API.equals(UBER_API))
				con.setRequestProperty(UBER_AUTH, UBER_TOKEN);
			else if(API.equals(LYFT_API))
				con.setRequestProperty(LYFT_AUTH, LYFT_TOKEN);

			con.setConnectTimeout(TIME_OUT);
			con.setReadTimeout(TIME_OUT);

			int responseCode = con.getResponseCode();

			if(responseCode == 404)
				return null;

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//System.out.println(response.toString());		
			return response.toString();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			return null;
		}
	}
}