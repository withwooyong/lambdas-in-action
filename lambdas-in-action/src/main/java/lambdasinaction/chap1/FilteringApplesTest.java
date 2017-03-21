package lambdasinaction.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import lambdasinaction.vo.Apple;

public class FilteringApplesTest {
	
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(
				new Apple(80, "green"), 
				new Apple(155, "green"), 
				new Apple(120, "red"));
		System.out.println(inventory);
		
		List<Apple> greenapples = filterApples(inventory, FilteringApplesTest::isGreenApple);
		System.out.println(greenapples);
		
		List<Apple> heavyApples = filterApples(inventory, FilteringApplesTest::isHeavyApple);
		System.out.println(heavyApples);
				
		List<Apple> greenapples2 = filterApples(inventory, (Apple apple) -> "green".equals(apple.getColor()));
		System.out.println(greenapples2);
		
		List<Apple> heavyApples2 = filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);
		System.out.println(heavyApples2);
		
		List<Apple> weirdApples = filterApples(inventory, (Apple apple) -> apple.getWeight() <= 80 || "brown".equals(apple.getColor()));
		System.out.println(weirdApples);
	}
	
	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterHeavyApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > 150) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}
	
	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}
}
