package PBOMebelOnline;

import java.sql.*;
import java.io.*;

public class NonElektronik extends Barang implements CRUD {
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
    private String bahan; //misal pvc, kayu mahoni, kayu jati, alumunium, dll
    private String alokasi; //misal ruang makan, ruang tidur, ruang santai, dll

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getAlokasi() {
        return alokasi;
    }

    public void setAlokasi(String alokasi) {
        this.alokasi = alokasi;
    }

    public NonElektronik() {
        super();
        this.bahan = bahan;
        this.alokasi = alokasi;
    }

    @Override
    public String createQuery() {
        String query = String.format("insert into barang values (NULL,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',NULL,NULL);", getNamaBarang(),getMerk(), getStokBarang(),
                getHargaBarang(), getHargaBarang(), getBahan(),getAlokasi());
        return query;
    }

    @Override
    public String updateQuery() {
        String query = String.format("update barang set namaBarang = \'%s\',merkBarang = \'%s\',stok = \'%s\',hargaBarang = \'%s\', " +
                        "beratBarang = \'%s\', bahan = \'%s\', alokasi = \'%s\', tegangan, NULL, daya = NULL where idEkspedisi = %s", getNamaBarang(),getMerk(), getStokBarang(),
                getHargaBarang(), getHargaBarang(), getBahan(),getAlokasi(),getIdBarang());
        return query;
    }

    @Override
    public String deleteQuery() {
        return null;
    }

    @Override
    public void menu() throws Exception {

    }

    @Override
    public void showBarang() {
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
        System.out.print("Bahannya adalah:");
        System.out.println(getBahan());
        System.out.print("Alokasi tempat adalah:");
        System.out.println(getAlokasi());
    }

    public void tambahNonElektronik() throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = connection.createStatement();
        ResultSet searchBarang;
        String temp;

        System.out.println("-------------");
        searchBarang = stat.executeQuery("select * from barang");
        System.out.println("Menambah data barang");
        System.out.print("Nama barang:");
        setNamaBarang(dataIn.readLine());
        System.out.print("Nama merk:");
        setMerk(dataIn.readLine());
        System.out.print("Jumlah persediaan:");
        setStokBarang(Integer.parseInt(dataIn.readLine()));
        System.out.print("Harga barang:");
        setHargaBarang(Integer.parseInt(dataIn.readLine()));
        System.out.print("Berat barang:");
        setBeratBarang(Integer.parseInt(dataIn.readLine()));
        System.out.print("Bahan barang:");
        setBahan(dataIn.readLine());
        System.out.print("Alokasi barang:");
        setAlokasi(dataIn.readLine());
        System.out.println("-------------");
        if (getNamaBarang().isBlank() || getMerk().isBlank() || getBahan().isBlank() || getAlokasi().isBlank()) {
            System.out.println("Mohon inputkan data dengan benar");
        }
            showBarang();
        System.out.print("Apakah data sudah benar? [Y/N] : ");
        if (dataIn.readLine().equalsIgnoreCase("Y")) {
            stat.executeUpdate(createQuery());
        } else {
            System.out.println("silahkan masukkan data kembali");
        }
        System.out.println("-------------");
        connection.close();
    }

    public void editNonElektronik() throws SQLException, IOException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = connection.createStatement();
        ResultSet searchBarang;
        String temp;

        System.out.println("-------------");
        searchBarang = stat.executeQuery("select * from barang");
        System.out.println("Masukkan id barang");
        temp = dataIn.readLine();
        while (searchBarang.next()) {
            if (temp.equals(searchBarang.getString("idBarang")) || temp.equals(searchBarang.getString("namaBarang"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                System.out.println("idBarang = " + searchBarang.getString("idBarang"));
                System.out.println("Nama barang = " + searchBarang.getString("namaBarang"));
                System.out.println("Merk = " + searchBarang.getString("merkBarang"));
                System.out.println("Stok = " + searchBarang.getString("stok"));
                System.out.println("Harga barang = " + searchBarang.getString("hargaBarang"));
                System.out.println("Berat barang = " + searchBarang.getString("beratBarang"));
                System.out.println("Bahan barang = " + searchBarang.getString("bahan"));
                System.out.println("Alokasi barang = " + searchBarang.getString("alokasi"));
                setIdBarang(searchBarang.getString("idBarang"));
                System.out.println("Apakah data ini akan diubah?[Y/N] :");
                temp = dataIn.readLine();
                if (temp.equalsIgnoreCase("y")) {
                    System.out.print("Nama barang:");
                    setNamaBarang(dataIn.readLine());
                    System.out.print("Nama merk:");
                    setMerk(dataIn.readLine());
                    System.out.print("Jumlah persediaan:");
                    setStokBarang(Integer.parseInt(dataIn.readLine()));
                    System.out.print("Harga barang:");
                    setHargaBarang(Integer.parseInt(dataIn.readLine()));
                    System.out.print("Berat barang:");
                    setBeratBarang(Integer.parseInt(dataIn.readLine()));
                    System.out.print("Bahan barang:");
                    setBahan(dataIn.readLine());
                    System.out.print("Alokasi barang:");
                    setAlokasi(dataIn.readLine());
                    System.out.println("-------------");
                    if (getNamaBarang().isBlank() || getMerk().isBlank() || getBahan().isBlank() || getAlokasi().isBlank()) {
                        System.out.println("Mohon inputkan data dengan benar");
                        break;
                    }
                    showBarang();
                    System.out.print("Apakah data sudah benar? [Y/N] : ");
                    temp = dataIn.readLine();
                    if (temp.equalsIgnoreCase("y")) {
                        stat.executeUpdate(updateQuery());
                    }
                }
            }
        }
        System.out.println("-------------");
        connection.close();

    }

}
