package vehicle.registration.management.VEHICLE;

import net.proteanit.sql.DbUtils;
import vehicle.registration.management.conn;
import vehicle.registration.management.Main_class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_vehicle extends JFrame implements ActionListener {
    JTable table;
    Choice choiceVHE;
    JButton search_, print, update, back;

    public view_vehicle() {
        getContentPane().setBackground(new Color(255, 131, 122));
        setLayout(null);

        JLabel search = new JLabel("Search by vehicle id");
        search.setBounds(20, 20, 150, 20);
        add(search);

        choiceVHE = new Choice();
        choiceVHE.setBounds(180, 20, 150, 20);
        add(choiceVHE);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select vid from vehicle");
            while (resultSet.next()) {
                choiceVHE.add(resultSet.getString("vid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from vehicle");
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
            String query = "select * from vehicle where vid='" + choiceVHE.getSelectedItem() + "'";
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
            new update_vehicle(this); // Pass the current frame to update_vehicle
        } else if (e.getSource() == back) {
            setVisible(false);
            new veh_page(); // Navigate back to veh_page
        }
    }

    public static void main(String[] args) {
        new view_vehicle();
    }
}
