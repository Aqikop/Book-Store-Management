package code.bookstore.views;

import javax.swing.*;
import javax.swing.FocusManager;
import java.awt.*;

public class signup {
    public JPanel init_panel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.setBackground(Color.decode("#eeeeee"));

        JLabel element3 = new JLabel("Create account");
        element3.setFont(new Font("Lato", Font.BOLD, 35));
        element3.setBackground(Color.decode("#ffffff"));
        element3.setForeground(Color.decode("#737674"));
        gbc.insets = new Insets(10, 10, 5, 10);
        panel.add(element3, gbc);

        JLabel element12 = new JLabel("<html>Already a member ? <font color='#0c08d1'>Log In</font></html>");
        element12.setFont(new Font("Lato", Font.BOLD, 14));
        element12.setForeground(Color.decode("#1b1b1b"));
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 10, 10);
        panel.add(element12, gbc);

        JTextField email_input = new JTextField(""){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
                    Graphics2D g2 = (Graphics2D)g.create();
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    g2.setColor(Color.decode("#999999"));
                    g2.drawString("Email", 10, 15);
                    g2.dispose();
                }
            }
        };
        email_input.setPreferredSize(new Dimension(300, 30));
        email_input.setFont(new Font("Lato", Font.BOLD, 14));
        email_input.setBackground(Color.decode("#ffffff"));
        email_input.setForeground(Color.decode("#737674"));
        //OnFocusEventHelper.setOnFocusText(email_input, "Email", Color.decode("#1b1b1b"),   Color.decode("#737674"));
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 20, 10, 20);
        panel.add(email_input, gbc);

        JTextField pass_input = new JTextField(""){
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
                    Graphics2D g2 = (Graphics2D)g.create();
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    g2.setColor(Color.decode("#999999"));
                    g2.drawString("Password", 10, 15);
                    g2.dispose();
                }
            }
        };
        pass_input.setPreferredSize(new Dimension(300,30));
        pass_input.setFont(new Font("Lato", Font.BOLD, 14));
        pass_input.setBackground(Color.decode("#ffffff"));
        pass_input.setForeground(Color.decode("#737674"));
        //OnFocusEventHelper.setOnFocusText(pass_input, "Password", Color.decode("#1b1b1b"),   Color.decode("#737674"));
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 10, 20);
        panel.add(pass_input, gbc);

        JButton signup_button = new JButton("Sign Up");
        signup_button.setBackground(Color.decode("#ffffff"));
        signup_button.setForeground(Color.decode("#1b1b1b"));
        signup_button.setFont(new Font("Lato", Font.BOLD, 14));
        signup_button.setFocusPainted(false);
        //OnClickEventHelper.setOnClickColor(signup_button, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
        gbc.gridy = 4;
        panel.add(signup_button, gbc);

        return panel;
    }
}
