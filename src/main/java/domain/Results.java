package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;
import java.io.Serializable;


@Entity
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;
@Id @GeneratedValue long idR;

    @XmlIDREF
    private int idevent;

   @XmlIDREF
   private int idQuestion;
   private String result;
   private float fee;

   public Results(int idevent,int idQuestion,String result,float fee){
       this.idevent=idevent;
       this.idQuestion=idQuestion;
       this.result=result;
       this.fee=fee;
   }

    public Results() {
        super();
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public void setIdevent(Integer idevent) {
        this.idevent = idevent;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }





    public int getIdevent() {
        return idevent;
    }

    public float getFee() {
        return fee;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public String getResult() {
        return result;
    }

    public long getIdR() {
        return idR;
    }

    @Override
    public String toString() {
        return "Results{" +

                "idevent=" + idevent +
                ", idQuestion=" + idQuestion +
                ", result='" + result + '\'' +
                ", fee=" + fee +
                '}';
    }
}