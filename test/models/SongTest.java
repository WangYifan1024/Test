package models;

import models.Artist;
import models.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {
    private Song validSong1, validSong2, validSong3, validSong4, invalidSong1, invalidSong2;
    private Song.Artist artist1, artist2;

    @BeforeEach
    void setUp() {
        artist1 = new Song.Artist("Rema", true);
        artist2 = new Song.Artist("Taylor Swift", false);

        validSong1 = new Song(1500, "Anti-Hero", artist1);
        validSong2 = new Song(9999, "Calm Down", artist2);
        validSong3 = new Song(1000, "12345678901234567890", artist1);
        validSong4 = new Song(998, "", artist2);
        invalidSong1 = new Song(999, "12345678901234567890123", artist1);
        invalidSong2 = new Song(998, "12345678901234567890123567890", artist2);
    }


    @AfterEach
    void tearDown() {
        validSong1 = validSong2 = validSong3 = validSong4 = null;
        invalidSong1 = invalidSong2 = null;
    }

    @Nested
    class Constructors {

        @Test
        void constructorTestingTheSettingOfDefaultValues() {
            //testing invalid values (lower boundary) for each variable at constructor level
            Song lowerBounds = new Song(999, "", new Song.Artist("`", true));
            Artist songArtistLower = new Artist("", true);
            assertEquals(9999, lowerBounds.getSongId());
            assertEquals("", lowerBounds.getName());  //
            assertEquals(songArtistLower, lowerBounds.getArtist());

            assertEquals(1, lowerBounds.getLength());
            Song upperBounds = new Song(10000, "123456789012345678901", new Song.Artist("1234567890123456", true));
            //testing invalid values (upper boundary) for each variable at constructor level
            Artist songArtistUpper = new Artist("1234567890123456", true);
            assertEquals(9999, upperBounds.getSongId());
            assertEquals("12345678901234567890", upperBounds.getName());  // exactly 20 chars
            assertEquals(songArtistUpper, upperBounds.getArtist());
            assertEquals(1, upperBounds.getLength());
        }

        @Test
        void constructorTestingValidValues() {
            //testing for values that lie on the lower boundary.
            Song lowerBounds = new Song(1000, "", new Song.Artist("", true));
            assertEquals(1000, lowerBounds.getSongId());
            assertEquals("", lowerBounds.getName());  // exactly 20 chars
            assertEquals(new Artist("", true), lowerBounds.getArtist());
            assertEquals(1, lowerBounds.getLength());
            // models.Song

            //testing for values that lie on the upper boundary.
            Song upperBounds = new Song(9999, "12345678901234567890", new Song.Artist("123456789012345", true));
            assertEquals(9999, upperBounds.getSongId());
            assertEquals("12345678901234567890", upperBounds.getName());  // exactly 20 chars

            assertEquals(600, upperBounds.getLength());
        }
    }

    @Nested
    class GettersAndSetters {

        @Test
        void songIdGetAndSetWorkingCorrectly() {
            assertEquals(1500, validSong1.getSongId());
            //testing upper limits of valid values
            validSong1.setSongId(9998);  // valid just below upper  boundary
            assertEquals(9998, validSong1.getSongId());
            validSong1.setSongId(9999);  // valid - on boundary
            assertEquals(9999, validSong1.getSongId());
            validSong1.setSongId(10000);  // invalid - value should remain unchanged
            assertEquals(9999, validSong1.getSongId());

            //testing lower limits
            validSong1.setSongId(1001);  // valid - just above lower  boundary
            assertEquals(1001, validSong1.getSongId());
            validSong1.setSongId(1000);  // valid - on lower  boundary
            assertEquals(1000, validSong1.getSongId());
            validSong1.setSongId(999);  // invalid  - just below  lower  boundary -  should remain unchanged
            assertEquals(1000, validSong1.getSongId());

        }

        @Test
        void songNameGetAndSetWorkingCorrectly() {

            assertEquals("Anti-Hero", validSong1.getName());
            validSong1.setName("1234567890123456789"); // valid - just below upper  boundary
            assertEquals("1234567890123456789", validSong1.getName());
            validSong1.setName("12345678901234567890"); // valid - on upper  boundary
            assertEquals("12345678901234567890", validSong1.getName());
            validSong1.setName("123456789012345678901"); // invalid  - just above upper boundary -  should remain unchanged
            assertEquals("12345678901234567890", validSong1.getName());

        }


        @Test
        void songLengthGetAndSetWorkingCorrectly() {
            assertEquals(123, validSong1.getLength());
            validSong1.setLength(599);  // valid - just below upper  boundary
            assertEquals(599, validSong1.getLength());
            validSong1.setLength(600);  // // valid - on upper  boundary
            assertEquals(600, validSong1.getLength());
            validSong1.setLength(123);  // to original value
            assertEquals(123, validSong1.getLength());
            validSong1.setLength(601);  // invalid  - just above upper boundary -  should remain unchanged
            assertEquals(123, validSong1.getLength());
            validSong1.setLength(2);// valid - just above lower  boundary
            assertEquals(2, validSong1.getLength());
            validSong1.setLength(1);// valid - just on lower  boundary
            assertEquals(1, validSong1.getLength());

            validSong1.setLength(123);  // to original value
            validSong1.setLength(0);   // invalid  - just below  lower  boundary -  should remain unchanged
            assertEquals(123, validSong1.getLength());

        }
        @Test
        void songArtistGetAndSetWorkingCorrectly() {
            Song artistSong1 = new Song(1500, "Cool song name", new Song.Artist((String) artist1.getArtistName(), artist1.isVerified()));
             assertEquals(artist1, artistSong1.getArtist());
             artistSong1.setArtist(artist2);
             assertEquals(artist2, artistSong1.getArtist());

        }

    }
    @Nested
    class ToString {
//  validSong1 = new models.Song(1500, "Anti-Hero", "Taylor Swift", true,  123);
        @Test
        void toStringContainsAllFieldsInObject() {
            //  validSong1 = new models.Song(1500, "Anti-Hero", "Taylor Swift", true,  123);
            assertTrue(validSong1.toString().contains("1500"));
            assertTrue(validSong1.toString().contains("Anti-Hero"));
            assertTrue(validSong1.toString().contains("Taylor Swift"));
            assertTrue(validSong1.toString().contains("is a verified artist"));
            assertTrue(validSong1.toString().contains("123"));

            //  validSong2 = new models.Song(9999, "Calm Down", "Rema", false, 600);
            assertTrue(validSong2.toString().contains("9999"));
            assertTrue(validSong2.toString().contains("Calm Down"));
            assertTrue(validSong2.toString().contains("Rema"));
            assertTrue(validSong2.toString().contains("is not a verified artist"));
            assertTrue(validSong2.toString().contains("600"));


        }
}
}

