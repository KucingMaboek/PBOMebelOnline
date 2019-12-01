package PBOMebelOnline;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
//    Scanner scan;
    private static Scanner scan  = new Scanner(System.in);
    private static String namaPelayan;
    private static Ekspedisi kurir = new Ekspedisi();
    private static Person member = new Person();
    private static Person kasir = new Kasir();
    private static Barang barang = new Barang();
    private static boolean status;
    private static BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws Exception {

        //temp
        String ID = "admin";
        String PW = "admin";
        String tempID,tempPW;
        int i = 2;

        System.out.println("===============");
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

        System.out.println("===============");

        System.out.println("Selamat datang di program POS furniture, silahkan login terlebih dahulu:");
        login:
        while(i >= 0) {
            ResultSet loginId;
            loginId = stat.executeQuery("select * from kasir");
            System.out.print("ID:");
            tempID = dataIn.readLine();
            System.out.print("Password:");
            tempPW = dataIn.readLine();
            while (loginId.next()) {
                if (tempID.equals(loginId.getString("idAkun")) && tempPW.equals(loginId.getString("passwordAkun"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                    status = Boolean.parseBoolean(loginId.getString("admin"));
                    namaPelayan = loginId.getString(("namaKasir"));
                    System.out.println("login berhasil");
                    System.out.println("==================");
                    if (status){System.out.println(String.format("selamat datang %s, anda login sebagai administrator", namaPelayan));}
                    else{System.out.println(String.format("selamat datang %s", namaPelayan));}
                    conn.close(); //close connection to database
                    home();
                    break login;
                }
            }
            if (i == 0) {
                System.out.println("Silahkan hubungi administrator");
                break login;
            } else {
                System.out.println("kesempatan mencoba tersisa : " + i);
                System.out.println("==================");
                i--;
            }
        }
    }

    public static void home() throws Exception {
        int pilihan;
        home:
        while (true) {
            System.out.println("Silahkan pilih menu: " +
                    "\n1.Data Transaksi" +
                    "\n2.Data Kasir" +
                    "\n3.Data Member" +
                    "\n4.Data Barang" +
                    "\n5.Data Ekspedisi" +
                    "\n6.Keluar");
            System.out.print("Masukkan pilihan menu:");
            pilihan = Integer.parseInt(dataIn.readLine());
            switch (pilihan) {
                case 1:
                    System.out.println("under maintenance");
                    break;
                case 2:
                    kasir.menu();
                    break;
                case 3:
                    member.menu();
                    break;
                case 4:
                    barang.menu();
                    break;
                case 5:
                    kurir.menu();
                    break;
                case 6:
                    break home;
                default:
                    System.out.println("Maaf, inputan diluar index, silahkan coba lagi.");
                    System.out.println("==================");
            }
        }
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
}
