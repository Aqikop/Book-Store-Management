package code.bookstore.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import code.bookstore.controllers.user_controller;

public class template extends JFrame{
    CardLayout page_container;
    JPanel page;
    user_controller userController;
    checkoutview checkoutView; // Add reference to checkout view

    public template(){
        this.userController = new user_controller();
        init_frame();
        init_navBar();
        init_pages();
        setVisible(true);
    }

    private void init_frame(){
        setSize(1000, 500);
        setTitle("Bala's Book Store");
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int yes = JOptionPane.showConfirmDialog(template.this, "Do you want to exit the app  ?","Logout ?", JOptionPane.YES_NO_OPTION);
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
        nav_bar.add(nav_items("Manage"));
        nav_bar.add(nav_items("Login"));
        nav_bar.add(nav_items("Logout"));

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
        browseview browse = new browseview(page_container, page);
        JPanel browse_view = browse.init_panel();
        page.add(browse_view, "Browse");

        //Init checkout page view
        checkoutView = new checkoutview(page_container, page, browse.getCheckoutController());
        JPanel checkout_view = checkoutView.init_panel();
        page.add(checkout_view, "Cart");

        // Link the views so browse can refresh checkout
        browse.setCheckoutView(checkoutView);

        signup signup = new signup(page_container, page);
        JPanel signup_view = signup.init_panel();
        page.add(signup_view, "Signup");

        login login = new login(page_container, page);
        JPanel login_view = login.init_panel();
        page.add(login_view, "Login");

        managebooksview manage = new managebooksview(page_container, page);
        JPanel manage_view = manage.init_panel();
        page.add(manage_view, "Manage");

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
                        // Refresh checkout to show items
                        if(checkoutView != null) {
                            checkoutView.refreshCheckoutView();
                        }
                        page_container.show(page, "Cart");
                        page.revalidate();
                        break;
                    case "Login":
                        page_container.show(page, "Login");
                        page.revalidate();
                        break;
                    case "Sign up":
                        page_container.show(page, "Signup");
                        page.revalidate();
                        break; 
                    case "Logout":
                        if(userController.is_logged_in()){
                            int yes = JOptionPane.showConfirmDialog(
                            template.this,
                            "Do you want to log out?",
                            "Confirm logout ",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                            if(yes == JOptionPane.YES_OPTION){
                                userController.logout_user();

                                page_container.show(page, "Home");
                                page.revalidate();
                                break;
                            }
                        } else{
                            JOptionPane.showMessageDialog(template.this, "Please login first to logout");
                            break;
                        }
                    case "Manage":
                        page_container.show(page, "Manage");
                        page.revalidate();
                    default:
                        break;
                }
            }
        });
        return item;
    }
}