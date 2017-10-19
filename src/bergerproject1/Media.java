/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bergerproject1;

import java.io.Serializable;

/**
 *
 * @author khari
 */
public class Media implements Comparable<Media>, Serializable{

    private String title;
    private String format;
    private Loan loan;
 
    public String getTitle() {
        return title;
    }

    public Media(String title, String format, Loan loan) {
        this.title = title;
        this.format = format;
        this.loan = loan;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public String getformat() {
        return format;
    }

    public void setformat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        if (loan != null && loan.containsNull()){
        return title + " - " + format;
        } else{
            return title + " - " + format + loan.toString();
        }
    }

    @Override
    public int compareTo(Media t) {
        return this.title.compareTo(t.getTitle());
    }
    
}
