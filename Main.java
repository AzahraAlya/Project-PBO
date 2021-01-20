
package ProjectPBO;

import ProjectPBO.DB.DBHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AkunPenggunaForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        try{
//            DataModel dm = new DataModel("MYSQL");
//            Peminjam p = new Peminjam(1,"Azahra","Bandar Lampung", "Female", "2002-07-25", new Buku(1,"Dilan"));
//            dm.addAkunPengguna(p);
//            System.out.println("Sukses ditambahkan");
        
//        try {
//           if (null != DBHelper.getConnection("MYSQL")){
//               System.out.println("Koneksi Sukses");
//           }else {
//               System.out.println("Koneksi GAGAL");
//           }
                 launch(args);
//       } catch (SQLException ex) {
//           System.out.println("Gagal ditambahkan");
//         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//       }

    }
    
}
