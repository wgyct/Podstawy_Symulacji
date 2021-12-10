package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZacznijKucharz extends BasicSimEvent<Smo, Klient>
{
    private final RNGenerator generator;

    public ZacznijKucharz(Smo parent) throws SimControlException
    {
    	super(parent);
    	generator = new RNGenerator();
    }

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent3 = getSimObj();

        if (smoParent3.liczbaZgl3() > 0)
        {
        	// Zablokuj gniazdo
        	smoParent3.setWolne3(false);
        	// Pobierz klienta
        	Klient zgl = smoParent3.usun3();
        	// Wygeneruj czas obsługi
            double czasObslugi;
            do {
            	czasObslugi = generator.gamma(10, 1);
            } while (czasObslugi<=0.0);
        	
            // Zapamiętaj dane monitorowane
        	smoParent3.MVczasy_obslugi3.setValue(czasObslugi);
            smoParent3.MVczasy_oczekiwania3.setValue(simTime() - zgl.getCzasPrzybycia());
            System.out.println(simTimeFormatted()+": Przygotowanie przez kucharza zamówienia klienta nr: " + zgl.getId()+" w gnieździe 3");
        	// Zaplanuj koniec obsługi
        	new ZakonczKucharz(smoParent3, czasObslugi, zgl);
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