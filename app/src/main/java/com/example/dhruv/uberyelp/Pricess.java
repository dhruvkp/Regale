package com.example.dhruv.uberyelp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Pricess
{
    private Prices[] prices;

    public Prices[] getPrices ()
    {
        return prices;
    }

    public void setPrices (Prices[] prices)
    {
        this.prices = prices;
    }

    @Override
    public String toString()
    {
        return "[prices = "+prices+"]";
    }
}
class Prices
{
    private String display_name;

    private String duration;

    private String distance;

    private String product_id;

    private String high_estimate;

    private String low_estimate;

    private String localized_display_name;

    private String currency_code;

    private String estimate;

    public String getDisplay_name ()
    {
        return display_name;
    }

    public void setDisplay_name (String display_name)
    {
        this.display_name = display_name;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getDistance ()
    {
        return distance;
    }

    public void setDistance (String distance)
    {
        this.distance = distance;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public void setProduct_id (String product_id)
    {
        this.product_id = product_id;
    }

    public String getHigh_estimate ()
    {
        return high_estimate;
    }

    public void setHigh_estimate (String high_estimate)
    {
        this.high_estimate = high_estimate;
    }

    public String getLow_estimate ()
    {
        return low_estimate;
    }

    public void setLow_estimate (String low_estimate)
    {
        this.low_estimate = low_estimate;
    }

    public String getLocalized_display_name ()
    {
        return localized_display_name;
    }

    public void setLocalized_display_name (String localized_display_name)
    {
        this.localized_display_name = localized_display_name;
    }

    public String getCurrency_code ()
    {
        return currency_code;
    }

    public void setCurrency_code (String currency_code)
    {
        this.currency_code = currency_code;
    }

    public String getEstimate ()
    {
        return estimate;
    }

    public void setEstimate (String estimate)
    {
        this.estimate = estimate;
    }

    @Override
    public String toString()
    {
        return " [display_name = "+display_name+", duration = "+duration+", distance = "+distance+", product_id = "+product_id+", high_estimate = "+high_estimate+", low_estimate = "+low_estimate+", localized_display_name = "+localized_display_name+", currency_code = "+currency_code+", estimate = "+estimate+"]";
    }
}