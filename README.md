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
    class Petugas{
      -String contact
    }
    class Buku{
      -int IDBuku
      -String judulBuku
    } 
