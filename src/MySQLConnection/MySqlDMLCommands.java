package MySQLConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MySqlDMLCommands implements ActionListener{

    public static class Customer {

        String connectionName,connectionPassword;

        Customer(String connectionName,String connectionPassword){
            this.connectionName = connectionName;
            this.connectionPassword = connectionPassword;
        }
        private void insert(int id,String name,String address,String phno,String table) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO "+table+" VALUES("+id+",'"+name+"','"+address+"','"+phno+"')";
            stmt.execute(insert);
            con.close();
            System.out.println("Successfully Inserted customer data with id:"+id);
        }
        private void update(int id,String name,String address,String phno,String table) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String update = "UPDATE "+table+" SET NAME='"+name+"' ,address='"+address+"' ,phoneno='"+phno+"' WHERE idcustomer="+id;
            stmt.execute(update);
            con.close();
            System.out.println("Successfully Updated customer data with id:"+id);
        }
        private void delete(int id,String table) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String delete = "DELETE FROM "+table+" WHERE idcustomer="+id;
            stmt.execute(delete);
            con.close();
            System.out.println("Successfully Deleted customer data with id:"+id);
        }
        private void display(String table) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String display = "SELECT * FROM "+table;
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

        Order(String connectionName,String connectionPassword){
             this.connectionName = connectionName;
             this.connectionPassword = connectionPassword;
        }
        private void place(int orderid,int quantity,String item) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String insert = "INSERT INTO stock.order VALUES("+orderid+","+quantity+","+"'"+item+"')";
            stmt.execute(insert);
            con.close();
            System.out.println("Order placed Successfully with order id:"+orderid+"...");
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
        private void update(int id,String productname,int price,String location) throws SQLException {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
            Statement stmt = con.createStatement();
            String update = "UPDATE PRODUCT SET PRODUCTNAME='"+productname+"' ,price="+price+" ,location='"+location+"' WHERE productid="+id;
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


    @Override
    public void actionPerformed(ActionEvent e) {
        String usernameText = userName.getText();
        String passwordText = password.getText();
        if(usernameText.equals("hareesh") && passwordText.equals("hareeshtheboss")){
            successLabel.setText("Admin Logged in!");
        }else{
            successLabel.setText("User Logged in!");
        }
    }
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel userLabel, passwordLabel,successLabel;
    private static JTextField userName;
    private static JPasswordField password;
    private static JButton loginButton;

    public static void main(String[] args) throws SQLException {

        frame = new JFrame("Stock Maintenance");
        panel = new JPanel();
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("Username");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userName = new JTextField(100);
        userName.setBounds(100,20,160,25);
        panel.add(userName);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        password = new JPasswordField(100);
        password.setBounds(100,50,160,25);
        panel.add(password);

        loginButton = new JButton("Login");
        loginButton.setBounds(170,80,90,25);
        loginButton.addActionListener((ActionListener) new MySqlDMLCommands());
        panel.add(loginButton);

        successLabel = new JLabel("");
        successLabel.setBounds(10,100,180,25);
        panel.add(successLabel);




//        String connectionName = "root";
//        String connectionPassword = "password";
//
//        Customer cust = new Customer(connectionName,connectionPassword);
//        cust.insert(7,"Hemesh","RMKEC","9988776650","customer");
//        cust.update(5,"Hareesh Sr","Somewhere under the sky","9988776655","customer");
//        cust.delete(5,"customer");
//        cust.display("customer");
//
//
//        Order order = new Order(connectionName,connectionPassword);
//        order.place(4,4,"Bottles");
//        order.place(5,3,"Phones");
//        order.place(6,7,"Laptops");
//        order.cancel(4);
//        order.display();
//
//        Product product = new Product(connectionName,connectionPassword);
//        product.add(2,"Bag",1000,"Chennai");
//        product.update(2,"Bags",1100,"Chennai");
//        product.delete(2);
    }
}
