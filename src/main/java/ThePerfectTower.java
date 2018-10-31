/**
 * @author Galatyuk Ilya
 */
public class ThePerfectTower {
    public static void main(String[] args) {
        calcRemainingGemsIdeal(1, 219);
        calcRemainingGemsIdeal(1.1, 351);

    }

    private static void calcRemainingGemsIdeal(double startingPercent, int gemsToUpgrade) {
        double upgradedPercent = startingPercent + 0.1;
        double balancePointAfterBuying = 10 * startingPercent * gemsToUpgrade;
        long balancePoint = Math.round(gemsToUpgrade + balancePointAfterBuying);
        System.out.println("Upgrading " + startingPercent + "% bonus to " + upgradedPercent + "% is worth when having " + balancePoint + " resources at least.");
    }
}
