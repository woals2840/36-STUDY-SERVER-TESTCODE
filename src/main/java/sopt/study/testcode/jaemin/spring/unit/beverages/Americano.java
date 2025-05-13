package sopt.study.testcode.jaemin.spring.unit.beverages;

public class Americano implements Beverage {
	@Override
	public String getName() {
		return "Americano";
	}

	@Override
	public int getPrice() {
		return 4000;
	}
}
