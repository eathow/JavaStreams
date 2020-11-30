import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A set of tools which operate on a Stack
 */
public class StackOperations {

    /**
     * Uses Java's streams to generate a Queue object from an existing Stack object in the same order.
     * Use the LinkedLists class since it implements the Queue interface
     *
     * @param stackOriginal the stack to be converted to a Queue
     * @return a queue containing all the elements of the original Stack in the same order
     */
    public static Queue<Character> toQueue(Stack<Character> stackOriginal) {
    	Queue<Character> queue = new LinkedList<>();
    	Iterator<Character> value = stackOriginal.iterator();
    	value.forEachRemaining((stackoriginal) -> queue.add((Character) value.next()));
    	return queue;
    }

    /**
     * Uses Java's streams to generate a new Stack object from an existing Stack object but in reversed order.
     * The original stack's becomes empty after copied over
     *
     * @param stackOriginal the stack to be reversed
     * @return a new stack containing the same items from the original Stack
     */
    
    public static Stack<Character> reverseStack(Stack<Character> stackOriginal) {
    	Stack<Character> newstack = new Stack<Character>();
    	int size = stackOriginal.size();
    	IntStream.range(1, size).forEachOrdered(i -> newstack.push(stackOriginal.pop()));
    	return newstack; 
    }

    /**
     * Uses Java's streams to sum all items between the star and end positions inclusive.
     * Account for invalid ranges such as endPosition < startPosition or large ranges
     *
     * @param stackOriginal the stack with the items to sum
     * @param startPosition the position of the starting element in the sum (included)
     * @param endPosition   the position of the end element in the sum (included)
     * @return the sum of all items between the star and end positions inclusive. The method returns -1 if the sum range is invalid
     */
    public static int sumBetween(Stack<Integer> stackOriginal, int startPosition, int endPosition) {
    	long size = stackOriginal.stream().count();
    	if(startPosition<endPosition && startPosition>=0 && endPosition<size) {
    		return stackOriginal.stream()
    				.skip(startPosition-1)
    				.limit(endPosition-startPosition)
    				.mapToInt(x -> x).sum();
    	}
    	else {
    		return -1;
    	}
    }

    /**
     * Uses Java's streams to read the characters from InputFile.txt one character at a time.
     * If the character read is a number, it is added to a Stack. Otherwise, the character is simply discarded.
     * In case of an Exception, simply print the stack trace which will cause the method to stop the read process and return.
     * <p>
     * Here is an example which filters the characters from a String
     *
     * <pre>   {@code
     *
     * String line = "abc0123!@#$";
     *   line.chars()
     *       .mapToObj(i -> (char) i)
     *       .forEach(c -> System.out.println(c));
     *
     * }</pre> </p>
     *
     * @param filePath the input file containing alpha-numeric characters
     * @return a stack containing numeric characters only
     * @throws FileNotFoundException 
     */
    public static Stack<Character> readNumericFromFile(final String filePath) {
    	Path path = Paths.get(filePath);
    	Stack<Character> read = new Stack<>();
    	try {
    		List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
    		list.forEach(string -> string.chars() 
    				.filter(i -> ((i>47)&&(i<58)))
    				.map(readC -> (char) readC)
    				.forEach(readChar -> read.add((char) readChar))
    		);
 
    	}
    	catch(IOException ex) {
    		System.err.println("File not found");
    		ex.printStackTrace();
    	}
    	return read;
    }
}
