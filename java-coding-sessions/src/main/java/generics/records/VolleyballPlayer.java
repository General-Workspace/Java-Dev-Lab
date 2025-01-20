package generics.records;

import generics.interfaces.Player;

public record VolleyballPlayer(String name, String position) implements Player {
}
