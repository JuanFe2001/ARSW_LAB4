package edu.eci.arsw.blueprints.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@Component
public class BluePrintApp {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
		Blueprint sala = new Blueprint("Daniel", "Sala2C", new Point[] { new Point(0, 0), new Point(1, 3) });
		Blueprint cuarto = new Blueprint("Daniel", "Cuarto3C", new Point[] { new Point(0, 1), new Point(2, 4) });
		Blueprint baño = new Blueprint("Daniel", "Baño4C", new Point[] { new Point(0, 2), new Point(3, 5) });
		Blueprint techo = new Blueprint("Daniel", "Techo4D",
				new Point[] { new Point(1, 1), new Point(1, 1), new Point(2, 2) });
		try {
			System.out.println("----------Registro de Planos----------");
			bps.addNewBlueprint(sala);
			bps.addNewBlueprint(cuarto);
			bps.addNewBlueprint(baño);
			bps.addNewBlueprint(techo);
			System.out.println("Plano de la Sala: " + bps.getBlueprint("Daniel", "Sala2C"));
			System.out.println("Plano del cuarto: " + bps.getBlueprint("Daniel", "Cuarto3C"));
			System.out.println("Plano del baño: " + bps.getBlueprint("Daniel", "Baño4C"));
			System.out.println("Plano del techo: " + bps.getBlueprint("Daniel", "Techo4D"));
			System.out.println("Puntos del plano del techo sin filtrar: ");
			for (int i = 0; i < bps.getBlueprint("Daniel", "Techo4D").getPoints().size(); i++) {
				System.out.println("x: " + bps.getBlueprint("Daniel", "Techo4D").getPoints().get(i).getX());
				System.out.println("x: " + bps.getBlueprint("Daniel", "Techo4D").getPoints().get(i).getY());
				System.out.println();
			}
			System.out.println("Planos del autor Daniel: " + bps.getBlueprintsByAuthor("Daniel"));
			bps.bluePrintFiltering(bps.getBlueprint("Daniel", "Techo4D"));
			System.out.println("PuntosPlano del techo filtrado: ");
			for (int i = 0; i < bps.getBlueprint("Daniel", "Techo4D").getPoints().size(); i++) {
				System.out.println("x: " + bps.getBlueprint("Daniel", "Techo4D").getPoints().get(i).getX());
				System.out.println("x: " + bps.getBlueprint("Daniel", "Techo4D").getPoints().get(i).getY());
				System.out.println();
			}
		} catch (BlueprintPersistenceException e) {
			e.printStackTrace();
		} catch (BlueprintNotFoundException e) {
			e.printStackTrace();
		}
	}

}
