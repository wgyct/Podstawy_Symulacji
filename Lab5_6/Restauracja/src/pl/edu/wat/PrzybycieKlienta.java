package pl.edu.wat;

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;

import java.util.Random;

/**
 * Zdarzenie obsługujące generowanie zgłoszeń. Tworzy obiekt zgłoszenia co losowy czas.
 * 
 * @author Dariusz Pierzchala

 */
public class PrzybycieKlienta extends BasicSimEvent<OtoczenieSklepu, Object> {
    private final RNGenerator generator;
    private int zimno;
    public PrzybycieKlienta(OtoczenieSklepu parent, double odstep, int p) throws SimControlException {
        super(parent, odstep);
        generator = new RNGenerator();
        zimno = p;

    }

    @Override
    protected void onTermination() throws SimControlException {
    }

    @Override
    protected void stateChange() throws SimControlException {
        //Wybór kolejki: 3 - kucharz, 4 - kelner
        Random rand = new Random();
        int random = rand.nextInt(2);
        Klient zgl = new Klient(simTime(), random);
        // zdarzenie jest przypisane do obiektu, ktorego stan zmienia. Podając w konstruktorze OtoczenieSklepu jako
        // "parent", tutaj możemy uzyskać dostęp to tego obiektu przy pomocy metody getSimObj().
        OtoczenieSklepu otoczenieSklepu = getSimObj();
        otoczenieSklepu.smo.dodaj(zgl);
        otoczenieSklepu.smo.dodaj2(zgl);
        if (random == 0)    otoczenieSklepu.smo.dodaj3(zgl);
        if (random == 1)    otoczenieSklepu.smo.dodaj4(zgl);

        System.out.println(simTimeFormatted() + ": Restauracja: Przybycie nowego klienta nr: " + zgl.getId());
        System.out.println(simDate(SimDateField.HOUR24) + " - " + simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) + " - "
                + simDate(SimDateField.MILLISECOND) + ": Restauracja: Nowy klient nr: " + zgl.getId()+ " ustawił się w kolejce do pomiaru temperatury");

        int zimno2 = simDate(SimDateField.SECOND)+60*simDate(SimDateField.MINUTE);

        if (zimno2>=zimno && zimno2<=zimno+15){
            System.out.println(simTimeFormatted() + " - "+ simDate(SimDateField.MINUTE) + " - " + simDate(SimDateField.SECOND) +"!!!!!!!!!ZIMNO!!!!!!!!!!!!!");
            System.out.println("Klient nr:" + zgl.getId() + " zrezygnował z czekania");
            otoczenieSklepu.smo.usunWskazany(zgl);
            otoczenieSklepu.smo.usunWskazany2(zgl);
            otoczenieSklepu.smo.usunWskazany3(zgl);
            otoczenieSklepu.smo.usunWskazany4(zgl);
        }

        // Aktywuj obsługę, jeżeli kolejka była pusta (gniazdo "spało")
        if (otoczenieSklepu.smo.liczbaZgl() == 1 && otoczenieSklepu.smo.isWolne()) {
            new RozpocznijPomiarTemperatury(otoczenieSklepu.smo);
        }
        // Wygeneruj czas do kolejnego zgłoszenia
        double odstep;
        do {
            odstep = generator.exponential(0.22);
        } while (odstep <= 0.0);

        otoczenieSklepu.MVczasyMiedzyZgl.setValue(odstep);
        setRepetitionPeriod(odstep);
        //alternatywnie: parent.zglaszaj = new Zglaszaj(parent, odstep);
    }

    @Override
    public Object getEventParams() {
        return null;
    }
}