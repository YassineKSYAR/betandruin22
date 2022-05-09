package domain;

import java.util.Date;

public class MyBet {
    long Id;

    Date date ;
    String ev;
    String q;
    String re;
    float f;
    float b;

    String type;
    long idR;



    public MyBet(long Id,Date date ,String ev,String q,String re,float f,float b){
        this.Id=Id;
        this.date=date;
        this.ev=ev;
        this.q=q;
        this.re=re;
        this.f=f;
        this.b=b;

    }

    public MyBet(Date date,String type ,float amount){
        this.date=date;
        this.f=amount;
        this.type=type;
    }
    public MyBet(long id,Date date,String type ,float amount){
        this.date=date;
        this.f=amount;
        this.type=type;
        this.idR=id;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getRe() {
        return re;
    }

    public String getQ() {
        return q;
    }

    public String getEv() {
        return ev;
    }

    public float getF() {
        return f;
    }

    public float getB() {
        return b;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }


    public long getId() {
        return Id;
    }

    public long getIdR() {
        return idR;
    }

    @Override
    public String toString() {
        return "MyBet{" +
                "Id=" + Id +
                ", date=" + date +
                ", ev='" + ev + '\'' +
                ", q='" + q + '\'' +
                ", re='" + re + '\'' +
                ", f=" + f +
                ", b=" + b +
                ", type='" + type + '\'' +
                ", idR=" + idR +
                '}';
    }
}

