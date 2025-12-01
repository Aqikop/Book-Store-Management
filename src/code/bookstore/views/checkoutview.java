package code.bookstore.views;

import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;

import code.bookstore.controllers.checkout_controller;
import code.bookstore.models.customer;
import code.bookstore.models.shippingtype;
import code.bookstore.models.shoppingcart;
import code.bookstore.models.order;
import code.bookstore.utils.sessionManager;

public class checkoutview {
    private CardLayout page_container;
    private JPanel page;
    private checkout_controller checkoutController;
    private JPanel checkoutPanel;
    
    // Form fields
    private JTextField fname_in;
    private JTextField lname_in;
    private JTextField address_in;
    private JTextField city_in;
    private JTextField zip_in;
    private JTextField state_in;
    private JTextField phone_in;
    private JTextField cc_num_in;
    private JComboBox<String> shipping_combo;
    
    // Current user and shipping price
    private Map<String, Object> curr_cus_info;
    private double curr_ship_price = 4.99;

    public checkoutview(CardLayout page_container, JPanel page, checkout_controller controller) {
        this.page_container = page_container;
        this.page = page;
        this.checkoutController = controller;
    }

    public JPanel init_panel(){
        checkoutPanel = new JPanel(new BorderLayout());
        refreshCheckoutView();
        return checkoutPanel;
    }

    public void refreshCheckoutView(){
        checkoutPanel.removeAll();
        
        checkoutPanel.setBackground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#e0e0e0")),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
        JLabel header_text = new JLabel("Review order");
        header_text.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        header_text.setFont(new Font("Lato", Font.BOLD, 18));
        header.add(header_text, BorderLayout.WEST);

        JPanel main_content = new JPanel(new BorderLayout());
        main_content.setBackground(Color.WHITE);

        JPanel left_side = new JPanel(new BorderLayout());
        left_side.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<Map<String, Object>> checkout_items = checkoutController.get_checkout_items();
        
        System.out.println("Refreshing checkout - Items count: " + checkout_items.size());

        JPanel review = new JPanel();
        review.setLayout(new BoxLayout(review, BoxLayout.Y_AXIS));
        review.setBackground(Color.WHITE);
        review.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        if(checkout_items.isEmpty()) {
            review.setPreferredSize(new Dimension(500, 200));
            JLabel no_items = new JLabel("No items in checkout");
            no_items.setFont(new Font("Lato", Font.BOLD, 18));
            no_items.setForeground(Color.GRAY);
            no_items.setHorizontalAlignment(JLabel.CENTER);
            review.add(Box.createVerticalGlue());
            review.add(no_items);
            review.add(Box.createVerticalGlue());
        } else {
            review.setPreferredSize(new Dimension(500, 180 * checkout_items.size()));
            
            for(int i = 0; i < checkout_items.size(); i++){
                Map<String, Object> book = checkout_items.get(i);
                JPanel book_entry = create_book_entry(book);
                review.add(book_entry);

                if(i < checkout_items.size() - 1){
                    review.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
        }
        
        JPanel pay_info_panel = payment_info();
        pay_info_panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 0, 0, 0), 
            pay_info_panel.getBorder()
        ));

        JPanel review_payment = new JPanel();
        review_payment.setLayout(new BoxLayout(review_payment, BoxLayout.Y_AXIS));
        review_payment.setBackground(Color.WHITE);
        review_payment.add(review);
        review_payment.add(pay_info_panel);

        JScrollPane scroll_bar = new JScrollPane(review_payment);
        scroll_bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_bar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_bar.getVerticalScrollBar().setUnitIncrement(16);
        scroll_bar.getVerticalScrollBar().setBlockIncrement(50);
        scroll_bar.setPreferredSize(new Dimension(600, 500));
        scroll_bar.setBorder(null);

        left_side.add(scroll_bar, BorderLayout.CENTER);

        JPanel order_summary = createOrderSummary(checkout_items);

        main_content.add(left_side, BorderLayout.CENTER);
        main_content.add(order_summary, BorderLayout.EAST);

        checkoutPanel.add(header, BorderLayout.NORTH);
        checkoutPanel.add(main_content, BorderLayout.CENTER);
        
        checkoutPanel.revalidate();
        checkoutPanel.repaint();
    }

    private JPanel createOrderSummary(List<Map<String, Object>> checkout_items) {
        JPanel order_summary = new JPanel(new BorderLayout());
        order_summary.setPreferredSize(new Dimension(280, 0));
        order_summary.setBackground(Color.WHITE);
        order_summary.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));
        JLabel summary_text = new JLabel("Order Summary");
        summary_text.setFont(new Font("Lato", Font.BOLD, 18));
        summary_text.setForeground(Color.decode("#2c3e50"));
        summary_text.setBorder(BorderFactory.createEmptyBorder(0 ,0, 10, 0));
        order_summary.add(summary_text, BorderLayout.NORTH);
        
        JPanel summary_content = new JPanel();
        summary_content.setLayout(new BoxLayout(summary_content, BoxLayout.Y_AXIS));
        summary_content.setOpaque(false);

        double subtotal = calculateSubtotal(checkout_items);
        double total = subtotal + curr_ship_price;

        summary_content.add(price_row("Item (" + checkout_items.size() + "):", "$" + String.format("%.2f", subtotal)));
        summary_content.add(price_row("Shipping", "$" + String.format("%.2f", curr_ship_price)));

        JSeparator separator = new JSeparator();
        separator.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        summary_content.add(separator);

        JPanel total_row = new JPanel(new BorderLayout());
        total_row.setOpaque(false);
        total_row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        total_row.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        JLabel total_label = new JLabel("Total:");
        total_label.setFont(new Font("Lato", Font.PLAIN, 15));
        total_label.setForeground(Color.BLACK);

        JLabel total_price = new JLabel("$" + String.format("%.2f", total));
        total_price.setFont(new Font("Lato", Font.BOLD, 15));
        total_price.setForeground(Color.decode("#e74c3c"));

        total_row.add(total_label, BorderLayout.WEST);
        total_row.add(total_price, BorderLayout.EAST);
        summary_content.add(total_row);

        order_summary.add(summary_content, BorderLayout.CENTER);

        JButton checkout_btn = new JButton("Complete Payment");
        checkout_btn.setMaximumSize(new Dimension(240, 40));
        checkout_btn.setBackground(Color.decode("#0651c9"));
        checkout_btn.setForeground(Color.WHITE);
        checkout_btn.setFont(new Font("Lato", Font.BOLD, 15));
        checkout_btn.setFocusPainted(false);
        checkout_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkout_btn.setBorder(BorderFactory.createEmptyBorder(10 ,15, 10, 15));
        checkout_btn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(processPayment()) {
                    JOptionPane.showMessageDialog(checkoutPanel,
                    "Transaction Complete! Thanks for shopping with us.",
                    "Success",
                    JOptionPane.PLAIN_MESSAGE
                    );
                page_container.show(page, "Home");
                page.revalidate();
                }
            }
        });

        order_summary.add(checkout_btn, BorderLayout.SOUTH);
        
        return order_summary;
    }

    private double calculateSubtotal(List<Map<String, Object>> items) {
        double subtotal = 0.0;
        for(Map<String, Object> item : items) {
            if(item.get("totalPrice") != null) {
                subtotal += (Double) item.get("totalPrice");
            }
        }
        return subtotal;
    }

    private JPanel payment_info(){
        JPanel payment_info = new JPanel(new BorderLayout());
        payment_info.setBackground(Color.WHITE);
        payment_info.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 3),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel header = new JLabel("Payment Information");
        header.setFont(new Font("Lato", Font.BOLD, 15));
        header.setForeground(Color.BLACK);
        
        payment_info.add(header, BorderLayout.NORTH);

        JPanel info_form = new JPanel(new GridBagLayout());
        info_form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Load current user data
        loadCurrentUserData();
        
        // First Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        JLabel fname = new JLabel("First Name");
        fname.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(fname, gbc);

        gbc.gridy = 1; gbc.gridwidth = 2;
        fname_in = new JTextField();
        fname_in.setPreferredSize(new Dimension(200, 30));
        fname_in.setFont(new Font("Lato", Font.PLAIN, 14));
        fname_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(fname_in, gbc);

        // Last Name
        gbc.gridx = 3; gbc.gridy = 0; gbc.gridwidth = 1;
        JLabel lname = new JLabel("Last Name");
        lname.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(lname, gbc);

        gbc.gridy = 1; gbc.gridwidth = 2;
        lname_in = new JTextField();
        lname_in.setPreferredSize(new Dimension(200, 30));
        lname_in.setFont(new Font("Lato", Font.PLAIN, 14));
        lname_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(lname_in, gbc);

        // Address
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        JLabel address = new JLabel("Address");
        address.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(address, gbc);

        gbc.gridy = 3; gbc.gridwidth = 5;
        address_in = new JTextField();
        address_in.setPreferredSize(new Dimension(400, 30));
        address_in.setFont(new Font("Lato", Font.PLAIN, 14));
        address_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(address_in, gbc);

        // City
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        JLabel city = new JLabel("City");
        city.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(city, gbc);

        gbc.gridy = 5; gbc.gridwidth = 2;
        city_in = new JTextField();
        city_in.setPreferredSize(new Dimension(200, 30));
        city_in.setFont(new Font("Lato", Font.PLAIN, 14));
        city_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(city_in, gbc);

        // State
        gbc.gridx = 3; gbc.gridy = 4; gbc.gridwidth = 1;
        JLabel state = new JLabel("State");
        state.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(state, gbc);

        gbc.gridy = 5; gbc.gridwidth = 1;
        state_in = new JTextField();
        state_in.setPreferredSize(new Dimension(60, 30));
        state_in.setFont(new Font("Lato", Font.PLAIN, 14));
        state_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(state_in, gbc);

        // Zip Code
        gbc.gridx = 4; gbc.gridy = 4; gbc.gridwidth = 1;
        JLabel zip = new JLabel("Zip Code");
        zip.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(zip, gbc);

        gbc.gridy = 5; gbc.gridwidth = 1;
        zip_in = new JTextField();
        zip_in.setPreferredSize(new Dimension(100, 30));
        zip_in.setFont(new Font("Lato", Font.PLAIN, 14));
        zip_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(zip_in, gbc);

        // Phone Number
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 1;
        JLabel phone = new JLabel("Phone Number");
        phone.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(phone, gbc);

        gbc.gridy = 7; gbc.gridwidth = 2;
        phone_in = new JTextField();
        phone_in.setPreferredSize(new Dimension(200, 30));
        phone_in.setFont(new Font("Lato", Font.PLAIN, 14));
        phone_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(phone_in, gbc);

        // Credit Card Number
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 1;
        JLabel cc_num = new JLabel("Credit Card Number");
        cc_num.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(cc_num, gbc);

        gbc.gridy = 9; gbc.gridwidth = 3;
        cc_num_in = new JTextField();
        cc_num_in.setPreferredSize(new Dimension(300, 30));
        cc_num_in.setFont(new Font("Lato", Font.PLAIN, 14));
        cc_num_in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0"), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
        info_form.add(cc_num_in, gbc);

        // Shipping Types
        gbc.gridx = 3; gbc.gridy = 6; gbc.gridwidth = 1;
        JLabel shipping_types = new JLabel("Shipping Types");
        shipping_types.setFont(new Font("Lato", Font.BOLD, 13));
        info_form.add(shipping_types, gbc);

        gbc.gridy = 7; gbc.gridwidth = 2;
        Map<String, Double> shippingOptions = shippingtype.get_shipping_types();
        String[] shipTypes = shippingOptions.keySet().toArray(new String[0]);
        
        if(shipTypes.length == 0) {
            shipTypes = new String[]{"Standard", "Express"};
        }

        shipping_combo = new JComboBox<>(shipTypes);
        shipping_combo.setFont(new Font("Lato", Font.PLAIN, 13));
        shipping_combo.addActionListener(e -> {
            String selectedType = (String) shipping_combo.getSelectedItem();
            if(selectedType != null) {
                if(shippingOptions.containsKey(selectedType)) {
                    curr_ship_price = shippingOptions.get(selectedType);
                } else {
                    curr_ship_price = 4.99;
                }
                System.out.println("Selected shipping: " + selectedType + " - Price: $" + curr_ship_price);
                refreshCheckoutView();
            }
        });
        info_form.add(shipping_combo, gbc);

        populateFormFields();

        payment_info.add(info_form, BorderLayout.CENTER);
        return payment_info;
    }

    // Load current user data
    private void loadCurrentUserData() {
        sessionManager session = sessionManager.get_inst();
        
        if(session.is_user_login()) {
            String userEmail = session.get_curr_user_email();
            
            if(userEmail != null) {
                curr_cus_info = customer.find_info_by_email(userEmail);
            }
        }
    }

    private void populateFormFields() {
        if(curr_cus_info != null && !curr_cus_info.isEmpty()) {
            // Split full name
            String fullName = (String) curr_cus_info.get("customerName");
            if(fullName != null && !fullName.isEmpty()) {
                String[] nameParts = fullName.split(" ", 2);
                fname_in.setText(nameParts.length > 0 ? nameParts[0] : "");
                lname_in.setText(nameParts.length > 1 ? nameParts[1] : "");
            }
            
            if(curr_cus_info.get("address") != null) {
                address_in.setText((String) curr_cus_info.get("address"));
            }
            if(curr_cus_info.get("city") != null) {
                city_in.setText((String) curr_cus_info.get("city"));
            }
            if(curr_cus_info.get("zip") != null) {
                zip_in.setText((String) curr_cus_info.get("zip"));
            }
            if(curr_cus_info.get("state") != null) {
                state_in.setText((String) curr_cus_info.get("state"));
            }
            if(curr_cus_info.get("phoneNumber") != null) {
                phone_in.setText((String) curr_cus_info.get("phoneNumber"));
            }
            if(curr_cus_info.get("creditCard") != null) {
                cc_num_in.setText((String) curr_cus_info.get("creditCard"));
            }
            
        } else {

        }
    }

    private boolean processPayment() {
        try {
            sessionManager session = sessionManager.get_inst();
            
            if(!session.is_user_login()) {
                JOptionPane.showMessageDialog(checkoutPanel, 
                    "Please login to complete checkout.", "Login Required", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }

            String firstName = fname_in.getText().trim();
            String lastName = lname_in.getText().trim();
            String address = address_in.getText().trim();
            String city = city_in.getText().trim();
            String zip = zip_in.getText().trim();
            String state = state_in.getText().trim();
            String phone = phone_in.getText().trim();
            String creditCard = cc_num_in.getText().trim();

            // Validate fields
            if(firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || 
               city.isEmpty() || zip.isEmpty() || state.isEmpty() ||
               phone.isEmpty() || creditCard.isEmpty()) {
                JOptionPane.showMessageDialog(checkoutPanel, 
                    "Please fill in all fields.", "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            //Get checkout items
            List<Map<String, Object>> checkout_items = checkoutController.get_checkout_items();
            if(checkout_items.isEmpty()){
                JOptionPane.showMessageDialog(checkoutPanel,
                    "No items to checkout", 
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Update customer info
            String customerId = session.get_curr_user_id();
            boolean updateSuccess = customer.update_cus_info(customerId, firstName, lastName, 
                address, city, zip, state, phone, creditCard);
            
            if(!updateSuccess) {
                JOptionPane.showMessageDialog(checkoutPanel, 
                    "Payment processed failed!", 
                    "Payment Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            int shopping_cart_id = shoppingcart.save_checkout_items(checkout_items);
            if(shopping_cart_id == 0){
                JOptionPane.showMessageDialog(checkoutPanel,
                    "Error saving shopping cart",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Create order
            String selectedShipping = (String) shipping_combo.getSelectedItem();
            int orderId = order.create_order(customerId, selectedShipping, shopping_cart_id);
            
            if(orderId == 0) {
                JOptionPane.showMessageDialog(checkoutPanel, 
                    "Error creating order.", "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Store order ID for receipt view
            session.set_order_id(orderId);
            
            JOptionPane.showMessageDialog(checkoutPanel, 
                "Payment processed successfully!\nOrder #" + orderId + " created.", 
                "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
            

        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(checkoutPanel, 
                "Error processing payment: " + e.getMessage(), "Payment Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private JPanel price_row(String row, String price){
        JPanel price_row = new JPanel(new BorderLayout());
        price_row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        price_row.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        price_row.setOpaque(false);

        JLabel row_name = new JLabel(row);
        row_name.setFont(new Font("Lato", Font.PLAIN, 13));
        row_name.setForeground(Color.BLACK);

        JLabel row_price = new JLabel(price);
        row_price.setFont(new Font("Lato", Font.PLAIN, 13));
        row_price.setForeground(Color.BLACK);

        price_row.add(row_name, BorderLayout.WEST);
        price_row.add(row_price, BorderLayout.EAST);

        return price_row;
    }

    private JPanel create_book_entry(Map<String, Object> book){
        JPanel book_entry = new JPanel(new BorderLayout());
        book_entry.setBackground(Color.decode("#ffffff"));
        book_entry.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#e0e0e0")),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        book_entry.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        book_entry.setPreferredSize(new Dimension(0, 170));

        JLabel book_cover_label = new JLabel();
        try{
            BufferedImage book_cover = ImageIO.read(new File("src\\resources\\images\\"));
            Image scaled = book_cover.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            book_cover_label.setIcon(new ImageIcon(scaled));
        } catch(Exception e){
            book_cover_label.setText("No img available");
            book_cover_label.setPreferredSize(new Dimension(150, 110));
            book_cover_label.setHorizontalAlignment(JLabel.CENTER);
            book_cover_label.setVerticalAlignment(JLabel.CENTER);
            book_cover_label.setOpaque(false);
        }
        book_cover_label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        JPanel book_info = new JPanel();
        book_info.setLayout(new BoxLayout(book_info, BoxLayout.Y_AXIS));
        book_info.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        book_info.setOpaque(false);

        JLabel book_title = new JLabel((String) book.get("bookName"));
        book_title.setFont(new Font("Lato", Font.PLAIN, 25));
        book_title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel book_author = new JLabel("by " + (String) book.get("authorName"));
        book_author.setFont(new Font("Lato", Font.ITALIC, 14));
        book_author.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel book_price = new JLabel("$" + String.format("%.2f", (Double) book.get("totalPrice")));
        book_price.setFont(new Font("Lato", Font.BOLD, 25));
        book_price.setForeground(Color.decode("#26ed5b"));
        book_price.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel quantity_display = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        quantity_display.setAlignmentX(Component.LEFT_ALIGNMENT);
        quantity_display.setOpaque(false);

        JLabel quantity_text = new JLabel("Quantity: " + book.get("quantity"));
        quantity_text.setFont(new Font("Lato", Font.PLAIN, 14));
        quantity_display.add(quantity_text);

        book_info.add(book_title);
        book_info.add(book_author);
        book_info.add(book_price);
        book_info.add(quantity_display);

        book_entry.add(book_cover_label, BorderLayout.WEST);
        book_entry.add(book_info, BorderLayout.CENTER);

        return book_entry;
    }
}