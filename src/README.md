# Project name: SongStoreApp

# Project Introduction: 
SongStoreApp is a simple song management application that allows users to create playlists, add songs, CRUD songs, search songs, view reports, and more. The application is developed in Java programming language, using object-oriented design concept and basic data persistence technology.

# Contributors: 

1. **Haoyang Xu**
- Responsible for the initial construction and design of the project.
- Implements the 'Artist' class that accepts the artist's name and validation status as parameters for initializing the artist object.
- Responsible for implementing the 'Song' class, which accepts the ID, name, length, and artist object of the song as parameters for initializing the song object.
- Participated in the project documentation and overall planning.

2. **Ruiyang Yu**
- Implementation of the initial version of the 'Driver' class.
- Implementation of the 'Driver' class, including loading and saving data, menu display and other functions.
- Participated in the anomalies and errors in the project and ensured the normal operation of the application.
- Participated in project testing and documentation.

3. **Yifan Wang**
- Implements CRUD operations of the 'Playlist' class, including adding, updating, deleting songs and other functions.
- Implements the list function of the 'Playlist' class, including listing all songs, listing songs that verify artists, etc.
- Participated in the anomalies and errors in the project and ensured the normal operation of the application.
- Participated in project testing and documentation.

# Features: 
- * Create playlists: * Users can create their own playlists and give them a name and description.
- * Add songs: * Users can add new songs to the playlist, including information such as song name, artist, and length.
- *CRUD operations: * Provides CRUD operations on songs, including adding, updating, and deleting songs.
- * Search function: * Users can search for songs by song title or artist name and view related information.
- * Report View: * provides a variety of reporting features, including listing all songs, listing verified artist songs, and more.
- * Data persistence: * XStream components are used to serialize and deserialize playlists and songs to achieve persistent data storage.

# How does it work: 
1. Clone or download the repository to a local computer.
2. Ensure that the Java runtime Environment has been installed.
3. On the CLI, go to the project directory.
4. Compile the project: 'javac *.java'
5. Run the program: 'java Driver'

* Note: 
- Before running the application, make sure to add the 'xstream-x.x.x.jar' file to the classpath.
- If you encounter any problems, please refer to the project documentation or contact project contributors.
