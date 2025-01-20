package generics.records;

import generics.interfaces.Player;

public record FootballPlayer(String name, String position) implements Player {
}
