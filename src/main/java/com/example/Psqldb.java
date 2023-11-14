package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Psqldb {

    private PasswordEncoder passwordEncoder;

    public Psqldb() {
    }

    // https://www.geeksforgeeks.org/how-to-insert-records-to-a-table-using-jdbc-connection/

    public void connect() {
        String url = "";
        String driverdb = "";

        url = "jdbc:postgresql://localhost:5432/multidbapp";
        driverdb = "org.postgresql.Driver";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverdb);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername("multidbuser");
        basicDataSource.setPassword("Calcite2022");

        System.out.println("connecting to database soruce: " + url);
        try {
            Connection conn = basicDataSource.getConnection();
            System.out.println("connected to database soruce: " + url);

            Statement stmt = conn.createStatement(); 
  
            // executeUpdate() is used for INSERT, UPDATE, 
            // DELETE statements.It returns number of rows 
            // affected by the execution of the statement
            String email = "multidbuser@onesql.com";
            String password = "Mdb2023xyzbcd";
            passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            // email character varying(255) COLLATE pg_catalog."default",
            // firstname character varying(255) COLLATE pg_catalog."default",
            // lastname character varying(255) COLLATE pg_catalog."default",
            // password character varying(255) COLLATE pg_catalog."default",
            // role character varying(255) COLLATE pg_catalog."default",

            String sqlString = "insert into mdbusers(email, firstname, lastname, password, role) ";
            sqlString = sqlString + "values('" + email + "'";
            sqlString = sqlString + ", 'Multidb'";
            sqlString = sqlString + ", 'User'";
            sqlString = sqlString + ", '" + encodedPassword + "'";
            sqlString = sqlString + ", 'user')";

            System.out.println("sqlString: " + sqlString);
            int result = stmt.executeUpdate(sqlString); 
  
            // if result is greater than 0, it means values 
            // has been added 
            if (result > 0) {
                System.out.println("successfully inserted"); 
            } else {
                System.out.println("unsucessful insertion ");             
            }
  
            // closing connection 
            conn.close(); 

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }

        // DatabaseMetaData databaseMetaData = con.getMetaData();
                // physicalTable = physicalTable.toLowerCase();
                // String dbms = dbconn.getDbms();
                // boolean tableFound = false;
                // if (dbms.equalsIgnoreCase("ORACLE")) {
                //     System.out.println("Oracle Database");
                //     physicalTable = physicalTable.toUpperCase();

                //     Statement stmt = con.createStatement();
                //     String query = "select table_name from user_tables";
                //     ResultSet rs1 = stmt.executeQuery(query);
                //     System.out.println("query: " + query);


    }

}
