
package ProjectPBO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class AkunPenggunaFormController implements Initializable {
    
   
    @FXML
    private TextField tfIDPengguna;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfAlamat;

    @FXML
    private ComboBox<String> cbJenisKelamin;

    @FXML
    private DatePicker dpTanggalLahir;

    @FXML
    private TextField tfIDBuku;

    @FXML
    private TextField tfJudulBuku;

    @FXML
    private Button btnSimpan;

    @FXML
    private Button btnTampilkan;

    @FXML
    private Button btnHapusButton;

    @FXML
    private TableView<?> tblBuku;

    @FXML
    private TableColumn<?, ?> collIDBuku;

    @FXML
    private TableColumn<?, ?> collJudulBuku;

    @FXML
    private TextField tfnewIDPengguna;

    @FXML
    private TextField tfnewIDBuku;

    @FXML
    private TextField tfnewJudulBuku;

    @FXML
    private Button btnTambah;

    @FXML
    private TableView<Peminjam> tblAkunPengguna;

    @FXML
    private TableColumn<Peminjam, Integer> colIDpengguna;

    @FXML
    private TableColumn<Peminjam, String> colNama;

    @FXML
    private TableColumn<Peminjam, String> colAlamat;

    @FXML
    private TableColumn<Peminjam, String> colJenisKelamin;

    @FXML
    private TableColumn<Peminjam, String> colTanggalLahir;

//    @FXML
//    private TableColumn<Peminjam, String> coljumlahbuku;
    
    @FXML
    private Label lbsavestatus;


    @FXML
    private Label lbDBStatus;
    
    private DataModel dm;

    @FXML
    void handleSimpanButton(ActionEvent event) {
         LocalDate ld = dpTanggalLahir.getValue();
        String tanggal = String.format("%d-%02d-%02d", ld.getYear(),ld.getMonthValue(),ld.getDayOfMonth());
        Peminjam pengguna = new Peminjam(Integer.parseInt(tfIDPengguna.getText()),
                tfNama.getText(),
                tfAlamat.getText(),
                cbJenisKelamin.getSelectionModel().getSelectedItem(),
                tanggal, 
                new Buku(Integer.parseInt(tfIDBuku.getText()),tfJudulBuku.getText()));
        try {
            dm.addAkunPengguna(pengguna);
            lbsavestatus.setText("Akun Berhasil Dibuat");
            btnTambah.fire();
            btnHapusButton.fire();
          
        } catch (SQLException ex) {
            lbsavestatus.setText("Akun Gagal Dibuat");
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void handleTambahButton(ActionEvent event) {
//         try {
//            Buku b =  new Buku(Integer.parseInt(tfnewIDBuku.getText()),(tfnewJudulBuku.getText()));
//            
//            dm.addBuku(Integer.parseInt(tfnewIDPengguna.getText()),b);          
//            viewDataAccount(Integer.parseInt(tfnewIDPengguna.getText()));
//            btnTampilkan.fire();
//            tfnewJudulBuku.setText("");    
//        }
//         catch (SQLException ex) {
//            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    @FXML
    void handleHapusButton(ActionEvent event) {
        try {
            tfIDPengguna.setText(""+dm.nextAkunPenggunaID());
            tfIDBuku.setText(tfIDPengguna.getText()+"01");
            tfNama.setText("");
            tfAlamat.setText("");
            cbJenisKelamin.getSelectionModel().clearSelection();
            tfJudulBuku.setText("");
            dpTanggalLahir.setValue(LocalDate.of(LocalDate.now().getYear() - 17, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
        } catch (SQLException ex) {
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }

    @FXML
    void handleTampilkanButton(ActionEvent event) {
        ObservableList<Peminjam> data = dm.getPeminjam();
        colIDpengguna.setCellValueFactory(new PropertyValueFactory<>("IDpengguna"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colJenisKelamin.setCellValueFactory(new PropertyValueFactory<>("jeniskelamin"));
        colTanggalLahir.setCellValueFactory(new PropertyValueFactory<>("tanggalLahir"));
     //   coljumlahbuku.setCellValueFactory(new PropertyValueFactory<>("jumlahbuku"));
        tblAkunPengguna.setItems(null);
        tblAkunPengguna.setItems(data);
   //     btnTampilkan.setDisable(true);


    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           ObservableList<String> jeniskelamin = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
        cbJenisKelamin.setItems(jeniskelamin);
        try {
            dm = new DataModel("MYSQL");
            lbDBStatus.setText(dm.conn == null ? "Tidak Terhubung" : "Terhubung");
       //     btnHapusButton.fire();
            btnTambah.fire();
            
        } catch (SQLException ex) {
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
