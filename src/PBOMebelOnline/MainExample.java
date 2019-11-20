package PBOMebelOnline;
import java.sql.*;
import java.util.Scanner;

public class MainExample {
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
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        int pilihan;
        System.out.println("Menyiapkan database");
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Initializing database
        System.out.println("Membuat database Test.db");
        createNewDatabase("Test.db");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Test.db");
        Statement stat = conn.createStatement();
        System.out.println("Menyiapkan data barang");
        try {
            stat.executeUpdate("create table barang (" +
                    "idBarang INTEGER PRIMARY KEY," +
                    "namaBarang TEXT NOT NULL," +
                    "hargaBarang TEXT NOT NULL);");
        } catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("tabel sudah ada");
        }
        String id,pw;

        System.out.println("======================");
        System.out.println("Selamat datang di program meubel online, silahkan login untuk melanjutkan");
        System.out.print("id:");
        id = scan.nextLine();
        System.out.print("pw:");
        pw = scan.nextLine();

        ResultSet search = stat.executeQuery("select * from testable;");
        while (search.next()){
            if (id.equals(search.getString("idMember")) || id.equals(search.getString("id")))
            { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                System.out.println("idMember = " + search.getString("idMember"));
                System.out.println("id = " + search.getString("id"));
                System.out.println("pw = " + search.getString("pw"));
                System.out.println("login berhasil");
//                System.out.println("ditemukan pada id ke-" + search.getString("idMember"));
                break;
            }
            System.out.println("tidak ditemukan pada id ke-" + search.getString("idMember"));
        }


        while(true) {
            System.out.println("Silahkan pilih menu:");
            System.out.println("1.Tambah data barang\n2.Cari data barang\n3.Hapus data barang\n4.edit data barang");
            pilihan = scan.nextInt();
            if (pilihan >= 1 && pilihan <= 4 ){
                break;
            } else {
                System.out.println("pilihan salah,coba lagi");
            }
        }

        System.out.println("pilihan benar");

        if (pilihan == 1){
            System.out.print("Masukkan nama barang");
            String namaBarang = scan.nextLine();
            System.out.print("Masukkan harga barang");
            int hargaBarang = scan.nextInt();
            String sqlQuery = (String.format("insert into barang values (NULL,\'%s\',\'%s\');", namaBarang,hargaBarang));
            stat.executeUpdate(sqlQuery);
            System.out.println("Barang berhasil dimasukkan");
            System.out.println("========================");
        }
        if (pilihan == 2){
            System.out.println("Masukkan id atau nama barang");
            String temp = scan.nextLine();
            ResultSet search = stat.executeQuery("select * from barang;");
            while (search.next()){
                if (temp.equals(search.getString("idBarang")) || temp.equals(search.getString("namaBarang")))
                { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                    System.out.println("idBarang = " + search.getString("idBarang"));
                    System.out.println("nama barang = " + search.getString("namaBarang"));
                    System.out.println("harga barang = " + search.getString("hargaBarang"));
                    System.out.println("login berhasil");
//                System.out.println("ditemukan pada id ke-" + search.getString("idMember"));
                    break;
                }
                System.out.println("tidak ditemukan pada id ke-" + search.getString("idMember"));
            }
        }
//        if (id.equals("admin")){
//            System.out.println("Anda saat ini sedang mengakses sebagai administrator");
//        }
//        String sqlQuery = (String.format("insert into testable values (NULL,\'%s\',\'%s\');", id,pw));
//        stat.executeUpdate(sqlQuery);
//
//        ResultSet rs = stat.executeQuery("select * from testable;");
//        while (rs.next()) {
//            System.out.println("idMember = " + rs.getString("idMember"));
//            System.out.println("id = " + rs.getString("id"));
//            System.out.println("pw = " + rs.getString("pw"));
//        }


//        conn.close(); //close connection to database


    }

}
