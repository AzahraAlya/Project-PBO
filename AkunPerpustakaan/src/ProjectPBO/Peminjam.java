
package ProjectPBO;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Peminjam extends AkunPengguna {

    StringProperty jeniskelamin;
    StringProperty tanggalLahir;

    public Peminjam(Integer IDpengguna, String nama, String alamat, String jeniskelamin, String tanggalLahir, ArrayList<Buku> accounts) {
        super(IDpengguna, nama, alamat, accounts);
        this.jeniskelamin = new SimpleStringProperty(jeniskelamin);
        this.tanggalLahir = new SimpleStringProperty(tanggalLahir);
    }

    public Peminjam(Integer IDpengguna, String nama, String alamat, String jeniskelamin, String tanggalLahir, Buku akun) {
        super(IDpengguna, nama, alamat, akun);
        this.jeniskelamin = new SimpleStringProperty(jeniskelamin);
        this.tanggalLahir = new SimpleStringProperty(tanggalLahir);
    }

    
    public String getJeniskelamin() {
        return jeniskelamin.get();
    }

    public void setJeniskelamin(String gender) {
        this.jeniskelamin.set(gender);
    }

    public String getTanggalLahir() {
        return tanggalLahir.get();
    }

    public void setTanggalLahir(String birthdate) {
        this.tanggalLahir.set(birthdate);
    }

    public StringProperty jeniskelaminProperty() {
        return jeniskelamin;
    }

    public StringProperty tanggalLahirProperty() {
        return tanggalLahir;
    }
    
}
