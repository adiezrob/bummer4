package domain;

public class Competition {
    int id;
    String name;
    String emblemUrl;
    Area area;
    String code;
    String plan;

    Season currentSeason;
    int numberOfAvailableSeasons;
    String lastUpdated;

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emblemUrl='" + emblemUrl + '\'' +
                ", area=" + area +
                ", plan='" + plan + '\'' +
                ", currentSeason=" + currentSeason +
                ", numberOfAvailableSeasons=" + numberOfAvailableSeasons +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }


    public class Area {
        int id;
        String name;
        String countryCode;
        String ensignUrl;

        @Override
        public String toString() {
            return "Area{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", ensignUrl='" + ensignUrl + '\'' +
                    '}';
        }
    }

    public class Season{
        int id;
        String startDate;
        String endDate;
        int currentMatchday;
        Object winner;

        @Override
        public String toString() {
            return "Season{" +
                    "id=" + id +
                    ", startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    ", currentMatchday=" + currentMatchday +
                    ", winner=" + winner +
                    '}';
        }
    }
}
