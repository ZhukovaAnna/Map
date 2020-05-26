package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileOpenManager {
    HashMap<String, String> map = new HashMap<>();

    public HashMap<String, String> getMap() {
        return map;
    }

    public void registerApp(String file, String app) {
        map.put(file, app);
    }

    public String getApp(String file) {
        return map.get(file);
    }

    public void removeKey(String file) {
        map.remove(file);
    }

    public List<String> getAllKeys() {
        Comparator byAlphabet = Comparator.naturalOrder();
        Set<String> files = new HashSet<>();
        files.addAll(map.keySet());
        ArrayList<String> listFiles = new ArrayList<>(files);
        listFiles.sort(byAlphabet);
        return listFiles;
    }

    public List<String> getAllValues() {
        Comparator byAlphabet = Comparator.naturalOrder();
        Set<String> files = new HashSet<>();
        files.addAll(map.values());
        ArrayList<String> listFiles = new ArrayList<>(files);
        listFiles.sort(byAlphabet);
        return listFiles;
    }
}
