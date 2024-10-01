package com.inventario.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.inventario.model.*;

public class ProductRegistrationFrame extends JFrame {
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTable productTable;
    private ProductTableModel tableModel;
    private int productIdCounter = 1; // Para asignar IDs únicos

    public ProductRegistrationFrame() {
        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Configuración del layout
        setLayout(new BorderLayout());

        // Panel para el formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Nombre:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Precio:"));
        txtPrice = new JTextField();
        formPanel.add(txtPrice);

        formPanel.add(new JLabel("Cantidad:"));
        txtQuantity = new JTextField();
        formPanel.add(txtQuantity);

        JButton btnSave = new JButton("Guardar");
        btnSave.addActionListener(new SaveButtonListener());
        formPanel.add(btnSave);

        add(formPanel, BorderLayout.NORTH);

        // Tabla de productos
        tableModel = new ProductTableModel();
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveProduct();
        }
    }

    private void saveProduct() {
        String name = txtName.getText();
        double price;
        int quantity;

        // Validar entrada
        if (name.isEmpty() || txtPrice.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            price = Double.parseDouble(txtPrice.getText());
            quantity = Integer.parseInt(txtQuantity.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio y Cantidad deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear producto y agregarlo a la tabla
        Product product = new Product(productIdCounter++, name, price, quantity);
        tableModel.addProduct(product);

        // Limpiar campos
        txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductRegistrationFrame frame = new ProductRegistrationFrame();
            frame.setVisible(true);
        });
    }
}