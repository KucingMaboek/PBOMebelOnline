package PBOMebelOnline;


import java.io.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String namaPelayan;
        Ekspedisi kurir = new Ekspedisi();
        Person member = new Person();
        Person kasir = new Kasir();
        Barang barang = new Barang();
        Transaksi transaksi = new Transaksi();
        DatabaseManager dbManager = new DatabaseManager();
        boolean status;
        //temp
        String tempID,tempPW;
        int i = 2;

        System.out.println("===============");
        Class.forName("org.sqlite.JDBC");

        //Initializing database
        System.out.println("Membuat database MebelOnline.db");
        dbManager.createNewDatabase("MebelOnline.db");
        dbManager.createNewTable();

        Connection conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = conn.createStatement();

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
                    transaksi.setIdPegawai(loginId.getString("idKasir"));
                    System.out.println("login berhasil");
                    System.out.println("==================");
                    if (status){System.out.println(String.format("selamat datang %s, anda login sebagai administrator", namaPelayan));}
                    else{System.out.println(String.format("selamat datang %s", namaPelayan));}
                    conn.close(); //close connection to database
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
                                transaksi.menu(status);
                                break;
                            case 2:
                                kasir.menu(status);
                                break;
                            case 3:
                                member.menu(status);
                                break;
                            case 4:
                                barang.menu(status);
                                break;
                            case 5:
                                kurir.menu(status);
                                break;
                            case 6:
                                break home;
                            default:
                                System.out.println("Maaf, inputan diluar index, silahkan coba lagi.");
                                System.out.println("==================");
                        }
                    }
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
}
