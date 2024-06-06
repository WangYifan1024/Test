package models;

import java.util.ArrayList;

public class Song{
    private int songId;
    private String name;
    private Artist artist;
    private int length;

    public Song(int songId, String name, Artist artist) {
        this.songId = validateSongId(songId);
        this.name = validateSongName(name);
        this.artist = artist;
        this.length = 1;

    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = validateSongId(songId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateSongName(name);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = validateLength(length);
    }

    private int validateSongId(int songId){
        if (songId >= 1000 && songId <=9999){
            return songId;
        }
        else {
            return 9999;
        }
    }
    private String validateSongName(String name){
        if (name.length() > 20){
            return name.substring(0, 20);
        }
        else {
            return name;
        }
    }

    private int validateLength(int length){
        if (length >= 1 && length <= 600){
            return length;
        }
        else {
            return 1;
        }
    }
    public boolean equals(Object thing){
        if (this == thing)
            return true;
        Song song = (Song) thing;
        return songId == song.songId;
    }



    public String toString(){
        return "Song Id: " + songId + "\n" +
                "Song Name: " + name + "\n" +
                "Artist Name: " + artist.getName() + "\n" +
                "Verified Artist: " + (artist.isVerified() ? "Yes" : "No") + "\n" +
                "Length of Song (seconds): " + length + "\n";
    }

    public static class Artist {
        private String name;
        private boolean verified;
        private Object artistName;

        public Artist(String name, boolean verified) {
            this.name = name;
            this.verified = verified;
        }

        public String getName() {
            return name;
        }

        public boolean isVerified() {
            return verified;
        }

        public Object getArtistName() {
            return artistName;
        }

        public void setArtistName(Object artistName) {
            this.artistName = artistName;
        }
    }
    public static class SongStoreApp {
        private ArrayList<Song> songList;

        public SongStoreApp() {
            songList = new ArrayList<>();
        }
        public void addSong(Song song) {
            songList.add(song);
        }
        public void displayAllSongs() {
            for (Song song : songList) {
                System.out.println(song);
            }
        }
        public void updateSong(int songId, String newName) {
            for (Song song : songList) {
                if (song.getSongId() == songId) {
                    song.setName(newName);
                    return;
                }
            }
            System.out.println("Song with id " + songId + " not found.");
        }
        public void deleteSong(int songId) {
            for (Song song : songList) {
                if (song.getSongId() == songId) {
                    songList.remove(song);
                    return;
                }
            }
            System.out.println("Song with id " + songId + " not found.");
        }

        public static void main(String[] args) {
            Artist rema = new Artist("Rema", true);
            SongStoreApp songStore = new SongStoreApp();
            Song song1 = new Song(1500, "Calm Down", rema);
            songStore.addSong(song1);
            songStore.displayAllSongs();

            songStore.updateSong(1500, "Calm Down (Remix)");
            songStore.displayAllSongs();

            songStore.deleteSong(1500);
            songStore.displayAllSongs();
        }

    }
}