package sopt.study.testcode.jaemin.spring.unit.beverages;

public class Latte implements Beverage {
	@Override
	public String getName() {
		return "Latte";
	}

	@Override
	public int getPrice() {
		return 5000;
	}
}
