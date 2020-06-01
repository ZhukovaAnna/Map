package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenManagerTest {
    private FileOpenManager manager = new FileOpenManager();
    private String html = "html";
    private String google = "Google Chrome";
    private String jpg = "jpg";
    private String photoshop = "Adobe Photoshop";
    private String idea = "idea";
    private String intel = "IntelliJ IDEA";

    @Nested
    public class Empty {

        @Test
        void shouldReturnNullIfNoApp() {
            String actual = manager.getApp("html");
            assertNull(actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToRemove() {
            HashMap<String, String> expected = new HashMap<>();
            manager.removeKey("html");
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetEmptyIfNoKey() {
            List<String> expected = new ArrayList<>();
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetEmptyIfNoValue() {
            List<String> expected = new ArrayList<>();
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class OneItem {

        @Test
        void shouldReturnApp() {
            manager.registerApp(html,google);
            String expected = google;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveKey() {
            manager.registerApp(html, google);
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(html);
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetOneKey() {
            manager.registerApp(html, google);
            List<String> expected = new ArrayList<>(List.of(html));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetOneValue() {
            manager.registerApp(html, google);
            List<String> expected = new ArrayList<>(List.of(google));
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MoreItems {

        @BeforeEach
        void setup() {
            manager = new FileOpenManager();
            manager.registerApp(html, google);
            manager.registerApp(jpg, photoshop);
            manager.registerApp(idea, intel);
        }

        @Test
        void shouldAddAll() {
            HashMap<String, String> expected = new HashMap<>();
            expected.put(html, google);
            expected.put(jpg, photoshop);
            expected.put(idea, intel);
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnOneApp() {
            String expected = google;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnNullIfInvalidKey() {
            String expected = null;
            String actual = manager.getApp("gif");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnNullIfKeyInUpCase() {
            String expected = null;
            String actual = manager.getApp("HTML");
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveKey() {
            manager.removeKey(jpg);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, google);
            expected.put(idea, intel);
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotRemoveKeyIfUpCase() {
            manager.removeKey("JPG");
            Map<String, String> expected = new HashMap<>();
            expected.put(html, google);
            expected.put(jpg, photoshop);
            expected.put(idea, intel);
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotRemoveKeyIfInvalid() {
            manager.removeKey("gif");
            Map<String, String> expected = new HashMap<>();
            expected.put(html, google);
            expected.put(jpg, photoshop);
            expected.put(idea, intel);
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetAllKeySorted() {
            manager.registerApp(html, google);
            manager.registerApp(jpg, photoshop);
            manager.registerApp(idea, intel);
            List<String> expected = new ArrayList<>(List.of(html, idea, jpg));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetAllValueSorted() {
            manager.registerApp(html, google);
            manager.registerApp(jpg, photoshop);
            manager.registerApp(idea, intel);
            List<String> expected = new ArrayList<>(List.of(photoshop, google, intel));
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }
}