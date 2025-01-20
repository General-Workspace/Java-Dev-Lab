package generics.records;

import generics.interfaces.Player;

public record BaseballPlayer(String name, String position) implements Player {
}
