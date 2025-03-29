package com.kenzie.imdb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores the relationships between movies and actors, allowing releasing a new movie
 * with all actors in the cast, adding a single actor to an existing (or new) movie,
 * unreleasing a movie completely, and querying actors by movie and vice versa.
 */
public class Imdb {

    private Map<Movie, Set<Actor>> moviesToActors = new HashMap<>();
    private Map<Actor, Set<Movie>> actorsToMovies = new HashMap<>();


    /**
     * Adds the new movie to the set of movies that an actor has appeared in.
     * If the movie already exists in the database, this will overwrite actors
     * associated with the movie with the new values provided.
     *
     * @param movie the movie being released
     * @param actors a set of actors that appear in the movie
     */
    public void releaseMovie(Movie movie, Set<Actor> actors) {
        moviesToActors.put(movie, actors);
        for (Actor actor : actors) {
            Set<Movie> allActorsMovies = actorsToMovies.get(actor);
            if (allActorsMovies != null){
                allActorsMovies.add(movie);
            }
            if (allActorsMovies == null){
                allActorsMovies = new HashSet<>();
                allActorsMovies.add(movie);
                actorsToMovies.put(actor, allActorsMovies);
            }
        }
    }

    /**
     * Removes the given movie from the database, including any actors
     * credited in the movie.
     *
     * @param movie the movie to remove
     * @return true if the movie was removed, false if it wasn't in Imdb
     *         to begin with
     */
    public boolean removeMovie(Movie movie) {
        //check if movie exists
        if (!(moviesToActors.containsKey(movie))){ return false; }
        //remove movie from movie list
        moviesToActors.remove(movie);
        //remove movie from actors list
        for (Map.Entry<Actor, Set<Movie>> actor : actorsToMovies.entrySet()) {
            actor.getValue().remove(movie);
        }
        //check if the remove was successful
        if (!(actorsToMovies.containsValue(movie)) && !(moviesToActors.containsKey(movie))){
            return true;
        }


        //fail safe
        return false;


    }

    /**
     * Adds a new movie to the set of movies that an actor has appeared in.
     * If the movie already exists in the database, will add the actor
     * if they haven't been added already. If the movie doesn't yet exist
     * in the database, this will add the movie with the actor as the only
     * credit.
     *
     * @param movie the movie to add to the actors set of movies
     * @param actor the actor that appears in this movie
     */
    public void tagActorInMovie(Movie movie, Actor actor) {
        //fetching lists for both sets
        Set<Actor> actorsList = moviesToActors.get(movie);
        Set<Movie> moviesList = actorsToMovies.get(actor);

        //checking if movie exists with cast
        if (actorsList!=null){
        moviesToActors.get(movie).add(actor);
    } // if not, creating list and adding it to moviesToActors master list
        else {
            actorsList = new HashSet<>();
            actorsList.add(actor);
            moviesToActors.put(movie, actorsList);
        }
        //checking if actor exists with movies
        if (moviesList != null){
        actorsToMovies.get(actor).add(movie);
    }
        //if not, creating list and adding it to actorsToMovies master list
        else {
            moviesList = new HashSet<>();
            moviesList.add(movie);
            actorsToMovies.put(actor, moviesList);
        }
    }


    /**
     * Returns a set of actors who are credited in the given movie. If a movie is not
     * released on IMDB throw an IllegalArgumentException.
     *
     * @param movie the movie to get actors for
     * @return the set of actors who are credited in the passed in movie
     */
    public Set<Actor> getActorsInMovie(Movie movie) {
        if (moviesToActors.containsKey(movie)){
         return moviesToActors.get(movie);
        } else { throw new IllegalArgumentException();
        }
    }


    /**
     * Returns a set of movies that the specified actor has appeared in. If the
     * actor has not appeared in any movies, return an empty Set.
     *
     * @param actor the actor to get movies for
     * @return the set of movies that the passed in actor has appeared in
     */
    public Set<Movie> getMoviesForActor(Actor actor) {
        Set<Movie> movies = new HashSet<>();
        for (Map.Entry<Actor, Set<Movie>> actors : actorsToMovies.entrySet()) {
            movies.addAll(actors.getValue());
            }

        if (actor == null || actorsToMovies.get(actor) == null || actorsToMovies.get(actor).isEmpty() || !moviesToActors.containsValue(actor)){
            return movies;
        }
        return actorsToMovies.get(actor);
    }

    // TODO: Implement this method
    /**
     * Returns all actors that IMDB has in its records as having appeared in a movie.
     *
     * @return a set of actors that IMDB has as appeared in movies
     */
    public Set<Actor> getAllActorsInIMDB() {
        return actorsToMovies.keySet();
    }

    // TODO: Implement this method
    /**
     * Returns the total number of individual movie-actor pairs in the database.
     *
     * So if there are 2 movies, the first movie has 1 actor and the second one
     * has 6 actors, this method will return 7.
     *
     * @return The total number of movie-actor pairings: the number of times
     *         any actor has appeared in any movie
     */
    public int getTotalNumCredits() {
        int creditCounter = 0;
        for (Set<Actor> actor : moviesToActors.values()) {
        creditCounter += actor.size();
        }

        return creditCounter;
    }
}
