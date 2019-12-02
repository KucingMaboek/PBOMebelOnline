package PBOMebelOnline;

import java.io.*;
import java.sql.*;

public class Person implements CRUD{ //masuk ke database member
    private BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

    private String idPerson;
    private String nama;
    private String alamat;
    private String tanggalLahir;
    private String kotaKelahiran;
    private String noTelp;
    private String noKTP;

    public String getIdMember() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getKotaKelahiran() {
        return kotaKelahiran;
    }

    public void setKotaKelahiran(String kotaKelahiran) {
        this.kotaKelahiran = kotaKelahiran;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public Person() {
        this.nama = nama;
        this.alamat = alamat;
        this.tanggalLahir = tanggalLahir;
        this.kotaKelahiran = kotaKelahiran;
        this.noTelp = noTelp;
        this.noKTP = noKTP;
    }

    public void showMember(){
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
    }

    @Override
    public String createQuery() {
        String query = String.format("insert into member values (NULL,\'%s\',\'%s\', \'%s\', \'%s\', \'%s\',\'%s\');", getNama(), getKotaKelahiran(), getTanggalLahir(), getAlamat(), getNoTelp(), getNoKTP());
        return query;
    }

    @Override
    public String updateQuery() {
        String query = String.format("update member set namaMember = \'%s\',kotaKelahiran = \'%s\', tanggalLahir = \'%s\', alamat = \'%s\', noTelp = \'%s\', NIK = \'%s\' where idPerson = %s", getNama(), getKotaKelahiran(), getTanggalLahir(), getAlamat(), getNoTelp(), getNoKTP(),getIdMember());
        return query;
    }

    @Override
    public String deleteQuery() {
        String query = String.format("update member set namaMember = NULL,kotaKelahiran = NULL, tanggalLahir = NULL, alamat = NULL, noTelp = NULL, NIK = NULL where idPerson = %s", getIdMember());
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
            System.out.println("Pilih menu member: " +
                    "\n1.Tambah data member" +
                    "\n2.Lihat daftar member" +
                    "\n3.telusuri member" +
                    "\n4.Edit data member" +
                    "\n5.hapus member" +
                    "\n0.Kembali");
            System.out.print("Masukkan pilihan menu:");
            pilihan = Integer.parseInt(dataIn.readLine());
            switch (pilihan) {
                case 1:
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select * from member");
                    System.out.println("Menambah data member");
                    System.out.print("Nama member:");
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
                    System.out.println("-------------");
                    if (getNama().isBlank() || getKotaKelahiran().isBlank() || getTanggalLahir().isBlank() || getAlamat().isBlank() || getNoTelp().isBlank() || getNoKTP().isBlank()){
                        System.out.println("Mohon inputkan data dengan benar");
                        break;}
                    showMember();
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
                    searchPerson = stat.executeQuery("select * from member");
                    ResultSet rs = stat.executeQuery("select * from member;");
                    System.out.print("idPerson");
                    System.out.print("    Nama");
                    System.out.print("    Kota kelahiran");
                    System.out.print("    Tanggal lahir");
                    System.out.print("    Alamat");
                    System.out.print("    No Telp");
                    System.out.println("    No KTP");
                    while (rs.next()) {
                        System.out.print(rs.getString("idMember  "));
                        System.out.print("  " + rs.getString("namaMember"));
                        System.out.print("  " + rs.getString("kotaKelahiran"));
                        System.out.print("  " + rs.getString("tanggalLahir"));
                        System.out.print("  " + rs.getString("alamat"));
                        System.out.print("  " + rs.getString("noTelp"));
                        System.out.println("  " + rs.getString("NIK"));
                    }
                    System.out.println("-------------");
                    break;
                case 3:
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select * from member");
                    System.out.println("Masukkan id atau nama member");
                    temp = dataIn.readLine();
                    while (searchPerson.next()) {
                        if (temp.equals(searchPerson.getString("idPerson")) || temp.equals(searchPerson.getString("namaMember"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama = " + searchPerson.getString("namaMember"));
                            System.out.println("Kota Kelahiran = " + searchPerson.getString("kotaKelahiran"));
                            System.out.println("Tanggal Lahir [DD/MM/YYYY] = " + searchPerson.getString("tanggalLahir"));
                            System.out.println("Alamat = " + searchPerson.getString("alamat"));
                            System.out.println("No Telp. = " + searchPerson.getString("noTelp"));
                            System.out.println("NIK = " + searchPerson.getString("NIK"));
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
                    searchPerson = stat.executeQuery("select * from member");
                    System.out.println("Masukkan id member");
                    temp = dataIn.readLine();
                    while (searchPerson.next()) {
                        if (temp.equals(searchPerson.getString("idPerson")) || temp.equals(searchPerson.getString("namaMember"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama = " + searchPerson.getString("namaMember"));
                            System.out.println("Kota Kelahiran = " + searchPerson.getString("kotaKelahiran"));
                            System.out.println("Tanggal Lahir [DD/MM/YYYY] = " + searchPerson.getString("tanggalLahir"));
                            System.out.println("Alamat = " + searchPerson.getString("alamat"));
                            System.out.println("No Telp. = " + searchPerson.getString("noTelp"));
                            System.out.println("NIK = " + searchPerson.getString("NIK"));
                            setIdPerson(searchPerson.getString("idPerson"));
                            System.out.println("Apakah data ini akan diubah?[Y/N] :");
                            temp = dataIn.readLine();
                            if (temp.equalsIgnoreCase("y")) {
                                System.out.println("Masukkan data baru:");
                                System.out.print("Nama member:");
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
                                if (getNama().isBlank() || getKotaKelahiran().isBlank() || getTanggalLahir().isBlank() || getAlamat().isBlank() || getNoTelp().isBlank() || getNoKTP().isBlank()){
                                    System.out.println("Mohon inputkan data dengan benar");
                                    break;}
                                showMember();
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
                    if (admin == false){
                        System.out.println("Maaf hanya admin yang bisa mengakses menu ini");
                        break;
                    }
                    System.out.println("-------------");
                    searchPerson = stat.executeQuery("select  * from member");
                    System.out.print("Masukkan id atau nama member: ");
                    temp = dataIn.readLine();
                    while (searchPerson.next()) {
                        if (temp.equals(searchPerson.getString("idPerson")) || temp.equals(searchPerson.getString("namaMember"))) { //mencari dengan cara mencocokkan inputan dengan idmember atau id. bisa di implemantasikan ke class barang
                            System.out.println("Nama = " + searchPerson.getString("namaMember"));
                            System.out.println("Kota Kelahiran = " + searchPerson.getString("kotaKelahiran"));
                            System.out.println("Tanggal Lahir [DD/MM/YYYY] = " + searchPerson.getString("tanggalLahir"));
                            System.out.println("Alamat = " + searchPerson.getString("alamat"));
                            System.out.println("No Telp. = " + searchPerson.getString("noTelp"));
                            System.out.println("NIK = " + searchPerson.getString("NIK"));
                            setIdPerson(searchPerson.getString("idPerson"));
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
