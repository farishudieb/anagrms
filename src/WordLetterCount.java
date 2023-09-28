import java.util.Map;
import java.util.Objects;

public class WordLetterCount {

    private String text;
    private Map<Integer, Integer> letterCountInText;

    public WordLetterCount(String text, Map<Integer, Integer> letterCountInWord){

        this.text = text;
        this.letterCountInText = letterCountInWord;
    }

    public boolean isAnagram(WordLetterCount other){

        //Not anagram
        if(other == null)
            return false;

        //We start by comparing letters, if both texts contains same number of DISTINCT letters, then they MIGHT be anagrams
        if(other.letterCountInText.size() != this.letterCountInText.size())
            return false;

        //Here we need to compare letter repetitions, if every letter in the other word is repeated same number of times as the letter in the original text, we consider them as anagrams
        return other.letterCountInText
                .entrySet()
                .stream()
                .allMatch(this::isLetterCountSame);
    }

    private boolean isLetterCountSame(Map.Entry<Integer, Integer> otherLetterCountEntry){

        Integer count = this.letterCountInText.get(otherLetterCountEntry.getKey());
        return count != null && count.equals(otherLetterCountEntry.getValue());
    }

    //Because we are using Set, retrieval of objects will be very easy
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordLetterCount that = (WordLetterCount) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
