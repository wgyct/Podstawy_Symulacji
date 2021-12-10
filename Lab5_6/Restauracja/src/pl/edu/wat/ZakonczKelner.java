package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczKelner extends BasicSimEvent<Smo, Klient>
{
	public ZakonczKelner(Smo parent, double delay, Klient zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }

	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent4 = getSimObj();

        System.out.println(simTimeFormatted()+": SMO4 - Koniec przygotowania przez kelnera zamówienia klienta nr: " + eventParams.getId()+" w gnieździe 4");

        smoParent4.setWolne4(true);
	}

	@Override
	public Klient getEventParams() {
		return null;
	}
}