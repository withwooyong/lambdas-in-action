package lambdasinaction.chap5;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {

	public static void main(String... args) throws Exception {

		// Stream.of
		Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
		stream.map(String::toUpperCase).forEach(System.out::print);
		System.out.println("================= 1");
		// Stream.empty
		Stream<String> emptyStream = Stream.empty();

		// Arrays.stream
		int[] numbers = { 2, 3, 5, 7, 11, 13 };
		System.out.println(Arrays.stream(numbers).sum());

		// Stream.iterate
		Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::print);
		System.out.println("================= 2");
		Stream.iterate(0, n -> n + 3).limit(10).forEach(System.out::print);
		System.out.println("================= 3");
		
		// fibonnaci with iterate
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], (t[0] + t[1]) }).limit(10)
				.forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
		System.out.println("================= 4");
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(10).map(t -> t[0])
				.forEach(System.out::println);
		System.out.println("================= 5");
		
		// random stream of doubles with Stream.generate
		Stream.generate(Math::random).limit(2).forEach(System.out::println);
		System.out.println("================= 6");
		
		IntStream.iterate(1, n -> n + 1).limit(2).forEach(System.out::println);
		// stream of 1s with Stream.generate
		IntStream.generate(() -> 1).limit(2).forEach(System.out::println);
		System.out.println("================= 7");
		IntStream.generate(new IntSupplier() {
			public int getAsInt() {
				return 2;
			}
		}).limit(5).forEach(System.out::println);
		System.out.println("================= 8");
		
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;

			public int getAsInt() {
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return this.previous;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);

		long uniqueWords = Files.lines(Paths.get("C:\\Users\\user\\git\\lambdas-in-action\\lambdas-in-action\\src\\main\\java\\lambdasinaction\\chap5\\data.txt"), Charset.defaultCharset())
				.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();

		System.out.println("There are " + uniqueWords + " unique words in data.txt");

	}
}
