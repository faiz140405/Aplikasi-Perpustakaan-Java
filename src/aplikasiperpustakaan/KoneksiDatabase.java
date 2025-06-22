package aplikasiperpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDatabase {
    // Variabel untuk menyimpan koneksi
    private static Connection koneksi;

    /**
     * Metode untuk mendapatkan koneksi ke database.
     * Jika koneksi belum ada, metode ini akan membuatnya.
     * * @return Objek Connection yang siap digunakan.
     */
    public static Connection getKoneksi() {
        // Cek apakah koneksi sudah pernah dibuat sebelumnya
        if (koneksi == null) {
            try {
                // 1. Definisikan parameter koneksi database
                String url = "jdbc:mysql://localhost:3306/db_perpustakaan?serverTimezone=UTC"; // Nama database
                String user = "root"; // User default XAMPP
                String password = ""; // Password default XAMPP kosong

                // 2. Register driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 3. Buat koneksi
                koneksi = DriverManager.getConnection(url, user, password);
                
                System.out.println("Koneksi ke database berhasil!");

            } catch (ClassNotFoundException | SQLException e) { // INI BAGIAN YANG DIPERBAIKI
                // Tangani jika terjadi error
                JOptionPane.showMessageDialog(null, "Error: Gagal terhubung ke database!");
                System.err.println("Error koneksi database: " + e.getMessage());
                // Cetak stack trace untuk debug yang lebih detail
                e.printStackTrace();
            }
        }
        return koneksi;
    }

    // Metode main untuk testing koneksi
    public static void main(String[] args) {
        Connection conn = KoneksiDatabase.getKoneksi();
        // Jika conn tidak null, berarti koneksi berhasil
        if(conn != null){
            System.out.println("Testing koneksi berhasil.");
        } else {
            System.out.println("Testing koneksi gagal.");
        }
    }
}