package models;

import java.util.ArrayList;
import java.util.Objects;

public class Song{
    private int songID = 9999;
    private String name = "";
    private models.Artist artist;
    private int length = 1;

    public Song(int songID, String name, String artistName, boolean isVerified, int length) {
        this.songID = (songID >= 1000 && songID <= 9999) ? songID : 9999;
        this.name = (name.length() <= 20) ? name : name.substring(0, 20);
        this.artist = new models.Artist(artistName, isVerified);
        this.length = (length >= 1 && length <= 600) ? length : 1;
    }

    public int getSongId() {
        return songID;
    }

    public void setSongID(int songID) {
        if (songID >= 1000 && songID <= 9999) {
            this.songID = songID;
        }
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
        if(length >= 1 && length <=600){
            this.length = length;
        }
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

    public void setSongId(int newSongId) {
        if (newSongId >= 1000 && newSongId <= 9999) {
            this.songID = newSongId;
        }
        else if (newSongId > 9999) {
            this.songID = 9999;
        }
        else {
            this.songID = 1000;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songID == song.songID && length == song.length && Objects.equals(name, song.name) && Objects.equals(artist, song.artist);
    }



    public String toString()
    {
        return "Song description: " + name
                + ", songID: " + songID
                + ", artist: " + artist.toString()
                + ", length: " + length;
    }

    /*public static class Artist {
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
*/

}