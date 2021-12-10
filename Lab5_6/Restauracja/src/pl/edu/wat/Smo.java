package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Description: Klasa gniazda obsługi obiektów - zgłoszeń 
 */

import java.util.LinkedList;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;



public class Smo extends BasicSimObj
{
    private final LinkedList <Klient> kolejka;
    private final LinkedList <Klient> kolejka2;
    private final LinkedList <Klient> kolejka3;
    private final LinkedList <Klient> kolejka4;
    private boolean wolne = true;
    private boolean wolne2 = true;
    private boolean wolne3 = true;
    private boolean wolne4 = true;

    public MonitoredVar MVczasy_obslugi;
    public MonitoredVar MVczasy_oczekiwania;
    public MonitoredVar MVdlKolejki;
    public MonitoredVar MVutraconeZgl;
    public MonitoredVar MVczasy_obslugi2;
    public MonitoredVar MVczasy_oczekiwania2;
    public MonitoredVar MVdlKolejki2;
    public MonitoredVar MVutraconeZgl2;
    public MonitoredVar MVczasy_obslugi3;
    public MonitoredVar MVczasy_oczekiwania3;
    public MonitoredVar MVdlKolejki3;
    public MonitoredVar MVutraconeZgl3;
    public MonitoredVar MVczasy_obslugi4;
    public MonitoredVar MVczasy_oczekiwania4;
    public MonitoredVar MVdlKolejki4;
    public MonitoredVar MVutraconeZgl4;
	
    public Smo() throws SimControlException
    {
        // Utworzenie wewnętrznej listy w kolejce
        kolejka = new LinkedList<>();
        kolejka2 = new LinkedList<>();
        kolejka3 = new LinkedList<>();
        kolejka4 = new LinkedList<>();
        // Deklaracja zmiennych monitorowanych
        MVczasy_obslugi = new MonitoredVar();
        MVczasy_oczekiwania = new MonitoredVar();
        MVdlKolejki = new MonitoredVar();
        MVutraconeZgl = new MonitoredVar();
        MVczasy_obslugi2 = new MonitoredVar();
        MVczasy_oczekiwania2 = new MonitoredVar();
        MVdlKolejki2 = new MonitoredVar();
        MVutraconeZgl2 = new MonitoredVar();
        MVczasy_obslugi3 = new MonitoredVar();
        MVczasy_oczekiwania3 = new MonitoredVar();
        MVdlKolejki3 = new MonitoredVar();
        MVutraconeZgl3 = new MonitoredVar();
        MVczasy_obslugi4 = new MonitoredVar();
        MVczasy_oczekiwania4 = new MonitoredVar();
        MVdlKolejki4 = new MonitoredVar();
        MVutraconeZgl4 = new MonitoredVar();
    }

    // Wstawienie zgłoszenia do kolejki
    public int dodaj(Klient zgl)
    {
        kolejka.add(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return kolejka.size();
    }

    // Pobranie zgłoszenia z kolejki
    public Klient usun()
    {
    	Klient zgl = (Klient) kolejka.removeFirst();
        MVdlKolejki.setValue(kolejka.size());
        return zgl;
    }

    // Wstawienie zgłoszenia do kolejki
    public int dodaj2(Klient zgl)
    {
        kolejka2.add(zgl);
        MVdlKolejki2.setValue(kolejka2.size());
        return kolejka2.size();
    }

    // Pobranie zgłoszenia z kolejki
    public Klient usun2()
    {
        Klient zgl = (Klient) kolejka2.removeFirst();
        MVdlKolejki2.setValue(kolejka2.size());
        return zgl;
    }

    public int dodaj3(Klient zgl)
    {
        kolejka3.add(zgl);
        MVdlKolejki3.setValue(kolejka3.size());
        return kolejka3.size();
    }

    // Pobranie zgłoszenia z kolejki
    public Klient usun3()
    {
        Klient zgl = (Klient) kolejka3.removeFirst();
        MVdlKolejki3.setValue(kolejka3.size());
        return zgl;
    }

    public int dodaj4(Klient zgl)
    {
        kolejka4.add(zgl);
        MVdlKolejki4.setValue(kolejka4.size());
        return kolejka4.size();
    }

    // Pobranie zgłoszenia z kolejki
    public Klient usun4()
    {
        Klient zgl = (Klient) kolejka4.removeFirst();
        MVdlKolejki4.setValue(kolejka4.size());
        return zgl;
    }

    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Klient zgl)
    {
    	Boolean b= kolejka.remove(zgl);
        MVdlKolejki.setValue(kolejka.size());
        return b;
    }

    public boolean usunWskazany2(Klient zgl)
    {
        Boolean b= kolejka2.remove(zgl);
        MVdlKolejki2.setValue(kolejka2.size());
        return b;
    }

    public boolean usunWskazany3(Klient zgl)
    {
        Boolean b= kolejka3.remove(zgl);
        MVdlKolejki3.setValue(kolejka3.size());
        return b;
    }

    public boolean usunWskazany4(Klient zgl)
    {
        Boolean b= kolejka4.remove(zgl);
        MVdlKolejki4.setValue(kolejka4.size());
        return b;
    }
    
    public int liczbaZgl()
    {
        return kolejka.size();
    }

	public boolean isWolne() {
		return wolne;
	}

	public void setWolne(boolean wolne) {
		this.wolne = wolne;
	}

    public int liczbaZgl2()
    {
        return kolejka2.size();
    }

    public boolean isWolne2() {
        return wolne2;
    }

    public void setWolne2(boolean wolne2) {
        this.wolne2 = wolne2;
    }

    public int liczbaZgl3()
    {
        return kolejka3.size();
    }

    public boolean isWolne3() {
        return wolne3;
    }

    public void setWolne3(boolean wolne3) {
        this.wolne3 = wolne3;
    }

    public int liczbaZgl4()
    {
        return kolejka4.size();
    }

    public boolean isWolne4() {
        return wolne4;
    }

    public void setWolne4(boolean wolne4) {
        this.wolne4 = wolne4;
    }

	@Override
	public void reflect(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean filter(IPublisher publisher, INotificationEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}