package domain;



public class Matches {

    public int id;


   

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
         public String name;

         @Override
         public String toString() {
             return "Hometeam{" +
                     ", name='" + this.name + '\'' +
                     '}';
         }
     }

    public Awayteam awayTeam;
    public class Awayteam{
        
        public String name;

        @Override
        public String toString() {
            return "Awayteam{" +
                   
                    ", name='" + this.name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Matches{" +
                ", id=" + id +
                
                ", utcDate='" + utcDate + '\'' +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", hometeam=" + homeTeam +
                ", awayteam=" + awayTeam +
                '}';
    }

}
