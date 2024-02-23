package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class SubsamplingFiltering implements BlueprintFilter {

	@Override
	public Blueprint bluePrintFiltering(Blueprint bp) {
		List<Point> Puntos = new ArrayList<Point>();
		for (int i = 0; i < bp.getPoints().size(); i++) {
			if (i % 2 != 0) {
				break;
			}
			Puntos.add(bp.getPoints().get(i));
			i++;
		}
		bp.newBluePrint();
		for (Point punto : Puntos) {
			bp.addPoint(punto);
		}
		return bp;
	}

}
