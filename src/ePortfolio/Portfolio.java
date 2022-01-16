package ePortfolio;

import java.util.*;

/**
 This class manages portfolios for investments.
 Author: Burak Ege Duruk
 Email: bduruk@uoguelph.ca
 Last Changed: Nov 30, 2021
 */
public class Portfolio {
    private ArrayList<Investment> investment = new ArrayList<Investment>();
    private HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();

    /** Checks if symbol is in list and returns a boolean value.*/
    public boolean checkIfSymbolInList(String symbol)   {
        for(int i = 0; i < investment.size(); ++i)
            if(symbol.equalsIgnoreCase(investment.get(i).getSymbol()))
                return true;

        return false;
    }

    /** Checks if the investment is sellable and returns a boolean value*/
    public boolean isSellable(int index, int quantity)  {
        return investment.get(index).getQuantity() >= quantity;
    }

    /** Used to decide between a partial sell or complete sell, returns true if a partial sell, false if not*/
    private boolean sellOrDelete(int index, int quantity)   {
        return investment.get(index).getQuantity() - quantity > 0;
    }

    /** Gets the index of the symbol and returns it*/
    public int getIndexOfSymbol(String symbol) {
        for (int i = 0; i < investment.size(); ++i)
            if (symbol.equalsIgnoreCase(investment.get(i).getSymbol()))
                return i;

        return -1;
    }

    /** Used to buy for an existing investment. Returns the result in the investment using toString. Checks for exceptions*/
    public String buyExisting(String symbol, int quantity, double price)    {
        int index = getIndexOfSymbol(symbol);
        try {
            investment.get(index).buyQuantityAndPrice(quantity, price);
        }catch (Exception e)    {
            return e.getMessage();
        }
        return investment.get(index).toString();
    }

    /**Used to buy a new investment. Returns the new investment using toString. Checks for exceptions.*/
    public String buyNew(String type, String symbol, int quantity, double price, String name)    {
        if(type.equals("mutualfund"))   {
            try {
                investment.add(new MutualFund(quantity, price, symbol.toUpperCase(), name));
            }catch (Exception e)    {
                return e.getMessage();
            }
        }
        else if(type.equals("stock"))   {
            try {
                investment.add(new Stock(quantity, price, symbol.toUpperCase(), name));
            }catch (Exception e)    {
                return e.getMessage();
            }
        }
        modifyIndex();
        return investment.get(investment.size()-1).toString();
    }

    /**Used to sell the entire investment, simply returns the amount paid to the user and removes the object from the arraylist*/
    private String sellAll(int index, double price, int quantity)   {
        if(investment.get(index).getClass() == Stock.class)   {
            investment.remove(index);
            modifyIndex();
            return ("You sold all of it and you got paid " + (price*quantity- Stock.STOCK_COMISSION));
        }
        else if(investment.get(index).getClass() == MutualFund.class)  {
            investment.remove(index);
            modifyIndex();
            return ("You sold all of it and you got paid " + (price*quantity- MutualFund.MUTUAL_FEE));
        }
        return null;
    }

    /**Used to sell a portion of the investment, simply returns the amount paid to the user and performs the operation. Also returns the result in the investment using toString. Checks for exceptions.*/
    private String sellPartial(int index, double price, int quantity)   {
        String anan = null;
        if(investment.get(index).getClass() == Stock.class)   {
            anan = ("You got paid " + (price*quantity- Stock.STOCK_COMISSION));
        }
        else if(investment.get(index).getClass() == MutualFund.class)  {
            anan = ("You got paid " + (price*quantity- MutualFund.MUTUAL_FEE));
        }
        try {
            investment.get(index).sellQuantityAndPrice(quantity, price);
        }catch (Exception e)    {
            return e.getMessage();
        }
        return (anan + "\n" + investment.get(index).toString());
    }

    /**Checks if it is a complete sell or a partial sell, calls the method according to that and returns the result*/
    public String sell(int index, double price, int quantity)   {
        if(sellOrDelete(index, quantity))   {
            return sellPartial(index, price, quantity);
        }
        else if(!sellOrDelete(index, quantity)) {
            return sellAll(index, price, quantity);
        }
        return null;
    }

    /**Updates the price with the new price and returns the toString method of object. Checks for exceptions.*/
    public String update(String symbol, double price)    {
        try {
            investment.get(getIndexOfSymbol(symbol)).setPrice(price);
        }catch (Exception e)    {
            return e.getMessage();
        }
        return investment.get(getIndexOfSymbol(symbol)).toString();
    }

    /**Simply calculates the total gain from calling every investment's getGain method and adding them up. Stores the gain for each investment in a list and then returns it for the entire portfolio*/
    public ArrayList<String> displayGain() {
        ArrayList<String> list = new ArrayList<String>(1);
        double gain = 0.0;

        //Checks for every index in list
        for (int j = 0; j < investment.size(); ++j)    {
            list.add("Total gain for " + investment.get(j).getSymbol() + " : " + investment.get(j).getGain());
            gain = gain + investment.get(j).getGain();
        }

        list.add(Double.toString(gain));
        return list;
    }

    /**Uses copy constructor and clone method to return the investments without privacy leaks.*/
    public ArrayList<Investment> getInvestment()    {
        ArrayList<Investment> temp = new ArrayList<Investment>();
        for (int i = 0; i < investment.size(); ++i) {
            if(investment.get(i).getClass() == Stock.class) {
                Stock tempi = (Stock) investment.get(i).clone();
                temp.add(tempi);
            }else if(investment.get(i).getClass() == MutualFund.class)  {
                MutualFund tempi = (MutualFund) investment.get(i).clone();
                temp.add(tempi);
            }
        }
        return temp;
    }

    /**Utilizes the hashMap to find the keyword efficiently. Returns the intersection set of indices with a TreeSet.*/
    private TreeSet<Integer> searchName(String name)    {
        String words[];
        words = name.split("[ ]+");

        TreeSet<Integer> toReturn = new TreeSet<>();
        for (int i = 0; i < words.length; ++i)  {
            if(index.containsKey(words[i])) {
                ArrayList<Integer> temp = index.get(words[i].toLowerCase());
                toReturn.retainAll(temp);
                if(i == 0)  {
                    for (int j = 0; j < temp.size(); j++)   {
                        toReturn.add(temp.get(j));
                    }
                }
            }
        }
        return toReturn;
    }

    /**Used to modify the names and their indices in the index HashMap*/
    private void modifyIndex()  {
        index.clear();
        String words[];

        for (int i = 0; i < investment.size(); ++i) {
            words = investment.get(i).getName().toLowerCase().split("[ ]+");    //Split the name

            for (int j = 0; j < words.length; ++j)  {
                if (index.containsKey(words[j]))    {
                    index.get(words[j]).add(i);
                }else   {
                    ArrayList<Integer> value = new ArrayList<Integer>();
                    value.add(i);
                    index.put(words[j], value);
                }
            }
        }
    }

    /**Checks for each case of low and high prices and adds the indices according to the conditions. Returns the list.*/
    private ArrayList<Integer> searchPrice(String lowPrice, String highPrice)    {
        ArrayList<Integer> temp = new ArrayList<>();
        if(lowPrice.isEmpty())    {
            if(highPrice.isEmpty()) {
                for (int i = 0; i < investment.size(); ++i) {
                    temp.add(i);
                }
            }
            else    {
                for (int i = 0; i < investment.size(); ++i) {
                    if(investment.get(i).getPrice() <= Double.parseDouble(highPrice))   {
                        temp.add(i);
                    }
                }
            }
        }
        else    {
            if(highPrice.isEmpty()) {
                for (int i = 0; i < investment.size(); ++i) {
                    if(investment.get(i).getPrice() >= Double.parseDouble(lowPrice))   {
                        temp.add(i);
                    }
                }
            }
            else    {
                for (int i = 0; i < investment.size(); ++i) {
                    if(investment.get(i).getPrice() >= Double.parseDouble(lowPrice) && investment.get(i).getPrice() <= Double.parseDouble(highPrice))   {
                        temp.add(i);
                    }
                }
            }
        }

        return temp;
    }

    /**Simply performs the search functionality for all investments and returns the TreeSet.*/
    public TreeSet<Integer> search(String symbol, String name, String lowPrice, String highPrice) {
        TreeSet<Integer> temp = searchName(name);
        if(!symbol.equals(""))  {
            int index = getIndexOfSymbol(symbol);
            ArrayList<Integer> bok = new ArrayList<>();
            bok.add(index);
            if(name.equals("")) temp.add(index);
            else    temp.retainAll(bok);
        }
        if(symbol.equals("") && name.equals("") && (!lowPrice.equals("") || !highPrice.equals("")))    {
            temp.addAll(searchPrice(lowPrice,highPrice));
        }
        else temp.retainAll(searchPrice(lowPrice, highPrice));
        return temp;
    }
}
