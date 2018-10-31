package core.misc;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Galatyuk Ilya
 */
public class ObserverPattern {

    public static void main(String[] args) {
        ObservableDemo observableDemo = new ObservableDemo(1, "1");
        ObserverExample observerExample = new ObserverExample();
        observableDemo.addObserver(observerExample);

        observableDemo.setLala(2);
        observableDemo.setFafa("2");
    }


    @Data
    static class ObservableDemo extends Observable {
        private int lala;
        private String fafa;

        public ObservableDemo(int lala, String fafa) {
            this.lala = lala;
            this.fafa = fafa;
        }

        public void setLala(int lala) {
            this.lala = lala;
            setChanged();
            notifyObservers();
        }

        public void setFafa(String fafa) {
            this.fafa = fafa;
            notifyObservers(); //doesn't work if changed is false
        }
    }

    static class ObserverExample implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            ObservableDemo someUpdate = (ObservableDemo) o;
            System.out.println("lala is " + someUpdate.getLala() + ", fafa is " + someUpdate.getFafa());
        }
    }
}
