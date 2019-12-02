package PBOMebelOnline;

import java.sql.*;
import java.io.*;

public class Elektronik extends Barang implements CRUD{
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

    private int voltase;
    private int watt;

    public int getVoltase() {
        return voltase;
    }

    public void setVoltase(int voltase) {
        this.voltase = voltase;
    }

    public int getWatt() {
        return watt;
    }

    public void setWatt(int watt) {
        this.watt = watt;
    }

    public Elektronik() {
        super();
        this.voltase = voltase;
        this.watt = watt;
    }


    @Override
    public String createQuery() {
        String query = String.format("insert into barang values (NULL,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',NULL,NULL,\'%s\',\'%s\');", getNamaBarang(),getMerk(), getStokBarang(),
                getHargaBarang(), getHargaBarang(), getVoltase(),getWatt());
        return query;
    }

    @Override
    public String updateQuery() {
        String query = String.format("update barang set namaBarang = \'%s\',merkBarang = \'%s\',stok = \'%s\',hargaBarang = \'%s\', " +
                "beratBarang = \'%s\', bahan = NULL, alokasi = NULL, tegangan, \'%s\', daya = \'%s\' where idEkspedisi = %s", getNamaBarang(),getMerk(), getStokBarang(),
                getHargaBarang(), getHargaBarang(), getVoltase(),getWatt(),getIdBarang());
        return query;
    }

    @Override
    public String deleteQuery() {
        return null;
    }

    @Override
    public void menu(boolean admin) throws Exception {

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
        System.out.print("Tegangannya adalah:");
        System.out.println(getVoltase());
        System.out.print("Dayanya adalah:");
        System.out.println(getWatt());
    }

    public void tambahElektronik() throws SQLException, IOException {
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
        System.out.print("Tegangan (satuan volt):");
        setVoltase(Integer.parseInt(dataIn.readLine()));
        System.out.print("Konsumsi daya (satuan watt):");
        setWatt(Integer.parseInt(dataIn.readLine()));
        System.out.println("-------------");
        if (getNamaBarang().isBlank() || getMerk().isBlank()) {
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

    public void editElektronik() throws SQLException, IOException{
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
                System.out.println("Nama Barang = " + searchBarang.getString("namaBarang"));
                System.out.println("Merk = " + searchBarang.getString("merkBarang"));
                System.out.println("Stok = " + searchBarang.getString("stok"));
                System.out.println("Harga Barang = " + searchBarang.getString("hargaBarang"));
                System.out.println("Berat Barang = " + searchBarang.getString("beratBarang"));
                System.out.println("Tegangan (satuan volt) = " + searchBarang.getString("tegangan"));
                System.out.println("Konsumsi daya (satuan watt) = " + searchBarang.getString("daya"));
                setIdBarang(searchBarang.getString("idBarang"));
                System.out.println("Apakah data ini akan diubah?[Y/N] :");
                temp = dataIn.readLine();
                if (temp.equalsIgnoreCase("y")) {
                    System.out.print("Nama barang:");
                    setNamaBarang(dataIn.readLine());
                    System.out.print("Nama merk:");
                    setMerk(dataIn.readLine());
                    System.out.println("Jumlah persediaan:");
                    setStokBarang(Integer.parseInt(dataIn.readLine()));
                    System.out.println("Harga barang:");
                    setHargaBarang(Integer.parseInt(dataIn.readLine()));
                    System.out.println("Berat barang:");
                    setBeratBarang(Integer.parseInt(dataIn.readLine()));
                    System.out.println("Tegangan (satuan volt):");
                    setVoltase(Integer.parseInt(dataIn.readLine()));
                    System.out.println("Konsumsi daya (satuan watt):");
                    setWatt(Integer.parseInt(dataIn.readLine()));
                    System.out.println("-------------");
                    if (getNamaBarang().isBlank() || getMerk().isBlank()) {
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
