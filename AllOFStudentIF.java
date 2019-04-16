package hackathon;

import java.io.Serializable;

public class AllOFStudentIF implements Serializable{
    private double amount;
    private String name;
    private String fatherName;
    private String motherName;
    private String regNum;
    private String year;
    private String semester;
    private int age;
    private String pass;
    private String currentCGPA;

    public AllOFStudentIF(String name,String fatherName,String motherName,String year,String semester,String currentCGPA,String regNum, int age,double amount,String pass)
    {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.year = year;
        this.semester = semester;
        this.age = age;
        this.currentCGPA = currentCGPA;
        this.amount = amount;
        this.pass = pass;
        this.regNum = regNum;
    }
    public void setCGPA(String CGPA){
        this.currentCGPA = CGPA;
    }
    public String getCGPA(){
        return this.currentCGPA;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }


    public boolean isAcc(String regNum,String pass)
    {
        return (this.pass.equals(pass) && this.regNum.equals(regNum));
    }

    public boolean deposit(double amount)
    {
        if(amount>=0)
        {
            this.amount +=amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount)
    {
        if(amount>=0 && amount<=this.amount)
        {
            this.amount -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {

        return "\nname: "+name + "\nFather Name: " + fatherName + "\nMother Name: " + motherName + "\nYear: " + year + "CGPA:  " + currentCGPA +"\nage: " + age + "\nbalance: "+ amount + "\n";
    }
}
