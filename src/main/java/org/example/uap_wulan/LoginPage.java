package org.example.uap_wulan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LoginPage {
    public LoginPage() {
        // Membuat JFrame utama
        JFrame mainFrame = new JFrame("Boutique Restaurant");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 500);
        mainFrame.setLocationRelativeTo(null);

        // Panel untuk menampung komponen
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null); // Layout null agar kita bisa menggunakan setBounds

        // Label "Welcome to Skibiki Restaurant" di tengah atas
        JLabel titleLabel = new JLabel("Welcome to Boutique Restaurant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(200, 100, 400, 30);  // Menentukan posisi dan ukuran label
        labelPanel.add(titleLabel);

        // Label "Please Log in"
        JLabel subtitleLabel = new JLabel("Please Login");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitleLabel.setBounds(300, 130, 150, 20);  // Menentukan posisi dan ukuran label
        labelPanel.add(subtitleLabel);

        // Label "Username" dan text field untuk username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 180, 100, 30);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(250, 180, 200, 30);
        labelPanel.add(usernameLabel);
        labelPanel.add(usernameField);

        // Label "Password" dan text field untuk password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 230, 100, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250, 230, 200, 30);
        labelPanel.add(passwordLabel);
        labelPanel.add(passwordField);

        // Menambahkan tombol login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 280, 200, 30);  // Menentukan posisi dan ukuran tombol
        labelPanel.add(loginButton);

        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    // Validasi login
                    if ("admin".equals(username) && "admin".equals(password)) {
                        JOptionPane.showMessageDialog(mainFrame, "Berhasil login!", "Login Sukses", JOptionPane.INFORMATION_MESSAGE);
                        mainFrame.dispose(); // Menutup frame login

                        // Mengarahkan ke SystemBooking
                        new SystemBooking();
                    } else {
                        throw new Exception("Username atau password salah");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Login Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menambahkan panel ke frame utama
        mainFrame.add(labelPanel);


        // Menampilkan frame
        mainFrame.setVisible(true);
    }
}
