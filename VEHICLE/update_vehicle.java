package vehicle.registration.management.VEHICLE;

import vehicle.registration.management.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update_vehicle extends JFrame implements ActionListener {
    JTextField tlpno, tcname, tmodel, ttype, tyear;
    JButton update, back;
    Choice choiceVehicleId;
    JFrame parentFrame;

    public update_vehicle(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Update Vehicle Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        // Vehicle ID
        JLabel vid = new JLabel("Vehicle ID");
        vid.setBounds(50, 150, 150, 30);
        vid.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vid);

        choiceVehicleId = new Choice();
        choiceVehicleId.setBounds(200, 150, 150, 30);
        add(choiceVehicleId);

        // Populate Choice box with Vehicle IDs
        try {
            conn c = new conn();
            String query = "SELECT vid FROM vehicle";
            ResultSet rs = c.statement.executeQuery(query);
            while (rs.next()) {
                choiceVehicleId.add(rs.getString("vid"));
            }
            if (choiceVehicleId.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "No Vehicle IDs found", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching vehicle IDs: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Plate number
        JLabel lpno = new JLabel("Plate No");
        lpno.setBounds(50, 200, 150, 30);
        lpno.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(lpno);

        tlpno = new JTextField();
        tlpno.setBounds(200, 200, 150, 30);
        tlpno.setBackground(new Color(177, 252, 197));
        add(tlpno);

        // Company name
        JLabel cname = new JLabel("Company Name");
        cname.setBounds(50, 250, 150, 30);
        cname.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(cname);

        tcname = new JTextField();
        tcname.setBounds(200, 250, 150, 30);
        tcname.setBackground(new Color(177, 252, 197));
        add(tcname);

        // Vehicle model
        JLabel model = new JLabel("Vehicle Model");
        model.setBounds(50, 300, 150, 30);
        model.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(model);

        tmodel = new JTextField();
        tmodel.setBounds(200, 300, 150, 30);
        tmodel.setBackground(new Color(177, 252, 197));
        add(tmodel);

        // Vehicle type
        JLabel vtype = new JLabel("Vehicle Type");
        vtype.setBounds(50, 350, 150, 30);
        vtype.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vtype);

        ttype = new JTextField();
        ttype.setBounds(200, 350, 150, 30);
        ttype.setBackground(new Color(177, 252, 197));
        add(ttype);

        // Vehicle year
        JLabel vyear = new JLabel("Year");
        vyear.setBounds(50, 400, 150, 30);
        vyear.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(vyear);

        tyear = new JTextField();
        tyear.setBounds(200, 400, 150, 30);
        tyear.setBackground(new Color(177, 252, 197));
        add(tyear);

        update = new JButton("UPDATE");
        update.setBounds(450, 550, 150, 40);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        back = new JButton("BACK");
        back.setBounds(250, 550, 150, 40);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        // Event Listener for Choice box to populate text fields
        choiceVehicleId.addItemListener(e -> {
            String selectedId = choiceVehicleId.getSelectedItem();
            String query = "SELECT * FROM vehicle WHERE vid = '" + selectedId + "'";
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery(query);
                if (rs.next()) {
                    tlpno.setText(rs.getString("lpno"));
                    tcname.setText(rs.getString("cname"));
                    tmodel.setText(rs.getString("model"));
                    ttype.setText(rs.getString("vtype"));
                    tyear.setText(rs.getString("vyear"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading vehicle details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            String vid = choiceVehicleId.getSelectedItem();
            String lpno = tlpno.getText();
            String cname = tcname.getText();
            String model = tmodel.getText();
            String vtype = ttype.getText();
            String vyear = tyear.getText();

            try {
                conn c = new conn();
                String query = "UPDATE vehicle SET lpno='" + lpno + "', cname='" + cname + "', model='" + model + "', vtype='" + vtype + "', vyear='" + vyear + "' WHERE vid='" + vid + "'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                parentFrame.setVisible(true); // Show the previous vehicle page
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating vehicle details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            parentFrame.setVisible(true); // Show the previous vehicle page
        }
    }

    public static void main(String[] args) {
        new update_vehicle(new JFrame());
    }
}
