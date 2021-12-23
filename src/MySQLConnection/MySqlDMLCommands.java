package MySQLConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class MySqlDMLCommands implements ActionListener{

    // Globals
    private static final String connectionName = "root";
    private static final String connectionPassword = "password";
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel currId,successLabel,currName;
    private static JTextField userName, currIdField, currNameField, currAddressField, currPhoneNumberField,currPriceField,currLocationField;
    private static JPasswordField password;
    private static JButton executeOption;
    private static JRadioButton addRadio , updateRadio , deleteRadio,displayRadio;
    private static String currAddressValue,currLocationValue, currPhoneNumberValue,currNameValue;
    private static Integer currPriceValue,currIdValue;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

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
        private void add(int id,String productname,int price,String location) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO PRODUCT VALUES("+id+",'"+productname+"',"+price+",'"+location+"')";
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

    public static class Invoice {

        String connectionName,connectionPassword;

        Invoice(String connectionName,String connectionPassword){
            this.connectionName = connectionName;
            this.connectionPassword = connectionPassword;
        }

        private void generate(int id){

        }

    }

    public static void updateUser(String userName){

        frame.remove(panel);

        JPanel userPanel = new JPanel();
        userPanel.setLayout(null);

        JLabel welcomeUser = new JLabel("Welcome " + userName);
        welcomeUser.setBounds(10,20,180,25);
        userPanel.add(welcomeUser);

        addRadio=new JRadioButton("Add");
        addRadio.setBounds(10,50,100,30);

        updateRadio=new JRadioButton("Update");
        updateRadio.setBounds(10,80,100,30);

        deleteRadio=new JRadioButton("Delete");
        deleteRadio.setBounds(10,110,100,30);

        displayRadio=new JRadioButton("Display");
        displayRadio.setBounds(10,140,100,30);

        currId = new JLabel("Enter Id");
        currId.setBounds(130,50,150,30);

        currIdField = new JTextField(100);
        currIdField.setBounds(280,50,150,30);
        userPanel.add(currIdField);

        currName = new JLabel("Enter Name");
        currName.setBounds(130,80,150,30);

        currNameField = new JTextField(100);
        currNameField.setBounds(280,80,150,30);
        userPanel.add(currNameField);

        JLabel currAddress = new JLabel("Enter Address");
        currAddress.setBounds(130,110,150,30);

        currAddressField = new JTextField(100);
        currAddressField.setBounds(280,110,150,30);
        userPanel.add(currAddressField);

        JLabel currPhno = new JLabel("Enter Phone Number");
        currPhno.setBounds(130,140,150,30);

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
        userPanel.add(currPhno);

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

        JLabel welcomeAdmin = new JLabel("Welcome Admin !");
        welcomeAdmin.setBounds(10,20,180,25);
        adminPanel.add(welcomeAdmin);

        addRadio=new JRadioButton("Add");
        addRadio.setBounds(10,50,100,30);

        updateRadio=new JRadioButton("Update");
        updateRadio.setBounds(10,80,100,30);

        deleteRadio=new JRadioButton("Delete");
        deleteRadio.setBounds(10,110,100,30);

        currId = new JLabel("Enter Id");
        currId.setBounds(130,50,150,30);

        currIdField = new JTextField(100);
        currIdField.setBounds(280,50,150,30);
        adminPanel.add(currIdField);

        currName = new JLabel("Enter Product Name");
        currName.setBounds(130,80,150,30);

        currNameField = new JTextField(100);
        currNameField.setBounds(280,80,150,30);
        adminPanel.add(currNameField);

        JLabel currPrice = new JLabel("Enter Price");
        currPrice.setBounds(130,110,150,30);

        currPriceField = new JTextField(100);
        currPriceField.setBounds(280,110,150,30);
        adminPanel.add(currPriceField);

        JLabel currLocation = new JLabel("Enter Location");
        currLocation.setBounds(130,140,150,30);

        currLocationField = new JTextField(100);
        currLocationField.setBounds(280,140,150,30);
        adminPanel.add(currLocationField);

        ButtonGroup bg=new ButtonGroup();
        bg.add(addRadio);
        bg.add(updateRadio);
        bg.add(deleteRadio);


        executeOption=new JButton("Execute !");
        executeOption.setBounds(135,250,180,30);
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

    public static void main(String[] args) throws SQLException {

        frame = new JFrame("Stock Maintenance");
        panel = new JPanel();
        frame.setSize(470,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userName = new JTextField(100);
        userName.setBounds(100,20,160,25);
        panel.add(userName);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        password = new JPasswordField(100);
        password.setBounds(100,50,160,25);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(170,80,90,25);

        loginButton.addActionListener(new MySqlDMLCommands());
        loginButton.addActionListener(e -> {
            String usernameText = userName.getText();
            String passwordText = password.getText();
            if (usernameText.equals("hareesh") && passwordText.equals("admin")) {
                successLabel.setText("Admin Logged in!");
                System.out.println("Admin Logged in!");
                manageProduct();
            } else {
                successLabel.setText("User Logged in!");
                System.out.println("User Logged in!");
                updateUser(usernameText);
            }
        });
        panel.add(loginButton);

        successLabel = new JLabel("");
        successLabel.setBounds(10,100,180,25);
        panel.add(successLabel);

        frame.setVisible(true);

//        Order order = new Order(connectionName,connectionPassword);
//        order.place(4,"Bottles");
//        order.place(3,"Phones");
//        order.place(7,"Laptops");
//        order.cancel(4);
//        order.display();

    }
}
