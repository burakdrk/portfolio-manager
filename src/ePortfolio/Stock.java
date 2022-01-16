package ePortfolio;

/**
 This class manages stock investments by inheriting investments class.
 Author: Burak Ege Duruk
 Email: bduruk@uoguelph.ca
 Last Changed: Nov 30, 2021
 */
public class Stock extends Investment{
    public static final double STOCK_COMISSION = 9.99;
    private double bookValue = 0.0; //dollars

    /**Constructor to initialize. Throws exception if validity check fails*/
    public Stock(int quantity, double price, String symbol, String name) throws Exception    {
        super(quantity, price, symbol, name);
        buyBookValue(quantity, price);
    }

    /**Copy constructor for stock*/
    public Stock(Stock stock) {
        super(stock);
        this.bookValue = stock.bookValue;
    }

    @Override
    public Stock clone()    {
        try {
            return new Stock(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Simply calculates the bookValue for buys*/
    private void buyBookValue(int quantity, double price)   {
        bookValue = bookValue + (quantity * price + STOCK_COMISSION);
    }

    /** Simply calculates the bookValue for sells*/
    public void sellBookValue(double quantity, double thisQuantity)   {
        bookValue = bookValue * (quantity/(thisQuantity+quantity));
    }

    /*Accessor method for bookValue**/
    public double getBookValue()    {
        return bookValue;
    }

    /** Simply returns the total gain*/
    public double getGain() {
        return (((getQuantity()*getPrice()) - STOCK_COMISSION) - bookValue);
    }

    /** Performs the quantity and buying operations for buys. Throws exception if validity check fails*/
    public void buyQuantityAndPrice(int quantity, double price) throws Exception{
        super.buyQuantityAndPrice(quantity, price);
        buyBookValue(quantity, price);
    }

    /** Performs the quantity and buying operations for sales. Throws exception if validity check fails*/
    public void sellQuantityAndPrice(int quantity, double price) throws Exception{
        super.sellQuantityAndPrice(quantity, price);
        sellBookValue(getQuantity(), quantity);
    }

    /**toString for this specific class. Inherits Investment.*/
    public String toString()    {
        return (super.toString() + ", bookValue: " + Double.toString(bookValue));
    }

    /**Truly overriding equals*/
    public boolean equals(Object other) {
        Stock otherStock = (Stock)other;
        return super.equals(other) && bookValue == otherStock.bookValue;
    }
}