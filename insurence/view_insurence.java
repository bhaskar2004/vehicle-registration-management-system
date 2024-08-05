package vehicle.registration.management.insurence;

import net.proteanit.sql.DbUtils;
import vehicle.registration.management.conn;
import vehicle.registration.management.Main_class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_insurence extends JFrame implements ActionListener {
    JTable table;
    Choice choiceInsuranceId;
    JButton search_, print, update, back;

    public view_insurence() {
        getContentPane().setBackground(new Color(255, 131, 122));
        setLayout(null);

        JLabel search = new JLabel("Search by Insurance ID");
        search.setBounds(20, 20, 150, 20);
        add(search);

        choiceInsuranceId = new Choice();
        choiceInsuranceId.setBounds(180, 20, 150, 20);
        add(choiceInsuranceId);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT insuid FROM insurance");
            while (resultSet.next()) {
                choiceInsuranceId.add(resultSet.getString("insuid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM insurance");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0, 100, 900, 600);
        add(jp);

        search_ = new JButton("Search");
        search_.setBounds(20, 70, 80, 20);
        search_.addActionListener(this);
        add(search_);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search_) {
            String query = "SELECT * FROM insurance WHERE insuid='" + choiceInsuranceId.getSelectedItem() + "'";
            try {
                conn c = new conn();
                ResultSet resultSet = c.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == update) {
            setVisible(false);
            new update_insurence(this); // Pass the current frame to update_insurance
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class(); // Navigate back to Main_class (adjust as needed)
        }
    }

    public static void main(String[] args) {
        new view_insurence();
    }
}
