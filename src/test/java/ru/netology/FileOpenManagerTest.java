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
    FileOpenManager manager = new FileOpenManager();

    @Nested
    public class Empty{

        @Test
        void shouldReturnNullIfNoApp(){
            String expected = null;
            String actual = manager.getApp("html");
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToRemove(){
            Map<String, String> expected = new HashMap<>();
            manager.removeKey("html");
            HashMap<String,String> actual = manager.getMap();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetEmptyIfNoKey(){
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of());
            List<String> actual = manager.getAllKeys();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetEmptyIfNoValue(){
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of());
            List<String> actual = manager.getAllValues();
            assertEquals(expected,actual);
        }
    }

    @Nested
    public class OneItem{

        @Test
        void shouldReturnApp(){
            manager.registerApp("html","Google Chrome");
            String expected = "Google Chrome";
            String actual = manager.getApp("html");
            assertEquals(expected,actual);
        }

        @Test
        void shouldRemoveKey(){
            manager.registerApp("html","Google Chrome");
            Map<String, String> expected = new HashMap<>();
            manager.removeKey("html");
            HashMap<String,String> actual = manager.getMap();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetOneKey(){
            manager.registerApp("html","Google Chrome");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("html"));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetOneValue(){
            manager.registerApp("html","Google Chrome");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("Google Chrome"));
            List<String> actual = manager.getAllValues();
            assertEquals(expected,actual);
        }
    }

    @Nested
    public class MoreItems{

        @BeforeEach
        void setup() {
            manager = new FileOpenManager();
            manager.registerApp("html","Google Chrome");
            manager.registerApp("jpg","Adobe Photoshop");
            manager.registerApp("idea","IntelliJ IDEA");
        }

        @Test
        void shouldAddAll(){
            HashMap<String, String> expected = new HashMap<>();
            expected.put("html","Google Chrome");
            expected.put("jpg","Adobe Photoshop");
            expected.put("idea","IntelliJ IDEA");
            HashMap<String, String> actual = manager.getMap();
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnOneApp(){
            String expected = "Google Chrome";
            String actual = manager.getApp("html");
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnNullIfInvalidKey(){
            String expected = null;
            String actual = manager.getApp("gif");
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnNullIfKeyInUpCase(){
            String expected = null;
            String actual = manager.getApp("HTML");
            assertEquals(expected,actual);
        }

        @Test
        void shouldRemoveKey(){
            manager.removeKey("jpg");
            Map<String, String> expected = new HashMap<>();
            expected.put("html","Google Chrome");
            expected.put("idea","IntelliJ IDEA");
            HashMap<String,String> actual = manager.getMap();
            assertEquals(expected,actual);
        }

        @Test
        void shouldNotRemoveKeyIfUpCase(){
            manager.removeKey("JPG");
            Map<String, String> expected = new HashMap<>();
            expected.put("html","Google Chrome");
            expected.put("jpg","Adobe Photoshop");
            expected.put("idea","IntelliJ IDEA");
            HashMap<String,String> actual = manager.getMap();
            assertEquals(expected,actual);
        }

        @Test
        void shouldNotRemoveKeyIfInvalid(){
            manager.removeKey("gif");
            Map<String, String> expected = new HashMap<>();
            expected.put("html","Google Chrome");
            expected.put("jpg","Adobe Photoshop");
            expected.put("idea","IntelliJ IDEA");
            HashMap<String,String> actual = manager.getMap();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetAllKeySorted(){
            manager.registerApp("html","Google Chrome");
            manager.registerApp("jpg","Adobe Photoshop");
            manager.registerApp("idea","IntelliJ IDEA");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("html","idea","jpg"));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetAllValueSorted(){
            manager.registerApp("html","Google Chrome");
            manager.registerApp("jpg","Adobe Photoshop");
            manager.registerApp("idea","IntelliJ IDEA");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("Adobe Photoshop","Google Chrome","IntelliJ IDEA"));
            List<String> actual = manager.getAllValues();
            assertEquals(expected,actual);
        }
    }
}