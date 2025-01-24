package generics_challenge;

import generics_challenge.interfaces.Mappable;

import java.util.ArrayList;
import java.util.List;

public class Layer <T extends Mappable> {
    private final List<T> layerElements;

    public Layer(T[] layerElements) {
        this.layerElements = new ArrayList<T>(List.of(layerElements));
    }

    @SafeVarargs
    public final void addElements(T... elements) {
        layerElements.addAll(List.of(elements));
    }

    public void renderLayer() {
        for (T element : layerElements) {
            element.render();
        }
    }
}
