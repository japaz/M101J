package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: alberto
 * Date: 4/13/13
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Final7 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("photo");
        DBCollection imagesCollection = database.getCollection("images");
        DBCollection albumsCollection = database.getCollection("albums");

        {
        DBCursor images = imagesCollection.find();
        int total = images.count();
        System.out.println("Total images: " + total);
        int orphan = 0;
        while (images.hasNext()) {
            BasicDBObject image = (BasicDBObject) images.next();

            Integer id = (Integer) image.get("_id");

            if (albumsCollection.find(new BasicDBObject("images", id)).count() == 0) {
                orphan++;
            }

        }

        System.out.println("Orphan: " + orphan);
        System.out.println("Diferencia: " + (total - orphan));
        }

        {
        DBCursor imagesSunsires = imagesCollection.find(new BasicDBObject("tags", "sunrises"));
        int totalSunrises = imagesSunsires.count();
        System.out.println("Total Sunsires images: " + totalSunrises);
        int orphanSunrises = 0;
        while (imagesSunsires.hasNext()) {
            BasicDBObject image = (BasicDBObject) imagesSunsires.next();

            Integer id = (Integer) image.get("_id");

            if (albumsCollection.find(new BasicDBObject("images", id)).count() == 0) {
                orphanSunrises++;
            }

        }

        System.out.println("Orphan Sunsires: " + orphanSunrises);
        System.out.println("Diferencia Sunsires: " + (totalSunrises - orphanSunrises));
        }
    }
}
