package domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class MyMovements {
    @Id
    @GeneratedValue
    long id;
    private Date date;
    private long idUser;
    private long idResults;
    private float amount ;
    private float fee;

    public  MyMovements(Date date,long idResults,int amount,float fee,long idUser){
        this.date=date;
        this.idResults=idResults;
        this.amount=amount;
        this.fee=fee;
        this.idUser=idUser;
    }



    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public MyMovements() {

    }

    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public long getIdResults() {
        return idResults;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MyMovements{" +
                "id=" + id +
                ", date=" + date +
                ", idUser=" + idUser +
                ", idResults=" + idResults +
                ", amount=" + amount +
                ", fee=" + fee +
                '}';
    }
}
