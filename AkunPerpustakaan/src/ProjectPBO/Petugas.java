
package ProjectPBO;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Petugas extends AkunPengguna {
    
    StringProperty contact;

    public Petugas(Integer IDpengguna, String nama, String alamat, String contact, ArrayList<Buku> accounts) {
        super(IDpengguna, nama, alamat, accounts);
        this.contact=new SimpleStringProperty(contact);
    }

    public Petugas(Integer IDpengguna, String nama, String alamat, String contact, Buku book) {
        super(IDpengguna, nama, alamat, book);
        this.contact.set(contact);
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }
    public StringProperty contactProperty() {
        return contact;
    
}

}