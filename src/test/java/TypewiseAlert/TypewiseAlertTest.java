package TypewiseAlert;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import TypewiseAlert.TypewiseAlert.AlertTarget;
import TypewiseAlert.TypewiseAlert.BatteryCharacter;
import TypewiseAlert.TypewiseAlert.CoolingType;

public class TypewiseAlertTest {

	@Test
	public void testInferBreach() {
		assertTrue(TypewiseAlert.inferBreach(12, 20, 30) == TypewiseAlert.BreachType.TOO_LOW);
		assertTrue(TypewiseAlert.inferBreach(32, 20, 30) == TypewiseAlert.BreachType.TOO_HIGH);
		assertTrue(TypewiseAlert.inferBreach(25, 20, 30) == TypewiseAlert.BreachType.NORMAL);
	}

	@Test
	public void testClassifyTemperatureBreach() {
		CoolingType coolingType = CoolingType.PASSIVE_COOLING;
		assertTrue(TypewiseAlert.classifyTemperatureBreach(coolingType , 30) == TypewiseAlert.BreachType.NORMAL);
		CoolingType hiCoolingType = CoolingType.HI_ACTIVE_COOLING;
		assertTrue(TypewiseAlert.classifyTemperatureBreach(hiCoolingType , 40) == TypewiseAlert.BreachType.NORMAL);
		CoolingType medCoolingType = CoolingType.MED_ACTIVE_COOLING;
		assertTrue(TypewiseAlert.classifyTemperatureBreach(medCoolingType , 35) == TypewiseAlert.BreachType.NORMAL);
	}

	@Test
	public void testCheckAndAlert() {
		AlertTarget alertTarget =  AlertTarget.TO_CONTROLLER;
		TypewiseAlert alert = new TypewiseAlert();
		BatteryCharacter batteryChar = alert.new BatteryCharacter();
		batteryChar.coolingType = CoolingType.PASSIVE_COOLING;
		batteryChar.brand = "BRAND";
		TypewiseAlert.checkAndAlert(alertTarget, batteryChar, 30);
	}
	
	@Test
	public void testCheckAndAlertEmail() {
		AlertTarget alertTarget =  AlertTarget.TO_EMAIL;
		TypewiseAlert alert = new TypewiseAlert();
		BatteryCharacter batteryChar = alert.new BatteryCharacter();
		batteryChar.coolingType = CoolingType.PASSIVE_COOLING;
		batteryChar.brand = "BRAND";
		TypewiseAlert.checkAndAlert(alertTarget, batteryChar, 30);
		TypewiseAlert.checkAndAlert(alertTarget, batteryChar, 46);
		TypewiseAlert.checkAndAlert(alertTarget, batteryChar, -2);
	}

}
