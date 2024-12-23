package org.example.uap_wulan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemBooking {
    public SystemBooking() {
        JFrame mainFrame = new JFrame("System Booking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 450);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null); // Layout null agar kita bisa menggunakan setBounds
        labelPanel.setBounds(0, 10, 700, 100);

        // Label "Welcome to Skibiki Restaurant" di tengah atas
        JLabel titleLabel = new JLabel("Select Menu Booking Seat");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(200, 30, 400, 40);  // Menentukan posisi dan ukuran label
        labelPanel.add(titleLabel);
        mainFrame.add(labelPanel);

        JButton btnLogout = new JButton("LogOut");
        btnLogout.setBounds(550, 50, 80,30);
        mainFrame.add(btnLogout);

        // Panel untuk tombol Booking dan Check
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(50, 100, 600, 300);
        mainFrame.add(menuPanel);

        // Tambahkan tombol Booking
        JButton btnBooking = new JButton("Booking");
        btnBooking.setBounds(150, 50, 120, 120);

        // Ubah ukuran gambar dan tambahkan ke tombol
        ImageIcon originalIcon = new ImageIcon("F:\\UMM\\SEMESTER 3\\Pemrograman Lanjut - Praktek\\UAP\\uap_wulan\\src\\components\\bookingIcon.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        btnBooking.setIcon(new ImageIcon(resizedImage));

        btnBooking.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBooking.setVerticalTextPosition(SwingConstants.BOTTOM);
        menuPanel.add(btnBooking);

        // Tambahkan tombol Check
        JButton btnCheck = new JButton("Check");
        btnCheck.setBounds(350, 50, 120, 120);

        // Ubah ukuran gambar dan tambahkan ke tombol
        ImageIcon checkIcon = new ImageIcon("F:\\UMM\\SEMESTER 3\\Pemrograman Lanjut - Praktek\\UAP\\uap_wulan\\src\\components\\checkIcon.png");
        Image resizedCheckImage = checkIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        btnCheck.setIcon(new ImageIcon(resizedCheckImage));

        btnCheck.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCheck.setVerticalTextPosition(SwingConstants.BOTTOM);
        menuPanel.add(btnCheck);

        //eventListener
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new LoginPage();
            }
        });

        //eventListener
        btnBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new FormBooking();
            }
        });

        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new ListBooking();
            }
        });






        mainFrame.setVisible(true);
    }
}
