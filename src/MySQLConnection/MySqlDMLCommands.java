package MySQLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class MySqlDMLCommands implements ActionListener{

    /**
     * <h1>Globals</h1>
     * The Necessary Globals are declared here
     * @author  Hareesh Rajendran
     * @version 1.0
     * @since   2021-12-23
     */
    private static final String connectionName = "root";
    private static final String connectionPassword = "password";
    private static JFrame frame;
    private static JPanel panel,userSelect;
    private static JLabel currName,currId;
    private static JTextField userName, currIdField, currNameField, currAddressField, currPhoneNumberField,currPriceField,currLocationField,currQuantityField;
    private static JPasswordField password;
    private static JButton executeOption;
    private static JRadioButton addRadio , updateRadio , deleteRadio,displayRadio;
    private static String currAddressValue,currLocationValue, currPhoneNumberValue,currNameValue;
    private static Integer currPriceValue,currIdValue,currQuantityValue;
    private static final Color black = new Color(0,0,0),white = new Color(255,255,255),green = new Color(132, 224, 158);

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * <h1>Backend</h1>
     * The Backend Starts Here
     * <p>
     * Customer , Product and Order are used
     * to communicate with Database and make modifications
     *
     *
     * @author  Hareesh Rajendran
     * @version 1.0
     * @since   2021-12-23
     */

    public static class Customer {

        String connectionName,connectionPassword;

        Customer(String connectionName,String connectionPassword){
            this.connectionName = connectionName;
            this.connectionPassword = connectionPassword;
        }
        private void insert(int id, String name, String address, String phoneNumber) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO "+ "customer" +" VALUES("+id+",'"+name+"','"+address+"','"+phoneNumber+"')";
            stmt.execute(insert);
            con.close();
            System.out.println("Successfully Inserted customer data with id:"+id);
        }
        private void update(int id, String name, String address, String phoneNumber) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String update = "UPDATE "+ "customer" +" SET NAME='"+name+"' ,address='"+address+"' ,phoneno='"+phoneNumber+"' WHERE idcustomer="+id;
            stmt.execute(update);
            con.close();
            System.out.println("Successfully Updated customer data with id:"+id);
        }
        private void delete(int id) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String delete = "DELETE FROM "+ "customer" +" WHERE idcustomer="+id;
            stmt.execute(delete);
            con.close();
            System.out.println("Successfully Deleted customer data with id:"+id);
        }
        private void display() throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String display = "SELECT * FROM "+ "customer";
            ResultSet res = stmt.executeQuery(display);

            while(res.next()){
                int idcustomer = res.getInt("idcustomer");
                String name = res.getString("name");
                String address = res.getString("address");
                String phoneno = res.getString("phoneno");

                System.out.println(idcustomer+"\t"+name+"\t"+address+"\t"+phoneno);
            }
            con.close();
            System.out.println("Data of all customers retrieved Successful...");
        }
    }

    public static class Order {

        String connectionName, connectionPassword;
        Random random = new Random();
        int randomOrderID = random.nextInt(1000);

        Order(String connectionName,String connectionPassword){
             this.connectionName = connectionName;
             this.connectionPassword = connectionPassword;
        }
        private void place(int quantity,String item) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO stock.order VALUES("+randomOrderID+","+quantity+","+"'"+item+"')";
            stmt.execute(insert);
            con.close();
            System.out.println("Order placed Successfully with order id:"+randomOrderID+"...");
        }
        private void cancel(int orderid) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "DELETE FROM stock.order WHERE orderid="+orderid;
            stmt.execute(insert);
            con.close();
            System.out.println("Order cancelled Successfully with order id:"+orderid+"...");
        }
        private void display() throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String display = "SELECT * FROM stock.order";
            ResultSet res = stmt.executeQuery(display);

            while(res.next()){
                int orderid = res.getInt("orderid");
                int quantity = res.getInt("quantity");
                String item = res.getString("item");

                System.out.println(orderid+"\t"+quantity+"\t"+item);
            }
            con.close();
            System.out.println("Data of all orders retrieved Successful...");
        }
    }

    public static class Product{

        String connectionName,connectionPassword;

        Product(String connectionName,String connectionPassword){
            this.connectionName = connectionName;
            this.connectionPassword = connectionPassword;
        }
        private void add(int id,String productName,int price,String location) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO PRODUCT VALUES("+id+",'"+productName+"',"+price+",'"+location+"')";
            stmt.execute(insert);
            con.close();
            System.out.println("Successfully Inserted product data with id:"+id);
        }
        private void update(int id,String productName,int price,String location) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String update = "UPDATE PRODUCT SET PRODUCTNAME='"+productName+"' ,price="+price+" ,location='"+location+"' WHERE productid="+id;
            stmt.execute(update);
            con.close();
            System.out.println("Successfully Updated product data with id:"+id);
        }
        private void delete(int id) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String update = "DELETE FROM PRODUCT WHERE PRODUCTID="+id;
            stmt.execute(update);
            con.close();
            System.out.println("Successfully Deleted product data with id:"+id);
        }
    }

    /**
     * <h1>Frontend</h1>
     * Frontend Built with Java Swing and AWT
     * Dark theme is maintained throughout with Green Accent
     * @author  Hareesh Rajendran
     * @version 1.0
     * @since   2021-12-23
     */

    public static void updateUser(String userName){

        frame.remove(panel);
        frame.remove(userSelect);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(null);
        userPanel.setBackground(black);

        JLabel welcomeUser = new JLabel("Welcome " + userName);
        welcomeUser.setBounds(10,20,180,25);
        welcomeUser.setForeground(white);
        userPanel.add(welcomeUser);

        addRadio=new JRadioButton("Add");
        addRadio.setBounds(10,50,100,30);
        addRadio.setBackground(black);
        addRadio.setForeground(white);
        addRadio.setFocusPainted(false);

        updateRadio=new JRadioButton("Update");
        updateRadio.setBounds(10,80,100,30);
        updateRadio.setBackground(black);
        updateRadio.setForeground(white);
        updateRadio.setFocusPainted(false);

        deleteRadio=new JRadioButton("Delete");
        deleteRadio.setBounds(10,110,100,30);
        deleteRadio.setBackground(black);
        deleteRadio.setForeground(white);
        deleteRadio.setFocusPainted(false);

        displayRadio=new JRadioButton("Display");
        displayRadio.setBounds(10,140,100,30);
        deleteRadio.setBackground(black);
        deleteRadio.setForeground(white);
        deleteRadio.setFocusPainted(false);

        currId = new JLabel("Enter Id");
        currId.setBounds(130,50,150,30);
        currId.setForeground(white);

        currIdField = new JTextField(100);
        currIdField.setBounds(280,50,150,30);
        userPanel.add(currIdField);

        currName = new JLabel("Enter Name");
        currName.setBounds(130,80,150,30);
        currName.setForeground(white);

        currNameField = new JTextField(100);
        currNameField.setBounds(280,80,150,30);
        userPanel.add(currNameField);

        JLabel currAddress = new JLabel("Enter Address");
        currAddress.setBounds(130,110,150,30);
        currAddress.setForeground(white);

        currAddressField = new JTextField(100);
        currAddressField.setBounds(280,110,150,30);
        userPanel.add(currAddressField);

        JLabel currPhoneNumber = new JLabel("Enter Phone Number");
        currPhoneNumber.setBounds(130,140,150,30);
        currPhoneNumber.setForeground(white);

        currPhoneNumberField = new JTextField(100);
        currPhoneNumberField.setBounds(280,140,150,30);
        userPanel.add(currPhoneNumberField);

        ButtonGroup bg=new ButtonGroup();
        bg.add(addRadio);
        bg.add(updateRadio);
        bg.add(deleteRadio);
        bg.add(displayRadio);

        executeOption=new JButton("Execute !");
        executeOption.setBounds(135,250,180,30);
        executeOption.setBackground(green);
        executeOption.setForeground(black);
        executeOption.setFocusPainted(false);
        executeOption.addActionListener(e -> {
            currIdValue = Integer.valueOf(currIdField.getText());
            currNameValue = currNameField.getText();
            currAddressValue = currAddressField.getText();
            currPhoneNumberValue = currPhoneNumberField.getText();
            Customer customer = new Customer(connectionName,connectionPassword);
            if(addRadio.isSelected()){
                try {
                    customer.insert(currIdValue,currNameValue,currAddressValue, currPhoneNumberValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("User Data Added! "+currIdValue+" , "+currNameValue+" , "+currAddressValue+" , "+ currPhoneNumberValue);
            }else if(updateRadio.isSelected()){
                try {
                    customer.update(currIdValue,currNameValue,currAddressValue, currPhoneNumberValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("User Data Updated! "+currIdValue+" , "+currNameValue+" , "+currAddressValue+" , "+ currPhoneNumberValue);
            }else if(deleteRadio.isSelected()){
                try {
                    customer.delete(currIdValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("User Data Deleted! "+currIdValue+" , "+currNameValue+" , "+currAddressValue+" , "+ currPhoneNumberValue);
            }else if(displayRadio.isSelected()){
                try {
                    customer.display();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        userPanel.add(currId);
        userPanel.add(currName);
        userPanel.add(currAddress);
        userPanel.add(currPhoneNumber);

        userPanel.add(addRadio);
        userPanel.add(updateRadio);
        userPanel.add(deleteRadio);
        userPanel.add(executeOption);

        frame.add(userPanel);
        frame.setVisible(true);
    }

    private static void manageProduct(){

        frame.remove(panel);

        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(null);
        adminPanel.setBackground(black);

        JLabel welcomeAdmin = new JLabel("Welcome Admin !");
        welcomeAdmin.setBounds(10,20,180,25);
        welcomeAdmin.setForeground(white);
        adminPanel.add(welcomeAdmin);

        addRadio=new JRadioButton("Add");
        addRadio.setBounds(10,50,100,30);
        addRadio.setBackground(black);
        addRadio.setForeground(white);
        addRadio.setFocusPainted(false);

        updateRadio=new JRadioButton("Update");
        updateRadio.setBounds(10,80,100,30);
        updateRadio.setBackground(black);
        updateRadio.setForeground(white);
        updateRadio.setFocusPainted(false);

        deleteRadio=new JRadioButton("Delete");
        deleteRadio.setBounds(10,110,100,30);
        deleteRadio.setBackground(black);
        deleteRadio.setForeground(white);
        deleteRadio.setFocusPainted(false);

        currId = new JLabel("Enter Id");
        currId.setBounds(130,50,150,30);
        currId.setForeground(white);

        currIdField = new JTextField(100);
        currIdField.setBounds(280,50,150,30);
        adminPanel.add(currIdField);

        currName = new JLabel("Enter Product Name");
        currName.setBounds(130,80,150,30);
        currName.setForeground(white);

        currNameField = new JTextField(100);
        currNameField.setBounds(280,80,150,30);
        adminPanel.add(currNameField);

        JLabel currPrice = new JLabel("Enter Price");
        currPrice.setBounds(130,110,150,30);
        currPrice.setForeground(white);

        currPriceField = new JTextField(100);
        currPriceField.setBounds(280,110,150,30);
        adminPanel.add(currPriceField);

        JLabel currLocation = new JLabel("Enter Location");
        currLocation.setBounds(130,140,150,30);

        currLocationField = new JTextField(100);
        currLocationField.setBounds(280,140,150,30);
        currLocation.setForeground(white);
        adminPanel.add(currLocationField);

        ButtonGroup bg=new ButtonGroup();
        bg.add(addRadio);
        bg.add(updateRadio);
        bg.add(deleteRadio);


        executeOption=new JButton("Execute !");
        executeOption.setBounds(135,250,180,30);
        executeOption.setBackground(green);
        executeOption.setForeground(black);
        executeOption.setFocusPainted(false);
        executeOption.addActionListener(e -> {
            currIdValue = Integer.valueOf(currIdField.getText());
            currNameValue = currNameField.getText();
            currPriceValue = Integer.valueOf(currPriceField.getText());
            currLocationValue = currLocationField.getText();
            Product product = new Product(connectionName,connectionPassword);

            if(addRadio.isSelected()){
                try {
                    product.add(currIdValue,currNameValue, currPriceValue,currLocationValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Product Added Successfully! "+currIdValue+" "+currNameValue+" "+ currPriceValue +" "+currLocationValue);
            }else if(updateRadio.isSelected()){
                try {
                    product.update(currIdValue,currNameValue, currPriceValue,currLocationValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Product Updated Successfully! "+currIdValue+" "+currNameValue+" "+ currPriceValue +" "+currLocationValue);
            }else if(deleteRadio.isSelected()){
                try {
                    product.delete(currIdValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Product Deleted Successfully! with Id : "+currIdValue);
            }
        });


        adminPanel.add(currId);
        adminPanel.add(currName);
        adminPanel.add(currLocation);
        adminPanel.add(currPrice);

        adminPanel.add(addRadio);
        adminPanel.add(updateRadio);
        adminPanel.add(deleteRadio);
        adminPanel.add(executeOption);

        frame.add(adminPanel);
        frame.setVisible(true);
    }

    public static void orderOrUpdateProfile(String usernameText){
        frame.remove(panel);

        userSelect = new JPanel();
        userSelect.setLayout(null);
        userSelect.setBackground(black);

        JLabel welcomeUser = new JLabel("Welcome "+usernameText+" !");
        welcomeUser.setBounds(10,20,180,25);
        welcomeUser.setForeground(white);
        userSelect.add(welcomeUser);

        JLabel selectionLabel = new JLabel("Select any one of the following");
        selectionLabel.setBounds(10,50,180,25);
        selectionLabel.setForeground(white);
        userSelect.add(selectionLabel);

        JRadioButton updateProfile = new JRadioButton("Update user details");
        updateProfile.setBounds(10,80,200,30);
        updateProfile.setBackground(black);
        updateProfile.setForeground(white);
        updateProfile.setFocusPainted(false);

        JRadioButton orderProduct = new JRadioButton("Order a product");
        orderProduct.setBounds(10,110,200,30);
        orderProduct.setBackground(black);
        orderProduct.setForeground(white);
        orderProduct.setFocusPainted(false);

        ButtonGroup bg=new ButtonGroup();
        bg.add(updateProfile);
        bg.add(orderProduct);

        executeOption = new JButton("Execute !");
        executeOption.setBounds(135,250,180,30);
        executeOption.setBackground(green);
        executeOption.setForeground(black);
        executeOption.setFocusPainted(false);
        executeOption.addActionListener(e -> {

            if(updateProfile.isSelected()){
                System.out.println("Updating the user");
                updateUser(usernameText);
            }else if(orderProduct.isSelected()){
                System.out.println("Ordering a product");
                orderProduct(usernameText);
            }
        });

        userSelect.add(updateProfile);
        userSelect.add(orderProduct);
        userSelect.add(executeOption);

        frame.add(userSelect);
        frame.setVisible(true);
    }

    public static void orderProduct(String userName){

        frame.remove(panel);
        frame.remove(userSelect);
        frame.setSize(650,350);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(null);
        orderPanel.setBackground(black);

        JLabel welcomeUser = new JLabel("Welcome " + userName);
        welcomeUser.setBounds(10,20,180,25);
        welcomeUser.setForeground(white);
        orderPanel.add(welcomeUser);

        JRadioButton placeRadio = new JRadioButton("Place Order !");
        placeRadio.setBounds(10,50,100,30);
        placeRadio.setBackground(black);
        placeRadio.setForeground(white);
        placeRadio.setFocusPainted(false);

        JRadioButton cancelRadio = new JRadioButton("Cancel an order of a product !");
        cancelRadio.setBounds(10,80,200,30);
        cancelRadio.setBackground(black);
        cancelRadio.setForeground(white);
        cancelRadio.setFocusPainted(false);

        JRadioButton displayRadio=new JRadioButton("Display the orders made");
        displayRadio.setBounds(10,110,200,30);
        displayRadio.setBackground(black);
        displayRadio.setForeground(white);
        displayRadio.setFocusPainted(false);

        currId = new JLabel("Enter Id");
        currId.setBounds(235,50,150,30);
        currId.setForeground(white);

        currIdField = new JTextField(100);
        currIdField.setBounds(450,50,150,30);
        orderPanel.add(currIdField);

        currName = new JLabel("Enter Product/Item name");
        currName.setBounds(235,80,250,30);
        currName.setForeground(white);

        currNameField = new JTextField(100);
        currNameField.setBounds(450,80,150,30);
        orderPanel.add(currNameField);

        JLabel currQuantity = new JLabel("Enter quantity you want to order");
        currQuantity.setBounds(235,110,200,30);
        currQuantity.setForeground(white);

        currQuantityField = new JTextField(100);
        currQuantityField.setBounds(450,110,150,30);
        orderPanel.add(currQuantityField);

        ButtonGroup bg=new ButtonGroup();
        bg.add(placeRadio);
        bg.add(cancelRadio);
        bg.add(displayRadio);

        executeOption=new JButton("Execute !");
        executeOption.setBounds(230,250,180,30);
        executeOption.setBackground(green);
        executeOption.setForeground(black);
        executeOption.setFocusPainted(false);
        executeOption.addActionListener(e -> {

            currIdValue = Integer.valueOf(currIdField.getText());
            currNameValue = currNameField.getText();
            currQuantityValue = Integer.valueOf(currQuantityField.getText());

            Order order = new Order(connectionName,connectionPassword);
            if(placeRadio.isSelected()){
                try {
                    order.place(currQuantityValue,currNameValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("User Data Added! "+currIdValue+" , "+currNameValue+" , "+currQuantityValue);
            }else if(cancelRadio.isSelected()){
                try {
                    order.cancel(currIdValue);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("User Data Updated! "+currIdValue+" , "+currNameValue+" , "+currQuantityValue);
            }else if(displayRadio.isSelected()){
                try {
                    order.display();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        orderPanel.add(currId);
        orderPanel.add(currName);
        orderPanel.add(currQuantity);

        orderPanel.add(placeRadio);
        orderPanel.add(cancelRadio);
        orderPanel.add(displayRadio);
        orderPanel.add(executeOption);

        frame.add(orderPanel);
        frame.setVisible(true);
    }

    /**
     * <h1>Stock Maintenance</h1>
     * The program starts Here and the frontend
     * gets initialized here and the frontend tels to invoke the backend
     * @author  Hareesh Rajendran
     * @version 1.0
     * @since   2021-12-23
     */

    public static void main(String[] args) {

        frame = new JFrame("Stock Maintenance");
        panel = new JPanel();
        frame.setSize(470,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("src/img/logo.png");

        frame.setIconImage(img.getImage());
        frame.add(panel);
        panel.setBackground(black);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        userLabel.setForeground(white);
        panel.add(userLabel);

        userName = new JTextField(100);
        userName.setBounds(100,20,160,25);
        panel.add(userName);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        passwordLabel.setForeground(white);
        panel.add(passwordLabel);

        password = new JPasswordField(100);
        password.setBounds(100,50,160,25);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170,80,90,25);
        loginButton.isDefaultButton();
        loginButton.setBackground(green);
        loginButton.setForeground(black);

        loginButton.addActionListener(new MySqlDMLCommands());
        loginButton.addActionListener(e -> {

            String usernameText = userName.getText();
            String passwordText = String.valueOf(password.getPassword());
            if (usernameText.equals("hareesh") && passwordText.equals("admin")) {
                System.out.println("Admin Logged in!");
                manageProduct();
            } else {
                System.out.println("User Logged in!");
                orderOrUpdateProfile(usernameText);
            }

        });

        panel.add(loginButton);
        frame.setVisible(true);
    }
}
