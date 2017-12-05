/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DisplaySelectedCustomersByBalancee {
    
   public static void readFile(String filename) throws FileNotFoundException {
       Scanner sc = new Scanner(new File(filename));
       String line;
       String[] vals;
       int count = 0;
       System.out.println("Records in File: ");
       while(sc.hasNextLine()) {
           count++;
           line = sc.nextLine();
           vals = line.split(" ");
           System.out.println(count+".");
           System.out.println("ID Number:"+vals[0]);
           System.out.println("First Name:"+vals[1]);
           System.out.println("Last Name:"+vals[2]);
           System.out.println("Balance owned:"+vals[3]);
           System.out.println();
           System.out.println();
       }
       if(count == 0) {
           System.out.println("No records in file.");
       }
      
   }
}
