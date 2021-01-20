
package ProjectPBO;

import ProjectPBO.DB.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DataModel {
    public final Connection conn;

    public DataModel(String driver) throws SQLException {
        this.conn=DBHelper.getConnection(driver);
    }
    public void addAkunPengguna(Peminjam pinjam) throws SQLException{
        String InsertPengguna = "INSERT INTO akun_pengguna(id_pengguna,nama,alamat)"+
                "VALUES(?,?,?)";
        String InsertPeminjam = "INSERT INTO peminjam(id_pengguna,jenis_kelamin,tanggal_lahir)"+
                "VALUES(?,?,?)";
        String InsertBuku = "INSERT INTO buku(id_buku,judul_buku,id_pengguna)"+
                "VALUES(?,?,?)";
        
        PreparedStatement statePengguna = conn.prepareStatement(InsertPengguna);
        statePengguna.setInt(1,pinjam.getIDpengguna());
        statePengguna.setString(2,pinjam.getNama());
        statePengguna.setString(3,pinjam.getAlamat());
        statePengguna.execute();
        
        PreparedStatement statePeminjam = conn.prepareStatement(InsertPeminjam);
        statePeminjam.setInt(1,pinjam.getIDpengguna());
        statePeminjam.setString(2,pinjam.getJeniskelamin());
        statePeminjam.setString(3,pinjam.getTanggalLahir());
        statePeminjam.execute();
        
        PreparedStatement stateAkun = conn.prepareStatement(InsertBuku);
        stateAkun.setInt(1,pinjam.getBook().get(0).getIDbuku());
        stateAkun.setString(2,pinjam.getBook().get(0).getJudulbuku());
        stateAkun.setInt(3,pinjam.getIDpengguna());
        stateAkun.execute();
    }
    
    public void addAkunPengguna(Petugas pinjam) throws SQLException{
         String InsertPengguna = "INSERT INTO akun_pengguna(id_pengguna,nama,alamat)"+
                "VALUES(?,?,?)";
        String InsertPetugas = "INSERT INTO petugas(id_pengguna,contact)"+
                "VALUES(?,?)";
        String InsertBuku = "INSERT INTO buku(id_buku,judul_buku,id_pengguna)"+
                "VALUES(?,?,?)";
        
        PreparedStatement statePengguna = conn.prepareStatement(InsertPengguna);
        statePengguna.setInt(1,pinjam.getIDpengguna());
        statePengguna.setString(2,pinjam.getNama());
        statePengguna.setString(3,pinjam.getAlamat());
        statePengguna.execute();
        
        PreparedStatement statePeminjam = conn.prepareStatement(InsertPetugas);
        statePeminjam.setInt(1,pinjam.getIDpengguna());
        statePeminjam.setString(2,pinjam.getContact());
        statePeminjam.execute();
        
        PreparedStatement stateAkun = conn.prepareStatement(InsertBuku);
        stateAkun.setInt(1,pinjam.getBook().get(0).getIDbuku());
        stateAkun.setString(2,pinjam.getBook().get(0).getJudulbuku());
        stateAkun.setInt(3,pinjam.getIDpengguna());
        stateAkun.execute();
        
    }
     public ObservableList<Peminjam> getPeminjam(){
        ObservableList<Peminjam> data = FXCollections.observableArrayList();
        String sql="SELECT `id_pengguna`, `nama`,`alamat`, `jenis_kelamin`, `tanggal_lahir` "
                + "FROM `akun_pengguna` NATURAL JOIN `peminjam` "
                + "ORDER BY nama";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlBuku = "SELECT id_buku, judul_buku "
                    + "FROM buku WHERE id_pengguna="+rs.getInt(1);
                ResultSet rsAccount = conn.createStatement().executeQuery(sqlBuku);
                ArrayList<Buku> dataAccount = new ArrayList<>();
                while (rsAccount.next()){
                    dataAccount.add(new Buku(rsAccount.getInt(1),rsAccount.getString(2)));
                }
                data.add(new Peminjam(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), dataAccount));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    public ObservableList<Petugas> getPetugas(){
        ObservableList<Petugas> data = FXCollections.observableArrayList();
        String sql="SELECT `id_pengguna`, `nama`,`alamat`, `contact` "
                + "FROM `akun_pengguna` NATURAL JOIN `petugas` "
                + "ORDER BY nama";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlBuku = "SELECT id_buku, judul_buku "
                    + "FROM buku WHERE id_pengguna="+rs.getInt(1);
                ResultSet rsAccount = conn.createStatement().executeQuery(sqlBuku);
                ArrayList<Buku> dataAccount = new ArrayList<>();
                while (rsAccount.next()){
                    dataAccount.add(new Buku(rsAccount.getInt(1),rsAccount.getString(2)));
                }
                data.add(new Petugas(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), dataAccount));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    public ObservableList<Buku> getBuku(int penggunaID){
        ObservableList<Buku> data = FXCollections.observableArrayList();
        String sql="SELECT `id_buku`, `judul_buku` "
                + "FROM `buku` "
                + "WHERE id_pengguna="+penggunaID;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                data.add(new Buku(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    public int nextAkunPenggunaID() throws SQLException{
        String sql="SELECT MAX(id_pengguna) from akun_pengguna";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)==0?1000001:rs.getInt(1)+1;
            }
        return 1000001;
    }
    public int nextIDBuku(int penggunaID) throws SQLException{
        String sql="SELECT MAX(id_buku) FROM buku WHERE id_pengguna="+penggunaID;
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)+1;
            }
        return 0;
    }
    
//    public void addAccount(int holderID, Account acc) throws SQLException{
//        String insertHolder = "INSERT INTO account (holder_id, acc_number, balance)"
//                + " VALUES (?,?,?)";
//  
//        PreparedStatement stmtHolder = conn.prepareStatement(insertHolder);
//        stmtHolder.setInt(1, holderID);
//        stmtHolder.setInt(2, acc.getAccNumber());
//        stmtHolder.setDouble(3, acc.getBalance());
//        stmtHolder.execute();
        
    }

    

