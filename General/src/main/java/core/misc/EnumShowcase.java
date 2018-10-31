package core.misc;

import static core.misc.EnumShowcase.Type.INT;

/**
 * @author Ilya Galatyuk
 */
class EnumShowcase {
    public static void main(String[] args) {
        EnumShowcase showcase = new EnumShowcase();
        System.out.println("-------------basicShowcase");
        showcase.basicShowcase();
        System.out.println("-------------functionShowcase");
        showcase.functionShowcase();
        System.out.println("-------------polymorphismShowcase");
        showcase.polymorphismShowcase();
    }

    enum Season {WINTER, SPRING, SUMMER, AUTUMN} // any enum implicitly extends java.land.Enum class

    public void basicShowcase() {
        Season season = Season.SPRING;
        System.out.println("season.name() = " + season.name()         // main auxiliary methods
                + "; season.toString() = " + season.toString()
                + "; season.ordinal() = " + season.ordinal());

        String nameOfSeason = "SUMMER";
        season = Season.valueOf(nameOfSeason);   // can get enum element this way, but obviously exceptions if not found/null

        Season[] fullCycle = Season.values();
        for (Season s : fullCycle)
            System.out.println(s);
    }

    enum Direction {     // can make additional functions easily
        UP, DOWN;

        public Direction opposite() {
            if (this == UP) return DOWN;
            return UP;
        }
    }

    public void functionShowcase() {
        Direction direction = Direction.DOWN;
        System.out.println(direction.opposite());
    }

    enum Type {   // polymorphism example

        INT(true) { //true here is argument for constructor(below)

            public Object parse(String string) {
                return Integer.valueOf(string);
            }
        },
        INTEGER(false) {
            public Object parse(String string) {
                return Integer.valueOf(string);
            }
        },
        STRING(false) {
            public Object parse(String string) {
                return string;
            }
        };

        boolean primitive; // every element(INT, INTEGER, STRING) contains this(end every other) field

        Type(boolean primitive) {   //constructor for all three elements
            this.primitive = primitive;
        }

        public boolean isPrimitive() {
            return primitive;
        }

        public abstract Object parse(String string);
    } //so, INT, INTEGER, STRING are subclasses of our 'enum Type' and have all its fields and methods

    public void polymorphismShowcase() {
        System.out.println(Type.class);
        System.out.println(INT.getClass() + "    ; superclass:    " + INT.getClass().getSuperclass());
        System.out.println(Type.INTEGER.getClass() + "    ; superclass:    " + Type.INTEGER.getClass().getSuperclass());
        System.out.println(Type.STRING.getClass() + "    ; superclass:    " + Type.STRING.getClass().getSuperclass());
        System.out.println(INT.parse("123"));
    }
}
