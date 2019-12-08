package com.maltem.demo.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maltem.demo.model.Movie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    private static Resource resource = new ClassPathResource("data/movies.json");
    @Override
    public  List<Movie> findAll() throws IOException {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(resource.getFile().getPath())) {
            Object obj = jsonParser.parse(reader);
            JSONArray mvList = (JSONArray) obj;
            return mvList;

        } catch (ParseException e) {
            e.printStackTrace();
        }

    return  null;
    }


    @Override
    public Movie addMovie(Movie movie) throws IOException {
        JSONObject obj=new JSONObject();
        obj.put("title", movie.getTitle());
        obj.put("director", movie.getDirector());
        obj.put("releaseDate", movie.getReleaseDate());
        obj.put("type", movie.getType());
        try (FileReader reader = new FileReader(resource.getFile().getPath())) {
            JSONParser jsonParser = new JSONParser();
            ObjectMapper objectMapper = new ObjectMapper();
            Object objM = jsonParser.parse(reader);
            JSONArray mvList = (JSONArray) objM;
            mvList.add(obj);
            objectMapper.writeValue(resource.getFile(), mvList);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public void removeMovie(String title) {
        try (FileReader reader = new FileReader(resource.getFile().getPath())) {
            JSONParser jsonParser = new JSONParser();
            ObjectMapper objectMapper = new ObjectMapper();
            Object obj = jsonParser.parse(reader);
            JSONArray mvList = (JSONArray) obj;
            int index= getMovieByTitle(mvList, title);
            mvList.remove(index);
            objectMapper.writeValue(resource.getFile(), mvList);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateMovie(String title, Movie mv) throws IOException {
        try (FileReader reader = new FileReader(resource.getFile().getPath())) {
            JSONParser jsonParser = new JSONParser();
            ObjectMapper objectMapper = new ObjectMapper();
            Object obj = jsonParser.parse(reader);
            JSONArray mvList = (JSONArray) obj;
            int index = getMovieByTitle(mvList, title);
            mvList.remove(index);
            JSONObject newMv=new JSONObject();
            newMv.put("title", mv.getTitle());
            newMv.put("director", mv.getDirector());
            newMv.put("releaseDate", mv.getReleaseDate());
            newMv.put("type", mv.getType());
            mvList.add(newMv);

            objectMapper.writeValue(resource.getFile(), mvList);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Movie getMovieByTitle(String title){
        try (FileReader reader = new FileReader(resource.getFile().getPath())) {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(reader);
            JSONArray mvList = (JSONArray) obj;
            //JSONObject objMv = getMovieByTitle(mvList, title);
            int index=getMovieByTitle(mvList, title);
            JSONObject objMv= (JSONObject)mvList.get(index);
            return new Movie((String) objMv.get("title"), (String) objMv.get("director"), (String) objMv.get("releaseDate"), (String) objMv.get("type"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getMovieByTitle(JSONArray array, String title) {
        boolean found=false;
        int i = 0;
        JSONObject movie=new JSONObject();
        while(!found && i < array.size()){
            movie = (JSONObject) array.get(i);
            if (movie.get("title").equals(title)) {
                found=true;
            }
            else
                i++;
        }
        return i;
    }
}
