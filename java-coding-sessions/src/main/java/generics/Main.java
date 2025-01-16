package generics;

public class Main {
    public static void main(String[] args) {
        BaseballTeam phillis = new BaseballTeam("Philadelphia Phillis");
        BaseballTeam astros = new BaseballTeam("Houston Astros");
        scoreResult(phillis, 3, astros, 5);

        var harper = new BaseballPlayer("B Harper", "Right Midfielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Midfielder");
        phillis.addTeamMembers(harper);
        phillis.addTeamMembers(marsh);

        phillis.listTeamMembers();
    }

    public static void scoreResult(BaseballTeam team1, int t1_score, BaseballTeam team2, int t2_score) {
        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
}
