package com.android.chintankhatri.expensetrack.dao;

import java.util.Date;

/**
 * Created by sruthimaddineni on 11/12/16.
 */

public class Message {
    private double amount;
    private String location;
    private Date timestamp;
    private String coordinates;
    private String bank;

    public String getLocation() {
        return location;
    }
    public double getAmount()   {
        return amount;
    }
    public Date getTimestamp()  {
        return timestamp;
    }

    public double[] getCoordinates()    {
        String[] tokens = coordinates.split(",");
        double[] coord =  new double[2];
        coord[0] = Double.parseDouble(tokens[0]);
        coord[1] = Double.parseDouble(tokens[1]);
        return coord;
    }

    public String getCoordinatesStr()   {
        return this.coordinates;
    }

    public String getBank() {
        return bank;
    }

    public void setAmount(final double amount)  {
        this.amount = amount;
    }

    public void setLocation(final String location)  {
        this.location = location;
    }

    public void setTimestamp(final Date date)   {
        this.timestamp = date;
    }

    public void setCoordinates(final double latitude, final double longitude)    {
        this.coordinates = String.valueOf(latitude).concat(",").concat(String.valueOf(longitude));
    }

    public void setBank(final String bank)  {
        this.bank = bank;
    }
}
