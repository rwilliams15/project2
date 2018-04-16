package com.example;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class vehicle
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String make_model;
    private int year;
    private double retail_price;

    public vehicle()
    {
        this.id = id;
        this.make_model = make_model;
        this.year = year;
        this.retail_price = retail_price;
    }


    public vehicle(int id, String make_model, int year, double retail_price)
    {
        this.id = id;
        this.make_model = make_model;
        this.year = year;
        this.retail_price = retail_price;
    }

    public String toString()
    {
        return this.getId() + ", " + this.make_model + ", Year: " + this.year + " Price: " + this.retail_price;
    }

    public int getId()
    {
        return id;
    }

    public String getMake_model()
    {
        return make_model;
    }

    public void setMake_model(String make_model)
    {
        this.make_model = make_model;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public double getRetail_price()
    {
        return retail_price;
    }

    public void setRetail_price(double retail_price)
    {
        this.retail_price = retail_price;
    }
}
