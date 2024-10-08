package com.miniproject.Bank.GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.miniproject.Bank.Account;

public class LoginFrame {
    public LoginFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(6, 1));

        JTextField nameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JLabel messageLabel = new JLabel();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());

            Account account = Account.getAccount(name, password);

            if (Account.accountExists(name, password)) {
                if (account != null) {
                    messageLabel.setText("Login successful!");
                    if (account.getpin() == 0) {
                        int option = JOptionPane.showConfirmDialog(null, "Do you want to set pin", "",
                                JOptionPane.YES_NO_OPTION);

                        if (option == 0) {
                            JPasswordField pf = new JPasswordField();

                            int options = JOptionPane.showConfirmDialog(null, pf, "Enter Pin",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE);
                            if (options == 0) {
                                int pin = Integer.parseInt(new String(pf.getPassword()));
                                account.setpin(pin);
                            }
                        }
                    }
                    frame.dispose();
                    new AccountFrame(account);

                } else {
                    messageLabel.setText("Invalid password. Try again.");
                }
            } else {
                messageLabel.setText("No account found with this name.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainFrame();
        });

        panel.add(new JLabel("Enter Account Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Enter Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);
        panel.add(messageLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
