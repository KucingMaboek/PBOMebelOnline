package PBOMebelOnline;

import java.sql.*;

public class DatabaseManager {

    public DatabaseManager() {
    }

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

    public void createNewTable() throws ClassNotFoundException, SQLException {
        System.out.println("Membuat tabel");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stat = conn.createStatement();

        System.out.println("===============");
        Class.forName("org.sqlite.JDBC");

        //Initializing database
        System.out.println("Membuat database MebelOnline.db");
        createNewDatabase("MebelOnline.db");

        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stat = conn.createStatement();

//        stat.executeUpdate("drop table member");

        //Initializing table

        System.out.println("Membuat tabel");
//        nama,provinsi,kota,alamat,tanggalLahir, noTelp,noKTP
        try {
            stat.executeUpdate("create table ekspedisi (" +
                    "idEkspedisi INTEGER PRIMARY KEY," +
                    "namaEkspedisi TEXT," +
                    "hargaPerkilo INTEGER);");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            stat.executeUpdate("create  table member (" +
                    "idMember INTEGER PRIMARY KEY," +
                    "namaMember TEXT," +
                    "kotaKelahiran TEXT," +
                    "tanggalLahir TEXT," +
                    "alamat TEXT," +
                    "noTelp TEXT," +
                    "NIK TEXT);");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            stat.executeUpdate("create  table kasir (" +
                    "idKasir INTEGER PRIMARY KEY," +
                    "namaKasir TEXT," +
                    "kotaKelahiran TEXT," +
                    "tanggalLahir TEXT," +
                    "alamat TEXT," +
                    "noTelp TEXT," +
                    "NIK TEXT," +
                    "admin TEXT," +
                    "idAkun TEXT UNIQUE," +
                    "passwordAkun TEXT);");

            stat.executeUpdate("insert into kasir values (NULL,'Default\',\'Default\', \'Default\', \'Default\', \'Default\',\'Default\', \'true\', \'admin\', \'admin\');");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            stat.executeUpdate("create  table barang (" +
                    "idBarang INTEGER PRIMARY KEY," +
                    "namaBarang TEXT," +
                    "merkBarang TEXT," +
                    "stok TEXT," +
                    "hargaBarang TEXT," +
                    "beratBarang TEXT," +
                    "bahan TEXT," +
                    "alokasi TEXT," +
                    "tegangan TEXT," +
                    "daya TEXT);");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            stat.executeUpdate("create  table kasir (" +
                    "idTransaksi TEXT" +
                    "pelayan TEXT," +
                    "member TEXT," +
                    "kurir TEXT," +
                    "barang TEXT," +
                    "banyakBarang TEXT);");

            stat.executeUpdate("insert into kasir values (NULL,'Default\',\'Default\', \'Default\', \'Default\', \'Default\',\'Default\', \'true\', \'admin\', \'admin\');");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        conn.close();
    }
}
