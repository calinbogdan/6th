import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Public key: ");
        int publicKey = scanner.nextInt();
        int primitiveRoot = getPrimitiveRootOf(publicKey);

        User firstUser = new User();
        User secondUser = new User();

        System.out.print("Private key of first user: ");
        firstUser.setPrivateKey(scanner.nextInt());

        System.out.print("Private key of second user: ");
        secondUser.setPrivateKey(scanner.nextInt());


        int firstUserY = firstUser.getY(primitiveRoot, publicKey);
        int secondUserY = secondUser.getY(primitiveRoot, publicKey);

        int firstUserK = firstUser.getK(secondUserY, publicKey);
        int secondUserK = secondUser.getK(firstUserY, publicKey);

        System.out.println(String.format("First K: %d, second K: %d", firstUserK, secondUserK));
    }

    private static int getPrimitiveRootOf(int publicKey) {
        Map<Integer, List<Integer>> theBestMapEver = IntStream.range(1, publicKey).boxed()
                .collect(toMap(identity(), a -> IntStream.range(1, publicKey).boxed()
                        .map(val -> (int) a * val % publicKey)
                        .collect(Collectors.toList())));


        return theBestMapEver.entrySet().stream()
                .filter(resultsMapEntry -> {
                    List<Integer> resultsList = resultsMapEntry.getValue();
                    return resultsList.size() == resultsList.stream().distinct().collect(toList()).size();
                })
                .findFirst().get()
                .getKey();
    }
}
