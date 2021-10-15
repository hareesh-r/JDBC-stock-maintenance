package MySQLConnection;

import java.sql.*;

public class MySqlDMLCommands {

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


    public static void main(String[] args) throws SQLException {
        String connectionName = "root";
        String connectionPassword = "password";

//        Customer cust = new Customer(connectionName,connectionPassword);
//        cust.insert(7,"Hareesh Jr","Somewhere under the sky","9988776655","customer");
//        cust.update(5,"Hareesh Sr","Somewhere under the sky","9988776655","customer");
//        cust.delete(5,"customer");
//        cust.display("customer");


        Order order = new Order(connectionName,connectionPassword);
//        order.place(4,4,"Bottles");
//        order.place(5,3,"Phones");
//        order.place(6,7,"Laptops");
//        order.cancel(4);
        order.display();

    }
}
