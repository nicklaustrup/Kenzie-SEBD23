package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CluesDTO {


    @JsonProperty("clues")
    private List<Clues> clues;

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }

    @Override
    public String toString() {
        return "CluesDTO{" +
                "clues=" + clues +
                '}';
    }

    public static class Clues {
        @JsonProperty("canon")
        private boolean canon;
        @JsonProperty("game")
        private Game game;
        @JsonProperty("category")
        private static Category category;
        @JsonProperty("invalidCount")
        private int invalidCount;
        @JsonProperty("gameId")
        private int gameId;
        @JsonProperty("categoryId")
        private int categoryId;
        @JsonProperty("value")
        private int value;
        @JsonProperty("question")
        private static String question;
        @JsonProperty("answer")
        private static String answer;
        @JsonProperty("id")
        private int id;

        public boolean getCanon() {
            return canon;
        }

        public void setCanon(boolean canon) {
            this.canon = canon;
        }

        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public static Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            Clues.category = category;
        }

        public int getInvalidCount() {
            return invalidCount;
        }

        public void setInvalidCount(int invalidCount) {
            this.invalidCount = invalidCount;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public static String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            Clues.question = question;
        }

        public static String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            Clues.answer = answer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Clues{" +
                    "canon=" + canon +
                    ", game=" + game +
                    ", invalidCount=" + invalidCount +
                    ", gameId=" + gameId +
                    ", categoryId=" + categoryId +
                    ", value=" + value +
                    ", id=" + id +
                    '}';
        }

        public static class Game {
            @JsonProperty("canon")
            private boolean canon;
            @JsonProperty("aired")
            private String aired;

            public boolean getCanon() {
                return canon;
            }

            public void setCanon(boolean canon) {
                this.canon = canon;
            }

            public String getAired() {
                return aired;
            }

            public void setAired(String aired) {
                this.aired = aired;
            }

            @Override
            public String toString() {
                return "Game{" +
                        "canon=" + canon +
                        ", aired='" + aired + '\'' +
                        '}';
            }
        }

        public static class Category {
            @JsonProperty("canon")
            private boolean canon;
            @JsonProperty("title")
            private String title;
            @JsonProperty("id")
            private int id;

            public boolean getCanon() {
                return canon;
            }

            public void setCanon(boolean canon) {
                this.canon = canon;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "Category{" +
                        "canon=" + canon +
                        ", title='" + title + '\'' +
                        ", id=" + id +
                        '}';
            }
        }
    }
}

