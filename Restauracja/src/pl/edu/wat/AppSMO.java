package pl.edu.wat; /**
 * @author Dariusz Pierzchala
 * 
 * Description: Klasa główna. Tworzy dwa SMO, inicjalizuje je.Startuje symulację. Wyświetla statystyki.
 * 
 * Wersja testowa.
 */

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Diagram.DiagramType;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.math.BigDecimal;


public class AppSMO {
	public static void main(String[] args) {
		try {
			SimManager simManager = SimManager.initInstance();
			// Powołanie Systemu Masowej Obslugi
			Smo smo = new Smo();

			Zimno z = new Zimno();
			int zimno = z.getZimno();
			int zimno2 = zimno+15;
			System.out.println("Zimno od minuty: "+zimno+" do "+zimno2);

			// Utworzenie otoczenia sklepu
			OtoczenieSklepu otoczenieSklepu = new OtoczenieSklepu(smo, zimno);

			// Dwa sposoby zaplanowanego końca symulacji
			simManager.setEndSimTime(100);
			// lub
			//SimControlEvent stopEvent = new SimControlEvent(1000.0, SimControlStatus.STOPSIMULATION);

			// Uruchomienie symulacji za pośrednictwem metody "start"
			simManager.startSimulation();

			// Wyniki
			Diagram d0 = new Diagram(DiagramType.HISTOGRAM,
					"Przybywanie zgłoszeń - rozkład Poissona");
			d0.add(otoczenieSklepu.MVczasyMiedzyZgl, java.awt.Color.BLUE);
			d0.show();
			double wynik = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę:   "
							+ wynik);
			wynik = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia czasu oczekiwania na obsługę:   "
							+ wynik);			
			wynik = BigDecimal.valueOf(Statistics
					.standardDeviation(smo.MVczasy_oczekiwania)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi:       "
							+ wynik);
			wynik = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania))
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę: "
					+ wynik);
			wynik = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia długości kolejki:       "
							+ wynik);
			wynik = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia długości kolejki:       "
							+ wynik);
			wynik = BigDecimal.valueOf(Statistics
					.max(smo.MVdlKolejki)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość maksymalna długości kolejki:       "
							+ wynik);
		
			Diagram d1 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania");
			d1.add(smo.MVczasy_obslugi, java.awt.Color.GREEN);
			d1.show();

			Diagram d2 = new Diagram(DiagramType.HISTOGRAM,
					"Dlugość kolejki - HISTOGRAM");
			d2.add(smo.MVdlKolejki, java.awt.Color.BLUE);
			d2.show();

			Diagram d3 = new Diagram(DiagramType.HISTOGRAM,
					"Czasy oczekiwania na obsługę");
			d3.add(smo.MVczasy_oczekiwania, java.awt.Color.BLUE);
			d3.show();

			Diagram d4 = new Diagram(DiagramType.TIME,
					"Długość kolejki w czasie");
			d4.add(smo.MVdlKolejki, java.awt.Color.RED);
			d4.show();

			double wynik2 = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVczasy_oczekiwania2)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę w 2 kolejce:   "
							+ wynik2);
			wynik2 = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVczasy_oczekiwania2)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia czasu oczekiwania na obsługę w 2 kolejce:   "
							+ wynik2);
			wynik2 = BigDecimal.valueOf(Statistics
					.standardDeviation(smo.MVczasy_oczekiwania2)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi w 2 kolejce:       "
							+ wynik2);
			wynik2 = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania2))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę w 2 kolejce: "
					+ wynik2);
			wynik2 = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVdlKolejki2)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia długości 2 kolejki:       "
							+ wynik2);
			wynik2 = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVdlKolejki2)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia długości  2 kolejki:       "
							+ wynik2);
			wynik2 = BigDecimal.valueOf(Statistics
					.max(smo.MVdlKolejki2)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość maksymalna długości 2 kolejki:       "
							+ wynik2);

			Diagram d5 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania w 2 gnieździe");
			d5.add(smo.MVczasy_obslugi2, java.awt.Color.GREEN);
			d5.show();

			Diagram d6 = new Diagram(DiagramType.HISTOGRAM,
					"Dlugość kolejki 2 - HISTOGRAM");
			d6.add(smo.MVdlKolejki2, java.awt.Color.BLUE);
			d6.show();

			Diagram d7 = new Diagram(DiagramType.HISTOGRAM,
					"Czasy oczekiwania na obsługę w 2 gnieździe");
			d7.add(smo.MVczasy_oczekiwania2, java.awt.Color.BLUE);
			d7.show();

			Diagram d8 = new Diagram(DiagramType.TIME,
					"Długość 2 kolejki w czasie");
			d8.add(smo.MVdlKolejki2, java.awt.Color.RED);
			d8.show();

			double wynik3 = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVczasy_oczekiwania3)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę w 3 kolejce:   "
							+ wynik3);
			wynik3 = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVczasy_oczekiwania3)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia czasu oczekiwania na obsługę w 3 kolejce:   "
							+ wynik3);
			wynik3 = BigDecimal.valueOf(Statistics
					.standardDeviation(smo.MVczasy_oczekiwania3)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi w 3 kolejce:       "
							+ wynik3);
			wynik3 = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania3))
			.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę w 3 kolejce: "
					+ wynik3);
			wynik3 = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVdlKolejki3)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia długości 3 kolejki:       "
							+ wynik3);
			wynik3 = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVdlKolejki3)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia długości 3 kolejki:       "
							+ wynik3);
			wynik3 = BigDecimal.valueOf(Statistics
					.max(smo.MVdlKolejki3)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość maksymalna długości 3 kolejki:       "
							+ wynik3);

			Diagram d9 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania 3");
			d9.add(smo.MVczasy_obslugi3, java.awt.Color.GREEN);
			d9.show();

			Diagram d10 = new Diagram(DiagramType.HISTOGRAM,
					"Dlugość kolejki 3 - HISTOGRAM");
			d10.add(smo.MVdlKolejki3, java.awt.Color.BLUE);
			d10.show();

			Diagram d11 = new Diagram(DiagramType.HISTOGRAM,
					"Czasy oczekiwania na obsługę 3");
			d11.add(smo.MVczasy_oczekiwania3, java.awt.Color.BLUE);
			d11.show();

			Diagram d12 = new Diagram(DiagramType.TIME,
					"Długość kolejki 3 w czasie");
			d12.add(smo.MVdlKolejki3, java.awt.Color.RED);
			d12.show();

			double wynik4 = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVczasy_oczekiwania4)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia czasu oczekiwania na obsługę w 4 kolejce:   "
							+ wynik4);
			wynik4 = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVczasy_oczekiwania4)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia czasu oczekiwania na obsługę w 4 kolejce:   "
							+ wynik4);
			wynik4 = BigDecimal.valueOf(Statistics
					.standardDeviation(smo.MVczasy_oczekiwania4)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Odchylenie standardowe dla czasu obsługi w 4 kolejce:       "
							+ wynik4);
			wynik4 = BigDecimal.valueOf(Statistics.max(smo.MVczasy_oczekiwania4))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out.println("Wartość maksymalna czasu oczekiwania na obsługę w 4 kolejce: "
					+ wynik4);
			wynik4 = BigDecimal.valueOf(Statistics
					.arithmeticMean(smo.MVdlKolejki4)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość średnia długości 4 kolejki:       "
							+ wynik4);
			wynik4 = BigDecimal.valueOf(Statistics
					.weightedMean(smo.MVdlKolejki4)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Ważona wartość średnia długości  4 kolejki:       "
							+ wynik4);
			wynik4 = BigDecimal.valueOf(Statistics
					.max(smo.MVdlKolejki4)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();
			System.out
					.println("Wartość maksymalna długości 4 kolejki:       "
							+ wynik4);

			Diagram d13 = new Diagram(DiagramType.DISTRIBUTION, "Czas obsługiwania w 4 gnieździe");
			d13.add(smo.MVczasy_obslugi4, java.awt.Color.GREEN);
			d13.show();

			Diagram d14 = new Diagram(DiagramType.HISTOGRAM,
					"Dlugość kolejki 4 - HISTOGRAM");
			d14.add(smo.MVdlKolejki4, java.awt.Color.BLUE);
			d14.show();

			Diagram d15 = new Diagram(DiagramType.HISTOGRAM,
					"Czasy oczekiwania na obsługę w 4 gnieździe");
			d15.add(smo.MVczasy_oczekiwania4, java.awt.Color.BLUE);
			d15.show();

			Diagram d16 = new Diagram(DiagramType.TIME,
					"Długość 4 kolejki w czasie");
			d16.add(smo.MVdlKolejki4, java.awt.Color.RED);
			d16.show();

			
		} catch (SimControlException e) {
			e.printStackTrace();
		}

	}
}
