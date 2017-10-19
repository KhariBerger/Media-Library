/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bergerproject1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author khari
 */
public class Loan implements Serializable {

    private String user;
    private Date date;

    public Loan() {
    }

    public Loan(String user, Date date) {
        this.user = user;
        this.date = date;
    }

    public boolean containsNull() {
        return user == null && date == null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

    @Override
    public String toString() {
        return "(" + user + " on " + new SimpleDateFormat("MM/dd/yyyy").format(date) + ')';
    }

}
