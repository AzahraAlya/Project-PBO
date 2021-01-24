
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
    private DatePicker dpTanggalPinjam;

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
    
//    @FXML
//    private Button btnHapus;

    @FXML
    private TableView<Buku> tblBuku;

    @FXML
    private TableColumn<Buku, Integer> collIDBuku;

    @FXML
    private TableColumn<Buku, String> collJudulBuku;

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
    private TableColumn<Peminjam, String> colTanggalPinjam;

//    @FXML
//    private TableColumn<Peminjam, String> coljumlahbuku;
    
    //petugas
     @FXML
    private TextField tfIDPengguna1;

    @FXML
    private Button btnHapusButton1;

    @FXML
    private TextField tfNama1;

    @FXML
    private TextField tfAlamat1;

    @FXML
    private TextField txfKontak;

    @FXML
    private TextField tfIDBuku1;

    @FXML
    private TextField tfJudulBuku1;

    @FXML
    private Button btnTampilkanButton1;

    @FXML
    private Button btnSimpanButton1;

    @FXML
    private TextField tfIDPengguna2;

    @FXML
    private TableView<Petugas> tbPenggunaP;

    @FXML
    private TableColumn<Petugas, Integer> colIDpengguna1;

    @FXML
    private TableColumn<Petugas, String> colNama1;

    @FXML
    private TableColumn<Petugas, String> colAlamat1;

    @FXML
    private TableColumn<Petugas, String> colKontak1;

    @FXML
    private TableView<Buku> tbTBuku;

    @FXML
    private TableColumn<Buku, Integer> colIDBuku1;

    @FXML
    private TableColumn<Buku, String> colJudulBuku1;

    @FXML
    private TextField tfnewIDPengguna1;

    @FXML
    private TextField tfnewIDBuku1;

    @FXML
    private TextField tfnewJudulBuku1;

    @FXML
    private Button btnTambahButton1;
    
    @FXML
    private Label lblsavestatus1;
    
    //petugas
    
    @FXML
    private Label lbsavestatus;


    @FXML
    private Label lbDBStatus;
    
    private DataModel dm;

    @FXML
    void handleSimpanButton(ActionEvent event) {
        LocalDate ld = dpTanggalPinjam.getValue();
        String tanggalPinjam = String.format("%d-%02d-%02d", ld.getYear(),ld.getMonthValue(),ld.getDayOfMonth());
        Peminjam pengguna = new Peminjam(Integer.parseInt(tfIDPengguna.getText()),
                tfNama.getText(),
                tfAlamat.getText(),
                cbJenisKelamin.getSelectionModel().getSelectedItem(),
                tanggalPinjam, 
                new Buku(Integer.parseInt(tfIDBuku.getText()),tfJudulBuku.getText()));
        try {
            dm.addAkunPengguna(pengguna);
            lbsavestatus.setText("Akun Berhasil Dibuat");
          
        } catch (SQLException ex) {
            lbsavestatus.setText("Akun Gagal Dibuat");
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void handleTambahButton(ActionEvent event) {
         try {
            Buku b =  new Buku(Integer.parseInt(tfnewIDBuku.getText()),(tfnewJudulBuku.getText()));
            
            dm.addBuku(Integer.parseInt(tfnewIDPengguna.getText()),b);          
            viewDataBuku(Integer.parseInt(tfnewIDPengguna.getText()));
            btnTampilkan.fire();
            tfnewJudulBuku.setText("");    
        }
         catch (SQLException ex) {
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void handleHapusButton(ActionEvent event) {
        try {
            tfIDPengguna.setText(""+dm.nextAkunPenggunaID());
            tfIDBuku.setText("");
            tfNama.setText("");
            tfAlamat.setText("");
            cbJenisKelamin.getSelectionModel().clearSelection();
            tfJudulBuku.setText("");
            dpTanggalPinjam.setValue(LocalDate.of(LocalDate.now().getYear() - 17, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
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
        colTanggalPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalLahir"));
    
        tblAkunPengguna.setItems(null);
        tblAkunPengguna.setItems(data);
        btnTambah.setDisable(true);



    }
    
    //petugas
    @FXML
    void handleHapusButton1(ActionEvent event) {
        try {
            tfIDPengguna1.setText(""+dm.nextAkunPenggunaID());
            tfIDBuku1.setText("");
            tfNama1.setText("");
            tfAlamat1.setText("");
            txfKontak.setText("");
            tfJudulBuku1.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleSimpanButton1(ActionEvent event) {
          
        Petugas pengguna = new Petugas(Integer.parseInt(tfIDPengguna1.getText()),
                tfNama1.getText(),
                tfAlamat1.getText(),
                txfKontak.getText(),
                new Buku(Integer.parseInt(tfIDBuku1.getText()),tfJudulBuku1.getText()));
        try {
            dm.addAkunPengguna(pengguna);
            lblsavestatus1.setText("Akun Berhasil Dibuat");
          
        } catch (SQLException ex) {
            lblsavestatus1.setText("Akun Gagal Dibuat");
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    @FXML
    void handleTambahButton1(ActionEvent event) {
        try {
            Buku b1 =  new Buku(Integer.parseInt(tfnewIDBuku1.getText()),(tfnewJudulBuku1.getText()));
            
            dm.addBuku(Integer.parseInt(tfnewIDPengguna1.getText()),b1);          
            viewDataBuku1(Integer.parseInt(tfnewIDPengguna1.getText()));
            btnTampilkanButton1.fire();
            tfnewJudulBuku1.setText("");    
        }
         catch (SQLException ex) {
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    void handleTampilkanButton1(ActionEvent event) {
        ObservableList<Petugas> data = dm.getPetugas();
        colIDpengguna1.setCellValueFactory(new PropertyValueFactory<>("IDpengguna"));
        colNama1.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat1.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        tbPenggunaP.setItems(null);
        tbPenggunaP.setItems(data);
        btnTambahButton1.setDisable(true);

    }

    //petugas

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

           ObservableList<String> jeniskelamin = FXCollections.observableArrayList("Laki-Laki", "Perempuan");
        cbJenisKelamin.setItems(jeniskelamin);
        try {
            dm = new DataModel("MYSQL");
            lbDBStatus.setText(dm.conn == null ? "Tidak Terhubung" : "Terhubung");
        } catch (SQLException ex) {
            Logger.getLogger(AkunPenggunaFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tblAkunPengguna.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tblAkunPengguna.getSelectionModel().getSelectedItem()!=null){
                Peminjam pinjam =  tblAkunPengguna.getSelectionModel().getSelectedItem();
                viewDataBuku(pinjam.getIDpengguna());
                btnTambah.setDisable(false);
                tfnewIDPengguna.setText(""+pinjam.getIDpengguna());
            }
        });   
        
        tbPenggunaP.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tbPenggunaP.getSelectionModel().getSelectedItem()!=null){
                Petugas pinjam1 =  tbPenggunaP.getSelectionModel().getSelectedItem();
                viewDataBuku1(pinjam1.getIDpengguna());
                btnTambahButton1.setDisable(false);
                tfnewIDPengguna1.setText(""+pinjam1.getIDpengguna());            
            }
        });   
        
    }    
    
    public void viewDataBuku(int penggunaID){
        ObservableList<Buku> data = dm.getBuku(penggunaID);
        collIDBuku.setCellValueFactory(new PropertyValueFactory<>("IDbuku"));
        collJudulBuku.setCellValueFactory(new PropertyValueFactory<>("judulbuku"));
        tblBuku.setItems(null);
        tblBuku.setItems(data);
    }
    public void viewDataBuku1(int penggunaID){
        ObservableList<Buku> data = dm.getBuku(penggunaID);
        colIDBuku1.setCellValueFactory(new PropertyValueFactory<>("IDbuku"));
        colJudulBuku1.setCellValueFactory(new PropertyValueFactory<>("judulbuku"));
        tbTBuku.setItems(null);
        tbTBuku.setItems(data);
    }
     
}
