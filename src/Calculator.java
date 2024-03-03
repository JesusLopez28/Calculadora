import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, clearButton, equalsButton, dotButton;
    private JButton modButton, powButton, sqrtButton, backButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public Calculator() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 50));
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(this);
        }

        functionButtons = new JButton[11]; // Added new buttons for modulo, power, sqrt, back
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        clearButton = new JButton("C");
        equalsButton = new JButton("=");
        dotButton = new JButton(".");
        modButton = new JButton("%");
        powButton = new JButton("^");
        sqrtButton = new JButton("√");
        backButton = new JButton("←");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = clearButton;
        functionButtons[5] = equalsButton;
        functionButtons[6] = dotButton;
        functionButtons[7] = modButton;
        functionButtons[8] = powButton;
        functionButtons[9] = sqrtButton;
        functionButtons[10] = backButton;

        for (int i = 0; i < 11; i++) {
            functionButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            functionButtons[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(backButton);
        panel.add(clearButton);
        panel.add(modButton);
        panel.add(divButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(sqrtButton);
        panel.add(numberButtons[0]);
        panel.add(dotButton);
        panel.add(equalsButton);

        add(textField, BorderLayout.NORTH);
        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText() + i);
            }
        }
        if (e.getSource() == dotButton) {
            textField.setText(textField.getText() + ".");
        }
        if (e.getSource() == clearButton) {
            clear();
        }
        if (e.getSource() == addButton) {
            performOperation('+');
        }
        if (e.getSource() == subButton) {
            performOperation('-');
        }
        if (e.getSource() == mulButton) {
            performOperation('*');
        }
        if (e.getSource() == divButton) {
            performOperation('/');
        }
        if (e.getSource() == modButton) {
            performOperation('%');
        }
        if (e.getSource() == powButton) {
            performOperation('^');
        }
        if (e.getSource() == sqrtButton) {
            performOperation('√');
        }
        if (e.getSource() == backButton) {
            backspace();
        }
        if (e.getSource() == equalsButton) {
            calculate();
        }
    }

    private void clear() {
        textField.setText("");
    }

    private void performOperation(char op) {
        num1 = Double.parseDouble(textField.getText());
        operator = op;
        if (op == '√') {
            calculate(); // Immediately calculate square root
        }
        textField.setText("");
    }

    private void backspace() {
        String currentText = textField.getText();
        if (!currentText.isEmpty()) {
            textField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void calculate() {
        num2 = Double.parseDouble(textField.getText());
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: División por cero");
                }
                break;
            case '%':
                result = num1 % num2;
                break;
            case '^':
                result = Math.pow(num1, num2);
                break;
            case '√':
                result = Math.sqrt(num1);
                break;
        }
        textField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
