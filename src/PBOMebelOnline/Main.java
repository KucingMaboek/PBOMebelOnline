package PBOMebelOnline;

import java.sql.*;

public class Main {
    public static void createNewDatabase(String filename){
        String url = "jdbc:sqlite:" + filename;
        try (Connection conn = DriverManager.getConnection(url)){
            if (conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Person member = new Person();
        member.Person();
        System.out.println("===============");
//        member.show();
        Class.forName("org.sqlite.JDBC");

        //Initializing database
        System.out.println("Membuat database MebelOnline.db");
        createNewDatabase("MebelOnline.db");

        Connection conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = conn.createStatement();

//        stat.executeUpdate("drop table member");

        //Initializing table
        System.out.println("Membuat tabel");
//        nama,provinsi,kota,alamat,tanggalLahir, noTelp,noKTP
        try {
            stat.executeUpdate("create table member (" +
                    "idMember INTEGER PRIMARY KEY," +
                    "nama TEXT NOT NULL," +
                    "provinsi TEXT NOT NULL," +
                    "kota TEXT NOT NULL," +
                    "alamat TEXT NOT NULL," +
                    "tanggalLahir TEXT NOT NULL," +
                    "noTelp TEXT NOT NULL," +
                    "noKTP TEXT NOT NULL);");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("tabel sudah ada");
        }
        System.out.println("===============");

        stat.executeUpdate(member.getSqlQuery());

        ResultSet rs = stat.executeQuery("select * from member;");
        while (rs.next()) {
            System.out.println("id = " + rs.getString("idMember"));
            System.out.println("nama = " + rs.getString("nama"));
            System.out.println("provinsi = " + rs.getString("provinsi"));
            System.out.println("kota = " + rs.getString("kota"));
            System.out.println("alamat = " + rs.getString("alamat"));
            System.out.println("tanggal lahir = " + rs.getString("tanggalLahir"));
            System.out.println("nomor telepon = " + rs.getString("noTelp"));
            System.out.println("nomor ktp = " + rs.getString("noKTP"));
        }


        conn.close(); //close connection to database


    }
}
