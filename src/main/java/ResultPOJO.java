public class ResultPOJO {
    String home_team;
    String away_team;
    String league_name;
    String event_status;
    String event_status_info;

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getEvent_status() {
        return event_status;
    }

    public void setEvent_status(String event_status) {
        this.event_status = event_status;
    }

    public String getEvent_status_info() {
        return event_status_info;
    }

    public void setEvent_status_info(String event_status_info) {
        this.event_status_info = event_status_info;
    }
    @Override
    public String toString() {
        return "Team1: " + home_team + "\n"
                + "Team2:" + away_team + "\n"
                + "League: " + league_name + "\n"
                //+"event_status is "+event_status+"\n"
                +"Summary: "+event_status_info;
    }
}
