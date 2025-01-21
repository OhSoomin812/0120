package org.example.ex2;

public enum WiseSayingService {

    INSTANCE;

    private String[] sentences;

    private WiseSayingService(){
        sentences = new String[5];
        sentences[0] = "The only limit to our realization of tomorrow is our doubts of today.";
        sentences[1] = "The best way to predict the future is to create it.";
        sentences[2] = "Life is what happens when you're busy making other plans";
        sentences[3] = "You are never too old to set another goal or to dream a new dream";
        sentences[4] = "In the end, it's not the years in your life that count. It's the life in your years.";
    }

    public String getOne() {
        int idx = (int)(Math.random() * 5);
        return sentences[idx];
    }
}
