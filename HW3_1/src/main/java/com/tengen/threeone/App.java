package com.tengen.threeone;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("school");
        DBCollection collection = database.getCollection("students");

        System.out.println("Total students: " + collection.count());

        DBCursor students = collection.find();

        while (students.hasNext()) {
            DBObject student = students.next();
            ArrayList<DBObject> scores = (ArrayList) student.get("scores");

            DBObject lowestHomework = null;

            for (DBObject score : scores) {
                if ("homework".equals(score.get("type"))) {
                    if (lowestHomework == null) {
                        lowestHomework = score;
                    } else if ((Double) score.get("score") < (Double) lowestHomework.get("score")) {
                            lowestHomework = score;
                    }
                }
            }

            if (lowestHomework != null) {
                scores.remove(lowestHomework);
            }

            System.out.println(student);

            collection.save(student);
        }

        System.out.println("Total students: " + students.count());
    }
}
