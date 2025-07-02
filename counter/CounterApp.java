import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;


public class CounterApp {
    private int count = 0;

    public CounterApp() {
        JFrame frame = new JFrame("Counter");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Number: ");
        JTextField textField = new JTextField("0", 10);
        textField.setEditable(false);

        JButton countButton = new JButton("Count");
        JButton resetButton = new JButton("Reset");

        countButton.addActionListener(e -> {
            count++;
            textField.setText(String.valueOf(count));
        });

        resetButton.addActionListener(e -> {
            count = 0;
            textField.setText("0");
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(countButton);
        panel.add(resetButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CounterApp::new);
    }
}
