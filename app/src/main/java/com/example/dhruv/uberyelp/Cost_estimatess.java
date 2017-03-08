package com.example.dhruv.uberyelp;

public class Cost_estimatess
{
    public Cost_estimates[] cost_estimates;

    public Cost_estimates[] getCost_estimates()
    {
        return cost_estimates;
    }

    public void setCost_estimates (Cost_estimates[] cost_estimates)
    {
        this.cost_estimates = cost_estimates;
    }

    @Override
    public String toString()
    {
        return "[cost_estimates = "+cost_estimates+"]";
    }
}
class Cost_estimates
{
    private String primetime_confirmation_token;

    private String display_name;

    private String estimated_duration_seconds;

    private String estimated_distance_miles;

    private String ride_type;

    private Double estimated_cost_cents_min;

    private Double estimated_cost_cents_max;

    private String is_valid_estimate;

    private String primetime_percentage;

    private String currency;

    public String getPrimetime_confirmation_token ()
    {
        return primetime_confirmation_token;
    }

    public void setPrimetime_confirmation_token (String primetime_confirmation_token)
    {
        this.primetime_confirmation_token = primetime_confirmation_token;
    }

    public String getDisplay_name ()
    {
        return display_name;
    }

    public void setDisplay_name (String display_name)
    {
        this.display_name = display_name;
    }

    public String getEstimated_duration_seconds ()
    {
        return estimated_duration_seconds;
    }

    public void setEstimated_duration_seconds (String estimated_duration_seconds)
    {
        this.estimated_duration_seconds = estimated_duration_seconds;
    }

    public String getEstimated_distance_miles ()
    {
        return estimated_distance_miles;
    }

    public void setEstimated_distance_miles (String estimated_distance_miles)
    {
        this.estimated_distance_miles = estimated_distance_miles;
    }

    public String getRide_type ()
    {
        return ride_type;
    }

    public void setRide_type (String ride_type)
    {
        this.ride_type = ride_type;
    }

    public double getEstimated_cost_cents_min ()
    {
        return estimated_cost_cents_min;
    }

    public void setEstimated_cost_cents_min (double estimated_cost_cents_min)
    {
        this.estimated_cost_cents_min = estimated_cost_cents_min;
    }

    public double getEstimated_cost_cents_max ()
    {
        return estimated_cost_cents_max;
    }

    public void setEstimated_cost_cents_max (double estimated_cost_cents_max)
    {
        this.estimated_cost_cents_max = estimated_cost_cents_max;
    }

    public String getIs_valid_estimate ()
    {
        return is_valid_estimate;
    }

    public void setIs_valid_estimate (String is_valid_estimate)
    {
        this.is_valid_estimate = is_valid_estimate;
    }

    public String getPrimetime_percentage ()
    {
        return primetime_percentage;
    }

    public void setPrimetime_percentage (String primetime_percentage)
    {
        this.primetime_percentage = primetime_percentage;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "primetime_confirmation_token = "+primetime_confirmation_token+", display_name = "+display_name+", estimated_duration_seconds = "+estimated_duration_seconds+", estimated_distance_miles = "+estimated_distance_miles+", ride_type = "+ride_type+", estimated_cost_cents_min = "+estimated_cost_cents_min+", estimated_cost_cents_max = "+estimated_cost_cents_max+", is_valid_estimate = "+is_valid_estimate+", primetime_percentage = "+primetime_percentage+", currency = "+currency+"]";
    }
}