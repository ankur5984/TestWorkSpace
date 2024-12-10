package com.workspace;

import java.util.*;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;
    private List<Player> users;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration){
       if(Objects.isNull(findSong(title))){
           return songs.add(new Song(title,duration));
       }
       return false;
    }

    public boolean addToPlayList(int track, LinkedList<Song> playList) {
        //song k list me track pick karke song ke list me dalna hain
        if(track > 0) {
            return playList.add(songs.get(track - 1));
        }
        return false;

    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        if(Objects.nonNull(findSong(title))) {
            return playList.add(findSong(title));
        }
        return false;

    }

    public Song findSong(String title){
        if(title.length() > 0) {
            for (Song song : this.songs) {
                if(song.getTitle().equalsIgnoreCase(title)){
                   return song;
                }
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }


    public String addUsers(Player player) {
        if(Objects.nonNull(player)) {
            if(null != users){
                if(users.contains(player)) {
                    users.add(player);
                    return "new user added with name "+ player.name();
                }
            }


        }
        return "player fails with some criteria";
    }
}
