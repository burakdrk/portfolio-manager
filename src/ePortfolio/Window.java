package ePortfolio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.TreeSet;

public class Window extends JFrame {
    private Portfolio portfolio = new Portfolio();
    public static final int WIDTH = 700;
    public static final int HEIGHT = 530;
    private JPanel initialInterface;
    private JPanel Interface;
    private JPanel buyTwoPanel;
    private JPanel buyOpt;
    private JPanel buyBut;
    private JPanel buyText;
    private JTextArea bottomText;
    private JTextField symbolText;
    private JTextField nameText;
    private JTextField quantText;
    private JTextField priceText;
    private JTextField gainText;
    private JTextField lowText;
    private JTextField keywordText;
    private JTextField highText;
    private JComboBox<String> tipList;
    private JLabel type;
    private JLabel type2;
    private JLabel type3;
    private JLabel type4;
    private JLabel type5;
    private JLabel type1;
    private JLabel type6;
    private JLabel type7;
    private JLabel type8;
    private JButton buyButt;
    private JButton sellButt;
    private JLabel ing;
    private JButton prevButt;
    private JButton nextButt;
    private JButton saveButt;
    private JButton reset;
    private JButton searchButt;
    private JLabel messText;

    /**JFrame's constructor to initialize the GUI*/
    public Window() {
        super("ePortfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        /*This part is for the menu and its items */
        JMenu commands = new JMenu("Commands");
        JMenuBar bar = new JMenuBar();
        bar.add(commands);
        setJMenuBar(bar);
        JMenuItem buyItem = new JMenuItem("Buy");
        JMenuItem sellItem = new JMenuItem("Sell");
        JMenuItem updateItem = new JMenuItem("Update");
        JMenuItem getGainItem = new JMenuItem("Get gain");
        JMenuItem searchItem = new JMenuItem("Search");
        JMenuItem quit = new JMenuItem("Quit");
        commands.add(buyItem);
        commands.add(sellItem);
        commands.add(updateItem);
        commands.add(getGainItem);
        commands.add(searchItem);
        commands.add(quit);
        quit.addActionListener(new QuitListener());
        buyItem.addActionListener(new BuyListener());
        sellItem.addActionListener(new sellListener());
        updateItem.addActionListener(new updateListener());
        getGainItem.addActionListener(new gainListener());
        searchItem.addActionListener(new searchListener());

        /*Initialize the initial interface*/
        initialInterface = new JPanel(null);
        JLabel welcome = new JLabel("Welcome to ePortfolio!");
        welcome.setFont(new Font("Verdana", Font.PLAIN, 16));
        Dimension size = welcome.getPreferredSize();
        welcome.setBounds(50, 80, size.width, size.height);
        JLabel message = new JLabel("Choose a command from the “Commands” menu to buy or" );
        JLabel message2 = new JLabel("an investment, update prices for all investments, get gain for the");
        JLabel message3 = new JLabel("portfolio, search for relevant investments, or quit the program.");
        message.setFont(new Font("Verdana", Font.PLAIN, 16));
        message2.setFont(new Font("Verdana", Font.PLAIN, 16));
        message3.setFont(new Font("Verdana", Font.PLAIN, 16));
        Dimension size2 = message.getPreferredSize();
        Dimension size3 = message2.getPreferredSize();
        Dimension size4 = message3.getPreferredSize();
        message.setBounds(50, 175, size2.width, size2.height);
        message2.setBounds(50, 200, size3.width, size3.height);
        message3.setBounds(50, 225, size4.width, size4.height);
        initialInterface.add(welcome);
        initialInterface.add(message);
        initialInterface.add(message2);
        initialInterface.add(message3);
        add(initialInterface);
        initialInterface.setVisible(true);

        /*Initialize the interface*/
        Interface = new JPanel(new GridLayout(2,1));
        add(Interface);
        Interface.setVisible(false);
        buyTwoPanel = new JPanel(new BorderLayout());
        buyOpt = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        reset = new JButton("Reset");
        buyButt = new JButton("Buy");
        buyButt.addActionListener(new buyButtEar());
        sellButt = new JButton("Sell");
        sellButt.addActionListener(new sellButtEar());
        prevButt = new JButton("Prev");
        prevButt.addActionListener(new prevButtEar());
        nextButt = new JButton("Next");
        nextButt.addActionListener(new nextButtEar());
        saveButt = new JButton("Save");
        saveButt.addActionListener(new saveButtEar());
        searchButt = new JButton("Search");
        searchButt.addActionListener(new searchButtEar());
        reset.setPreferredSize(new Dimension(100,50));
        buyButt.setPreferredSize(new Dimension(100,50));
        sellButt.setPreferredSize(new Dimension(100,50));
        prevButt.setPreferredSize(new Dimension(100,50));
        nextButt.setPreferredSize(new Dimension(100,50));
        saveButt.setPreferredSize(new Dimension(100,50));
        searchButt.setPreferredSize(new Dimension(100,50));
        buyOpt.add(reset,c);
        reset.addActionListener(new resetListener());
        c.insets = new Insets(30,0,0,0);  //top padding
        c.gridx = 0;
        c.gridy = 2;
        buyOpt.add(buyButt,c);
        c.gridx = 0;
        c.gridy = 3;
        buyOpt.add(sellButt,c);
        c.gridx = 0;
        c.gridy = 4;
        buyOpt.add(prevButt,c);
        c.gridx = 0;
        c.gridy = 5;
        buyOpt.add(nextButt,c);
        c.gridx = 0;
        c.gridy = 6;
        buyOpt.add(saveButt,c);
        c.gridx = 0;
        c.gridy = 7;
        buyOpt.add(searchButt,c);
        buyOpt.setBorder(new EmptyBorder(20,20,20,20));
        GridBagConstraints c1 = new GridBagConstraints();
        c1.anchor = GridBagConstraints.NORTHWEST;
        c1.weightx = 1;
        c1.weighty = 1;

        buyBut = new JPanel(new GridBagLayout());
        ing = new JLabel("");
        ing.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(ing, c1);
        buyBut.setBorder(new EmptyBorder(20,20,20,20));
        c1.gridy = 2;
        type = new JLabel("         Type");
        type.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type,c1);
        String[] tip = {"stock", "mutualfund"};
        tipList = new JComboBox<>(tip);
        tipList.setSelectedIndex(0);
        tipList.setPreferredSize(new Dimension(200,25));
        buyBut.add(tipList, c1);
        c1.gridy = 3;
        type1 = new JLabel("         Symbol");
        type1.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type1, c1);
        symbolText = new JTextField(10);
        buyBut.add(symbolText, c1);
        c1.gridy = 4;
        type2 = new JLabel("         Name");
        type2.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type2, c1);
        nameText = new JTextField(20);
        buyBut.add(nameText, c1);
        c1.gridy = 5;
        type3 = new JLabel("         Quantity");
        type3.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type3, c1);
        quantText = new JTextField(7);
        buyBut.add(quantText, c1);
        c1.gridy = 6;
        type4 = new JLabel("         Price");
        type4.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type4, c1);
        priceText = new JTextField(7);
        buyBut.add(priceText, c1);
        c1.gridy = 7;
        type5 = new JLabel("         Total gain");
        type5.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type5, c1);
        gainText = new JTextField(10);
        buyBut.add(gainText, c1);
        c1.gridy=8;
        type6 = new JLabel("<html>&emsp; &emsp;  Name<br/>&emsp; &emsp; keywords</html>");
        type6.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type6, c1);
        keywordText = new JTextField(20);
        buyBut.add(keywordText, c1);
        c1.gridy=9;
        type7 = new JLabel("         Low price");
        type7.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type7, c1);
        lowText = new JTextField(7);
        buyBut.add(lowText, c1);
        c1.gridy=10;
        type8 = new JLabel("         High price");
        type8.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyBut.add(type8, c1);
        highText = new JTextField(7);
        buyBut.add(highText, c1);
        gainText.setEditable(false);
        JSplitPane sp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp1.setResizeWeight(0.7);
        sp1.setEnabled(false);
        sp1.setDividerSize(0);
        sp1.add(buyBut);
        sp1.add(buyOpt);

        buyTwoPanel.add(sp1, BorderLayout.CENTER);
        buyText = new JPanel(new BorderLayout());
        messText =new JLabel("");
        messText.setFont(new Font("Verdana", Font.PLAIN, 16));
        buyText.add(messText, BorderLayout.PAGE_START);
        bottomText = new JTextArea(5,35);
        buyText.setBorder(new EmptyBorder(20,20,20,20));
        bottomText.setEditable(false);
        JScrollPane scroll = new JScrollPane(bottomText);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        buyText.add(scroll);
        Interface.add(buyTwoPanel);
        Interface.add(buyText);

        pack();
    }

    /** This is the inner class for quit option*/
    private class QuitListener implements ActionListener    {
        /**Action listener to exit*/
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /** This is the inner class for buy option*/
    private class BuyListener implements ActionListener {
        /**Action listener to set up the buy interface*/
        public void actionPerformed(ActionEvent e)   {
            resetTextFields();
            initialInterface.setVisible(false);
            Interface.setVisible(true);
            /*Visibility of labels*/
            type.setVisible(true);
            type1.setVisible(true);
            type2.setVisible(true);
            type3.setVisible(true);
            type4.setVisible(true);
            type5.setVisible(false);
            type6.setVisible(false);
            type7.setVisible(false);
            type8.setVisible(false);
            /*Visibility of text fields*/
            tipList.setVisible(true);
            nameText.setVisible(true);
            quantText.setVisible(true);
            priceText.setVisible(true);
            gainText.setVisible(false);
            symbolText.setVisible(true);
            keywordText.setVisible(false);
            highText.setVisible(false);
            lowText.setVisible(false);
            /*Visibility of buttons*/
            reset.setVisible(true);
            buyButt.setVisible(true);
            sellButt.setVisible(false);
            prevButt.setVisible(false);
            nextButt.setVisible(false);
            searchButt.setVisible(false);
            saveButt.setVisible(false);
            //Description label
            ing.setText("Buying an investment");
            messText.setText("Messages");
            symbolText.setEditable(true);
            nameText.setEditable(true);
        }
    }

    /**This is the inner class for the reset button*/
    private class resetListener implements ActionListener   {
        /**Action listener for the reset button.*/
        public void actionPerformed(ActionEvent e)   {
          symbolText.setText("");
          nameText.setText("");
          quantText.setText("");
          priceText.setText("");
          gainText.setText("");
          keywordText.setText("");
          highText.setText("");
          lowText.setText("");
        }
    }

    /**This is the inner class for the sell option*/
    private class sellListener implements ActionListener    {
        public void actionPerformed(ActionEvent e)   {
            /**Action listener to set up the sell interface*/
            resetTextFields();
            initialInterface.setVisible(false);
            Interface.setVisible(true);
            /*Visibility of labels*/
            type.setVisible(false);
            type1.setVisible(true);
            type2.setVisible(false);
            type3.setVisible(true);
            type4.setVisible(true);
            type5.setVisible(false);
            type6.setVisible(false);
            type7.setVisible(false);
            type8.setVisible(false);
            /*Visibility of text fields*/
            tipList.setVisible(false);
            nameText.setVisible(false);
            quantText.setVisible(true);
            priceText.setVisible(true);
            gainText.setVisible(false);
            symbolText.setVisible(true);
            keywordText.setVisible(false);
            highText.setVisible(false);
            lowText.setVisible(false);
            /*Visibility of buttons*/
            reset.setVisible(true);
            sellButt.setVisible(true);
            buyButt.setVisible(false);
            prevButt.setVisible(false);
            nextButt.setVisible(false);
            saveButt.setVisible(false);
            searchButt.setVisible(false);
            //Description label
            ing.setText("Selling an investment");
            messText.setText("Messages");
            symbolText.setEditable(true);
            nameText.setEditable(true);
        }
    }

    /**This is the inner class for the update option*/
    private class updateListener implements ActionListener  {
        /**Action listener to set up the update interface*/
        public void actionPerformed(ActionEvent e)   {
            resetTextFields();
            initialInterface.setVisible(false);
            Interface.setVisible(true);
            prevButt.setEnabled(true);
            nextButt.setEnabled(true);
            saveButt.setEnabled(true);
            /*Visibility of labels*/
            type.setVisible(false);
            type1.setVisible(true);
            type2.setVisible(true);
            type3.setVisible(false);
            type4.setVisible(true);
            type5.setVisible(false);
            type6.setVisible(false);
            type7.setVisible(false);
            type8.setVisible(false);
            /*Visibility of text fields*/
            tipList.setVisible(false);
            nameText.setVisible(true);
            quantText.setVisible(false);
            priceText.setVisible(true);
            gainText.setVisible(false);
            symbolText.setVisible(true);
            keywordText.setVisible(false);
            highText.setVisible(false);
            lowText.setVisible(false);
            /*Visibility of buttons*/
            sellButt.setVisible(false);
            buyButt.setVisible(false);
            prevButt.setVisible(true);
            nextButt.setVisible(true);
            saveButt.setVisible(true);
            reset.setVisible(false);
            searchButt.setVisible(false);
            //Description label
            ing.setText("Updating investments");
            messText.setText("Messages");
            symbolText.setEditable(false);
            nameText.setEditable(false);
            //Initialize the text areas with first investment in the array
            if (portfolio.getInvestment().size() > 1)   {
                symbolText.setText(portfolio.getInvestment().get(0).getSymbol());
                nameText.setText(portfolio.getInvestment().get(0).getName());
                prevButt.setEnabled(false);
                return;
            }
            else if(portfolio.getInvestment().size() == 1)   {
                symbolText.setText(portfolio.getInvestment().get(0).getSymbol());
                nameText.setText(portfolio.getInvestment().get(0).getName());
                prevButt.setEnabled(false);
                nextButt.setEnabled(false);
                return;
            }
            prevButt.setEnabled(false);
            nextButt.setEnabled(false);
            saveButt.setEnabled(false);
        }
    }

    /**This is the inner class for the get gain option*/
    private class gainListener implements ActionListener  {
        /**Action listener to set up the get gain interface*/
        public void actionPerformed(ActionEvent e)   {
            resetTextFields();
            initialInterface.setVisible(false);
            Interface.setVisible(true);
            /*Visibility of labels*/
            type.setVisible(false);
            type1.setVisible(false);
            type2.setVisible(false);
            type3.setVisible(false);
            type4.setVisible(false);
            type5.setVisible(true);
            type6.setVisible(false);
            type7.setVisible(false);
            type8.setVisible(false);
            /*Visibility of text fields*/
            tipList.setVisible(false);
            nameText.setVisible(false);
            quantText.setVisible(false);
            priceText.setVisible(false);
            gainText.setVisible(true);
            symbolText.setVisible(false);
            keywordText.setVisible(false);
            highText.setVisible(false);
            lowText.setVisible(false);
            /*Visibility of buttons*/
            sellButt.setVisible(false);
            buyButt.setVisible(false);
            prevButt.setVisible(false);
            nextButt.setVisible(false);
            saveButt.setVisible(false);
            reset.setVisible(false);
            searchButt.setVisible(false);
            //Description label
            ing.setText("Getting total gain");
            messText.setText("Individual gains");

            int i=0;
            ArrayList<String> list = portfolio.displayGain();
            for (; i < list.size()-1; i++) {
                bottomText.append(list.get(i) + "\n");
            }
            Formatter formatter = new Formatter();
            formatter.format("%.3f", Double.parseDouble(list.get(i)));
            gainText.setText(formatter.toString());
        }
    }

    /**This is the inner class for the get search option*/
    private class searchListener implements ActionListener    {
        /**Action listener to set up the search interface*/
        public void actionPerformed(ActionEvent e)   {
            resetTextFields();
            initialInterface.setVisible(false);
            Interface.setVisible(true);
            /*Visibility of labels*/
            type.setVisible(false);
            type1.setVisible(true);
            type2.setVisible(false);
            type3.setVisible(false);
            type4.setVisible(false);
            type5.setVisible(false);
            type6.setVisible(true);
            type7.setVisible(true);
            type8.setVisible(true);
            /*Visibility of text fields*/
            tipList.setVisible(false);
            nameText.setVisible(false);
            quantText.setVisible(false);
            priceText.setVisible(false);
            gainText.setVisible(false);
            symbolText.setVisible(true);
            keywordText.setVisible(true);
            highText.setVisible(true);
            lowText.setVisible(true);
            /*Visibility of buttons*/
            sellButt.setVisible(false);
            buyButt.setVisible(false);
            prevButt.setVisible(false);
            nextButt.setVisible(false);
            saveButt.setVisible(false);
            reset.setVisible(true);
            searchButt.setVisible(true);
            //Description label
            ing.setText("Searching investments");
            messText.setText("Search results");
            symbolText.setEditable(true);
            nameText.setEditable(true);
        }
    }

    /**This is the inner class for the buy button*/
    private class buyButtEar implements ActionListener  {
        /**Action listener for the buy button. Exception and valitiy handling and invoking of methods for the buying functionality.*/
        public void actionPerformed(ActionEvent e)  {
            int index = portfolio.getIndexOfSymbol(symbolText.getText());
            String selected = tipList.getSelectedItem().toString();
            // Check if entered symbol already exists in the other list.
            if(portfolio.checkIfSymbolInList(symbolText.getText()) && portfolio.getInvestment().get(index).getClass() == Stock.class && selected.equals("mutualfund"))  {
                bottomText.append("The symbol you entered for a mutual fund is already in use for a stock. Please try again.\n");
                return;
            }
            else if(portfolio.checkIfSymbolInList(symbolText.getText()) && portfolio.getInvestment().get(index).getClass() == MutualFund.class && selected.equals("stock"))  {
                bottomText.append("The symbol you entered for a stock is already in use for a mutual fund. Please try again.\n");
                return;
            }
            // Check if it will be a new investment or buy more for an existing one.
            if (portfolio.checkIfSymbolInList(symbolText.getText()))    {
                bottomText.append("You are buying an existing investment. Name will be ignored.\n");
                if(symbolText.getText().equals("")) {
                    bottomText.append("Symbol cannot be empty. Try again.\n");
                    return;
                }
                //Exception if price or quantity is not a number.
                try {
                    Double.parseDouble(quantText.getText());
                    Double.parseDouble(priceText.getText());
                } catch(NumberFormatException ex){
                    bottomText.append("Exception! You need to enter numbers for price and quantity.\n");
                    return;
                }
                String anan = portfolio.buyExisting(symbolText.getText(), Integer.parseInt(quantText.getText()), Double.parseDouble(priceText.getText()));
                bottomText.append(anan + "\n");
            }
            else if(!portfolio.checkIfSymbolInList(symbolText.getText()))   {
                if(symbolText.getText().equals("")) {
                    bottomText.append("Symbol cannot be empty. Try again.\n");
                    return;
                }if(nameText.getText().equals("")) {
                    bottomText.append("Name cannot be empty. Try again.\n");
                    return;
                }
                //Exception if price or quantity is not a number.
                try {
                    Double.parseDouble(priceText.getText());
                    Double.parseDouble(quantText.getText());
                } catch(NumberFormatException ex){
                    bottomText.append("Exception! You need to enter numbers for price and quantity.\n");
                    return;
                }
                String anan = portfolio.buyNew(selected, symbolText.getText(), Integer.parseInt(quantText.getText()), Double.parseDouble(priceText.getText()), nameText.getText());
                bottomText.append(anan + "\n");
            }
        }
    }

    /**This is the inner class for the sell button*/
    private class sellButtEar implements ActionListener {
        /**Action listener for the sell button. Exceptions handling and invoking of methods for selling functionality.*/
        public void actionPerformed(ActionEvent e)  {
            if(!portfolio.checkIfSymbolInList(symbolText.getText())) {
                bottomText.append("Cannot find the investment. Try again.\n");
            }
            else    {
                int index = portfolio.getIndexOfSymbol(symbolText.getText());
                //Exception if price or quantity is not a number.
                try {
                    Double.parseDouble(quantText.getText());
                    Double.parseDouble(priceText.getText());
                } catch(NumberFormatException ex){
                    bottomText.append("Exception! You need to enter numbers for price and quantity.\n");
                    return;
                }
                if(portfolio.isSellable(index, Integer.parseInt(quantText.getText()))) {
                    String anan = portfolio.sell(index, Double.parseDouble(priceText.getText()), Integer.parseInt(quantText.getText()));
                    bottomText.append(anan + "\n");
                }else
                    bottomText.append("You cannot sell more than what you have\n");
            }
        }
    }

    /**This is the inner class for the prev button*/
    private class prevButtEar implements ActionListener {
        /**Action listener for the prev button.*/
        public void actionPerformed(ActionEvent e)  {
            prevButt.setEnabled(true);
            nextButt.setEnabled(true);
            int index = portfolio.getIndexOfSymbol(symbolText.getText());
            if(index == 0) {
                prevButt.setEnabled(false);
            }else   {
                prevButt.setEnabled(true);
                if(index - 1 == 0) prevButt.setEnabled(false);
                index--;
            }
            symbolText.setText(portfolio.getInvestment().get(index).getSymbol());
            nameText.setText(portfolio.getInvestment().get(index).getName());
        }
    }

    /**This is the inner class for the next button*/
    private class nextButtEar implements ActionListener {
        /**Action listener for the next button.*/
        public void actionPerformed(ActionEvent e)  {
            prevButt.setEnabled(true);
            nextButt.setEnabled(true);
            int index = portfolio.getIndexOfSymbol(symbolText.getText());
            if(index == portfolio.getInvestment().size()-1) {
                nextButt.setEnabled(false);
            }else{
                nextButt.setEnabled(true);
                if(index + 1 == portfolio.getInvestment().size()-1) nextButt.setEnabled(false);
                index++;
            }
            symbolText.setText(portfolio.getInvestment().get(index).getSymbol());
            nameText.setText(portfolio.getInvestment().get(index).getName());
        }
    }

    /**This is the inner class for the save button*/
    private class saveButtEar implements ActionListener {
        /**Action listener for the save button. Exception checks for the prices and invoking of methods to update.*/
        public void actionPerformed(ActionEvent e)  {
            //Exception if price is not a number.
            try {
                Double.parseDouble(priceText.getText());
            } catch(NumberFormatException ex){
                bottomText.append("Exception! You need to enter a number for price.\n");
                return;
            }
            String anan = portfolio.update(symbolText.getText(), Double.parseDouble(priceText.getText()));
            bottomText.append(anan + "\n");
        }
    }

    /**This is the inner class for the search button*/
    private class searchButtEar implements ActionListener {
        /**Action listener for the search button. Exception checks for the prices and invoking of methods to search.*/
        public void actionPerformed(ActionEvent e)  {
            bottomText.setText("");
            if(!lowText.getText().isEmpty())    {
                try {
                    Double.parseDouble(lowText.getText());
                    if(Double.parseDouble(lowText.getText()) < 0) throw new Exception();
                } catch(Exception ex){
                    bottomText.append("Exception! You need to enter a non-negative number for price.\n");
                    return;
                }
            }
            else if(!highText.getText().isEmpty())  {
                try {
                    Double.parseDouble(highText.getText());
                    if(Double.parseDouble(highText.getText()) < 0) throw new Exception();
                } catch(Exception ex){
                    bottomText.append("Exception! You need to enter a non-negative number for price.\n");
                    return;
                }
            }
            if(!highText.getText().isEmpty() && !lowText.getText().isEmpty())   {
                if(!(Double.parseDouble(lowText.getText()) <= Double.parseDouble(highText.getText())))  {
                    bottomText.append("Low end needs to be lower than or equal to high end!\n");
                }
            }
            TreeSet<Integer> temp = portfolio.search(symbolText.getText(), keywordText.getText(), lowText.getText(), highText.getText());
            if(temp != null)    {
                for(Integer i: temp)    {
                    bottomText.append(portfolio.getInvestment().get(i).toString() + "\n");
                }
            }
        }
    }

    /**Simply resets the text fields*/
    private void resetTextFields()  {
        symbolText.setText("");
        nameText.setText("");
        quantText.setText("");
        priceText.setText("");
        gainText.setText("");
        keywordText.setText("");
        highText.setText("");
        lowText.setText("");
        bottomText.setText("");
    }

    /**Main function to execute the code*/
    public static void main(String[] args)   {
        Window window = new Window();
        window.setVisible(true);
    }
}
