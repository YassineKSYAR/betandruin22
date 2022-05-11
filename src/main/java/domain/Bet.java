package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bet {

    @Id
    @GeneratedValue long id;
    private long idUser;
    private long idResults;
    private float amount ;
    private float fee;

    public  Bet(long idResults,int amount,float fee,long idUser){
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

    public Bet() {

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

    public float getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idResults=" + idResults +
                ", amount=" + amount +
                ", fee=" + fee +
                '}';
    }
}
