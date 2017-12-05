/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafinal;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.channels.FileChannel;
import java.text.*;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;


public class WriteCustomerList {

    final String ID_FORMAT = "0000";
    final String NAME_FORMAT = "               ";
    final int NAME_LENGTH = NAME_FORMAT.length();
    final String BALANCE_FORMAT = "00000.00";
    String delimiter = ",";
    String defaultRecord = ID_FORMAT + delimiter + NAME_FORMAT + delimiter + NAME_FORMAT + delimiter + BALANCE_FORMAT + System.getProperty("line.separator");
    int record_size = defaultRecord.length();
    Path newPath = Paths.get("C:\\Users\\yearup\\Desktop\\Blah.txt");
    
    public static void main(String[] args) {
        
        try{         
              WriteCustomerList writeCL = new WriteCustomerList();
              writeCL.writeDefaultRecords();
              writeCL.writeActualRecords();
        }
        catch(Exception e)
        {
            System.out.println("Encountered exception : " + e.toString());
        }
    }
    
    public void writeDefaultRecords() {
        try {
             OutputStream outStream = new BufferedOutputStream(Files.newOutputStream(newPath, CREATE));
              BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
              
            for (int i = 0; i < 5; i++) {
                writer.write(defaultRecord, 0, defaultRecord.length());
            }
            writer.close();
        } catch (Exception e) {
                System.out.println("Encountered exception : " + e.toString());
        }
    }
   
    public void writeActualRecords() {
        try {
            FileChannel fc = (FileChannel) Files.newByteChannel(newPath, CREATE, WRITE);
            Scanner inputScanner = new Scanner(System.in);
            int id = 0;
            String firstName = null;
            String lastName = null;
            double balance = 0.00;
            
            System.out.println("Enter the next Id number, or type 'EXIT' to quit");
            String nextId = inputScanner.nextLine();
            
            while(!nextId.equals("EXIT"))
            {
                id = Integer.parseInt(nextId);
               
                System.out.println("Enter the first name for Id number: " + id);
                firstName = inputScanner.nextLine();
                StringBuilder sb = new StringBuilder(firstName);
                sb.setLength(NAME_LENGTH);
                firstName = sb.toString();
                
                System.out.println("Enter the last name for Id number: " + id);
                lastName = inputScanner.nextLine();
                sb = new StringBuilder(lastName);
                sb.setLength(NAME_LENGTH);
                lastName = sb.toString();
                
                System.out.println("Enter the balance for Id number " + id); 
                balance = inputScanner.nextDouble();
                inputScanner.nextLine();
                DecimalFormat df = new DecimalFormat(BALANCE_FORMAT);
                
                String recordString = nextId + delimiter + firstName + delimiter + lastName + delimiter + df.format(balance) + System.getProperty("line.separator");
                byte[] bytes = recordString.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(bytes);
                fc.position(id * record_size);
                fc.write(buffer);
                 
                System.out.println("Enter the next Id number, or type 'EXIT' to quit");
                nextId = inputScanner.nextLine();           
            }
            
            fc.close();

        } catch (Exception e) {         
             System.out.println("Encountered exception : " + e.toString());
        }
    }
}