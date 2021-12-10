package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczPomiarTemperatury extends BasicSimEvent<Smo, Klient>
{
	public ZakonczPomiarTemperatury(Smo parent, double delay, Klient zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }

	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent = getSimObj();

        System.out.println(simTimeFormatted()+": SMO1 - Koniec pomiaru temperatury klienta nr: " + eventParams.getId()+" w gnieździe 1");

        smoParent.setWolne(true);

		if (smoParent.liczbaZgl2() > 0 && smoParent.isWolne2())
		{
			new ZlozZamowienie(smoParent);
		}

        if (smoParent.liczbaZgl() > 0)
        {
        	new RozpocznijPomiarTemperatury(smoParent);
        }
	}

	@Override
	public Klient getEventParams() {
		return null;
	}
}