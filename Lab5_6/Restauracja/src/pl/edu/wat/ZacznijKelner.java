package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZacznijKelner extends BasicSimEvent<Smo, Klient>
{
    private final RNGenerator generator;

    public ZacznijKelner(Smo parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
    }

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent4 = getSimObj();

        if (smoParent4.liczbaZgl4() > 0)
        {
        	// Zablokuj gniazdo
        	smoParent4.setWolne4(false);
        	// Pobierz klienta
        	Klient zgl = smoParent4.usun4();
        	// Wygeneruj czas obsługi
            double czasObslugi;
            do {
            	czasObslugi = generator.exponential(0.75);
            } while (czasObslugi<=0.0);
        	
            // Zapamiętaj dane monitorowane
        	smoParent4.MVczasy_obslugi4.setValue(czasObslugi);
            smoParent4.MVczasy_oczekiwania4.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+": Przygotowanie przez kelnera zamówienia klienta nr: " + zgl.getId()+" w gnieździe 4");
        	// Zaplanuj koniec obsługi
        	new ZakonczKelner(smoParent4, czasObslugi, zgl);
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