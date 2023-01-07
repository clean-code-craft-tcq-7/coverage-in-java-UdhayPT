package TypewiseAlert;

import java.util.ArrayList;

public class TypewiseAlert {
	public enum BreachType {
		NORMAL, TOO_LOW, TOO_HIGH
	};

	public static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
		if (value < lowerLimit) {
			return BreachType.TOO_LOW;
		}
		if (value > upperLimit) {
			return BreachType.TOO_HIGH;
		}
		return BreachType.NORMAL;
	}

	public enum CoolingType {
		PASSIVE_COOLING, HI_ACTIVE_COOLING, MED_ACTIVE_COOLING
	};

	public static BreachType classifyTemperatureBreach(CoolingType coolingType, double temperatureInC) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		getLimits(list, coolingType);
		int lowerLimit = list.get(0);
		int upperLimit = list.get(1);

		return inferBreach(temperatureInC, lowerLimit, upperLimit);
	}

	private static void getLimits(ArrayList<Integer> list, CoolingType coolingType) {
		int lowerLimit = 0;
		int upperLimit = 0;
		if (coolingType == CoolingType.PASSIVE_COOLING) {
			upperLimit = 35;
		} else {
			upperLimit = getUpperLimit(coolingType);
		}
		list.add(lowerLimit);
		list.add(upperLimit);
	}

	private static int getUpperLimit(CoolingType coolingType) {
		if(coolingType == CoolingType.HI_ACTIVE_COOLING) {
			return 45;
		} else {
			return 40;
		}
	}

	public enum AlertTarget {
		TO_CONTROLLER, TO_EMAIL
	};

	public class BatteryCharacter {
		public CoolingType coolingType;
		public String brand;
	}

	public static void checkAndAlert(AlertTarget alertTarget, BatteryCharacter batteryChar, double temperatureInC) {

		BreachType breachType = classifyTemperatureBreach(batteryChar.coolingType, temperatureInC);

		switch (alertTarget) {
		case TO_CONTROLLER:
			sendToController(breachType);
			break;
		case TO_EMAIL:
			sendToEmail(breachType);
			break;
		}
	}

	public static void sendToController(BreachType breachType) {
		int header = 0xfeed;
		System.out.printf("%d : %s\n", header, breachType);
	}

	public static void sendToEmail(BreachType breachType) {
		String recepient = "a.b@c.com";
		switch (breachType) {
		case TOO_LOW:
			System.out.printf("To: %s\n", recepient);
			System.out.println("Hi, the temperature is too low\n");
			break;
		case TOO_HIGH:
			System.out.printf("To: %s\n", recepient);
			System.out.println("Hi, the temperature is too high\n");
			break;
		default:
			break;
		}
	}
}
