package PBOMebelOnline;

import java.io.*;
import java.sql.*;

public class Barang implements CRUD{
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
    private static Elektronik elektro = new Elektronik();
    private static NonElektronik nonElektro = new NonElektronik();

    private String idBarang;
    private String namaBarang;
    private String merk;
    private int stokBarang;
    private int hargaBarang;
    private double beratBarang;

    public Barang() {
        this.namaBarang = namaBarang;
        this.merk = merk;
        this.stokBarang = stokBarang;
        this.hargaBarang = hargaBarang;
        this.beratBarang = beratBarang;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public double getBeratBarang() {
        return beratBarang;
    }

    public void setBeratBarang(double beratBarang) {
        this.beratBarang = beratBarang;
    }

    public void showBarang() throws IOException {
        System.out.print("Nama barang adalah:");
        System.out.println(getNamaBarang());
        System.out.print("Nama merk adalah:");
        System.out.println(getMerk());
        System.out.print("Jumlah persediaannya adalah:");
        System.out.println(getStokBarang());
        System.out.print("Harga barangnya adalah:");
        System.out.println(getHargaBarang());
        System.out.print("Berat barangnya adalah:");
        System.out.println(getBeratBarang());
    }

    @Override
    public String createQuery() {
        return null;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        String query = String.format("update barang set namaBarang = NULL,merkBarang = NULL,stok = NULL,hargaBarang = NULL, " +
                "beratBarang = NULL, bahan = NULL, alokasi = NULL, tegangan, NULL, daya = NULL where idEkspedisi = %s", getIdBarang());
        return query;
    }

    @Override
    public void menu() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = conn.createStatement();
        ResultSet searchBarang;
        String temp;
        int pilihan;
        menuBarang:
        while (true) {
            System.out.println("Pilih menu transaksi: " +
                    "\n1.Tambah data barang meubel" +
                    "\n2.Tambah data barang elektronik" +
                    "\n3.Lihat daftar barang" +
                    "\n4.Telusuri barang" +
                    "\n5.Edit data barang meubel" +
                    "\n6.Edit data barang elektronik" +
                    "\n7.Hapus data barang" +
                    "\n0.Kembali");
            System.out.print("Masukkan pilihan menu:");
            pilihan = Integer.parseInt(dataIn.readLine());
            switch (pilihan) {
                case 1:
                    nonElektro.tambahNonElektronik();
                    break;
                case 2:
                    elektro.tambahElektronik();
                    break;
                case 3:
                    System.out.println("-------------");
                    searchBarang = stat.executeQuery("select * from barang");
                    ResultSet rs = stat.executeQuery("select * from barang;");
                    System.out.print("idBarang");
                    System.out.print("    Nama barang");
                    System.out.print("    Merk");
                    System.out.print("    Stok");
                    System.out.print("    Harga barang");
                    System.out.print("    Berat barang");
                    System.out.print("    Bahan barang");
                    System.out.print("    Alokasi barang");
                    System.out.print("    Tegangan");
                    System.out.println("    Konsumsi daya");
                    while (rs.next()) {
                        System.out.print(rs.getString("idBarang"));
                        System.out.print("  " + rs.getString("namaBarang"));
                        System.out.print("  " + rs.getString("merkBarang"));
                        System.out.print("  " + rs.getString("stok"));
                        System.out.print("  " + rs.getString("hargaBarang"));
                        System.out.print("  " + rs.getString("beratBarang"));
                        System.out.print("  " + rs.getString("bahan"));
                        System.out.print("  " + rs.getString("alokasi"));
                        System.out.print("  " + rs.getString("tegangan"));
                        System.out.println("  " + rs.getString("daya"));
                    }
                    System.out.println("-------------");
                    break;
                case 4:
                    System.out.println("-------------");
                    searchBarang = stat.executeQuery("select * from barang");
                    System.out.println("Masukkan id atau nama barang");
                    temp = dataIn.readLine();
                    while (searchBarang.next()) {
                        if (temp.equals(searchBarang.getString("idBarang")) || temp.equals(searchBarang.getString("namaBarang"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("idBarang = " + searchBarang.getString("idBarang"));
                            System.out.println("Nama Barang = " + searchBarang.getString("namaBarang"));
                            System.out.println("Merk = " + searchBarang.getString("merkBarang"));
                            System.out.println("Stok = " + searchBarang.getString("stok"));
                            System.out.println("Harga Barang = " + searchBarang.getString("hargaBarang"));
                            System.out.println("Berat Barang = " + searchBarang.getString("beratBarang"));
                            System.out.println("Bahan Barang = " + searchBarang.getString("bahan"));
                            System.out.println("Alokasi Barang = " + searchBarang.getString("alokasi"));
                            System.out.println("Tegangan Barang = " + searchBarang.getString("tegangan"));
                            System.out.println("Daya Barang = " + searchBarang.getString("daya"));
                        }
                    }
                    System.out.println("-------------");
                    break;
                case 5:
                    nonElektro.editNonElektronik();
                    break;
                case 6:
                    elektro.editElektronik();
                    break;
                case 7:
                    System.out.println("-------------");
                    searchBarang = stat.executeQuery("select  * from barang");
                    System.out.print("Masukkan id atau nama barang: ");
                    temp = dataIn.readLine();
                    while (searchBarang.next()) {
                        if (temp.equals(searchBarang.getString("idBarang")) || temp.equals(searchBarang.getString("namaBarang"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama Barang = " + searchBarang.getString("namaBarang"));
                            System.out.println("Merk = " + searchBarang.getString("merkBarang"));
                            System.out.println("Stok = " + searchBarang.getString("stok"));
                            System.out.println("Harga Barang = " + searchBarang.getString("hargaBarang"));
                            System.out.println("Berat Barang = " + searchBarang.getString("beratBarang"));
                            System.out.println("Bahan Barang = " + searchBarang.getString("hargaBarang"));
                            System.out.println("Alokasi Barang = " + searchBarang.getString("beratBarang"));
                            System.out.println("Tegangan Barang = " + searchBarang.getString("hargaBarang"));
                            System.out.println("Daya Barang = " + searchBarang.getString("beratBarang"));
                            setIdBarang(searchBarang.getString("idBarang"));
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
                    break menuBarang;
                default:
                    System.out.println("Maaf, inputan diluar index, silahkan coba lagi.");
                    System.out.println("==================");
            }
        }
    }
}
