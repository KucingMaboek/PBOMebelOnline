package PBOMebelOnline;

import java.io.*;

public class Person {
    //    private String idPerson;
    private String nama;
    private String provinsi;
    private String kota;
    private String alamat;
    private String tanggalLahir;
    private String noTelp;
    private String noKTP;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    private String sqlQuery;

//    public String getIdPerson() {
//        return idPerson;
//    }

//    public void setIdPerson(String idPerson) {
//        this.idPerson = idPerson;
//    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
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

    public void Person() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String temp;

        System.out.print("Nama: ");
        temp = dataIn.readLine();
        this.nama = temp;

        System.out.print("Provinsi: ");
        temp = dataIn.readLine();
        this.provinsi = temp;

        System.out.print("Kota: ");
        temp = dataIn.readLine();
        this.kota = temp;

        System.out.print("Alamat: ");
        temp = dataIn.readLine();
        this.alamat = temp;

        System.out.print("Tanggal lahir: ");
        temp = dataIn.readLine();
        this.tanggalLahir = temp;

        System.out.print("Nomor Telepon: ");
        temp = dataIn.readLine();
        this.noTelp = temp;

        System.out.print("No KTP: ");
        temp = dataIn.readLine();
        this.noKTP = temp;

//        sqlQuery = "insert into pegawai values (NULL,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');", %getNama(),%getProvinsi(),%getKota(),%getAlamat(),%getTanggalLahir(), %getNoTelp(),%getNoKTP();
        sqlQuery = (String.format("insert into member values (NULL,\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\');", nama,provinsi,kota,alamat,tanggalLahir, noTelp,noKTP));
    }

    void show(){
        System.out.println(sqlQuery);
//        System.out.print(String.format("nama: %s", nama));
//        System.out.print("Provinsi: %s", %provinsi);
//        System.out.print("Kota: %s", %kota);
//        System.out.print("Alamat: %s", %alamat);
//        System.out.print("Tanggal lahir: %s", %tanggalLahir);
//        System.out.print("Nomor Telepon: %s", %noTelp);
//        System.out.print("No KTP: %s", %noKTP);
    }

}
