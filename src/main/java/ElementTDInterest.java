/**
 * @author Galatyuk Ilya
 */
public class ElementTDInterest {
    public static void main(String[] args) {
        int initial = 300;

        for (int i = 1; i <= 10; i++) {
            int times = i * 50;
            System.out.println(times + " times, " + times / 4 + " minutes ,money = " + addInterest(initial, times));
        }
    }

    private static int addInterest(int startingMoney, int times) {
        int money = startingMoney;
        for (int i = 0; i < times; i++) {
            money += money / 50;
        }
        return money;
    }
}
