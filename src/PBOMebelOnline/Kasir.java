package PBOMebelOnline;

import java.sql.*;
import java.io.*;

public class Kasir extends Person implements CRUD{ //masuk ke database kasir
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

    private String admin;
    private String idAkun;
    private String passwordAkun;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(String idAkun) {
        this.idAkun = idAkun;
    }

    public String getPasswordAkun() {
        return passwordAkun;
    }

    public void setPasswordAkun(String passwordAkun) {
        this.passwordAkun = passwordAkun;
    }

    public Kasir() {
        this.admin = admin;
        this.idAkun = idAkun;
        this.passwordAkun = passwordAkun;
    }

    public void showKasir(){
        System.out.print("Nama: ");
        System.out.println(getNama());
        System.out.print("Kota kelahiran:");
        System.out.println(getKotaKelahiran());
        System.out.print("Tanggal lahir:");
        System.out.println(getTanggalLahir());
        System.out.print("Alamat:");
        System.out.println(getAlamat());
        System.out.print("No telp:");
        System.out.println(getNoTelp());
        System.out.print("No KTP:");
        System.out.println(getNoKTP());
        System.out.print("Status admin [true/false]:");
        System.out.println(getAdmin());
        System.out.print("ID akun:");
        System.out.println(getIdAkun());
        System.out.print("Password akun:");
        System.out.println(getPasswordAkun());
        //bisa ngeliat id sama pw juga kalo yang ngeliat admin
    }


    @Override
    public String createQuery() {
        String query = String.format("insert into kasir values (NULL,\'%s\',\'%s\', \'%s\', \'%s\', \'%s\',\'%s\', \'%s\', \'%s\', \'%s\');", getNama(), getKotaKelahiran(), getTanggalLahir(), getAlamat(), getNoTelp(), getNoKTP(), getAdmin(), getIdAkun(), getPasswordAkun());
        return query;
    }

    @Override
    public String updateQuery() {
        String query = String.format("update kasir set namaKasir = \'%s\',kotaKelahiran = \'%s\', tanggalLahir = \'%s\', alamat = \'%s\', noTelp = \'%s\', NIK = \'%s\', admin = \'%s\'" +
                ", idAkun = \'%s\', passwordAkun = \'%s\' where idKasir = %s", getNama(),getKotaKelahiran(),getTanggalLahir(),getAlamat(),getNoTelp(),getNoTelp(),getNoKTP(),getAdmin(),getIdAkun(),getPasswordAkun(),getIdMember());
        return query;
    }

    @Override
    public String deleteQuery() {
        String query = String.format("update kasir set namaKasir = NULL,kotaKelahiran = NULL, tanggalLahir = NULL, alamat = NULL, noTelp = NULL, NIK = NULL, admin = NULL" +
                ", idAkun = NULL, passwordAkun = NULL where idKasir = %s", getIdMember());
        return query;
    }

    @Override
    public void menu(boolean admin) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:MebelOnline.db");
        Statement stat = conn.createStatement();
        ResultSet searchPerson;
        String temp;
        int pilihan;
        menuMember:
        while (true) {
            if (admin == false){
                System.out.println("Maaf hanya admin yang bisa mengakses menu ini");
                break;
            }
            System.out.println("Pilih menu kasir: " +
                    "\n1.Tambah data kasir" +
                    "\n2.Lihat daftar kasir" +
                    "\n3.telusuri kasir" +
                    "\n4.Edit data kasir" +
                    "\n5.hapus kasir" +
                    "\n0.Kembali");
            System.out.print("Masukkan pilihan menu:");
            pilihan = Integer.parseInt(dataIn.readLine());
            switch (pilihan) {
                case 1:
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select * from kasir");
                    System.out.println("Menambah data kasir");
                    System.out.print("Nama kasir:");
                    setNama(dataIn.readLine());
                    System.out.print("Kota kelahiran:");
                    setKotaKelahiran(dataIn.readLine());
                    System.out.print("Tanggal lahir [DD/MM/YYYY]:");
                    setTanggalLahir(dataIn.readLine());
                    System.out.print("Alamat:");
                    setAlamat(dataIn.readLine());
                    System.out.print("No telp:");
                    setNoTelp(dataIn.readLine());
                    System.out.print("NIK:");
                    setNoKTP(dataIn.readLine());
                    System.out.print("Status admin [true/false]:");
                    setAdmin(dataIn.readLine());
                    System.out.print("ID akun:");
                    setIdAkun(dataIn.readLine());
                    System.out.print("Password akun:");
                    setPasswordAkun(dataIn.readLine());
                    System.out.println("-------------");
                    if (getNama().isBlank() || getKotaKelahiran().isBlank() || getTanggalLahir().isBlank() || getAlamat().isBlank() || getNoTelp().isBlank() || getNoKTP().isBlank() || getAdmin().isBlank() || getIdAkun().isBlank() || getPasswordAkun().isBlank()){
                        System.out.println("Mohon inputkan data dengan benar");
                        break;}
                    showKasir();
                    System.out.print("Apakah data sudah benar? [Y/N] : ");
                    if (dataIn.readLine().equalsIgnoreCase("Y")) {
                        try {
                            stat.executeUpdate(createQuery());
                        } catch (SQLException e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("silahkan masukkan data kembali");
                    }
                    System.out.println("-------------");
                    break;
                case 2:
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select * from kasir");
                    ResultSet rs = stat.executeQuery("select * from kasir;");
                    System.out.print("idKasir");
                    System.out.print("    Nama");
                    System.out.print("    Kota kelahiran");
                    System.out.print("    Tanggal lahir");
                    System.out.print("    Alamat");
                    System.out.print("    No Telp");
                    System.out.print("    No KTP");
                    System.out.print("  Status admin");
                    System.out.print("  Id Akun");
                    System.out.println("  Password Akun");
                    while (rs.next()) {
                        System.out.print(rs.getString("idKasir"));
                        System.out.print("  " + rs.getString("namaKasir"));
                        System.out.print("  " + rs.getString("kotaKelahiran"));
                        System.out.print("  " + rs.getString("tanggalLahir"));
                        System.out.print("  " + rs.getString("alamat"));
                        System.out.print("  " + rs.getString("noTelp"));
                        System.out.print("  " + rs.getString("NIK"));
                        System.out.print("  " + rs.getString("admin"));
                        System.out.print("  " + rs.getString("idAkun"));
                        System.out.println("  " + rs.getString("passwordAkun"));
                    }
                    System.out.println("-------------");
                    break;
                case 3:
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select * from kasir");
                    System.out.println("Masukkan id atau nama kasir");
                    temp = dataIn.readLine();
                    while (searchPerson.next()) {
                        if (temp.equals(searchPerson.getString("idKasir")) || temp.equals(searchPerson.getString("namaKasir"))) { //mencari dengan cara mencocokkan inputan dengan idkasir atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama = " + searchPerson.getString("namaKasir"));
                            System.out.println("Kota Kelahiran = " + searchPerson.getString("kotaKelahiran"));
                            System.out.println("Tanggal Lahir [DD/MM/YYYY] = " + searchPerson.getString("tanggalLahir"));
                            System.out.println("Alamat = " + searchPerson.getString("alamat"));
                            System.out.println("No Telp. = " + searchPerson.getString("noTelp"));
                            System.out.println("NIK = " + searchPerson.getString("NIK"));
                            System.out.println("admin = " + searchPerson.getString("admin"));
                            System.out.println("idAkun = " + searchPerson.getString("idAkun"));
                            System.out.println("passwordAkun = " + searchPerson.getString("passwordAkun"));
                        }
                    }
                    System.out.println("-------------");
                    break;
                case 4:
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select * from kasir");
                    System.out.print("Masukkan id kasir");
                    temp = dataIn.readLine();
                    while (searchPerson.next()) {
                        if (temp.equals(searchPerson.getString("idKasir")) || temp.equals(searchPerson.getString("namaKasir"))) { //mencari dengan cara mencocokkan inputan dengan idkasir atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama = " + searchPerson.getString("namaKasir"));
                            System.out.println("Kota Kelahiran = " + searchPerson.getString("kotaKelahiran"));
                            System.out.println("Tanggal Lahir [DD/MM/YYYY] = " + searchPerson.getString("tanggalLahir"));
                            System.out.println("Alamat = " + searchPerson.getString("alamat"));
                            System.out.println("No Telp. = " + searchPerson.getString("noTelp"));
                            System.out.println("NIK = " + searchPerson.getString("NIK"));
                            System.out.println("admin = " + searchPerson.getString("admin"));
                            System.out.println("idAkun = " + searchPerson.getString("idAkun"));
                            System.out.println("passwordAkun = " + searchPerson.getString("passwordAkun"));
                            setIdPerson(searchPerson.getString("idKasir"));
                            System.out.println("Apakah data ini akan diubah?[Y/N] :");
                            temp = dataIn.readLine();
                            if (temp.equalsIgnoreCase("y")) {
                                System.out.println("Masukkan data baru:");
                                System.out.print("Nama kasir:");
                                setNama(dataIn.readLine());
                                System.out.print("Kota kelahiran:");
                                setKotaKelahiran(dataIn.readLine());
                                System.out.print("Tanggal lahir [DD/MM/YYYY]:");
                                setTanggalLahir(dataIn.readLine());
                                System.out.print("Alamat:");
                                setAlamat(dataIn.readLine());
                                System.out.print("No telp:");
                                setNoTelp(dataIn.readLine());
                                System.out.print("NIK:");
                                setNoKTP(dataIn.readLine());
                                System.out.print("Status admin [true/false]:");
                                setAdmin(dataIn.readLine());
                                System.out.print("ID akun:");
                                setIdAkun(dataIn.readLine());
                                System.out.print("Password akun:");
                                setPasswordAkun(dataIn.readLine());
                                if (getNama().isBlank() || getKotaKelahiran().isBlank() || getTanggalLahir().isBlank() || getAlamat().isBlank() || getNoTelp().isBlank() || getNoKTP().isBlank() || getAdmin().isBlank() || getIdAkun().isBlank() || getPasswordAkun().isBlank()){
                                    System.out.println("Mohon inputkan data dengan benar");
                                    break;}
                                showKasir();
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
                    searchPerson = stat.executeQuery("select * from kasir");
                    System.out.print("Masukkan id atau nama kasir: ");
                    temp = dataIn.readLine();
                    while (searchPerson.next()) {
                        if (temp.equals(searchPerson.getString("idKasir")) || temp.equals(searchPerson.getString("namaKasir"))) { //mencari dengan cara mencocokkan inputan dengan idkasir atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama = " + searchPerson.getString("namaKasir"));
                            System.out.println("Kota Kelahiran = " + searchPerson.getString("kotaKelahiran"));
                            System.out.println("Tanggal Lahir [DD/MM/YYYY] = " + searchPerson.getString("tanggalLahir"));
                            System.out.println("Alamat = " + searchPerson.getString("alamat"));
                            System.out.println("No Telp. = " + searchPerson.getString("noTelp"));
                            System.out.println("NIK = " + searchPerson.getString("NIK"));
                            System.out.println("admin = " + searchPerson.getString("admin"));
                            System.out.println("idAkun = " + searchPerson.getString("idAkun"));
                            System.out.println("passwordAkun = " + searchPerson.getString("passwordAkun"));
                            setIdPerson(searchPerson.getString("idKasir"));
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
                    break menuMember;
                default:
                    System.out.println("Maaf, inputan diluar index, silahkan coba lagi.");
                    System.out.println("==================");
            }


        }
    }
}
