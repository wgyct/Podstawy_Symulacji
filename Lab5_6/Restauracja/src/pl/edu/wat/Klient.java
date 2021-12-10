package pl.edu.wat;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

import java.util.Random;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 * 
 * @author Dariusz Pierzchala
 */

public class Klient extends BasicSimObj {
    public static int licznikKlientow = 0;
    private final double czasPrzybycia;
    private final int id;
    private final int random;

    public Klient(double czasPrzybycia, int r) {
        this.czasPrzybycia = czasPrzybycia;
        this.id = licznikKlientow++;
        this.random = r;
    }

    @Override
    public void reflect(IPublisher publisher, INotificationEvent event) {
    }

    @Override
    public boolean filter(IPublisher publisher, INotificationEvent event) {
        return false;
    }

    public int getId() {
        return id;
    }

    public double getCzasPrzybycia() {
        return czasPrzybycia;
    }

    public double getRandom() {
        return random;
    }
}