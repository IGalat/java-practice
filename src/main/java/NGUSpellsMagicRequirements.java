/**
 * @author Galatyuk Ilya
 */
public class NGUSpellsMagicRequirements {
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            long cap = 100000000 * (long) (Math.pow(10, i));
            long power = (long) Math.sqrt(cap / 25000);
            long xpPower = power * 450;
            long capacity = power * 25000;
            long xpCapacity = (capacity * 120 / 10000) + (capacity / 100000 * 1125);
            long xpNeeded = xpPower + xpCapacity;

            System.out.println("Spell " + i + " has cap " + cap + ", needs " + power + " power (" + xpPower + " xp), "
                    + capacity + " capacity (" + xpCapacity + " xp for it and bars), will need " + xpNeeded + " xp total");
        }
    }
}
