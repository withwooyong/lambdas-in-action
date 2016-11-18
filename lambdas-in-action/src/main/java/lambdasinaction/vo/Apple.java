package lambdasinaction.vo;

import lombok.Data;

@Data
public class Apple {
	
	private int weight = 0;
	private String color = "";

	public Apple(int weight, String color) {
		this.weight = weight;
		this.color = color;
	}	
}
