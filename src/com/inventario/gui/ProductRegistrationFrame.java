package com.inventario.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.inventario.model.Product;

public class ProductRegistrationFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton saveButton;

    public ProductRegistrationFrame() {
        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Inicializa los componentes
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel nameLabel = new JLabel("Nombre:");
        nameField = new JTextField();

        JLabel priceLabel = new JLabel("Precio:");
        priceField = new JTextField();

        JLabel quantityLabel = new JLabel("Cantidad:");
        quantityField = new JTextField();

        saveButton = new JButton("Guardar");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(saveButton);

        add(panel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProduct();
            }
        });
    }

    private void saveProduct() {
        String id = idField.getText();
        String name = nameField.getText();
        String priceText = priceField.getText();
        String quantityText = quantityField.getText();

        try {
            int productId = Integer.parseInt(id);
            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);

            Product product = new Product(productId, name, price, quantity);

            idField.setText("");
            nameField.setText("");
            priceField.setText("");
            quantityField.setText("");

            JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
