package org.example.uap_wulan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListBooking {

    // Map untuk menyimpan jalur gambar setiap baris
    private static final Map<Integer, String> imagePaths = new HashMap<>();

    // Renderer untuk tombol
    static class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor untuk tombol
    static class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private JTable table;
        private int row;
        private int column;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> handleButtonClick());
        }

        private void handleButtonClick() {
            if (column == 4) { // Image column
                // Mengambil jalur gambar dari map berdasarkan baris
                String imagePath = imagePaths.get(row);
                if (imagePath != null && !imagePath.isEmpty()) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    JOptionPane.showMessageDialog(button, new JLabel(imageIcon), "Image Viewer", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(button, "Gambar tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (column == 5) { // Edit column
                String currentName = (String) table.getValueAt(row, 0);
                String currentSeat = (String) table.getValueAt(row, 3);

                JTextField nameField = new JTextField(currentName);
                JTextField seatField = new JTextField(currentSeat);

                Object[] message = {
                        "Edit Nama:", nameField,
                        "Edit Kursi Tersedia:", seatField
                };

                int option = JOptionPane.showConfirmDialog(button, message, "Edit Data", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    table.setValueAt(nameField.getText(), row, 0);
                    table.setValueAt(seatField.getText(), row, 3);
                    FormBooking.getBookingData().get(row)[0] = nameField.getText();
                    FormBooking.getBookingData().get(row)[3] = seatField.getText();
                }
            } else if (column == 6) { // Delete column
                int confirm = JOptionPane.showConfirmDialog(button, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) table.getModel()).removeRow(row);
                    FormBooking.getBookingData().remove(row);
                    imagePaths.remove(row);
                }
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            this.column = column;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

    public ListBooking() {
        JFrame mainFrame = new JFrame("List Booking Seat");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 450);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null);
        labelPanel.setBounds(0, 10, 700, 100);

        JLabel titleLabel = new JLabel("List Booking Seat");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(270, 30, 400, 40);
        labelPanel.add(titleLabel);
        mainFrame.add(labelPanel);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(550, 50, 80, 25);
        mainFrame.add(btnBack);

        // Membuat tabel
        String[] columnNames = {"Nama", "Membership", "Tanggal Booking", "Kursi Tersedia", "Image", "Edit", "Delete"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Mengisi tabel dengan data dari FormBooking
        List<Object[]> bookingData = FormBooking.getBookingData();
        for (int i = 0; i < bookingData.size(); i++) {
            Object[] row = bookingData.get(i);
            Object[] rowData = new Object[row.length + 3];
            System.arraycopy(row, 0, rowData, 0, row.length);
            rowData[row.length] = "";  // Tombol View untuk Image
            rowData[row.length + 1] = "";   // Tombol Edit
            rowData[row.length + 2] = ""; // Tombol Delete
            tableModel.addRow(rowData);

            // Simpan jalur gambar ke dalam map
            imagePaths.put(i, (String) row[4]);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 600, 250);
        mainFrame.add(scrollPane);

        // Menambahkan renderer dan editor untuk kolom tombol
        table.getColumn("Image").setCellRenderer(new ButtonRenderer());
        table.getColumn("Image").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), table));

        // EventListener untuk tombol Back
        btnBack.addActionListener(e -> {
            mainFrame.dispose();
            new SystemBooking();
        });

        mainFrame.setVisible(true);
    }
}
