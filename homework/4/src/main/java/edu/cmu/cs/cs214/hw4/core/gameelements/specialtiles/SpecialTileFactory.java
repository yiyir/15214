package edu.cmu.cs.cs214.hw4.core.gameelements.specialtiles;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the special tile store in the game using the factory method pattern.
 */
public class SpecialTileFactory {
    private final SpecialTile boom = new Boom();
    private final SpecialTile myOwnTile = new MyOwnTile();
    private final SpecialTile negativePoints = new NegativePoints();
    private final RemoveConsonants removeConsonants = new RemoveConsonants();
    private final ReversePlayerOrder reversePlayerOrder = new ReversePlayerOrder();

    public SpecialTile getSpecialTile(String tileType) {
        if (tileType == null) return null;
        if (tileType.equalsIgnoreCase("Boom")) return boom;
        if (tileType.equalsIgnoreCase("MyOwnTile")) return myOwnTile;
        if (tileType.equalsIgnoreCase("NegativePoints")) return negativePoints;
        if (tileType.equalsIgnoreCase("RemoveConsonants")) return removeConsonants;
        if (tileType.equalsIgnoreCase("ReversePlayerOrder")) return reversePlayerOrder;
        return null;
    }

    public List<SpecialTile> getAllSpecialTiles() {
        List<SpecialTile> all = new ArrayList<>();
        all.add(boom);
        all.add(myOwnTile);
        all.add(negativePoints);
        all.add(removeConsonants);
        all.add(reversePlayerOrder);
        return all;
    }
}
