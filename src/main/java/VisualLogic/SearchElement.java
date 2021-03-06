/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VisualLogic;

import VisualLogic.exception.VisualLogicIoException;
import org.tinylog.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * @author salafica
 */
public class SearchElement {

    private ArrayList<Search_found_item> items = new ArrayList<>();

    /**
     *
     * @param filename
     * @return
     * @throws VisualLogicIoException Thrown if we can't read the specified file
     */
    public Hashtable<String, String> load_definition_def(String filename) throws VisualLogicIoException {

        //TODO remove hashtable
        Hashtable<String, String> res = new Hashtable<>();
        try (
            BufferedReader br = Files.newBufferedReader(Path.of(filename))) {
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String[] cols = strLine.split("=");
                if (cols.length == 2) {
                    res.put(cols[0].trim().toLowerCase(), cols[1].trim());
                }
            }
        return res;
        } catch (IOException e) {
            throw  new VisualLogicIoException(e);
        }


    }

    public ArrayList<Search_found_item> search(String path, String suchwort, String language) {
        items.clear();
        walk(path, suchwort, language);
        return items;
    }

    public void walk(String path, String suchwort, String language) {

        suchwort = suchwort.toLowerCase();

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            return;
        }

        for (File f : list) {
            if (f.isDirectory()) {

                String definition = f.getAbsolutePath() + "/definition.def";
                if (new File(definition).exists()) {
                    try {
                        Hashtable<String, String> def_def = load_definition_def(definition);

                        String isdirectory = def_def.get("isdirectory");
                        if (isdirectory != null && isdirectory.equalsIgnoreCase("true")) {
                            walk(f.getAbsolutePath(), suchwort, language);
                        } else {

                            String gefunden = "";
                            String caption = def_def.get("caption");
                            String caption_en = def_def.get("caption_en");
                            String caption_es = def_def.get("caption_es");

                            if (language.equalsIgnoreCase("de")) {
                                if (caption != null && caption.toLowerCase().contains(suchwort)) {
                                    gefunden = caption;
                                }
                            }
                            if (language.equalsIgnoreCase("en")) {
                                if (caption_en != null && caption_en.toLowerCase().contains(suchwort)) {
                                    gefunden = caption_en;
                                }
                            }
                            if (language.equalsIgnoreCase("es")) {
                                if (caption_es != null && caption_es.toLowerCase().contains(suchwort)) {
                                    gefunden = caption_es;
                                }
                            }

                            if (gefunden.trim().length() > 0) {

                                Search_found_item item = new Search_found_item();
                                item.found_word = gefunden;
                                item.found_in_element_dir = f.getAbsolutePath();
                                items.add(item);
                                //System.out.println("suchwort=" + suchwort + "   gefunden=" + gefunden + "  im Element: " + f.getAbsolutePath());
                            }
                        }
                    }catch (VisualLogicIoException e) {
                        Logger.error(e,"Error. Tried to load {}",definition);
                    }
                }

                //System.out.println("Dir:" + f.getAbsoluteFile());
            } else {
                //System.out.println("File:" + f.getAbsoluteFile());
            }
        }
    }
}
