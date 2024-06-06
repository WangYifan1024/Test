import controllers.Playlist;
import models.Artist;
import models.Song;
import utils.ScannerInput;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;

//# Contributors:
//
//1. **Haoyang Xu**
//- Responsible for the initial construction and design of the project.
//- Implements the 'Artist' class that accepts the artist's name and validation status as parameters for initializing the artist object.
//- Responsible for implementing the 'Song' class, which accepts the ID, name, length, and artist object of the song as parameters for initializing the song object.
//- Participated in the project documentation and overall planning.
//
//2. **Ruiyang Yu**
//- Implementation of the initial version of the 'Driver' class.
//- Implementation of the 'Driver' class, including loading and saving data, menu display and other functions.
//- Participated in the anomalies and errors in the project and ensured the normal operation of the application.
//- Participated in project testing and documentation.
//
//3. **Yifan Wang**
//- Implements CRUD operations of the 'Playlist' class, including adding, updating, deleting songs and other functions.
//- Implements the list function of the 'Playlist' class, including listing all songs, listing songs that verify artists, etc.
//- Participated in the anomalies and errors in the project and ensured the normal operation of the application.
//- Participated in project testing and documentation.
public class Driver {

    private Playlist playlist;

    public Driver() {
        this.playlist = new Playlist("Chill-24", "Chillout songs to study to");
        load(); // Load data from XML file when Driver is instantiated
        displayMenu();
    }

    public static void main(String[] args) {
        new Driver();
    }
    

    private void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the Song Store App!");
            System.out.println("1. Add a song");
            System.out.println("2. Display all songs");
            System.out.println("3. Update a song by ID");
            System.out.println("4. Delete a song");
            System.out.println("5. Search for a song by name");
            System.out.println("6. Generate reports");
            System.out.println("7. Save playlist to XML");
            System.out.println("8. Search for a song by index");
            System.out.println("9. Search for a song by id");
            System.out.println("10. Search for a song By ArtistName");
            System.out.println("11. Update a song by Index");
            System.out.println("12. Display short songs");
            System.out.println("13. list Of Songs Of Artist");
            System.out.println("14. Display short than length songs");
            System.out.println("15. Exit");
            int choice = ScannerInput.readNextInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    addSong();
                    break;
                case 2:
                    displayAllSongs();
                    break;
                case 3:
                    updateSongbyID();
                    break;
                case 4:
                    deleteSongs();
                    break;
                case 5:
                    searchSongsbyname();
                    break;
                case 6:
                    generateReports();
                    break;
                case 7:
                    save();
                    break;
                case 8:
                    searchSongsbyIndex();
                    break;
                case 9:
                    searchSongsbyID();
                    break;
                case 10:
                    searchSongByArtistName();
                    break;
                case 11:
                    updateSongbyIndex();
                    break;
                case 12:
                    listnumberOfShortSongs();
                    break;
                case 13:
                    listOfSongsOfArtist();
                    break;
                case 14:
                    listSongsLongerThan();
                    break;
                case 15:
                    exit = true;
                    System.out.println("Exiting the Song Store App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addSong() {
        int songId = ScannerInput.readNextInt("Enter the Song Id:");
        String name = ScannerInput.readNextLine("Enter the Name:");
        String artistName = ScannerInput.readNextLine("Enter the ArtistName:");
        boolean verified = false;
        if (ScannerInput.readNextChar("If it is verified") == 'y'){
            verified = true;
        }
        int length = ScannerInput.readNextInt("Enter the Length:");
        boolean isAdded = playlist.addSong(new Song(songId, name, artistName, verified, length));
    }

    private void displayAllSongs() {
        for (Song song : playlist.getSongs()) {
            System.out.println(song.toString());
        }
    }

    private void updateSongbyID() {
        int songId = ScannerInput.readNextInt("Enter the ID");
        String newName = ScannerInput.readNextLine("Enter the Newname");
        boolean verified = false;
        if (ScannerInput.readNextChar("If it is verified") == 'y') {
            verified = true;
        }
        Artist artist = new Artist(ScannerInput.readNextLine("Enter the ArtistName:"), verified);
        for (Song song : playlist.getSongs()) {
            if (song.getSongId() == songId) {
                song.setName(newName);
                song.setArtist(artist);
            }
        }
    }

    private void updateSongbyIndex() {
        int songId = ScannerInput.readNextInt("Enter the ID");
        String newName = ScannerInput.readNextLine("Enter the Newname");
        String artistName = ScannerInput.readNextLine("Enter the ArtistName:");
        boolean verified = false;
        if (ScannerInput.readNextChar("If it is verified") == 'y') {
            verified = true;
        }
        int length = ScannerInput.readNextInt("Enter the Length:");
        int index = ScannerInput.readNextInt("Enter the Index");
        playlist.updateSong(index,new Song(songId, newName, artistName, verified, length));
    }

    private void deleteSongs() {
        displayAllSongs();
        if (playlist.numberOfSongs() > 0){
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the song to delete ==> ");
            Song songToDelete = playlist.deleteSong(indexToDelete);
            if (songToDelete != null){
                System.out.println("Delete Successful! Deleted product: " + songToDelete.getName());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void searchSongsbyname() {
        String name = ScannerInput.readNextLine("Enter the name of the song to find ==>");
        Song findSong1 = playlist.findSong(name);
        System.out.println(findSong1.toString());
    }

    private void searchSongsbyIndex() {
        int index = ScannerInput.readNextInt("Enter the index of the song to find ==>");
        Song findSong1 = playlist.findSong(index);
        System.out.println(findSong1.toString());
    }

    private void searchSongsbyID() {
        int id = ScannerInput.readNextInt("Enter the ID of the song to find ==>");
        Song findSong1 = playlist.findSongByCode(id);
        System.out.println(findSong1.toString());
    }

    private void searchSongByArtistName() {
        String artistName = ScannerInput.readNextLine("Enter the artistName of the song to find ==>");
        System.out.println(playlist.searchSongsByArtistName(artistName));
    }
    private void generateReports() {
        playlist.getTotalPlayListLength();
        playlist.getAverageSongLength();
        playlist.listSongsFromVerifiedArtists();
    }

    private void save() {
        try {
            XStream xstream = new XStream();
            FileWriter writer = new FileWriter("playlist.xml");
            xstream.toXML(playlist, writer);
            writer.close();
            System.out.println("Playlist saved to playlist.xml successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error occurred while saving playlist to XML.");
        }
    }

    private void load() {
        try {
            XStream xstream = new XStream();
            FileReader reader = new FileReader("playlist.xml");
            playlist = (Playlist) xstream.fromXML(reader);
            reader.close();
            System.out.println("Playlist loaded from playlist.xml successfully!");
        } catch (IOException e) {
            System.out.println("No playlist data found. Creating new playlist.");
        }
    }

    private void listOfSongsOfArtist(){
        String artistName = ScannerInput.readNextLine("Enter the artistName of the song ==>");
        System.out.println(playlist.listOfSongsOfArtist(artistName));
    }

    private void listSongsLongerThan(){
        int length = ScannerInput.readNextInt("Enter the length of the song ==>");
        System.out.println(playlist.listSongsLongerThan(length));
    }

    private void listnumberOfShortSongs(){
        playlist.numberOfShortSongs();
    }
}
