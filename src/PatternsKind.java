//208384420

/**
 * this class returning the patterns with a for loop.
 */
public class PatternsKind {

    /**
     * this is the constructor.
     */
    public PatternsKind() {

    }

    /**
     * return each pattern by the number.
     * @param numOfPattern the num of each pattern
     * @return the wanted pattern
     */
    public String getPattern(int numOfPattern) {


        if (numOfPattern == 1) {
            return "<np>([^<]+)<\\/np>(?: ,)? such as (<np>[^<]+<\\/np>?(?:(?: ,)? <np>[^<]+<\\/np>)*"
                    + "(?:(?: or | , or | and | , and )<np>[^<]+<\\/np>)?)";

        }
        if (numOfPattern == 2) {

            return "such <np>([^<]+)<\\/np>(?: ,)? as (?<words><np>[^<]+<\\/np> (?:, <np>[^<]+<\\/np> )*"
                    + "(?:(?: or | , or | and | , and )?<np>[^<]+<\\/np>)?)";

        }

        if (numOfPattern == 3) {

            return "(<np>[^<]+<\\/np>)(?: ,)? which is (?:(?:, )?(?:an example of |a kind of |a class of )?"
                    + "<np>([^<]+)<\\/np>)";
        }

        if (numOfPattern == 4) {
            return "<np>([^<]+)<\\/np>(?: ,)? including (<np>[^<]+<\\/np>?(?:(?: ,)? <np>[^<]+<\\/np>)*"
                    + "(?:(?: or | , or | and | , and )<np>[^<]+<\\/np>)?)";

        }
        if (numOfPattern == 5) {
            return "<np>([^<]+)<\\/np>(?: ,)? especially (<np>[^<]+<\\/np>?(?:(?: ,)? <np>[^<]+<\\/np>)*"
                    + "(?:(?: or | , or | and | , and )<np>[^<]+<\\/np>)?)";
        }
        return "null";

    }


}