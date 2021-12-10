package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class PrzyjmijZamowienie extends BasicSimEvent<Smo, Klient>
{
    public PrzyjmijZamowienie(Smo parent, double delay, Klient zgl) throws SimControlException
    {
    	super(parent, delay, zgl);
    }
  
	@Override
	protected void onTermination() throws SimControlException {}

	@Override
	protected void stateChange() throws SimControlException {
    	Smo smoParent2 = getSimObj();

        System.out.println(simTimeFormatted()+": SMO2 - Koniec składania zamówienia przez klienta nr: " + eventParams.getId()+" w gnieździe 2");
		smoParent2.setWolne2(true);
		int wybor = (int) eventParams.getRandom();
		// Zaplanuj ponownie obsługę, jeśli sa zgłoszenia w kolejce
       /* if (smoParent2.liczbaZgl2() > 0)
        {
        	new ZlozZamowienie(smoParent2);
        }*/
		//Kolejka 0 - kucharz,1 - kelner
		if (wybor == 0){
			if (smoParent2.liczbaZgl3() > 0 && smoParent2.isWolne3())
			{
				new ZacznijKucharz(smoParent2);
			}}
		if (wybor == 1){
			if (smoParent2.liczbaZgl4() > 0 && smoParent2.isWolne4())
			{
				new ZacznijKelner(smoParent2);
			}}
	}

	@Override
	public Klient getEventParams() {
		return null;
	}
}