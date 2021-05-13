
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
@RunWith(MockitoJUnitRunner.class)
public class DictionaryTester {

    private Dictionary dictionary;
    private DictionaryService dictService;
    List<String> dictionaryList;

    @Before
    public void setUp() {
        dictionary = new Dictionary();
        dictService = mock(DictionaryService.class);
        dictionary.setDictionary(dictService);
        when(dictService.getDictionary()).thenReturn(createDictionaryArray());
        dictionaryList = dictService.getDictionary();
    }
    static List<String> createDictionaryArray() {
        List<String> listDictionary = new ArrayList<String>();
        BufferedReader reader;

        try {
            ClassLoader loader = DictionaryTester.class.getClassLoader();
            File file = new File(loader.getResource("EnglishWords").getFile());
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                listDictionary.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDictionary;
    }
    public boolean isThisEnglish(String word) {
        for (String w : dictionaryList) {
            if (w.equals(word.toLowerCase())) {
                System.out.println(word + " is a valid english word");
                return true;
            }
        }
        return false;
    }

    @Test
    public void validateColdWord() {
        when(dictService.isValidWord("COLD")).thenReturn(isThisEnglish("COLD"));
        Assert.assertTrue(dictionary.isValidWord("COLD"));
    }
    @Test
    public void validateHOTWord() {
        when(dictService.isValidWord("HOT")).thenReturn(isThisEnglish("HOT"));
        Assert.assertTrue(dictionary.isValidWord("HOT"));
    }
    @Test
    public void validateZYSEWord() {
        when(dictService.isValidWord("ZYSE")).thenReturn(isThisEnglish("ZYSE"));
        Assert.assertFalse(dictionary.isValidWord("ZYSE"));
    }
    @Test
    public void validateWERDSZWord() {
        when(dictService.isValidWord("WERDSZ")).thenReturn(isThisEnglish("WERDSZ"));
        Assert.assertFalse(dictionary.isValidWord("WERDSZ"));
    }
    @Test
    public void validateOKWord() {
        when(dictService.isValidWord("OK")).thenReturn(isThisEnglish("OK"));
        Assert.assertTrue(dictionary.isValidWord("OK"));
        Assert.assertEquals(Arrays.asList("k","ko","o", "ok"),(dictionary.findPossibleWords("OK")));
    }

    @Test
    public void validateWorkingWord() {
        when(dictService.isValidWord("WORKING")).thenReturn(isThisEnglish("WORKING"));
        Assert.assertTrue(dictionary.isValidWord("WORKING"));
        dictionary.findPossibleWords("WORKING");
    }
}
