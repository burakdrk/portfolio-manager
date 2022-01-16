package ePortfolio;

/**
 This class manages mutual fund investments by inheriting investments class.
 Author: Burak Ege Duruk
 Email: bduruk@uoguelph.ca
 Last Changed: Nov 30, 2021
 */
public class MutualFund extends Investment{
    public static final double MUTUAL_FEE = 45.0;
    private double bookValue = 0.0; //dollars

    /**Constructor to initialize. Throws exception if validity check fails*/
    public MutualFund(int quantity, double price, String symbol, String name) throws Exception    {
        super(quantity, price, symbol, name);
        buyBookValue(quantity, price);
    }

    /**Copy constructor for mutual*/
    public MutualFund(MutualFund mutualFund) {
        super(mutualFund);
        this.bookValue = mutualFund.bookValue;
    }

    @Override
    public MutualFund clone() {
        try {
            return new MutualFund(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Simply calculates the bookValue for buys*/
    private void buyBookValue(int quantity, double price)   {
        bookValue = bookValue + (quantity * price);
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
        return (((getQuantity()*getPrice()) - MUTUAL_FEE) - bookValue);
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
        MutualFund otherMutualFund = (MutualFund)other;
        return super.equals(other) && bookValue == otherMutualFund.bookValue;
    }
}