package com.abc.ui;
import java.awt.EventQueue;
import java.awt.Font;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

public class PaymentDetailsForm extends JFrame 
{

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtCustomerNumber;
    private JTable table;
    private JLabel lblTotalValue;

    // ✅ Change this to your real context path
    // Example: Project1 or orderdetails-api
    private static final String BASE_URL =
            "http://localhost:8080/orderdetails-api/api/paymentdetails";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PaymentDetailsForm frame = new PaymentDetailsForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PaymentDetailsForm() 
    {
        setTitle("Payment Details Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 360);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblOrder = new JLabel("Customer Number:");
        lblOrder.setBounds(20, 20, 100, 20);
        contentPane.add(lblOrder);

        txtCustomerNumber = new JTextField();
        txtCustomerNumber.setBounds(130, 20, 140, 22);
        contentPane.add(txtCustomerNumber);

        JButton btnLoad = new JButton("Load");
        btnLoad.setBounds(290, 20, 90, 22);
        contentPane.add(btnLoad);

        // Table model (columns)
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Check Number", "Payment Date", "Amount"}, 0
        ) 
        {
            // Prevent editing cells
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false;
            }
        };

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 510, 200);
        contentPane.add(scrollPane);

        JLabel lblTotalText = new JLabel("Total Value:");
        lblTotalText.setBounds(20, 275, 100, 22);
        contentPane.add(lblTotalText);

        lblTotalValue = new JLabel("-");
        lblTotalValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotalValue.setBounds(130, 275, 200, 22);
        contentPane.add(lblTotalValue);

        // Button action
        btnLoad.addActionListener(e -> loadPaymentDetails());
    }

    private void loadPaymentDetails() 
    {

        String input = txtCustomerNumber.getText().trim();
        if (input.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Please enter customerNumber.");
            return;
        }

        int customerNumber;
        try 
        {
            customerNumber = Integer.parseInt(input);
        } 
        catch (NumberFormatException ex) 
        {
            JOptionPane.showMessageDialog(this, "customerNumber must be an integer.");
            return;
        }

        // Disable UI (optional)
        setControlsEnabled(false);
        lblTotalValue.setText("Loading...");

        // ✅ Background thread (important for Swing)
        new SwingWorker<PaymentResponse, Void>() 
        {

            @Override
            protected PaymentResponse doInBackground() throws Exception 
            {

                String url = BASE_URL + "?customerNumber=" + customerNumber;

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(
                        request, HttpResponse.BodyHandlers.ofString()
                );

                if (response.statusCode() != 200) {
                    throw new RuntimeException("HTTP " + response.statusCode()
                            + " : " + response.body());
                }

                return gson.fromJson(response.body(), PaymentResponse.class);
            }

            @Override
            protected void done() 
            {
                try {
                    PaymentResponse data = get();

                    // Load into JTable
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // clear old rows

                    if (data.items != null) {
                        for (PaymentItem item : data.items) {
                            model.addRow(new Object[]{
                                    item.checkNumber,
                                    item.paymentDate,
                                    item.amount
                            });
                        }
                    }

                    lblTotalValue.setText(String.format("%.2f", data.totalValue));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PaymentDetailsForm.this,
                            "Failed to load data.\n" + ex.getMessage());
                    lblTotalValue.setText("-");
                } finally {
                    setControlsEnabled(true);
                }
            }

        }.execute();
    }

    private void setControlsEnabled(boolean enabled) {
        txtCustomerNumber.setEnabled(enabled);
        // enable/disable other controls if you want
    }

    // ----------------------------
    // JSON mapping classes for Gson
    // ----------------------------

    private static class PaymentResponse 
    {
        int customerNumber;
        List<PaymentItem> items;
        double totalValue;
    }

    private static class PaymentItem 
    {
        String checkNumber;
       Date paymentDate;
        double amount;
    }
}
