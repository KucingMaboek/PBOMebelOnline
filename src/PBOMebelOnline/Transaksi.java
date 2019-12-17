package PBOMebelOnline;

import java.util.ArrayList;
import java.io.*;
import java.sql.*;

public class Transaksi implements CRUD {
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
    private int idTransaksi;
    private String idPegawai;
    private String idKurir;
    private String idMember;
    private String idBarang;
    private int banyakBarangPerItem;
    private String hargaPerKilo;
    private String namaMember;
    private ArrayList<String> barang = new ArrayList<String>();
    private ArrayList<String> namaBarang = new ArrayList<String>();
    private ArrayList<Integer> hargaBarang = new ArrayList<Integer>();
    private ArrayList<Integer> banyakBarang = new ArrayList<Integer>();
    private ArrayList<Integer> beratBarang = new ArrayList<Integer>();
    private ArrayList<Integer> totalPerItem = new ArrayList<Integer>();
    private String kurir;
    private int total;

    private void clearArray(){
        barang.clear();
        namaBarang.clear();
        hargaBarang.clear();
        banyakBarang.clear();
        beratBarang.clear();
        totalPerItem.clear();
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getBanyakBarangPerItem() {
        return banyakBarangPerItem;
    }

    public void setBanyakBarangPerItem(int banyakBarangPerItem) {
        this.banyakBarangPerItem = banyakBarangPerItem;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getHargaPerKilo() {
        return hargaPerKilo;
    }

    public void setHargaPerKilo(String hargaPerKilo) {
        this.hargaPerKilo = hargaPerKilo;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(String idPegawai) {
        this.idPegawai = idPegawai;
    }


    public String getNamaMember() {
        return namaMember;
    }

    public void setNamaMember(String namaMember) {
        this.namaMember = namaMember;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Transaksi() {
        this.idTransaksi = idTransaksi;
        this.idPegawai = idPegawai;
        this.namaMember = namaMember;
        this.barang = barang;
        this.hargaBarang = hargaBarang;
        this.banyakBarang = banyakBarang;
        this.beratBarang = beratBarang;
        this.totalPerItem = totalPerItem;
        this.kurir = kurir;
        this.total = total;
        this.namaBarang = namaBarang;
    }

    @Override
    public String createQuery() {
        String query = String.format("insert into transaksi values (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');",
                getIdTransaksi(),getIdPegawai(),getIdMember(),getIdKurir(),getIdBarang(),getBanyakBarangPerItem() );
        return query;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        String query = String.format("update transaksi set pelayan = NULL,member = NULL,kurir = NULL,barang = NULL,banyakBarang = NULL where idTransaksi = %s",
                getIdTransaksi());
        return query;
    }

    @Override
    public void menu(boolean admin) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = conn.createStatement();
        ResultSet searchTransaksi;
        ResultSet searchEkspedisi;
        ResultSet searchBarang;
        ResultSet searchPerson;

        searchTransaksi = stat.executeQuery("select * from transaksi");
        searchPerson = stat.executeQuery("select * from member");
//        searchBarang = stat.executeQuery("select * from barang");
//        searchEkspedisi = stat.executeQuery("select * from ekspedisi");

        String temp;
        int pilihan;
        menuTransaksi:
        while (true) {
            System.out.println("Pilih menu transaksi: " +
                    "\n1.Tambah data transaksi" +
                    "\n2.Lihat daftar transaksi" +
                    "\n3.telusuri transaksi" +
                    "\n4.hapus data transaksi" +
                    "\n0.Kembali");
            System.out.print("Masukkan pilihan menu:");
            pilihan = Integer.parseInt(dataIn.readLine());
            clearArray();
            setTotal(0);
            switch (pilihan) {
                case 1:
                    System.out.println("-------------");
                    System.out.println("Menambah data transaksi");

                    //get last transaction id
                    searchTransaksi = stat.executeQuery("select * from transaksi");
                    while (searchTransaksi.next()){
                        idTransaksi+=1;
                    }




                    while (true) {
                        System.out.println("Masukkan id atau nama barang");
                        temp = dataIn.readLine();
                        searchBarang = stat.executeQuery("select * from barang");
//                        System.out.println(searchBarang.next());
                        tambahBarang:
                        while (searchBarang.next()) {
//                            System.out.println("masuk search barang");
                            if (temp.equals(searchBarang.getString("idBarang"))
                                    || temp.equals(searchBarang.getString("namaBarang"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                                if (Integer.parseInt(searchBarang.getString("stok")) < 1) {
                                    System.out.println("Maaf, stok barang tersebut tidak mencukupi");
                                } else {
                                    barang.add(searchBarang.getString("idBarang"));
                                    namaBarang.add(searchBarang.getString("namaBarang"));
                                    hargaBarang.add(Integer.parseInt(searchBarang.getString("hargaBarang")));

                                    System.out.print("Masukkan banyak barang: ");
                                    temp = dataIn.readLine();
                                    if (Integer.parseInt(searchBarang.getString("stok")) >= Integer.parseInt(temp)) {
                                        banyakBarang.add(Integer.parseInt(temp));
                                        beratBarang.add(Integer.parseInt(searchBarang.getString("beratBarang"))
                                                * Integer.parseInt(temp)); //berat barang di kali banyak
                                        totalPerItem.add(Integer.parseInt(searchBarang.getString("hargaBarang"))
                                                * Integer.parseInt(temp)); //harga barang dikali banyak barang
                                        break tambahBarang;
                                    } else {
                                        System.out.println("Maaf, stok barang tersebut tidak mencukupi");
                                        break tambahBarang;
                                    }
                                }
                            }
                        }
                        System.out.println("Apakah anda ingin menambah barang?[Y/N]");
                        if (dataIn.readLine().equalsIgnoreCase("y")) {
                            System.out.println("-------------");
                        }
                        else {
                            if (barang.size() <= 0){
                                break menuTransaksi;
                            }
                            System.out.println("-------------");
                            break;
                        }
                    }

                    if (barang.size() <= 0){
                        break;
                    }

                    System.out.print("No");
                    System.out.print("      namaBarang");
                    System.out.print("      hargaBarang");
                    System.out.print("      beratBarang");
                    System.out.print("      banyakBarang");
                    System.out.println("    totalPerItem");
                    for (int i = 0; i < barang.size(); i++) {
                        System.out.print(i+1);
                        System.out.print("  \"" + namaBarang.get(i) + "\"");
                        System.out.print("  " + hargaBarang.get(i));
                        System.out.print("  " + beratBarang.get(i));
                        System.out.print("  " + banyakBarang.get(i));
                        System.out.println("  " + totalPerItem.get(i));
                    }
                    for (int a = 0; a < barang.size() ; a++) {
                        total = total + beratBarang.get(a);
                    }

                    cariEkspedisi:
                    while(true) {
                        System.out.println("Masukkan id atau nama ekspedisi");
                        temp = dataIn.readLine();
                        searchEkspedisi = stat.executeQuery("select * from ekspedisi");
                        while (searchEkspedisi.next()) {
                            if (temp.equals(searchEkspedisi.getString("idEkspedisi")) || temp.equals(searchEkspedisi.getString("namaEkspedisi"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                                setIdKurir(searchEkspedisi.getString("idEkspedisi"));
                                setTotal(total * Integer.parseInt(searchEkspedisi.getString("hargaPerKilo")));
                                break cariEkspedisi;
                            }
                        }
                        System.out.println("Maaf ekspedisi tidak ditemukan");
                    }

                    cariMember:
                    while(true) {
                        System.out.println("Masukkan id atau nama member");
                        temp = dataIn.readLine();
                        searchPerson = stat.executeQuery("select * from member");
                        while (searchPerson.next()) {
                            if (temp.equals(searchPerson.getString("idMember")) || temp.equals(searchPerson.getString("namaMember"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                                setIdMember(searchPerson.getString("idMember"));
                                break cariMember;
                            }
                        }
                        System.out.println("Maaf member tidak ditemukan");
                    }

                    for (int i = 0; i < barang.size(); i++) {
                        total = total + totalPerItem.get(i);
                    }
                    System.out.println("Total harga = " +  total);
                    System.out.println("apakah data sudah benar?[Y/N]");
                    temp = dataIn.readLine();
                    if (temp.equalsIgnoreCase("y")){
//                        System.out.println("program masuk");
                        for (int i = 0; i < barang.size(); i++) {
                            setIdBarang(barang.get(i));
                            setBanyakBarangPerItem(banyakBarang.get(i));
                            stat.executeUpdate(createQuery());

                            int stok;
                            searchBarang = stat.executeQuery("select * from barang");
                            while (searchBarang.next()){
                                if (getIdBarang().equals(searchBarang.getString("idBarang"))){
                                    stok = Integer.parseInt(searchBarang.getString("stok"));
                                    stok = stok-banyakBarangPerItem;
                                    stat.executeUpdate(String.format("update barang set stok = \'%s\' where idBarang = %s", stok, getIdBarang()));
//                                    break;
                                }
                            }
                        }
                        setIdTransaksi(0);
                        break;
                    }
                    else{
                        System.out.println("program keluar");
                        break;
                    }
                case 2:
                    System.out.println("-------------");
                    System.out.print("idTransaksi");
                    System.out.print("    Pelayan");
                    System.out.print("    Member");
                    System.out.print("    Kurir");
                    System.out.print("    Barang");
                    System.out.println("    Banyak Barang");
                    searchTransaksi = stat.executeQuery("select t1.idTransaksi ,t4.namaKasir,t3.namaMember,t5.namaEkspedisi, t2.namaBarang, t1.banyakBarang\n" +
                            "from transaksi t1\n" +
                            "join barang t2 on t1.barang = t2.idBarang\n" +
                            "join member t3 on t1.member = t3.idMember\n" +
                            "join kasir t4 on t1.pelayan = t4.idKasir\n" +
                            "join ekspedisi t5 on t1.kurir = t5.idEkspedisi\n");
                    while (searchTransaksi.next()) {
                        System.out.print(searchTransaksi.getString("idTransaksi"));
                        System.out.print("  " + searchTransaksi.getString("namaKasir"));

                        System.out.print("  " + searchTransaksi.getString("namaMember"));

                        System.out.print("  " + searchTransaksi.getString("namaBarang"));
                        System.out.println("  " + searchTransaksi.getString("banyakBarang"));
                    }
                    System.out.println();
                    System.out.println("-------------");
                    break;
                case 3:
                    System.out.println("-------------");
                    System.out.println("Masukkan id transaksi");
                    temp = dataIn.readLine();
                    searchEkspedisi = stat.executeQuery("select * from transaksi");
                    while (searchTransaksi.next()) {
                        if (temp.equals(searchTransaksi.getString("idTransaksi"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("idTransaksi = " + searchTransaksi.getString("idTransaksi"));
                            System.out.println("Pelayan = " + searchTransaksi.getString("pelayan"));
                            System.out.println("Member = " + searchTransaksi.getString("member"));
                            System.out.println("Kurir = " + searchTransaksi.getString("kurir"));
                            System.out.println("Barang = " + searchTransaksi.getString("barang"));
                            System.out.println("Banyak Barang = " + searchTransaksi.getString("banyakBarang"));
                        }
                    }
                    System.out.println("-------------");
                    break;
                case 4:
                    if (admin == false){
                        System.out.println("Maaf hanya admin yang bisa mengakses menu ini");
                        break;
                    }
                    System.out.println("-------------");
                    System.out.print("Masukkan id: ");
                    temp = dataIn.readLine();
                    setIdTransaksi(Integer.parseInt(temp));
                    searchTransaksi = stat.executeQuery("select * from transaksi");
                    while (searchTransaksi.next()) {
                        if (temp.equals(searchTransaksi.getString("idTransaksi"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("idTransaksi = " + searchTransaksi.getString("idTransaksi"));
                            System.out.println("Pelayan = " + searchTransaksi.getString("pelayan"));
                            System.out.println("Member = " + searchTransaksi.getString("member"));
                            System.out.println("Kurir = " + searchTransaksi.getString("kurir"));
                            System.out.println("Barang = " + searchTransaksi.getString("barang"));
                            System.out.println("Banyak Barang = " + searchTransaksi.getString("banyakBarang"));
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
                    break menuTransaksi;

            }
        }
    }
}
