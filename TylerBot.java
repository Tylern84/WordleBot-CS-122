import java.util.ArrayList;
import java.util.Random;

public class TylerBot implements WordlePlayer{
    ArrayList<String> knownWords;
    ArrayList<String> alreadyGuessed = new ArrayList<String>();
    int guessNumber;

    /**
     * Called prior to starting a game, to allow the player to fetch legal words
     * from the game instance.
     *
     * @param game
     */
    @Override
    public void beginGame(Wordle game) {
        knownWords= game.getKnownWords();
        guessNumber = 0;


    }


    /**
     *
     * @return true if a player can make a guess.
     */
    @Override
    public boolean hasNextGuess() {
        while(guessNumber < 5){
            return true;
        }
        return false;
    }

    /**
     *
     * @return the player's next guess.
     */
    @Override
    public String nextGuess() {
        Random random = new Random();
        int index = random.nextInt(knownWords.size());
        String randomWord = knownWords.get(index);
        System.out.println("my guess is " + randomWord);
        alreadyGuessed.add(randomWord);


        return randomWord;

    }

    /**
     * Called by the 'game loop' to communicate a hint from the game instance
     * to the player.
     *
     * @param h
     */
    //takes in the hint the game gives and starts eliminating words
    @Override
    public void tell(Hint h) {
        //focus on the 3 word puzzle first
        ArrayList<Character> CorretPlaceHint = new ArrayList<Character>();
        ArrayList<Character> incorrectPlacedHint = new ArrayList<>();
        ArrayList<Character>  notInWord = new ArrayList<Character>();


        //eliminate already guessed
        for(int i= 0; i < knownWords.size(); i++){
            if(alreadyGuessed.contains(knownWords.get(i))){
                knownWords.remove(knownWords.get(i));
            }
        }


        //appends the right letter/right placementt in the arrayList called "CorretPlaceHint"
        for(int i = 0; i < h.getCorrectlyPlaced().length(); i++){
            if(h.getCorrectlyPlaced().charAt(i) != '-'){
                CorretPlaceHint.add(h.getCorrectlyPlaced().charAt(i));
            }
        }
        //appends the right letter but incorrect place in the arrayList called "incorrectPlacedHint"
        for(int i = 0; i < h.getIncorrectlyPlaced().length(); i++){
            if(h.getIncorrectlyPlaced().charAt(i) != '-'){
                incorrectPlacedHint.add(h.getIncorrectlyPlaced().charAt(i));
            }
        }

        //appends the wrong letters place in the arrayList called "notInWord"
        for(int i = 0; i < h.getNotInPuzzle().length(); i++){
            if(h.getNotInPuzzle().charAt(i) != '-'){
                notInWord.add(h.getNotInPuzzle().charAt(i));
            }
        }

        //eliminating words
        for(int i=0; i < knownWords.size(); i++){
            for(int j= 0; j < knownWords.get(i).length(); j++){
                for(char c : notInWord){
                    if(knownWords.get(i).indexOf(j) == c){
                        knownWords.remove(knownWords.indexOf(i));
                    }
                }
            }
        }







    }
}
