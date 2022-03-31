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
    public MyBet(long Id,Date date ,String ev,String q,String re,float f,float b){
        this.Id=Id;
        this.date=date;
        this.ev=ev;
        this.q=q;
        this.re=re;
        this.f=f;
        this.b=b;

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

    public long getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "MyBet{" +
                "date=" + date +
                ", ev='" + ev + '\'' +
                ", q='" + q + '\'' +
                ", re='" + re + '\'' +
                ", f=" + f +
                ", b=" + b +
                '}';
    }
}

