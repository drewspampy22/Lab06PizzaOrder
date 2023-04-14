import javax.swing.*;
import java.awt.*;

public class PizzaGUIFrame extends JFrame {
    private JPanel mainPnl;
    private JPanel optionMenu;
    private JPanel crustType;
    private JRadioButton thinCrust;
    private JRadioButton regularCrust;
    private JRadioButton deepCrust;

    private JComboBox sizeSelect;

    private JPanel ingredients;
    private JCheckBox anchovy;
    private JCheckBox peperoni;
    private JCheckBox sausage;
    private JCheckBox onion;
    private JCheckBox bellPepper;
    private JCheckBox mushroom;


    private JPanel orderPanel;
    private JTextArea finalOrder;
    private JScrollPane orderOut;


    private JPanel finalPanel;
    private JButton orderBtn;
    private JButton clearBtn;
    private JButton quitBtn;

    public PizzaGUIFrame(){
        mainPnl = new JPanel();

        createOptionMenu();
        mainPnl.add(optionMenu);

        createOrderPanel();
        mainPnl.add(orderPanel);

        createFinalPanel();
        mainPnl.add(finalPanel);

        add(mainPnl);
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createOptionMenu(){
        optionMenu = new JPanel();
        optionMenu.setLayout(new GridLayout(3, 1));

        crustType = new JPanel();
        crustType.setLayout(new GridLayout(1, 3));
        thinCrust = new JRadioButton("Thin");
        thinCrust.addActionListener(e -> {
            if(thinCrust.isSelected()){
                regularCrust.setSelected(false);
                deepCrust.setSelected(false);
            }
        });
        regularCrust = new JRadioButton("Regular");
        regularCrust.setSelected(true);
        regularCrust.addActionListener(e -> {
            if(regularCrust.isSelected()){
                thinCrust.setSelected(false);
                deepCrust.setSelected(false);
            }
        });
        deepCrust = new JRadioButton("Deep-Dish");
        deepCrust.addActionListener(e -> {
            if(deepCrust.isSelected()){
                thinCrust.setSelected(false);
                regularCrust.setSelected(false);
            }
        });
        crustType.add(thinCrust);
        crustType.add(regularCrust);
        crustType.add(deepCrust);
        optionMenu.add(crustType);

        sizeSelect = new JComboBox(new String[]{"Small", "Medium", "Large", "Super"});
        sizeSelect.setSelectedIndex(1);
        optionMenu.add(sizeSelect);

        ingredients = new JPanel();
        ingredients.setLayout(new GridLayout(2, 3));
        anchovy = new JCheckBox("Anchovy");
        peperoni = new JCheckBox("Peperoni");
        sausage = new JCheckBox("Sausage");
        onion = new JCheckBox("Onion");
        bellPepper = new JCheckBox("Bell Pepper");
        mushroom = new JCheckBox("Mushroom");

        ingredients.add(anchovy);
        ingredients.add(peperoni);
        ingredients.add(sausage);
        ingredients.add(onion);
        ingredients.add(bellPepper);
        ingredients.add(mushroom);
        optionMenu.add(ingredients);
    }

    private void createOrderPanel(){
        finalOrder = new JTextArea(10, 40);
        orderOut = new JScrollPane(finalOrder);
        orderPanel = new JPanel();
        orderPanel.add( orderOut);
    }

    private void createFinalPanel(){
        finalPanel = new JPanel();
        finalPanel.setLayout(new GridLayout(1, 3));

        orderBtn = new JButton("Order");
        orderBtn.addActionListener(e -> calculateOrder());
        finalPanel.add(orderBtn);
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(e -> clearOrder());
        finalPanel.add(clearBtn);
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?") == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });
        finalPanel.add(quitBtn);
    }

    private void calculateOrder(){
        double crustTotal = 0;
        String crustSize = "";
        switch (sizeSelect.getSelectedIndex()){
            case 0:
                crustTotal = 8;
                crustSize = "Small";
                break;
            case 1:
                crustTotal = 12;
                crustSize = "Medium";
                break;
            case 2:
                crustTotal = 16;
                crustSize = "Large";
                break;
            case 3:
                crustTotal = 20;
                crustSize = "Super";
                break;
        }
        String crustType = "";
        if(thinCrust.isSelected()){
            crustType = "Thin";
        }
        else if(regularCrust.isSelected()){
            crustType = "Regular";
        }
        else{
            crustType = "DeepDish";
        }

        double ingredientTotal = 0;
        if(anchovy.isSelected())
            ingredientTotal += 1;
        if(peperoni.isSelected())
            ingredientTotal += 1;
        if(sausage.isSelected())
            ingredientTotal += 1;
        if(onion.isSelected())
            ingredientTotal += 1;
        if(mushroom.isSelected())
            ingredientTotal += 1;
        if(bellPepper.isSelected())
            ingredientTotal += 1;

        double subTotal = crustTotal + ingredientTotal;
        double tax = subTotal * 0.07;
        double finalTotal = subTotal + tax;

        String finalOut  =
                "========================================\n"+
                        String.format("%-20s%20.2f\n", crustType + " " + crustSize,crustTotal) +
                        String.format("%-20s%20.2f\n\n\n", "Toppings",ingredientTotal) +
                        String.format("%-20s%20.2f\n", "Sub-total:",subTotal) +
                        String.format("%-20s%20.2f\n", "Tax:",tax) +
                        "----------------------------------------\n"   +
                        String.format("%-20s%20.2f\n", "Total:",finalTotal) +
                        "========================================";
        finalOrder.setText(finalOut);
    }

    private void clearOrder(){
        anchovy.setSelected(false);
        peperoni.setSelected(false);
        sausage.setSelected(false);
        onion.setSelected(false);
        mushroom.setSelected(false);
        bellPepper.setSelected(false);

        finalOrder.setText("");
    }
}