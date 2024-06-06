package controllers;

import models.Song;
import utils.Utilities;
import java.util.ArrayList;



public class Playlist {
    private String playlistName = ""; // valid length is 20 - default to the first 20 characters of input.
    private ArrayList<Song> songs = new ArrayList<Song>();  // should start empty
    private String description = ""; // valid length is 30 - default to the first 30 characters of input.
    private int likes = 0;

    public Playlist(String playlistName, String description) {
        this.playlistName = Utilities.truncateString(playlistName, 20);
        this.description = Utilities.truncateString(description, 30);
    }

    public String listSongs() {
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            sb.append(i).append(":\t")
                    .append("Songs from playlist :\t")
                    .append("playlistName is: ").append(playlistName).append("\t")
                    .append("Song Id is : ").append(song.getSongId()).append("\t")
                    .append("Song Name is : ").append(song.getName()).append("\t")
                    .append("Artist Name is : ").append(song.getArtist().getArtistName()).append("\t")
                    .append("Song Length is : ").append(song.getLength()).append("\t")
                    .append("Artist verified is : ").append(song.getArtist().isVerified()).append("\n");
        }
        return sb.toString();
    }

    public int numSongs() {
        return songs.size();
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getDescription() {
        return description;
    }

    public int getLikes() {
        return likes;
    }

    public void setDescription(String description) {
        if (description.length() <= 30)
            this.description = description;
    }

    public void setPlaylistName(String playlistName) {
        if (playlistName.length() <= 20)
            this.playlistName = playlistName;
    }

    public void setLikes(int likes) {
        if (likes >= 0)
        this.likes = likes;
    }

    public boolean addSong(Song song) {
        return songs.add(song);
    }

    public boolean updateSong(int indexToUpdate, Song song) {
        if (isValidIndex(indexToUpdate)) {
            Song foundSong = songs.get(indexToUpdate);
            foundSong.setName(song.getName());
            foundSong.setSongId(song.getSongId());
            foundSong.setLength(song.getLength());
            foundSong.setArtist(song.getArtist());
            return true;
        }
        return false;
    }

    public Song deleteSong(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return songs.remove(indexToDelete);
        }
        return null;
    }

    public void addLike() {
        likes++;
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < songs.size();
    }

    public int numberOfSongs() {
        return songs.size();
    }

    public int numberOfShortSongs() {
        int count = 0;
        for (Song song : songs) {
            if (song.getLength() <= 180) {
                count++;
            }
        }
        return count;
    }

    public int getTotalPlayListLength() {
        if (songs.isEmpty()) {
            return -1;
        }
        int totalLength = 0;
        for (Song song : songs) {
            totalLength += song.getLength();
        }
        return totalLength;
    }

    public int getAverageSongLength() {
        if (songs.isEmpty()) {
            return -1;
        }
        int totalLength = getTotalPlayListLength();
        return totalLength / songs.size();
    }

    public String listSongsFromVerifiedArtists() {
        boolean verifiedArtistsPresent = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getArtist().isVerified()) {
                sb.append(i).append(": ").append(song).append("\n");
                verifiedArtistsPresent = true;
            }
        }
        if (verifiedArtistsPresent) {
            return sb.toString();
        } else {
            if (songs.isEmpty()) {
                return "No songs in playlist.";
            } else {
                return "There are no songs from verified artists on this playlist.";
            }
        }
    }

    public String listSongsLongerThan(int length) {
        boolean songsLongerThanLengthPresent = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getLength() >= length) {
                sb.append(i).append(": ").append(song).append("\n");
                songsLongerThanLengthPresent = true;
            }
        }
        if (songsLongerThanLengthPresent) {
            return sb.toString();
        } else {
            if (songs.isEmpty()) {
                return "No songs in playlist.";
            } else {
                return "There are no songs on this playlist longer than :" + length + " secs.";
            }
        }
    }

    public String listOfSongsOfArtist(String artistName) {
        boolean songsByArtistPresent = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getArtist().getArtistName().equalsIgnoreCase(artistName)) {
                sb.append(i).append(": ").append(song).append("\n");
                songsByArtistPresent = true;
            }
        }
        if (songsByArtistPresent) {
            return sb.toString();
        } else {
            if (songs.isEmpty()) {
                return "No songs in playlist.";
            } else {
                return "There are no  songs on this playlist by'" + artistName + "'.";
            }
        }
    }

    public Song findSong(int index) {
        if (isValidIndex(index)) {
            return songs.get(index);
        }
        return null;
    }

    public Song findSongByCode(int code) {
        for (Song song : songs) {
            if (song.getSongId() == code) {
                return song;
            }
        }
        return null;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String searchSongsByArtistName(String artistName) {
        boolean songsFound = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getArtist().getArtistName().toLowerCase().contains(artistName.toLowerCase())) {
                sb.append(i).append(": ").append(song).append("\n");
                songsFound = true;
            }
        }
        if (songsFound) {
            return sb.toString();
        } else {
            if (songs.isEmpty()) {
                return "No songs.";
            } else {
                return "No songs found for this artist.";
            }
        }
    }

    public Song findSong(String songName){
        boolean condi1 = true;
        int index = 0;
        while (condi1){
            Song songtoCheck = songs.get(index);
            if (songtoCheck.getName().equals(songName)){
                condi1 = false;
                return songtoCheck;
            }
        }
        return null;
    }

    public String toString() {
        if (songs.isEmpty()) {
            return "No songs in playlist.";
        }
        return  "controllers.Playlist Name" +
                playlistName + "\n" +
                "controllers.Playlist Description" +
                description + "\n" +
                "Songs" + songs + "\n" +
                "Number of songs: " + songs.size() + "\n" +
                "Likes: " + likes;
    }

}
