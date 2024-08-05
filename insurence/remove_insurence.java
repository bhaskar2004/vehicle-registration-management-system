package vehicle.registration.management.insurence;

import vehicle.registration.management.conn;
import vehicle.registration.management.Main_class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class remove_insurence extends JFrame implements ActionListener {
    Choice choiceInsuranceId;
    JButton delete, back;

    public remove_insurence() {
        getContentPane().setBackground(new Color(255, 131, 122));
        setLayout(null);

        JLabel heading = new JLabel("Remove Insurance");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel lblInsuranceId = new JLabel("Insurance ID");
        lblInsuranceId.setBounds(50, 150, 150, 30);
        lblInsuranceId.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lblInsuranceId);

        choiceInsuranceId = new Choice();
        choiceInsuranceId.setBounds(200, 150, 150, 30);
        add(choiceInsuranceId);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT insuid FROM insurance");
            while (rs.next()) {
                choiceInsuranceId.add(rs.getString("insuid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        delete = new JButton("DELETE");
        delete.setBounds(450, 250, 150, 40);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("BACK");
        back.setBounds(250, 250, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(900, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            String id = choiceInsuranceId.getSelectedItem();
            try {
                conn c = new conn();
                String query = "DELETE FROM insurance WHERE insuid = '" + id + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Insurance record removed successfully");
                setVisible(false);
                new Main_class(); // Navigate back to Main_class (adjust as needed)
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error removing insurance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Main_class(); // Navigate back to Main_class (adjust as needed)
        }
    }

    public static void main(String[] args) {
        new remove_insurence();
    }
}
