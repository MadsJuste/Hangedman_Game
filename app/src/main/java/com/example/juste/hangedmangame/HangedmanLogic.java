package com.example.juste.hangedmangame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class HangedmanLogic {
  private ArrayList<String> possibleWord = new ArrayList<String>();
  private String word;
  private ArrayList<String> usedLetter = new ArrayList<String>();
  private String visibleWord;
  private int numberOfWrongWords;
  private boolean lastLetterWasCorrect;
  private boolean gameIsWon;
  private boolean gameIsLost;


  public ArrayList<String> getUsedLetter() {
    return usedLetter;
  }

  public String getVisibleWord() {
    return visibleWord;
  }

  public String getWord() {
    return word;
  }

  public int getNumberOfWrongWords() {
    return numberOfWrongWords;
  }

  public boolean isLastLetterCorrect() {
    return lastLetterWasCorrect;
  }

  public boolean isTheGameWon() {
    return gameIsWon;
  }

  public boolean isTheGameLost() {
    return gameIsLost;
  }

  public boolean isTheGameDone() {
    return gameIsLost || gameIsWon;
  }


  public HangedmanLogic() {
    possibleWord.add("car");
    possibleWord.add("computer");
    possibleWord.add("programing");
    possibleWord.add("highway");
    possibleWord.add("busroute");
    possibleWord.add("sidewalk");
    possibleWord.add("snail");
    possibleWord.add("bird");
    refresh();
  }

  // Har modificeret grundkoden lidt for at hente ord fra allerede brugte objekter istedet for at gentage at hente fra dr
  public ArrayList getpossibleWord(){
    return possibleWord;
  }

  public void refresh() {
    usedLetter.clear();
    numberOfWrongWords = 0;
    gameIsWon = false;
    gameIsLost = false;
    word = possibleWord.get(new Random().nextInt(possibleWord.size()));
    updateVisibleWord();
  }


  private void updateVisibleWord() {
    visibleWord = "";
    gameIsWon = true;
    for (int n = 0; n < word.length(); n++) {
      String Letter = word.substring(n, n + 1);
      if (usedLetter.contains(Letter)) {
        visibleWord = visibleWord + Letter;
      } else {
        visibleWord = visibleWord + "*";
        gameIsWon = false;
      }
    }
  }

  public void guessLetter(String letter) {
    if (letter.length() != 1) return;
    if (letter.equals(" ")) return;
    System.out.println("your guess on the letter: " + letter);
    if (usedLetter.contains(letter)) return;
    if (gameIsWon || gameIsLost) return;

    usedLetter.add(letter);

    if (word.contains(letter)) {
      lastLetterWasCorrect = true;
      System.out.println("the letter was correct: " + letter);
    } else {
      // Vi g�ttede p� et letter der ikke var i Word.
      lastLetterWasCorrect = false;
      System.out.println("the letter was not correct: " + letter);
      numberOfWrongWords = numberOfWrongWords + 1;
      if (numberOfWrongWords > 6) {
        gameIsLost = true;
      }
    }
    updateVisibleWord();
  }

  public void logStatus() {
    System.out.println("---------- ");
    System.out.println("- Word (hidden) = " + word);
    System.out.println("- visable word = " + visibleWord);
    System.out.println("- wrong letter = " + numberOfWrongWords);
    System.out.println("- used letters = " + usedLetter);
    if (gameIsLost) System.out.println("- Game is Lost");
    if (gameIsWon) System.out.println("- Game is Won");
    System.out.println("---------- ");
  }


  public static String getUrl(String url) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
      sb.append(line + "\n");
      line = br.readLine();
    }
    return sb.toString();
  }

  public void getWordFromDR() throws Exception {
    String data = getUrl("http://dr.dk");
    System.out.println("data = " + data);
    data = data.substring(data.indexOf("<body")).
            replaceAll("<.+?>", " ").toLowerCase().replaceAll("<[^>]+>"," "). // tilfoejet lidt tagremoval
            replaceAll("[^a-zæøå]", " ").
            replaceAll(" [a-zæøå] ", " "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] ", " "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    possibleWord.clear();
    possibleWord.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("possible Word = " + possibleWord);
    refresh();
  }

    public void getWordFromGuardian () throws Exception{
        String data =getUrl("http://www.theguardian.com/international");
        System.out.println("data = " + data);
        data = data.substring(data.indexOf("<body")).
                replaceAll("<.+?>", " ").toLowerCase().replaceAll("<[^>]+>"," "). // tilføjet lidt tagremoval
                replaceAll("[^a-zæøå]", " ").
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord
        System.out.println("data = " + data);
        possibleWord.clear();
        possibleWord.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

        System.out.println("possible Word = " + possibleWord);
        refresh();
    }
}
