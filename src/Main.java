import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        // Template  "jdbc:<Provider>://localhost:<port-provider>/<nama-databease>";
        String url = "jdbc:postgresql://localhost:5432/warung_makan_bahari";
        String username = System.getenv("DB_USERNAME"); // ini kan user, password dann databease ketahuan, user, ini bahaya , ini bisa diakali dengan
        String password = System.getenv("DB_PASSWORD"); // yaitu dengan diakali di sistem, dengan mebuat variablke di OS (Operating system), atau bisa langsung di terminal dengan path yang sesuai..
                                      // untuk opsi lebih mudah, kita bisa simpan di intellij, dan dia berada di entry-point (Main)..
        //NB (teknologi baru): di Java 7 keatas, ada konsep yang bernama 'Try-With-Resources'TWR suatu blok try yang mampu meng-close sumber daya,
        // entah itu, statement, file, connection, buffer dll. tanpa melakukan .close() berikut adalah contohnya
        try(Connection connection = DriverManager.getConnection(url, username, password)) {
            try(Statement statement = connection.createStatement()) {
                //statement.close(); // tak perlu melakukan ini, karena redundance
                String query = "SELECT   * FROM customer_table"; // idelanya jangan pakai all, karena akan terjadi acak, atau urutan yang tak terkontrol,
                // jadi seharusnya SELECT nama_kolom1,2,3,... FROM nama_tabel ; kenpa? karena lebih terkontrol,, kita bisa atur sesuai keinginan kita urutanya
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()) {
                    System.out.println("=".repeat(50));
//                    System.out.println("ID      : "+resultSet.getInt("jembut"));
                    System.out.println("Name    : "+resultSet.getString("customer_name"));
                    System.out.println("Phone   : "+resultSet.getString(4)); // angka ini menunjukan kolom ke - ...
                    System.out.println("Member  : "+resultSet.getBoolean("member_status"));
                    System.out.println("=".repeat(50));
//                    jika data tidak ada, diat tidak error, karena tidak di-looping
                }
            }catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
            //connection.close(); // tak perlu melakukan ini, karena redundance
        }catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }



//        saveCustomer(url, username, password);
//        System.out.println("\nHALLO TEST");

    }// telek lincung

    private static void saveCustomer(String url, String username, String password) {
        Connection connection = null;
        //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/warung_makan_bahari",System.getenv("DB_USERNAME"),System.getenv("DB_PASSWORD"));

        //ConnectionPool pool = new ConnectionPool(url, username, password); //ini untuk multi koneksi
        try {

           //sekerang kita mulai,
           // 01. INSERT, kita harus membuat query SQL, yang kita masukan ke dalam 'statement (nama bisa terserah)' suatu object yang dibentuk dari class Statement di java untuk menuliskan Query.
           // dimana nanti kita tulis 'statement' tersebut di 'connection' sintax atau variabel yang berisi penghubung ke database (API),

           connection = DriverManager.getConnection(url, username, password);
           Statement statement = connection.createStatement(); // 'createStatement()' adalah inetrface
           // sekarang kita tulis Querynya
           String query = "insert into customer_table (customer_name , customer_phone , member_status) values ('Simanjuntak','081226999703',true);"; // agar tidak terjadi asal insert , kabu buat pengondisian, atau aturan2 tertentu baik di dalam DB (tabelnya), atau di aplikasi java ini sendiri, misal nama gak boleh null seperti disamping.
           int resultCount =  statement.executeUpdate(query);
           // '.executeUpdate()' ini untuk melakukan perintah query DML (Data Manipulating Language), bisa DDL cuman berisiko tinggi merusak bahkan menghilangkan Data.

           if (resultCount <= 0){
               statement.close();
               connection.close();
               throw new RuntimeException("Error while create new customer");
           }
           System.out.println("Penasaran? , ini dia   "+resultCount);

           statement.close();
           //connection.close(); // ini tidak perlu close connection lagi, karena telah di los di bawah didalam finaly

       }catch (SQLException e){
          // e.printStackTrace(); // opsi 1 "printStackTrace()" untuk menampilkan error apa adanya wkwkwk, atau juba bisa pakai throw
           throw new RuntimeException(e); //opsi 2. tapi kode setelahnya mati gak bisa dieksekusi seperti error biasa,  paling pas opsi 1 & 3
          //  System.out.println(e.getMessage() +" Hallo"); // opsi 3
       }finally {
           if (connection != null){
               try {
                   connection.close();
               }catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }
       }
    }
}