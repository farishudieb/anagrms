# Thoughts

The implementation based on the anagrams definition covers following conditions:

- A text is considered anagram, if it re-uses same letters in the original text in any different order
- Anagrams should also reuse same letters in same amount of times it appears in the original text
- Space is not considered of importance in anagrams, if original text contains one space, anagrams may contains more or less spaces

The requirements was not so clear to me, specially in terms of expected input, and there should be some how a storage of intermediate results. Therefore I decided to do it as a service which does the following:

- A function which receives an array of texts. The function will group anagrams and store them in a variable
- A function which receives a single text and will return back a collection of its anagrams from the stored values in previous API.

## Technical Implementation
My solution was to analyze every text received to the first function, and produce a new model which contains

- Original text
- A map which contains each letter with how many times it was present in the text

The result from up will be an array of this model, I start from index 0 in the array, and I compare it
with the rest of the array to find which text is considered an anagram for this value.

If I find an anagram in a certain position, I store this in a Set and I set it's value as NULL so 
it won't be taken for analysis any more (Cause it Anagram is already found)

Main method contains sample run examples

## Performance
Right now I am calculating the new model for every given text in the input at the beginning of the analysis, that might be inefficient, we this model could be created lazily when comparing 2 texts so that we could save memory.

Building the new model might be the most expensive part, the rest is just query using fast access data structure like HashMap and HashSet which will have no overhead


### Please note there are no unit tests, just a sample of executions of the functions, just in order to speed development time
