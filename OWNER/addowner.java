package vehicle.registration.management.OWNER;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addowner extends JFrame implements ActionListener {
    JTextField toid, toname, taddress, tphno, tlino;
    JButton add, back;
    JFrame parentFrame;

    public addowner(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Add Owner Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Owner ID
        JLabel oid = new JLabel("Owner ID");
        oid.setBounds(50, 150, 150, 30);
        oid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(oid);

        toid = new JTextField();
        toid.setBounds(200, 150, 150, 30);
        toid.setBackground(new Color(177, 252, 197));
        add(toid);

        // Owner Name
        JLabel oname = new JLabel("Owner Name");
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

        // Phone Number
        JLabel phno = new JLabel("Phone No");
        phno.setBounds(50, 300, 150, 30);
        phno.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(phno);

        tphno = new JTextField();
        tphno.setBounds(200, 300, 150, 30);
        tphno.setBackground(new Color(177, 252, 197));
        add(tphno);

        // License Number
        JLabel lino = new JLabel("License No");
        lino.setBounds(50, 350, 150, 30);
        lino.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lino);

        tlino = new JTextField();
        tlino.setBounds(200, 350, 150, 30);
        tlino.setBackground(new Color(177, 252, 197));
        add(tlino);

        add = new JButton("ADD");
        add.setBounds(450, 450, 150, 40);
        add.setBackground(Color.black);
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(250, 450, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String oid = toid.getText();
            String oname = toname.getText();
            String address = taddress.getText();
            String phno = tphno.getText();
            String lino = tlino.getText();

            try {
                conn c = new conn();
                String query = "INSERT INTO owner (oid, oname, address, phno, lino) VALUES ('"
                        + oid + "','" + oname + "','" + address + "','" + phno + "','" + lino + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Owner details added successfully.");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous page
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error adding owner details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous page
        }
    }

    public static void main(String[] args) {
        new addowner(new JFrame());
    }
}
