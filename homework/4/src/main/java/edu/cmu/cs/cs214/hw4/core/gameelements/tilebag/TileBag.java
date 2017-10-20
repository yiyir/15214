package edu.cmu.cs.cs214.hw4.core.gameelements.tilebag;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the letter bag used in the game Scrabble to hold 98 letter tiles
 * (blank tiles are removed from this game version).
 */
public class TileBag {
    /**
     * the letter tiles contained in the tile bag
     */
    private final List<LetterTile> tileBag = new ArrayList<>();

    /**
     * Creates a new tile bag containing 98 different letter tiles.
     */
    public TileBag() {
        LetterTile a = new LetterTile(1, 'A');
        LetterTile b = new LetterTile(3, 'B');
        LetterTile c = new LetterTile(3, 'C');
        LetterTile d = new LetterTile(2, 'D');
        LetterTile e = new LetterTile(1, 'E');
        LetterTile f = new LetterTile(4, 'F');
        LetterTile g = new LetterTile(2, 'G');
        LetterTile h = new LetterTile(4, 'H');
        LetterTile ii = new LetterTile(1, 'I');
        LetterTile j = new LetterTile(8, 'J');
        LetterTile k = new LetterTile(5, 'K');
        LetterTile l = new LetterTile(1, 'L');
        LetterTile m = new LetterTile(3, 'M');
        LetterTile n = new LetterTile(1, 'N');
        LetterTile o = new LetterTile(1, 'O');
        LetterTile p = new LetterTile(3, 'P');
        LetterTile q = new LetterTile(10, 'Q');
        LetterTile r = new LetterTile(1, 'R');
        LetterTile s = new LetterTile(1, 'S');
        LetterTile t = new LetterTile(1, 'T');
        LetterTile u = new LetterTile(1, 'U');
        LetterTile v = new LetterTile(4, 'V');
        LetterTile w = new LetterTile(4, 'W');
        LetterTile x = new LetterTile(8, 'X');
        LetterTile y = new LetterTile(4, 'Y');
        LetterTile z = new LetterTile(10, 'Z');
        for (int i = 0; i < 9; i++) {
            this.tileBag.add(a);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(b);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(c);
        }
        for (int i = 0; i < 4; i++) {
            this.tileBag.add(d);
        }
        for (int i = 0; i < 12; i++) {
            this.tileBag.add(e);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(f);
        }
        for (int i = 0; i < 3; i++) {
            this.tileBag.add(g);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(h);
        }
        for (int i = 0; i < 9; i++) {
            this.tileBag.add(ii);
        }

        this.tileBag.add(j);


        this.tileBag.add(k);

        for (int i = 0; i < 4; i++) {
            this.tileBag.add(l);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(m);
        }
        for (int i = 0; i < 6; i++) {
            this.tileBag.add(n);
        }
        for (int i = 0; i < 8; i++) {
            this.tileBag.add(o);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(p);
        }
        this.tileBag.add(q);

        for (int i = 0; i < 6; i++) {
            this.tileBag.add(r);
        }
        for (int i = 0; i < 4; i++) {
            this.tileBag.add(s);
        }
        for (int i = 0; i < 6; i++) {
            this.tileBag.add(t);
        }
        for (int i = 0; i < 4; i++) {
            this.tileBag.add(u);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(v);
        }
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(w);
        }
        this.tileBag.add(x);
        for (int i = 0; i < 2; i++) {
            this.tileBag.add(y);
        }
        this.tileBag.add(z);
    }

    /**
     * Gets the letter tiles contained in the tile bag.
     *
     * @return the letter tiles contained in the tile bag
     */
    public List<LetterTile> getTileBag() {
        return tileBag;
    }
}
