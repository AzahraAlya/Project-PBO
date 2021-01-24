# Project-PBO
Jihan Cahya Fatimah 1917051016 (membuat class dan subclass)
Azahra Alya Hidayah 1917051017 (membuat Design fxml)
Dina Putri Aulia 1957041004 (membuat database)

# Sistem Peminjaman Buku
Program ini merupakan program sistem peminjaman buku perpustakaan. Jika ingin meminjam buku harus membuat akun perpustakaan terlebih dahulu. Akun perpustakaan terdiri dari 2 jenis akun yaitu akun pengunjung dan akun petugas. Baik pengunjung maupun petugas boleh meminjam buku lebih dari satu buku.

Libraries and Tools of this project:

mysql-connector-java-5.1.xx.jar
sqlite-jdbc-3.32.jar
scene builder
mysql server (xampp recomended)
netbean editor

# Class Diagram
    classDiagram
    AkunPengguna <|-- Peminjam
    AkunPengguna <|-- Petugas
    AkunPengguna "1"--o"*" Buku : borrow
    
    class AkunPengguna{
      <<abstract>>
      -int IDpengguna
      -String nama
      -String alamat
      +int nextID()
    }
    
    class Peminjam{
      -String jenisKelamin
      -String tanggalPinjam
    }
    
   # ER Diagram
     erDiagram
          AkunPengguna ||..|| Peminjam : is
          AkunPengguna||--|| Petugas : is
          AkunPengguna ||--|{ Buku: "borrow"
          AkunPengguna {
            int IDpengguna
            string nama
            string alamat
          }
          Peminjam {
            string jenisKelamin
            string tanggalPinjam
          }
          Petugas{
            string contact
          }
          Buku{
            int IDBuku
            string judulBuku
          }
    class Petugas{
      -String contact
    }
    class Buku{
      -int IDBuku
      -String judulBuku
    } 
    
   # Design Class Diagram for JavaFX and Database
     classDiagram
    AkunPengguna <|-- Peminjam
    AkunPengguna <|-- Petugas
    AkunPengguna "1"--o"*" Buku : borrow
    AkunPengguna o-- DataModel : Data Modeling
    DataModel <-- AkunPenggunaFormController : Data Control
    DataModel --> DBHelper : DB Connection
    AkunPenggunaFormController <.. AkunPenggunaForm : Form Control      

    class AkunPengguna{
      <<abstract>>
      -IntegerProperty IDpengguna
      -StringProperty nama
      -StringProperty alamat
      
      +IntegerProperty nextID()
    }
    
    class Peminjam{
      -StringProperty jenisKelamin
      -StringProperty tanggalPinjam
    }
    class Petugas{
      -StringProperty contact
    }
    class Buku{
      -IntegerProperty IDbuku
      -StringProperty judulBuku
      +String getJudulBuku()
    }

    class DataModel{
        Connection conn
        addAkunPengguna()
        addBuku()
        getPeminjam()
        getPetugas()
        nextAkunPenggunaID()
    }

    class AkunPenggunaFormController{
        initialize()
        handleButtonTambah()
        handleSimpanButton()
        loadDataPeminjam()
        loadDataPetugas()
        loadDataBuku()
        handleHapusButton()
    }
    class DBHelper{
        - String USERNAME
        - String PASSWORD
        - String DB
        getConnection()
        getConnection(String driver)
    }

