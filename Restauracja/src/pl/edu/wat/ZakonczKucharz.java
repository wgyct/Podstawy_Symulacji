package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczKucharz extends BasicSimEvent<Smo, Klient>
{
	public ZakonczKucharz(Smo parent, double delay, Klient zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }

	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent3 = getSimObj();

        System.out.println(simTimeFormatted()+": SMO3 - Koniec przygotowania przez kucharza zamówienia klienta nr: " + eventParams.getId()+" w gnieździe 3");

        smoParent3.setWolne3(true);
	}

	@Override
	public Klient getEventParams() {
		return null;
	}
}