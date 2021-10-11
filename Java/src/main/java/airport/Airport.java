package airport;

import models.ClassificationLevel;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;
import planes.ExperimentalPlane;

import java.util.*;

import org.testng.Assert;

// version: 1.1
// made by Vitali Shulha
// 4-Jan-2019

public class Airport {
	private List<? extends Plane> planes;

	public boolean isHasUnclassifiedPlane() {
		List<ExperimentalPlane> experimentalPlanes = this.getExperimentalPlanes();
		for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
			if (experimentalPlane.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED) {
				return true;
			}
		}
		return false;
	}

	public boolean isHasBomber() {
		List<MilitaryPlane> bomberMilitaryPlanes = this.getBomberMilitaryPlanes();
		for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
			if ((militaryPlane.getType() == MilitaryType.BOMBER))
				return true;
		}
		return false;
	}

	public List<PassengerPlane> getPassengerPlanes() {
		List<? extends Plane> l = this.planes;
		List<PassengerPlane> x = new ArrayList<>();
		for (Plane p : l) {
			if (p instanceof PassengerPlane) {
				x.add((PassengerPlane) p);
			}
		}
		return x;
	}

	public List<MilitaryPlane> getMilitaryPlanes() {
		List<MilitaryPlane> militaryPlanes = new ArrayList<>();
		for (Plane plane : planes) {
			if (plane instanceof MilitaryPlane) {
				militaryPlanes.add((MilitaryPlane) plane);
			}
		}
		return militaryPlanes;
	}

	public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
		List<PassengerPlane> passengerPlanes = getPassengerPlanes();
		PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);
		for (int i = 0; i < passengerPlanes.size(); i++) {
			if (passengerPlanes.get(i).getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
				planeWithMaxCapacity = passengerPlanes.get(i);
			}
		}

		return planeWithMaxCapacity;
	}

	public List<MilitaryPlane> getTransportMilitaryPlanes() {
		List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
		List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
		for (int i = 0; i < militaryPlanes.size(); i++) {
			MilitaryPlane plane = militaryPlanes.get(i);
			if (plane.getType() == MilitaryType.TRANSPORT) {
				transportMilitaryPlanes.add(plane);
			}
		}
		return transportMilitaryPlanes;
	}

	public List<MilitaryPlane> getBomberMilitaryPlanes() {
		List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
		List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
		for (int i = 0; i < militaryPlanes.size(); i++) {
			MilitaryPlane plane = militaryPlanes.get(i);
			if (plane.getType() == MilitaryType.BOMBER) {
				bomberMilitaryPlanes.add(plane);
			}
		}
		return bomberMilitaryPlanes;

	}

	public List<ExperimentalPlane> getExperimentalPlanes() {
		List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
		for (Plane plane : planes) {
			if (plane instanceof ExperimentalPlane) {
				experimentalPlanes.add((ExperimentalPlane) plane);
			}
		}
		return experimentalPlanes;
	}

	public Airport sortByMaxDistance() {
		Collections.sort(planes, new Comparator<Plane>() {
			public int compare(Plane o1, Plane o2) {
				return o1.getMaxFlightDistance() - o2.getMaxFlightDistance();
			}
		});
		return this;
	}

	public Airport sortByMaxSpeed() {
		Collections.sort(planes, new Comparator<Plane>() {
			public int compare(Plane o1, Plane o2) {
				return o1.getMaxSpeed() - o2.getMaxSpeed();
			}
		});
		return this;
	}

	public boolean isSortedByMaxLoadCapacity() {
		for (int i = 0; i < planes.size() - 1; i++) {
			Plane currentPlane = planes.get(i);
			Plane nextPlane = planes.get(i + 1);
			if (currentPlane.getManLoadCapacity() > nextPlane.getManLoadCapacity()) {
				return false;
			}
		}
		return true;
	}

	public Airport sortByMaxLoadCapacity() {
		Collections.sort(planes, new Comparator<Plane>() {
			public int compare(Plane o1, Plane o2) {
				return o1.getManLoadCapacity() - o2.getManLoadCapacity();
			}
		});
		return this;
	}

	public List<? extends Plane> getPlanes() {
		return planes;
	}

	private void print(Collection<? extends Plane> collection) {
		Iterator<? extends Plane> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Plane plane = iterator.next();
			System.out.println(plane);
		}
	}

	@Override
	public String toString() {
		return "Airport{" + "Planes=" + planes.toString() + '}';
	}

	public Airport(List<? extends Plane> planes) {
		this.planes = planes;
	}

}
