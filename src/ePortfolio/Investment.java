package ePortfolio;

/**
 This class is the super-abstract-class for MutualFunds and Stocks.
 Author: Burak Ege Duruk
 Email: bduruk@uoguelph.ca
 Last Changed: Nov 30, 2021
 */
public abstract class Investment {
    private String symbol;
    private String name;
    private int quantity; //shares
    private double price; //dollars

    /**Constructor to initialize. Throws exception if validity check fails.*/
    public Investment(int quantity, double price, String symbol, String name) throws Exception   {
        if (isValid(quantity, price, symbol, name))   {
            this.quantity = quantity;
            this.price = price;
            this.symbol = symbol;
            this.name = name;
        }else   {
            throw new Exception("Exception! The quantity and price has to be bigger than 0. Symbol and name must not be empty.");
        }
    }

    /**Copy constructor.*/
    public Investment(Investment other) {
        this.symbol = other.symbol;
        this.name = other.name;
        this.quantity = other.quantity;
        this.price = other.price;
    }

    /**Abstract method to tackle privacy leaks*/
    public abstract Investment clone();

    /**Mutator for the price. Throws exception if validity check fails.*/
    public void setPrice(double price) throws Exception {
        if (isValid(price))
            this.price = price;
        else    {
            throw new Exception("Exception! The price has to be bigger than 0.");
        }
    }

    /** Checks if the input is valid for quantity, price, name and symbol*/
    public boolean isValid(int quantity, double price, String name, String symbol)  {
        return quantity>0 && price>0.0 && !name.isEmpty() && !symbol.isEmpty();
    }

    /** Checks if the input is valid for quantity and price*/
    public boolean isValid(int quantity, double price)  {
        return quantity>0 && price>0.0;
    }

    /** Checks if the input is valid for price only*/
    public boolean isValid(double price)  {
        return price>0.0;
    }

    /*Accessor method for quantity**/
    public int getQuantity()    {
        return quantity;
    }

    /*Accessor method for price**/
    public double getPrice()    {
        return price;
    }

    /*Accessor method for symbol**/
    public String getSymbol()   {
        return symbol;
    }

    /*Accessor method for name**/
    public String getName() {
        return name;
    }

    /** Performs the quantity and buying operations for buys. Throws exception if validity check fails*/
    public void buyQuantityAndPrice(int quantity, double price) throws Exception{
        if (isValid(quantity, price)) {
            this.quantity = this.quantity + quantity;
            this.price = price;
        } else {
            throw new Exception("Exception! The quantity and price has to be bigger than 0.");
        }
    }

    /** Performs the quantity and buying operations for sales. Throws exception if validity check fails*/
    public void sellQuantityAndPrice(int quantity, double price) throws Exception{
        if (isValid(quantity, price)) {
            this.price = price;
            this.quantity = this.quantity - quantity;
        } else {
            throw new Exception("Exception! The quantity and price has to be bigger than 0.");
        }
    }

    /*Accessor method for bookValue**/
    public abstract double getBookValue();

    /*Accessor method for gain (Calculated in subclasses)**/
    public abstract double getGain();

    /**toString method for this designed for this class.*/
    public String toString()    {
        return "Symbol: " + symbol + ", Name: " + name + ", Quantity: " + Integer.toString(quantity) + ", Price: " + Double.toString(price);
    }

    /**Truly overriding equals*/
    public boolean equals(Object other) {
        if(other == null)
            return false;
        else if(getClass() != other.getClass())
            return false;
        else    {
            Investment otherInvestment = (Investment)other;
            return symbol.equals(otherInvestment.symbol) && name.equals(otherInvestment.name) && quantity == otherInvestment.quantity && price == otherInvestment.price;
        }
    }
}