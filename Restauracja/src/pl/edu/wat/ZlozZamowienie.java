package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZlozZamowienie extends BasicSimEvent<Smo, Klient>
{
    private final RNGenerator generator;

    public ZlozZamowienie(Smo parent2) throws SimControlException
    {
    	super(parent2);
    	generator = new RNGenerator();
    }

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent2 = getSimObj();

        if (smoParent2.liczbaZgl2() > 0)
        {
        	// Zablokuj gniazdo
        	smoParent2.setWolne2(false);
        	// Pobierz klienta
        	Klient zgl = smoParent2.usun2();
        	// Wygeneruj czas obsługi
            double czasObslugi;
            do {
            	czasObslugi = generator.uniform(3,7);
            } while (czasObslugi<=0.0);
        	
            // Zapamiętaj dane monitorowane
        	smoParent2.MVczasy_obslugi2.setValue(czasObslugi);
            smoParent2.MVczasy_oczekiwania2.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+": Przyjmowanie zamówienia klienta nr: " + zgl.getId()+" w gnieździe 2");
        	// Zaplanuj koniec obsługi
        	new PrzyjmijZamowienie(smoParent2, czasObslugi, zgl);
        }
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Klient getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}