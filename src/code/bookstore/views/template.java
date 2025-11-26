package code.bookstore.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class template extends JFrame{
    CardLayout page_container;
    JPanel page;

    public template(){
        init_frame();
        init_navBar();
        init_pages();
        setVisible(true);
    }

    private void init_frame(){
        setSize(1000, 500);
        setTitle("Bala's Book Store");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int yes = JOptionPane.showConfirmDialog(template.this, "Logout ?","Logout ?", JOptionPane.YES_NO_OPTION);
                        if(yes == JOptionPane.YES_OPTION){
                            System.out.println("Close successfully");
                            System.exit(0); 
                        }
            }
        });
    }

    private void init_navBar(){
        // Nav bar
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1000,60));
        header.setBackground(Color.decode("#F5F5F5"));
        header.setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);

        // Title
        JLabel title = new JLabel("Bala's Book Store");
        title.setFont(new Font("Montserrat", Font.BOLD, 20));
        title.setBackground(Color.decode("#1E1E1E"));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        header.add(title, BorderLayout.WEST);

        title.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e){
            page_container.show(page, "Home");
            page.revalidate();
           } 
        });

        //Nav bar items
        JPanel nav_bar = new JPanel();
        nav_bar.setLayout(new FlowLayout(FlowLayout.RIGHT, 25, 20));
        nav_bar.setOpaque(false);
        nav_bar.add(nav_items("Browse"));
        nav_bar.add(nav_items("Cart"));
        nav_bar.add(nav_items("Login"));
        nav_bar.add(nav_items("Sign up"));

        header.add(nav_bar, BorderLayout.EAST);
    }

    // Handle panel switching
    private void init_pages(){
        // Set up panels
        page_container = new CardLayout(); 
        page = new JPanel(page_container);
        add(page, BorderLayout.CENTER);

        // Init homepage view
        homeview home = new homeview();
        JPanel home_view = home.init_panel();
        page.add(home_view, "Home");

        // Init browse page view
        browseview browse = new browseview();
        JPanel browse_view = browse.init_panel();
        page.add(browse_view, "Browse");

        signup signup = new signup();
        JPanel signup_view = signup.init_panel();
        page.add(signup_view, "Signup");

        login login = new login();
        JPanel login_view = login.init_panel();
        page.add(login_view, "Login");

        // Always shows homepage
        page_container.show(page, "Home");
    }

    private JLabel nav_items(String title){
        JLabel item = new JLabel(title);
        item.setFont(new Font("Montserrat", Font.BOLD, 15));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Handle mouse clicked
        item.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                switch (title) {
                    case "Browse":
                        page_container.show(page, "Browse");
                        page.revalidate();
                        break;
                    case "Cart":
                        break;
                    case "Login":
                        page_container.show(page, "Login");
                        page.revalidate();
                        break;
                    case "Sign up":
                        page_container.show(page, "Signup");
                        page.revalidate();
                        break;  
                    default:
                        break;
                }
            }
        });
        return item;
    }
}