import java.awt.*;
import java.awt.event.*;

class Calculator extends WindowAdapter implements ActionListener {
    Frame f;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button bAdd, bSub, bMul, bLog, bEqual, bClear;
    Button bBack, bDot, bPlusMinus; 
    TextArea display;
    
    double num1, num2, result;
    String operation = "";
    boolean newNumber = true;

    Calculator(){
        f = new Frame("Scientific Calculator");
        
        display = new TextArea("0", 2, 20, TextArea.SCROLLBARS_NONE);
        display.setEditable(false);
        display.setBackground(Color.LIGHT_GRAY);
        display.setBounds(50, 50, 260, 60);

        b1 = new Button("1");
        b1.setBounds(50, 340, 50, 50);
        b2 = new Button("2");
        b2.setBounds(120, 340, 50, 50);
        b3 = new Button("3");
        b3.setBounds(190, 340, 50, 50);
        b4 = new Button("4");
        b4.setBounds(50, 270, 50, 50);
        b5 = new Button("5");
        b5.setBounds(120, 270, 50, 50);
        b6 = new Button("6");
        b6.setBounds(190, 270, 50, 50);
        b7 = new Button("7");
        b7.setBounds(50, 200, 50, 50);
        b8 = new Button("8");
        b8.setBounds(120, 200, 50, 50);
        b9 = new Button("9");
        b9.setBounds(190, 200, 50, 50);
        b0 = new Button("0");
        b0.setBounds(50, 410, 50, 50);

        bAdd = new Button("+");
        bAdd.setBounds(260, 340, 50, 50);
        bSub = new Button("-");
        bSub.setBounds(260, 270, 50, 50);
        bMul = new Button("*");
        bMul.setBounds(260, 200, 50, 50);
        bLog = new Button("Log");
        bLog.setBounds(260, 130, 50, 50);
        bEqual = new Button("=");
        bEqual.setBounds(190, 410, 50, 50);
        bClear = new Button("CE");
        bClear.setBounds(50, 130, 50, 50);
        
        bBack = new Button("←");
        bBack.setBounds(130, 130, 50, 50);
        bDot = new Button(".");
        bDot.setBounds(120, 410, 50, 50);
        bPlusMinus = new Button("+/-");
        bPlusMinus.setBounds(260, 410, 50, 50);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        bAdd.addActionListener(this);
        bSub.addActionListener(this);
        bMul.addActionListener(this);
        bLog.addActionListener(this);
        bEqual.addActionListener(this);
        bClear.addActionListener(this);
        bBack.addActionListener(this);
        bDot.addActionListener(this);
        bPlusMinus.addActionListener(this);

        f.addWindowListener(this);

        f.add(display);
        f.add(b1); f.add(b2); f.add(b3); f.add(b4); f.add(b5); f.add(b6);
        f.add(b7); f.add(b8); f.add(b9); f.add(b0);
        f.add(bAdd); f.add(bSub); f.add(bMul); f.add(bLog); f.add(bEqual); f.add(bClear);
        f.add(bBack); f.add(bDot); f.add(bPlusMinus);

        f.setSize(360, 500);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String currentText = display.getText();

        if (command.matches("[0-9]")) {
            if (newNumber || currentText.equals("0")) {
                display.setText(command);
                newNumber = false;
            } else {
                display.setText(currentText + command);
            }
        }
        else if (command.equals(".")) {
            if (newNumber) {
                display.setText("0.");
                newNumber = false;
            } else if (!currentText.contains(".")) {
                display.setText(currentText + ".");
            }
        }
        else if (command.equals("+") || command.equals("-") || command.equals("*")) {
            num1 = Double.parseDouble(currentText);
            operation = command;
            newNumber = true;
        }
        else if (command.equals("Log")) {
            double value = Double.parseDouble(currentText);
            if (value > 0) {
                result = Math.log10(value);
                display.setText(String.valueOf(result));
            } else {
                display.setText("Error");
            }
            newNumber = true;
        }
        else if (command.equals("=")) {
            if (!operation.isEmpty()) {
                num2 = Double.parseDouble(currentText);
                
                switch (operation) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                }
                
                display.setText(String.valueOf(result));
                operation = "";
                newNumber = true;
            }
        }
        else if (command.equals("CE")) {
            display.setText("0");
            num1 = 0;
            num2 = 0;
            result = 0;
            operation = "";
            newNumber = true;
        }
        else if (command.equals("←")) {
            if (currentText.length() > 1) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                display.setText("0");
                newNumber = true;
            }
        }
        else if (command.equals("+/-")) {
            if (!currentText.equals("0")) {
                if (currentText.startsWith("-")) {
                    display.setText(currentText.substring(1));
                } else {
                    display.setText("-" + currentText);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Calculator();
    }
}