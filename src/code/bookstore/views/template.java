package code.bookstore.views;

import java.awt.*;
import javax.swing.*;

public class template {
    public void init_frame(){
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setTitle("Bala's Book Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Nav bar
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000,60));
        header.setBackground(Color.decode("#03fcdb"));
        header.setLayout(new BorderLayout());
        frame.add(header, BorderLayout.NORTH);

        // Title
        JLabel name = new JLabel("Bala's Book Store");
        name.setFont(new Font("Montserrat", Font.BOLD, 20));
        name.setBackground(Color.BLACK);
        name.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        header.add(name, BorderLayout.WEST);

        //Nav bar items
        JPanel nav_bar = new JPanel();
        nav_bar.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 20));
        nav_bar.setOpaque(false);
        JLabel item1 = new JLabel("Home");
        item1.setFont(new Font("Montserrat", Font.BOLD, 15));
        JLabel item2 = new JLabel("Browse");
        item2.setFont(new Font("Montserrat", Font.BOLD, 15));
        JLabel item3 = new JLabel("Logout");
        item3.setFont(new Font("Montserrat", Font.BOLD, 15));
        nav_bar.add(item1);
        nav_bar.add(item2);
        nav_bar.add(item3);

        header.add(nav_bar, BorderLayout.EAST);

        frame.setVisible(true);
    }
}