package PBOMebelOnline;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.sql.*;

public class Ekspedisi implements CRUD {

    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

    private String idKurir;
    private String namaKurir;
    private int hargaPerkilo;
//    private ArrayList<String> zonaPelayanan = new ArrayList<String>();


    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    public int getHargaPerkilo() {
        return hargaPerkilo;
    }

    public void setHargaPerkilo(int hargaPerkilo) {
        this.hargaPerkilo = hargaPerkilo;
    }

    public void showKurir() {
        System.out.print("Nama ekspedisinya adalah: ");
        System.out.println(getNamaKurir());
        System.out.print("harga perkilonya adalah: ");
        System.out.println(getHargaPerkilo());

    }

    public Ekspedisi() { //contructor
        this.namaKurir = namaKurir;
        this.hargaPerkilo = hargaPerkilo;
    }

    @Override
    public String createQuery() {
        String query = String.format("insert into ekspedisi values (NULL,\'%s\',\'%s\');", getNamaKurir(), getHargaPerkilo());
        return query;
    }

    @Override
    public String updateQuery() {
        String query = String.format("update ekspedisi set namaEkspedisi = \'%s\',hargaPerkilo = \'%s\' where idEkspedisi = %s;", getNamaKurir(), getHargaPerkilo(), getIdKurir());
        return query;
    }

    @Override
    public String deleteQuery() {
        String query = String.format("update ekspedisi set namaEkspedisi = NULL,hargaPerkilo = NULL where idEkspedisi = %s", getIdKurir());
        return query;
    }


    @Override
    public void menu() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = conn.createStatement();
        ResultSet searchEkspedisi;
        String temp;
        int pilihan;
        menuEkspedisi:
        while (true) {
            System.out.println("Pilih menu transaksi: \n1.Tambah data mitra ekspedisi\n2.Lihat daftar mitra ekspedisi\n3.telusuri ekspedisi\n4.Edit data mitra ekspedisi\n5.hapus data mitra ekspedisi\n0.Kembali");
            System.out.print("Masukkan pilihan menu:");
            pilihan = Integer.parseInt(dataIn.readLine());
            switch (pilihan) {
                case 1:
                    System.out.println("-------------");
                    searchEkspedisi = stat.executeQuery("select * from ekspedisi");
                    System.out.println("Menambah data ekspedisi");
                    System.out.print("Nama kurir:");
                    setNamaKurir(dataIn.readLine());
                    System.out.print("Harga jasa perkilo:");
                    setHargaPerkilo(Integer.parseInt(dataIn.readLine()));
                    System.out.println("-------------");
                    if (getNamaKurir().isBlank()){
                        System.out.println("Mohon inputkan data dengan benar");
                        break;}
                    showKurir();
                    System.out.print("Apakah data sudah benar? [Y/N] : ");
                    if (dataIn.readLine().equalsIgnoreCase("Y")) {
                        stat.executeUpdate(createQuery());
                    } else {
                        System.out.println("silahkan masukkan data kembali");
                    }
                    System.out.println("-------------");
                    break;
                case 2:
                    System.out.println("-------------");
                    searchEkspedisi = stat.executeQuery("select * from ekspedisi");
                    ResultSet rs = stat.executeQuery("select * from ekspedisi;");
                    System.out.print("idEkspedisi");
                    System.out.print("    Nama Ekspedisi");
                    System.out.println("    Harga Perkilo");
                    while (rs.next()) {
                        System.out.print(rs.getString("idEkspedisi"));
                        System.out.print("  " + rs.getString("namaEkspedisi"));
                        System.out.println("  " + rs.getString("hargaPerkilo"));
                    }
                    System.out.println("-------------");
                    break;
                case 3:
                    System.out.println("-------------");
                    searchEkspedisi = stat.executeQuery("select * from ekspedisi");
                    System.out.println("Masukkan id atau nama ekspedisi");
                    temp = dataIn.readLine();
                    while (searchEkspedisi.next()) {
                        if (temp.equals(searchEkspedisi.getString("idEkspedisi")) || temp.equals(searchEkspedisi.getString("namaEkspedisi"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("idEkspedisi = " + searchEkspedisi.getString("idEkspedisi"));
                            System.out.println("Nama Ekspedisi = " + searchEkspedisi.getString("namaEkspedisi"));
                            System.out.println("Harga Perkilo = " + searchEkspedisi.getString("hargaPerkilo"));
                        }
                    }
                    System.out.println("-------------");
                    break;
                case 4:
                    System.out.println("-------------");
                    searchEkspedisi = stat.executeQuery("select * from ekspedisi");
                    System.out.println("Masukkan id ekspedisi");
                    temp = dataIn.readLine();
                    while (searchEkspedisi.next()) {
                        if (temp.equals(searchEkspedisi.getString("idEkspedisi")) || temp.equals(searchEkspedisi.getString("namaEkspedisi"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("idEkspedisi = " + searchEkspedisi.getString("idEkspedisi"));
                            System.out.println("Nama Ekspedisi = " + searchEkspedisi.getString("namaEkspedisi"));
                            System.out.println("Harga Perkilo = " + searchEkspedisi.getString("hargaPerkilo"));
                            setIdKurir(searchEkspedisi.getString("idEkspedisi"));
                            System.out.println("Apakah data ini akan diubah?[Y/N] :");
                            temp = dataIn.readLine();
                            if (temp.equalsIgnoreCase("y")) {
                                System.out.println("Masukkan data baru:");
                                System.out.print("Nama kurir:");
                                setNamaKurir(dataIn.readLine());
                                System.out.print("Harga jasa perkilo:");
                                setHargaPerkilo(Integer.parseInt(dataIn.readLine()));
                                System.out.println("-------------");
                                if (getNamaKurir().isBlank()){
                                    System.out.println("Mohon inputkan data dengan benar");
                                    break;}
                                showKurir();
                                System.out.print("Apakah data sudah benar? [Y/N] : ");
                                temp = dataIn.readLine();
                                if (temp.equalsIgnoreCase("y")) {
                                    stat.executeUpdate(updateQuery());
                                }
                            }
                        }
                    }
                    System.out.println("-------------");
                    break;
                case 5:
                    System.out.println("-------------");
                    searchEkspedisi = stat.executeQuery("select  * from ekspedisi");
                    System.out.print("Masukkan id atau nama ekspedisi: ");
                    temp = dataIn.readLine();
                    while (searchEkspedisi.next()) {
                        if (temp.equals(searchEkspedisi.getString("idEkspedisi")) || temp.equals(searchEkspedisi.getString("namaEkspedisi"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("idEkspedisi = " + searchEkspedisi.getString("idEkspedisi"));
                            System.out.println("Nama Ekspedisi = " + searchEkspedisi.getString("namaEkspedisi"));
                            System.out.println("Harga Perkilo = " + searchEkspedisi.getString("hargaPerkilo"));
                            setIdKurir(searchEkspedisi.getString("idEkspedisi"));
                            System.out.print("Apakah anda ingin menghapus data ini? [Y/N]: ");
                            temp = dataIn.readLine();
                            if (temp.equalsIgnoreCase("y")) {
                                stat.executeUpdate(deleteQuery());
                            }
                        }
                    }
                    System.out.println("-------------");
                    break;
                case 0:
                    conn.close();
                    System.out.println("==================");
                    break menuEkspedisi;
                default:
                    System.out.println("Maaf, inputan diluar index, silahkan coba lagi.");
                    System.out.println("==================");
            }


        }
    }
}





