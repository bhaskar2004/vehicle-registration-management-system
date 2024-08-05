package vehicle.registration.management.VEHICLE;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class remove_vehicle extends JFrame implements ActionListener {
    Choice choicevheid;
    JButton delete, back;

    public remove_vehicle() {

        JLabel label = new JLabel("vehicle id");
        label.setBounds(50, 50, 100, 30);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(label);

        choicevheid = new Choice();
        choicevheid.setBounds(200, 50, 150, 30);
        add(choicevheid);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from vehicle");
            while (resultSet.next()) {
                choicevheid.add(resultSet.getString("vid"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labellpno = new JLabel("lpno");
        labellpno.setBounds(50, 100, 100, 30);
        labellpno.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labellpno);

        JLabel textlpno = new JLabel();
        textlpno.setBounds(200, 100, 100, 30);
        add(textlpno);

        JLabel labelcname = new JLabel("cname");
        labelcname.setBounds(50, 150, 100, 30);
        labelcname.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelcname);

        JLabel textcname = new JLabel();
        textcname.setBounds(200, 150, 100, 30);
        add(textcname);

        JLabel labelmodel = new JLabel("Model");
        labelmodel.setBounds(50, 200, 100, 30);
        labelmodel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelmodel);

        JLabel textmodel = new JLabel();
        textmodel.setBounds(200, 200, 100, 30);
        add(textmodel);

        JLabel labelvtype = new JLabel("Vtype");
        labelvtype.setBounds(50, 250, 100, 30);
        labelvtype.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelvtype);

        JLabel textvtype = new JLabel();
        textvtype.setBounds(200, 250, 100, 30);
        add(textvtype);

        JLabel labelvyear = new JLabel("Vyear");
        labelvyear.setBounds(50, 300, 100, 30);
        labelvyear.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(labelvyear);

        JLabel textvyear = new JLabel();
        textvyear.setBounds(200, 300, 100, 30);
        add(textvyear);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from vehicle where vid='" + choicevheid.getSelectedItem() + "'");
            while (resultSet.next()) {
                textlpno.setText(resultSet.getString("lpno"));
                textcname.setText(resultSet.getString("cname"));
                textmodel.setText(resultSet.getString("model"));
                textvtype.setText(resultSet.getString("vtype"));
                textvyear.setText(resultSet.getString("vyear"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        choicevheid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery("select * from vehicle where vid='" + choicevheid.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        textlpno.setText(resultSet.getString("lpno"));
                        textcname.setText(resultSet.getString("cname"));
                        textmodel.setText(resultSet.getString("model"));
                        textvtype.setText(resultSet.getString("vtype"));
                        textvyear.setText(resultSet.getString("vyear"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(80, 400, 100, 30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 400, 100, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400, 90, 200, 200);
        add(img);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        img.setBounds(0, 0, 1120, 630);
        add(imgg);

        setSize(800, 500);
        setLocation(300, 150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                conn c = new conn();
                String selectedVid = choicevheid.getSelectedItem();

                // Check for dependent records
                String checkQuery = "SELECT COUNT(*) FROM service_records WHERE vid = '" + selectedVid + "'";
                ResultSet rs = c.statement.executeQuery(checkQuery);
                if (rs.next() && rs.getInt(1) > 0) {
                    // Notify the user that there are dependent records
                    JOptionPane.showMessageDialog(null, "Cannot delete vehicle record because there are dependent service records.");
                } else {
                    // Proceed with deletion
                    String deleteVehicleQuery = "DELETE FROM vehicle WHERE vid = '" + selectedVid + "'";
                    c.statement.executeUpdate(deleteVehicleQuery);
                    JOptionPane.showMessageDialog(null, "Vehicle deleted successfully.");
                    setVisible(false);
                    new veh_page();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new remove_vehicle();
    }
}
