package vehicle.registration.management.OWNER;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update_owner extends JFrame implements ActionListener {
    JTextField toname, taddress, tphno, tlino;
    JButton update, back;
    Choice choiceOwnerId;
    JFrame parentFrame;

    public update_owner(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Update Owner Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Owner ID
        JLabel oid = new JLabel("Owner ID");
        oid.setBounds(50, 150, 150, 30);
        oid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(oid);

        choiceOwnerId = new Choice();
        choiceOwnerId.setBounds(200, 150, 150, 30);
        add(choiceOwnerId);

        // Populate Choice box with Owner IDs
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT oid FROM owner");
            while (rs.next()) {
                choiceOwnerId.add(rs.getString("oid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Name
        JLabel oname = new JLabel("Name");
        oname.setBounds(50, 200, 150, 30);
        oname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(oname);

        toname = new JTextField();
        toname.setBounds(200, 200, 150, 30);
        toname.setBackground(new Color(177, 252, 197));
        add(toname);

        // Address
        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 150, 30);
        address.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(200, 250, 150, 30);
        taddress.setBackground(new Color(177, 252, 197));
        add(taddress);

        // Phone No
        JLabel phno = new JLabel("Phone No");
        phno.setBounds(50, 300, 150, 30);
        phno.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phno);

        tphno = new JTextField();
        tphno.setBounds(200, 300, 150, 30);
        tphno.setBackground(new Color(177, 252, 197));
        add(tphno);

        // License No
        JLabel lino = new JLabel("License No");
        lino.setBounds(50, 350, 150, 30);
        lino.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lino);

        tlino = new JTextField();
        tlino.setBounds(200, 350, 150, 30);
        tlino.setBackground(new Color(177, 252, 197));
        add(tlino);

        update = new JButton("UPDATE");
        update.setBounds(450, 450, 150, 40);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        back = new JButton("BACK");
        back.setBounds(250, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        // Event Listener for Choice box to populate text fields
        choiceOwnerId.addItemListener(e -> {
            String selectedId = choiceOwnerId.getSelectedItem();
            String query = "SELECT * FROM owner WHERE oid = '" + selectedId + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(query);
                if (rs.next()) {
                    toname.setText(rs.getString("oname"));
                    taddress.setText(rs.getString("address"));
                    tphno.setText(rs.getString("phno"));
                    tlino.setText(rs.getString("lino"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        setLayout(null);
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String oid = choiceOwnerId.getSelectedItem();
            String oname = toname.getText();
            String address = taddress.getText();
            String phno = tphno.getText();
            String lino = tlino.getText();

            try {
                conn c = new conn();
                String query = "UPDATE owner SET oname='" + oname + "', address='" + address + "', phno='" + phno + "', lino='" + lino + "' WHERE oid='" + oid + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous owner page
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating owner: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous owner page
        }
    }

    public static void main(String[] args) {
        new update_owner(new JFrame());
    }
}
