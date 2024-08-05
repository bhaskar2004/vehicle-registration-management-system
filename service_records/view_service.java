package vehicle.registration.management.service_records;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class view_service extends JFrame {

    JTable table;

    public view_service() {
        setTitle("Service Records");
        setSize(800, 400);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Service ID", "Vehicle ID", "Service Date", "Cost", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        try {
            conn c = new conn();
            String query = "SELECT * FROM service_records";
            ResultSet rs = c.statement.executeQuery(query);

            while (rs.next()) {
                String serviceId = rs.getString("service_id");
                String vid = rs.getString("vid");
                String serviceDate = rs.getString("service_date");
                String cost = rs.getString("cost");
                String description = rs.getString("sdescription");

                Object[] data = {serviceId, vid, serviceDate, cost, description};
                tableModel.addRow(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching records: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new view_service();
    }
}
