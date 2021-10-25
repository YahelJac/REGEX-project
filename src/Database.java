//208384420

import java.util.Comparator;
import java.util.TreeMap;


/**
 * this class responsible on the data of the program. its filed by the files in the CreatHypernymDatabase.
 */
public class Database {

    private TreeMap<String, TreeMap<String, Integer>> hypernymMap;

    /**
     * the constructor, its have an array of Hypernym.
     */
    public Database() {
        hypernymMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    }

    /**
     * return the Hypernym list.
     *
     * @return the hypernymList
     */
    public TreeMap<String, TreeMap<String, Integer>> getHupernymMap() {
        return hypernymMap;
    }

    /**
     * add a word to a Hypernym word list.
     *
     * @param word     the new word
     * @param hypernym the hypernym the word belongs
     */

    public void addWord(String word, String hypernym) {
        if (isExist(hypernym)) {
            if (hypernymMap.get(hypernym).containsKey(word)) {
                int temp = hypernymMap.get(hypernym).get(word) + 1;
                hypernymMap.get(hypernym).replace(word, temp);
            } else {
                hypernymMap.get(hypernym).put(word, 1);
            }

        } else {
            hypernymMap.put(hypernym, new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER));
            hypernymMap.get(hypernym).put(word, 1);
        }
    }


    /**
     * check is the hypernym is exists.
     *
     * @param hypernym the hypernym
     * @return boolean true if exists, false if not
     */
    public boolean isExist(String hypernym) {


        if (hypernymMap.containsKey(hypernym)) {
            return true;
        }
        return false;
    }

    /**
     * sort the lists by the name of the hypernym.
     */
    public class SortHypernymByInt implements Comparator<String> {
        private TreeMap<String, Integer> hypernymMap;

        /**
         * this is the constructor.
         * @param treeMap the tree that need to be sorted.
         */
        public SortHypernymByInt(TreeMap<String, Integer> treeMap) {
            this.hypernymMap = treeMap;

        }

        @Override
        public int compare(String o1, String o2) {
            if (this.hypernymMap.get(o1).compareTo(hypernymMap.get(o2)) == 0) {
                return 1;
            } else {
                return -(hypernymMap.get(o1).compareTo(hypernymMap.get(o2)));
            }
        }


    }

    /**
     * sort the lists by the word. how many times ist appear in te hypernym.
     */
    public class SortHypernymByInFor2 implements Comparator<String> {
        private TreeMap<String, TreeMap<String, Integer>> hypernymMap;
        private String word;

        /**
         * this is the constructor.
         * @param treeMap the tree that need to be sorted.
         * @param word the word.
         */
        public SortHypernymByInFor2(TreeMap<String, TreeMap<String, Integer>> treeMap, String word) {
            this.hypernymMap = treeMap;
            this.word = word;

        }

        @Override
        public int compare(String o1, String o2) {

            if (this.hypernymMap.get(o1).get(word).compareTo(hypernymMap.get(o2).get(word)) == 0) {
                return 1;
            } else {
                return -(this.hypernymMap.get(o1).get(word).compareTo(hypernymMap.get(o2).get(word)));
            }
        }
    }

    /**
     * calling the sorted class.
     * @param treeMap the tree that need to be sorted
     * @return the sorted tree
     */
    public TreeMap<String, Integer> sortTree(TreeMap<String, Integer> treeMap) {

        TreeMap<String, Integer> sortedTree = new TreeMap<>(new SortHypernymByInt(treeMap));
        sortedTree.putAll(treeMap);
        return sortedTree;
    }
    /**
     * calling the sorted class.
     * @param treeMap the tree that need to be sorted
     * @param word the word
     * @return the sorted tree
     */
    public TreeMap<String, TreeMap<String, Integer>> sortTreeFor2(TreeMap<String, TreeMap<String, Integer>> treeMap,
                                                                  String word) {

        TreeMap<String, TreeMap<String, Integer>> sortedTree = new TreeMap<>(new SortHypernymByInFor2(treeMap, word));
        sortedTree.putAll(treeMap);
        return sortedTree;
    }


}
