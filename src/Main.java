import java.util.Set;

public class Main {
    public static void main(String[] args) {

        anagramAnalysisWithWordsOnly();
        //anagramAnalysisWithSentencesOnly();
    }

    public static void anagramAnalysisWithWordsOnly(){
        AnagramService anagramService = new AnagramService();

        String[] texts = {"spot", "stop","study", "angered", "below", "act", "tops", "cat", "opst",  "enraged", "post", "pots", "gainly", "laying", "elbow", "dusty"};
        anagramService.generateAnagramGroups(texts);

        String anagramsForText = "spot";
        Set<String> found = anagramService.getAnagramsForText(anagramsForText);
        if(found.isEmpty())
            System.out.print("No anagrams found for ["+anagramsForText+"]");
        else
            System.out.println("Anagrams for [ "+anagramsForText+" ] are: "+String.join(",", found));

    }

    public static void anagramAnalysisWithSentencesOnly(){
        AnagramService anagramService = new AnagramService();

        String[] texts = {"Eleven plus two", "Tom Marvolo Riddle", "I am Lord Voldemort", "A decimal point", "Im a dot in place", "Twelve plus one"};
        anagramService.generateAnagramGroups(texts);

        String anagramsForText = "Tom Marvolo Riddle";
        Set<String> found = anagramService.getAnagramsForText(anagramsForText);
        if(found.isEmpty())
            System.out.print("No anagrams found for ["+anagramsForText+"]");
        else
            System.out.println("Anagrams for [ "+anagramsForText+" ] are: "+String.join(",", found));
    }
}