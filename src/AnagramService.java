import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnagramService {

    private final List<Set<String>> anagramGroups = new ArrayList<>();

    public Set<String> getAnagramsForText(String text){

        //We loop on all stored anagram groups, and we check if this text is present in any of them (Max at one of them). If so, we get this set, and we remove this text from its copy
        Optional<Set<String>> foundAnagramsOptional = anagramGroups.stream().filter(anagrams -> anagrams.contains(text.toLowerCase())).findFirst();

        //Remove the search subject from the set and keep the anagrams text
        Set<String> foundAnagrams = foundAnagramsOptional.orElse(Collections.emptySet());
        return foundAnagrams.stream().filter(anagram -> !anagram.equals(text.toLowerCase())).collect(Collectors.toSet());
    }
    public void generateAnagramGroups(String...texts){

        if(texts == null || texts.length == 0)
            return;

        //For each text create a map for each letter and how many times it was present in that text
        WordLetterCount[] wordLetterCountArr = mapWordsToLetterCount(texts);

        //Outer loop will find anagrams for the text at that position. It starts with index 0, and ends at BEFORE last index, for example, if we have 10 texts, this will loop over elements from index 0 to index 8. It makes no sense to find anagrams for a single word, we need at least 2
        for(int i=0; i< texts.length-1; i++) {
            WordLetterCount text = wordLetterCountArr[i];
            //If we find an anagram for a text below, we set it's value in this temporary array to null, so we skip it here
            if(text == null)
                continue;

            //This will contain all anagrams for the text up (including the text itself), it represents a single anagram group
            Set<String> wordAnagrams = new HashSet<>();
            //Inner loop will be used to find the anagrams for the text from the previous loop, it starts from the position after the position of the previous loop, and ends at the end of the array (so all texts are evaluated if they anagrams or not)
            for (int j = i + 1; j < texts.length; j++) {
                WordLetterCount otherText = wordLetterCountArr[j];
                //Checking if this text is anagram for the text from first loop
                if(text.isAnagram(otherText)) {
                    //If it is anagram, we add this text into the anagrams group set
                    wordAnagrams.add(otherText.getText());
                    //We set the value to null to mark this position as not needed to be processed (Cause it's anagrams already found)
                    wordLetterCountArr[j] = null;
                }
            }

            //After scanning all elements to try to fin an anagram for the text in the first loop, we check, if we could find any anagram or not
            if(!wordAnagrams.isEmpty()) {
                //If we did find any anagram, and the anagram set is NOT EMPTY, we add this text to the anagrams
                wordAnagrams.add(text.getText());
                //And then we add this group of anagrams to be persisted in the class variable
                anagramGroups.add(wordAnagrams);
            }
        }

        //At the end we print found anagram groups
        IntStream.range(0, anagramGroups.size()).forEach(value ->
            System.out.println("Anagram Group "+(value+1)+" - "+String.join(",", anagramGroups.get(value))));

    }
    private WordLetterCount[] mapWordsToLetterCount(String... words){

        return Arrays.stream(words).map(this::mapWordToLetterCount).toArray(WordLetterCount[]::new);
    }

    private WordLetterCount mapWordToLetterCount(String word){

        //For every received word, we need to find each letter in it how many times appeared, and create a new model to map the word with its letter count
        word = word.toLowerCase();
        Map<Integer, Integer> letterCount = new HashMap<>();
        //Important here, we skip space chars as defined in the requirements of anagrams
        word.chars().filter(letter -> letter != ' ').forEach(letter ->  letterCount.compute(letter, (letterKey, count) -> count == null? 1 : count+1));

        return new WordLetterCount(word, letterCount);
    }
}
