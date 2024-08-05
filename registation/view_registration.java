package vehicle.registration.management.registation;

import net.proteanit.sql.DbUtils;
import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class view_registration extends JFrame implements ActionListener {

    JTable table;
    Choice choiceRegistrationId;
    JButton search, print, back;

    public view_registration() {
        setTitle("View Registration");
        setLayout(null);
        getContentPane().setBackground(new Color(200, 250, 200));

        JLabel lblSelectId = new JLabel("Select Registration ID");
        lblSelectId.setBounds(20, 20, 200, 30);
        lblSelectId.setFont(new Font("Sans Serif", Font.BOLD, 15));
        add(lblSelectId);

        choiceRegistrationId = new Choice();
        choiceRegistrationId.setBounds(220, 20, 150, 30);
        add(choiceRegistrationId);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT rid FROM registration");
            while (rs.next()) {
                choiceRegistrationId.add(rs.getString("rid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 100, 750, 400);
        add(scrollPane);

        search = new JButton("Search");
        search.setBounds(20, 60, 100, 30);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(140, 60, 100, 30);
        print.addActionListener(this);
        add(print);

        back = new JButton("Back");
        back.setBounds(260, 60, 100, 30);
        back.addActionListener(this);
        add(back);

        setSize(800, 550);
        setLocation(250, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String selectedId = choiceRegistrationId.getSelectedItem();
            String query = "SELECT * FROM registration WHERE rid = '" + selectedId + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(query);

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No data found for the selected Registration ID.");
                } else {
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while fetching data.");
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while printing the table.");
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_registration();
    }
}
