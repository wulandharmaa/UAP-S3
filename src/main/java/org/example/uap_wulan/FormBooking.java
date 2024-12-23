package org.example.uap_wulan;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class FormBooking {
    private static List<Object[]> bookingData = new ArrayList<>(); // List untuk menyimpan data booking
    private String imagePath = null;

    public FormBooking() {
        JFrame mainFrame = new JFrame("From Booking");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 550);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);

        JLabel labelJudul = new JLabel("Form Booking Meja Restauran Boutique");
        labelJudul.setFont(new Font("Arial", Font.BOLD, 15));
        labelJudul.setBounds(50,70,300,25);
        mainFrame.add(labelJudul);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(310, 30, 65,25);
        mainFrame.add(btnBack);

        JLabel name = new JLabel("Nama");
        name.setBounds(20,130,150,25);
        mainFrame.add(name);

        JTextField txtName = new JTextField();
        txtName.setBounds(150,130,150,25);
        mainFrame.add(txtName);

        JLabel membership = new JLabel("Membership");
        membership.setBounds(20,165,150,25);
        mainFrame.add(membership);

        JRadioButton radEksklusif = new JRadioButton("Eksklusif");
        radEksklusif.setBounds(150,165,80,25);
        mainFrame.add(radEksklusif);

        JRadioButton radUmum = new JRadioButton("Umum");
        radUmum.setBounds(230,165,80,25);
        mainFrame.add(radUmum);

        ButtonGroup groupMembership = new ButtonGroup();
        groupMembership.add(radEksklusif);
        groupMembership.add(radUmum);

        JLabel tglBooking = new JLabel("Tanggal Booking");
        tglBooking.setBounds(20,200,150,25);
        mainFrame.add(tglBooking);

        String hari[] = new String[31];
        for (int i = 0;i< hari.length;i++){
            hari[i] = Integer.toString(i+1);
        }

        JComboBox cbHari = new JComboBox(hari);
        cbHari.setBounds(150,200,40,25);
        mainFrame.add(cbHari);

        String bulan[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        JComboBox cbBulan = new JComboBox(bulan);
        cbBulan.setBounds(200,200,55,25);
        mainFrame.add(cbBulan);

        String tahun[] = {"2024", "2025"};
        JComboBox cbTahun = new JComboBox(tahun);
        cbTahun.setBounds(265,200,55,25);
        mainFrame.add(cbTahun);

        JLabel pilihMeja = new JLabel("Pilih Meja");
        pilihMeja.setBounds(20,235,150,25);
        mainFrame.add(pilihMeja);

        String meja[] = new String[28];
        for (int i = 0;i< meja.length;i++){
            meja[i] = Integer.toString(i+1);
        }

        JComboBox cbMeja = new JComboBox(meja);
        cbMeja.setBounds(150,235,40,25);
        mainFrame.add(cbMeja);

        JLabel ttlBiaya = new JLabel("Total Biaya");
        ttlBiaya.setBounds(20,270,150,25);
        mainFrame.add(ttlBiaya);

        JLabel ttlBiayaFill = new JLabel("-");
        ttlBiayaFill.setBounds(150,270,150,25);
        mainFrame.add(ttlBiayaFill);

        JLabel jmlBayar = new JLabel("Jumlah Bayar");
        jmlBayar.setBounds(20,305,150,25);
        mainFrame.add(jmlBayar);

        JTextField inBayar = new JTextField();
        inBayar.setBounds(150,305,150,25);
        mainFrame.add(inBayar);

        JLabel kembalian = new JLabel("Kembalian");
        kembalian.setBounds(20,340,150,25);
        mainFrame.add(kembalian);

        JLabel kembalianFill = new JLabel("-");
        kembalianFill.setBounds(150,340,150,25);
        mainFrame.add(kembalianFill);

        JLabel buktiPembayaran = new JLabel("Bukti Pembayaran");
        buktiPembayaran.setBounds(20,375,150,25);
        mainFrame.add(buktiPembayaran);

        JButton btnUploadImage = new JButton("Upload Image");
        btnUploadImage.setBounds(150, 375, 150, 25);
        mainFrame.add(btnUploadImage);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(200,420,100,25);
        mainFrame.add(btnSimpan);

        //eventListener
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new SystemBooking();
            }
        });

        radEksklusif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ttlBiayaFill.setText("50000");
            }
        });

        radUmum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ttlBiayaFill.setText("20000");
            }
        });

        inBayar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                calculateChange();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                calculateChange();
            }

            private void calculateChange() {
                try {
                    int totalBiaya = Integer.parseInt(ttlBiayaFill.getText());
                    int jumlahBayar = Integer.parseInt(inBayar.getText());
                    int kembalian = jumlahBayar - totalBiaya;
                    kembalianFill.setText("Rp." + kembalian);
                } catch (NumberFormatException ex) {
                    kembalianFill.setText("-"); // Reset jika input salah
                }
            }
        });

        btnUploadImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                    JOptionPane.showMessageDialog(mainFrame, "Image uploaded successfully!", "Upload Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validasi data
                    validateBookingData(txtName.getText(),
                            radEksklusif.isSelected() ? "Eksklusif" : (radUmum.isSelected() ? "Umum" : ""),
                            cbHari.getSelectedItem() + "-" + cbBulan.getSelectedItem() + "-" + cbTahun.getSelectedItem(),
                            cbMeja.getSelectedItem().toString(),
                            imagePath);

                    // Simpan data ke bookingData
                    FormBooking.bookingData.add(new Object[]{txtName.getText(),
                            radEksklusif.isSelected() ? "Eksklusif" : "Umum",
                            cbHari.getSelectedItem() + "-" + cbBulan.getSelectedItem() + "-" + cbTahun.getSelectedItem(),
                            cbMeja.getSelectedItem().toString(),
                            imagePath});

                    JOptionPane.showMessageDialog(mainFrame, "Data berhasil disimpan!");
                    mainFrame.dispose();
                    new SystemBooking();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mainFrame.setVisible(true);
    }

    public void validateBookingData(String nama, String membership, String tanggalBooking, String kursi, String imagePath) throws Exception {
        if (nama == null || nama.trim().isEmpty()) {
            throw new Exception("Nama tidak boleh kosong!");
        }
        if (membership == null || membership.trim().isEmpty()) {
            throw new Exception("Membership harus dipilih!");
        }
        if (tanggalBooking == null || tanggalBooking.trim().isEmpty()) {
            throw new Exception("Tanggal Booking harus lengkap!");
        }
        if (kursi == null || kursi.trim().isEmpty()) {
            throw new Exception("Pilih meja yang tersedia!");
        }
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new Exception("Bukti pembayaran (gambar) harus diunggah!");
        }
    }


    public static List<Object[]> getBookingData() {
        return bookingData;
    }

}
