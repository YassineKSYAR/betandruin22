package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Movements {
    @Id
    @GeneratedValue
    long id;
    private Date date;

    private String type;
    private long idUser;
    private long idResults;
    private float amount ;


    public Movements(long idResults,Date date,String type,int amount, long idUser){
        this.date=date;
        this.amount=amount;
        this.idUser=idUser;
        this.type=type;
        this.idResults=idResults;

    }



    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Movements() {

    }

    public String getType() {
        return type;
    }


    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }


    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Movements{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", idUser=" + idUser +
                ", idResults=" + idResults +
                ", amount=" + amount +
                '}';
    }

    public long getIdResults() {
        return idResults;
    }
}
