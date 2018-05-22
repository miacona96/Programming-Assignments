/**
 * Michael Iacona
 */
import java.sql.*;

public class BoatRental {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost";

    // Database credentials
    static final String USER = "root";
    //the user name
    static final String PASS = "mji102196";
    //the password

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{

            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection to database
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String drop = "DROP DATABASE IF EXISTS BoatRental";
            stmt.executeUpdate(drop);
            //Use SQL to Create Database;
            String sql = "CREATE DATABASE BoatRental";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");

            sql = "use BoatRental";
            stmt.executeUpdate(sql);

            // Table Sailor
            sql = "create table Sailor( sid integer not null PRIMARY KEY, " + "sname varchar(20) not null," + "rating real not null," + "age integer not null )";
            stmt.executeUpdate(sql);

            // Table Boats
            sql = "create table Boats( bid integer not null PRIMARY KEY, bname varchar(40) not null, color varchar(40) not null )";
            stmt.executeUpdate(sql);

            // Table Reserves
            sql = "create table Reserves(sid integer not null, bid integer not null, day date not null," +
                    "PRIMARY KEY( sid, bid, day ), FOREIGN KEY (sid) REFERENCES Sailor(sid), FOREIGN KEY (bid) REFERENCES Boats(bid) )";
            stmt.executeUpdate(sql);

            // Tuples for Sailors
            sql = "insert into Sailor values( 22, 'Dustin', 7, 45 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values( 29, 'Brutus', 1, 33 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values( 31, 'Lubber', 8, 55 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values(32, 'Andy', 8, 26 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values( 58, 'Rusty', 10, 35 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values( 64, 'Horatio', 7, 35 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values( 71, 'Zorba', 20, 18 )";
            stmt.executeUpdate(sql);
            sql = "insert into Sailor values( 74, 'Horatio', 9, 35 )";
            stmt.executeUpdate(sql);

            // Tuples for Boats
            sql = "insert into Boats values( 101, 'Interlake', 'Blue' )";
            stmt.executeUpdate(sql);
            sql = "insert into Boats values( 102, 'Interlake', 'Red' )";
            stmt.executeUpdate(sql);
            sql = "insert into Boats values( 103, 'Clipper', 'Green' )";
            stmt.executeUpdate(sql);
            sql = "insert into Boats values( 104, 'Marine', 'Red' )";
            stmt.executeUpdate(sql);

            // Tuples for Reserves
            sql = "insert into Reserves values( 22, 101, '2008-10-10' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 22, 102, '2008-10-10' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 22, 103, '2009-10-8' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 22, 104, '2009-10-9' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 31, 102, '2008-11-10' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 31, 103, '2008-11-6' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 31, 104, '2008-11-12')";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 64, 101, '2008-9-5' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 64, 102, '2008-9-8' )";
            stmt.executeUpdate(sql);
            sql = "insert into Reserves values( 74, 103, '2008-9-8' )";
            stmt.executeUpdate(sql);

            //Q1: Find the sids of all sailors who have reserved red boats but not green boats
            Statement question1 = conn.createStatement();
            question1.executeQuery ( "SELECT S.sname, S.sid FROM Sailor S, Boats B, Reserves R WHERE S.sid = R.sid AND R.bid = B.bid AND B.color = 'red' AND S.sid NOT IN (SELECT S2.sid FROM Sailor S2, Boats B2, Reserves R2 WHERE S2.sid = R2.sid AND R2.bid = B2.bid AND B2.color = 'green' )");
            ResultSet ans1 = question1.getResultSet();
            int cnt1 = 0;
            int cnt2 = 0;
            int cnt3 = 0;
            int cnt4 = 0;
            int cnt5 = 0;

            System.out.println( "Question 1: " );

            while ( ans1.next() ) {
                System.out.println( "Sailor name: " + ans1.getString("sname") + " ; sid: " + ans1.getString("sid" ) );
                ++cnt1;
            }

            ans1.close();
            question1.close();

            if( cnt1 == 1 ){
                System.out.println( cnt1 + " row retrieved" );
            }
            else{
                System.out.println( cnt1 + " rows retrieved" );
            }

            //Q2: Find the names of sailors who have NOT reserved a red boat
            Statement question2 = conn.createStatement();
            question2.executeQuery( "SELECT S.sname, S.sid FROM Sailor S WHERE S.sid NOT IN (SELECT R.sid FROM Reserves R WHERE R.bid IN (SELECT B.bid FROM Boats B WHERE B.color = 'red') )" );
            ResultSet ans2 = question2.getResultSet();
            cnt2 = 0;
            System.out.println( "Question 2: " );

            while ( ans2.next() ) {
                System.out.println( "Sailor name: " + ans2.getString( "sname" ) + " ; sid: " + ans2.getString("sid") );
                cnt2++;
            }

            ans2.close();
            question2.close();

            if( cnt2 == 1 ){
                System.out.println( cnt2 + " row retrieved" );
            }
            else{
                System.out.println( cnt2 + " rows retrieved" );
            }

            //Q3: Find sailors whose rating is better than every sailor named Horatio.
            Statement question3 = conn.createStatement();
            question3.executeQuery( "SELECT S.sname, S.sid FROM Sailor S WHERE S.rating > ALL(SELECT S2.rating FROM Sailor S2 WHERE S2.sname = 'Horatio')" );
            ResultSet ans3 = question3.getResultSet();
            cnt3 = 0;
            System.out.println( "Question 3: " );

            while ( ans3.next() ) {
                System.out.println( "Sailor name: " + ans3.getString("sname") + " ; sid: " + ans3.getString("sid") );
                cnt3++;
            }

            ans3.close();
            question3.close();

            if( cnt3 == 1 ){
                System.out.println( cnt3 + " row retrieved" );
            }
            else{
                System.out.println( cnt3 + " rows retrieved" );
            }

            //Q4: Find the names of sailors who have reserved all boats.
            Statement question4 = conn.createStatement();
            question4.executeQuery( "SELECT s.sname, s.sid FROM Sailor s WHERE NOT EXISTS ( SELECT b.bid FROM Boats b WHERE NOT EXISTS(SELECT r.bid FROM RESERVES r WHERE r.bid = b.bid AND r.sid = s.sid ))" );
            ResultSet ans4 = question4.getResultSet();
            cnt4 = 0;
            System.out.println( "Question 4: " );

            while ( ans4.next() ) {
                System.out.println( "Sailor name: " + ans4.getString("sname") + " ; sid: " + ans4.getString("sid") );
                cnt4++;
            }

            ans4.close();
            question4.close();

            if( cnt4 == 1 ){
                System.out.println( cnt4 + " row retrieved" );
            }
            else{
                System.out.println( cnt4 + " rows retrieved" );
            }

            //Q5: For each red boat, find its number of reservations.
            Statement question5 = conn.createStatement();
            question5.executeQuery( "SELECT b.bid, COUNT(*) AS scount FROM Boats b, Reserves R WHERE r.bid = b.bid AND b.color = 'Red' GROUP BY b.bid" );
            ResultSet ans5 = question5.getResultSet();
            cnt5 = 0;
            System.out.println( "Question 5: " );

            while ( ans5.next() ) {
                System.out.println( "Boat bid: " + ans5.getString("bid") + " ; num reservations: " + ans5.getString("scount") );
                cnt5++;
            }

            ans5.close();
            question5.close();

            if( cnt5 == 1 ){
                System.out.println( cnt5 + " row retrieved" );
            }
            else{
                System.out.println( cnt5 + " rows retrieved" );
            }


        }catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ( "Database connection terminated" );
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
        System.out.println( "Goodbye!" );
    }
}