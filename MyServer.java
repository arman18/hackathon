package hackathon;

import java.net.*;  
import java.io.*;  
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
class MyServer{ 
    private static String adminName;
    private static String adminPass;
	
public static void main(String args[])throws Exception{  
	System.out.println("welcome to server");
	ServerSocket ss=new ServerSocket(3333);  
	Socket s=ss.accept();  
	System.out.println("connected a client:");
	DataInputStream din=new DataInputStream(s.getInputStream());  
	DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
	  
	String myStr,strClient,accName,accPass,accAge,accBalance,hudai;
        ArrayList<AllOFStudentIF> arrOfObject = new ArrayList<>();
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        AllOFStudentIF ob = null;
        try {
            fi = new FileInputStream(new File("myObjects.txt"));
            oi = new ObjectInputStream(fi);
            while(true) 
            {
                ob = (AllOFStudentIF) oi.readObject();
                arrOfObject.add(ob);
            }
            
        } 
        catch (IOException e){
            
            while(true){
                String regNumber,password;
                dout.writeUTF("1)login as client\n2)login as admin\n3)close connection\nchoose: ");
//		dout.flush();
		strClient=din.readUTF();  
                if(strClient.equals("1")){   //login
                    if(arrOfObject.isEmpty()) continue;
                    dout.writeUTF("reg number: ");
                    regNumber=din.readUTF();
                    dout.writeUTF("password: ");
                    password=din.readUTF();
                    Iterator<AllOFStudentIF> it = arrOfObject.iterator();
                    AllOFStudentIF bk = null;
                    boolean check= false;
                    while(it.hasNext()){
                        bk = it.next();
                        if(bk.isAcc(regNumber,password)) {
                                check = true;
                                break;
                            }
                            
                    }
                    while(check){
                    dout.writeUTF("1)withdraw\n2)deposit\n3)information\n4)logout\n5)Application\nchoose: ");
                    strClient=din.readUTF();
                    if(strClient.equals("1")) //withdraw
                    {
                        dout.writeUTF("withdraw amount: ");
                        strClient=din.readUTF();
                        boolean bl = (bk.withdraw(Double.parseDouble(strClient)));
                        if(bl==true) dout.writeUTF("sucess withdraw");
                        else  dout.writeUTF("wrong withdraw");
                        din.readUTF();
                    }
                    else if(strClient.equals("2")) //deposit
                    {
                        dout.writeUTF("deposit amount: ");
                        strClient=din.readUTF();
                        boolean bl = (bk.deposit(Double.parseDouble(strClient)));
                        if(bl==true) dout.writeUTF("sucess deposit");
                        else  dout.writeUTF("wrong deposit");
                        din.readUTF();
                    }
                    else if(strClient.equals("3")){ //information
                        dout.writeUTF(bk.toString());
                        din.readUTF();
                    }
                    else if(strClient.equals("5")){
                        boolean bl = bk.withdraw(100);
                        if(bl==true) {
                            FileOutputStream fo = new FileOutputStream(new File("testimonial.txt"));
                            String form;
                            form = "This is to certify that " + bk.getName() + " son of " + bk.getFatherName() +
                                    " and " + bk.getMotherName() + " studing software engineering from Dhaka university at " +
                                    bk.getSemester() + " , " + bk.getYear() + "\n\nTo the best of my knoledge he did not take part "+
                                    "in any activities subversive of the state or dicipline.\nI wish him every in every success in life.";
                            byte b[]=form.getBytes();
                            fo.write(b);
                        }
                        else  dout.writeUTF("you have not sufficient money");
                        din.readUTF();
                    }
                    
                    else break; //logout
                  }
                }
                else if(strClient.equals("2")){ // login as admin
                    String name;
                    dout.writeUTF("name: ");
                    name=din.readUTF();
                    dout.writeUTF("password: ");
                    password=din.readUTF();
                    if(adminName.equals(name) && adminPass.equals(password)){
                        dout.writeUTF("1)login as student's account\n2)close connection\nchoose: ");
                        strClient=din.readUTF();
                        if(strClient.equals("1")){
                            dout.writeUTF("give sudent's reg number");
                            name=din.readUTF();
                            dout.writeUTF("password: ");
                            password=din.readUTF();
                            Iterator<AllOFStudentIF> it = arrOfObject.iterator();
                            AllOFStudentIF bk = null;
                            boolean check= false;
                            while(it.hasNext()){
                                bk = it.next();
                                if(bk.isAcc(name,password)) {
                                        check = true;
                                        break;
                                    }

                            }
                            while(check){
                                dout.writeUTF("change:\n1)student name\n2)CGPA\n3)year\n4)age\nchoose");
                                strClient = din.readUTF();
                                if(strClient.equals("1")){
                                    dout.writeUTF("name: ");
                                    strClient = din.readUTF();
                                    bk.setName(strClient);
                                }
                                else if(strClient.equals("2")){
                                    dout.writeUTF("CGPA: ");
                                    strClient = din.readUTF();
                                    bk.setName(strClient);
                                }
                                else if(strClient.equals("3")){
                                    dout.writeUTF("year: ");
                                    strClient = din.readUTF();
                                    bk.setName(strClient);
                                }
                                else if(strClient.equals("4")){
                                    dout.writeUTF("age: ");
                                    strClient = din.readUTF();
                                    bk.setName(strClient);
                                }
                                else continue;
                            }
                            
                        }
                        else if(strClient.equals("2")){
                            break;
                        }
                        else {
                            dout.writeUTF("wrong input!");
                            din.readUTF();
                        }
                    }
                    
                }
                
                else if(strClient.equals("3")) break; //close connection
                else{
                    dout.writeUTF("wrong input!");
                    din.readUTF();
                }                
		dout.flush();      
	}
            dout.close();
            oi.close();
            fi.close();
        }
        try
        {
            FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            //arrOfObject.clear();
            Iterator<AllOFStudentIF> it = arrOfObject.iterator();
            while(it.hasNext()) o.writeObject(it.next());
            
            o.close();
            f.close();
            
        }
        catch (IOException e)
        {
            System.out.println("Error initializing stream");
        }
        
	  
	din.close();  
	s.close();  
	ss.close(); 
	
	System.out.println("OK from Server");
	
	}	
} 