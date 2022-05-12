package domain;

import java.awt.*;

public class Matches {

    public int id;


    /*public Competition competition;
    public class Competition{
        public int id;
        public String name;

        @Override
        public String toString() {
            return "Competition{" +
                    "id=" + this.id +
                    ", Name='" + this.name + '\'' +
                    '}';
        }
    }*/

    public String utcDate;

    public String status;

    public Score score;

    public class Score{
        public String winner;

        public Fulltime fullTime;
        public class Fulltime{
            public int homeTeam;
            public int awayTeam;

            @Override
            public String toString() {
                return "Fulltime{" +
                        "hometeam=" + this.homeTeam +
                        ", awayteam=" + this.awayTeam +
                        '}';
            }
        }
        @Override
        public String toString() {
                return "Score{" +
                        "winner='" + this.winner + '\'' +
                        ", fulltime= HomeTeam:" + this.fullTime.homeTeam + ",AwayTeam:" + this.fullTime.awayTeam +
                        '}';
        }
    }

    public Hometeam homeTeam;
     public class Hometeam{
         //public int id;
         public String name;

         @Override
         public String toString() {
             return "Hometeam{" +
                     /*"id=" + this.id +*/
                     ", name='" + this.name + '\'' +
                     '}';
         }
     }

    public Awayteam awayTeam;
    public class Awayteam{
        //public int id;
        public String name;

        @Override
        public String toString() {
            return "Awayteam{" +
                    /*"id=" + this.id +*/
                    ", name='" + this.name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Matches{" +
                ", id=" + id +
                /*", competition=" + competition.toString() +*/
                ", utcDate='" + utcDate + '\'' +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", hometeam=" + homeTeam +
                ", awayteam=" + awayTeam +
                '}';
    }

   /* public Matches(int id,String utcDate,String status,String winner,int homeTeam,int awayTeam,String homeTeamName,String awayTeamName){
        this.id = id;
        this.utcDate = utcDate;
        this.status =status;
        this.score.winner= winner;
        this.score.fullTime.homeTeam = homeTeam;
        this.score.fullTime.awayTeam = awayTeam;
        this.homeTeam.name = homeTeamName;
        this.awayTeam.name = awayTeamName;
    }*/


}
