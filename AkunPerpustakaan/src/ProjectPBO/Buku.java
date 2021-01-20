
package ProjectPBO;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Buku {
   
    private IntegerProperty IDbuku;
    private StringProperty judulbuku;

    

    public Buku(int IDbuku, String judulbuku) {
        this.IDbuku=new SimpleIntegerProperty(IDbuku);
        this.judulbuku=new SimpleStringProperty(judulbuku);
    }
    

    public String getJudulbuku() {
        return judulbuku.get();
    }

    public void setJudulbuku(String judulbuku) {
        this.judulbuku.set(judulbuku);
    }

    public Integer getIDbuku() {
        return IDbuku.get();
    }

    public void setIDbuku(int IDbuku) {
        this.IDbuku.set(IDbuku);
    }
    public IntegerProperty getIDbukuProperty(){
        return IDbuku;
    }
    public StringProperty JudulbukuProperty(){
        return judulbuku;

    }
    }


