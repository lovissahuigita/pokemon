/**
 * Represents a Pokemon object. Each has a number, a name, and two elemental
 * types, chosen from the PokemonType enumeration.
 *
 * @author  Joe Rossi, Lovissa Winyoto
 * @version 1.0
 */
public class Pokemon implements Comparable<Pokemon> {

    // ------Instance data here------
    private String name;
    private int number;
    private PokemonType pType, sType;

    /**
     * Constructs a Pokemon object
     *
     * @param num   this Pokemon's unique number
     * @param name  this Pokemon's name
     * @param p this Pokemon's primary type
     * @param s this Pokemon's secondary type
     */
    public Pokemon(int num, String name, PokemonType p, PokemonType s) {
        this.name = name;
        this.number = num;
        this.pType = p;
        this.sType = s;
    }

    @Override
    public int compareTo(Pokemon o) {
        return number - o.getNumber();
    }

    //Is this the correct equals method?
    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon that = (Pokemon) o;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + number;
        result = 31 * result + pType.ordinal();
        result = 31 * result + sType.ordinal();
        return result;
    }

    @Override
    public String toString() {
        return String.format("#\t%d: %s \t\t Primary Type: %s\t\t"
                + "Secondary Type: %s\n", number, name, pType, sType);
    }

    /**
     * @return  the name of this Pokemon
     */
    public String getName() {
        return name;
    }

    /**
     * @return  the unique number of this Pokemon
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return  the primary type of this Pokemon
     */
    public PokemonType getPrimaryType() {
        return pType;
    }

    /**
     * @return  the secondary type of this Pokemon
     */
    public PokemonType getSecondaryType() {
        return sType;
    }


}
