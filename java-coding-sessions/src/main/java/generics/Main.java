package generics;

import generics.records.BaseballPlayer;
import generics.records.FootballPlayer;
import generics.records.VolleyballPlayer;
import generics.records.Affiliation;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        var philly = new Affiliation("city", "Philadelphia, PA", "US");

        BaseballTeam phillis1 = new BaseballTeam("Philadelphia Phillis");
        BaseballTeam astros1 = new BaseballTeam("Houston Astros");
        scoreResult(phillis1, 3, astros1, 5);

        SportsTeam phillis2 = new SportsTeam("Philadelphia Phillis");
        SportsTeam astros2 = new SportsTeam("Houston Astros");
        scoreResult(phillis2, 3, astros2, 5);

        Team<BaseballPlayer, Affiliation> phillis = new Team<>("Philadelphia Phillis", philly);
        Team<BaseballPlayer, Affiliation> astros = new Team<>("Houston Astros");
        scoreResult(phillis, 3, astros, 5);

        var harper = new BaseballPlayer("B Harper", "Right Midfielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Midfielder");
        phillis.addTeamMembers(harper);
        phillis.addTeamMembers(marsh);
        var guthrie = new BaseballPlayer("D Guthrie", "Attacking Midfielder");
        phillis.addTeamMembers(guthrie);
        phillis.listTeamMembers();

        SportsTeam afc1 = new SportsTeam("Adelaide Crows");
        Team<FootballPlayer, String> afc = new Team<>("Adelaide Crows", "City of Adelaide, South Australia, in AU");
        var tex = new FootballPlayer("Tex Walker", "Centre half Forward");
        afc.addTeamMembers(tex);
        afc.addTeamMembers(new FootballPlayer("Rory Laird", "Centre Midfielder"));
        afc.listTeamMembers();

        Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Storm");
        adelaide.addTeamMembers(new VolleyballPlayer("N Roberts", "Setter"));
        adelaide.listTeamMembers();

        Team<VolleyballPlayer, Affiliation> canberra = new Team("Canberra Heat");
        canberra.addTeamMembers(new VolleyballPlayer("B Black", "Opposite"));
        canberra.listTeamMembers();
    }

    public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(SportsTeam team1, int t1_score, SportsTeam team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(Team<BaseballPlayer, Affiliation> team1, int t1_score, Team<BaseballPlayer, Affiliation> team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void dsa() {
        Queue<String> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(15);
        System.out.println(arrayList);

        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        System.out.println(map);
    }
}
