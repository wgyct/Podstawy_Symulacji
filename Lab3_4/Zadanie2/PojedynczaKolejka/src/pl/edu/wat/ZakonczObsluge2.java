package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZakonczObsluge2 extends BasicSimEvent<Smo, Klient>
{
    public ZakonczObsluge2(Smo parent, double delay, Klient zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }
  
	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent2 = getSimObj();

        System.out.println(simTimeFormatted()+": SMO-Koniec obsługi zgl. nr: " + eventParams.getId()+" w gnieździe 2");
		smoParent2.setWolne2(true);
		// Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
       /* if (smoParent2.liczbaZgl2() > 0)
        {
        	new RozpocznijObsluge2(smoParent2);
        }*/
	}

	@Override
	public Klient getEventParams() {
		return null;
	}
}