import java.util.*;
public class Dictionary {

    private DictionaryService dictionaryService;
    public void setDictionary(DictionaryService dictionaryService){
        this.dictionaryService = dictionaryService;
    }
    public List<String> getDictionary() {
        return dictionaryService.getDictionary();
    }
    public boolean isValidWord(String word) {
        return dictionaryService.isValidWord(word);
    }
    public List<String> findPossibleWords(String input) {
        ArrayList <String> matches = new ArrayList <String> ();
        List<String> dictionary = getDictionary();
        input = input.toLowerCase();

        for (String word : dictionary) {
            Boolean nonMatch = true;
            for (char chWord : word.toCharArray()) {
                String w = Character.toString(chWord);
                if (word.length() - word.replace(w, "").length() !=
                        input.length() - input.replace(w, "").length()) {
                    nonMatch = false;
                    break;
                }
            }
            if (nonMatch) {
                matches.add(word);
            }
        }
        System.out.println(matches);
        return matches;
    }
}
