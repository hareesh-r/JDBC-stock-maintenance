package MySQLConnection;

import java.sql.*;

public class MySqlDMLCommands {
    private static void insertCust(int id,String name,String address,String phno,String table,String connectionName,String connectionPassword) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
        Statement stmt = con.createStatement();
        String insert = "INSERT INTO "+table+" VALUES("+id+",'"+name+"','"+address+"','"+phno+"')";
        stmt.execute(insert);
        con.close();
        System.out.println("Insertion Successful...");
    }
    private static void updateCust(int id,String name,String address,String phno,String table,String connectionName,String connectionPassword) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
        Statement stmt = con.createStatement();
        String update = "UPDATE "+table+" SET NAME='"+name+"' ,address='"+address+"' ,phoneno='"+phno+"' WHERE idcustomer="+id;
        stmt.execute(update);
        con.close();
        System.out.println("Updation Successful...");
    }
    private static void deleteCust(int id,String table,String connectionName,String connectionPassword) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock", connectionName, connectionPassword);
        Statement stmt = con.createStatement();
        String delete = "DELETE FROM "+table+" WHERE idcustomer="+id;
        stmt.execute(delete);
        con.close();
        System.out.println("Deletion Successful...");
    }
    private static void displayCust(String table,String connectionName,String connectionPassword) throws SQLException {
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
        System.out.println("Data retrieved Successful Successful...");
    }

    public static void main(String[] args) throws SQLException {
        String connectionName = "root";
        String connectionPassword = "password";
        insertCust(5,"Hareesh Jr","Somewhere under the sky","9988776655","customer",connectionName,connectionPassword);
        updateCust(5,"Hareesh Sr","Somewhere under the sky","9988776655","customer",connectionName,connectionPassword);
        deleteCust(5,"customer",connectionName,connectionPassword);
        displayCust("customer",connectionName,connectionPassword);
    }
}
