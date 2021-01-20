
package ProjectPBO;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class AkunPengguna {

    private IntegerProperty IDpengguna;
    private StringProperty nama;
    private StringProperty alamat;
    private IntegerProperty jumlahbuku;
    private ArrayList<Buku> book;

    
    public AkunPengguna(Integer IDpengguna, String nama, String alamat,
        ArrayList<Buku> book) {
        this.IDpengguna=new SimpleIntegerProperty(IDpengguna);
        this.nama= new SimpleStringProperty(nama);
        this.alamat=new SimpleStringProperty(alamat);
        this.book = book;
       this.jumlahbuku =new SimpleIntegerProperty(book.size());
    }
    public AkunPengguna(Integer IDpengguna, String nama, String alamat, Buku akun) {
        book = new ArrayList<>();
        this.IDpengguna=new SimpleIntegerProperty(IDpengguna);
        this.nama=new SimpleStringProperty(nama);
        this.alamat=new SimpleStringProperty(alamat);
        this.book.add(akun);
        this.jumlahbuku=new SimpleIntegerProperty(book.size());
    }
    

    public Integer getIDpengguna() {
        return IDpengguna.get();
    }

    public void setIDpengguna(Integer IDpengguna) {
        this.IDpengguna.set(IDpengguna);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public Integer getJumlahbuku() {
        return jumlahbuku.get();
    }

    public ArrayList<Buku> getBook() {
        return book;
    }

    public void setAkuns(ArrayList<Buku> book) {
        this.book = book;
    }
     
    public IntegerProperty IDpenggunaProperty(){
        return IDpengguna;
    }
    public StringProperty namaProperty(){
        return nama;
    }
    public StringProperty alamatProperty(){
        return alamat;
    }
    public IntegerProperty jumlahbukuProperty(){
        return jumlahbuku;
    }
//    public void addBuku(Buku books) {
//        this.book.add(books);
//    }

}

