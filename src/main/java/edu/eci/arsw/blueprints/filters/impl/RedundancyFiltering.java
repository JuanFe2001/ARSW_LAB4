package edu.eci.arsw.blueprints.filters.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
public class RedundancyFiltering implements BlueprintFilter {

	@Override
	public Blueprint bluePrintFiltering(Blueprint bp) {
		ArrayList<Point> filteredPoints = new ArrayList<>();
		for (int i = 0; i < bp.getPoints().size() - 1; i++) {
			if (!(bp.getPoints().get(i).equals(bp.getPoints().get(i + 1)))) {
				filteredPoints.add(bp.getPoints().get(i));
			}
		}
		filteredPoints.add(bp.getPoints().get(bp.getPoints().size() - 1));
		bp.setPoints(filteredPoints.toArray(new Point[filteredPoints.size()]));
		return bp;
	}

}
