package com.tengen.twotwo;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("students");
        DBCollection collection = database.getCollection("grades");

        System.out.println("Total grades: " + collection.count());

        DBCursor homeworks = collection.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", "1").append("score", "1"));

        Integer lastStudentId = -1;

        while (homeworks.hasNext()) {
            DBObject homework = homeworks.next();
            Integer studentId = (Integer) homework.get("student_id");
            Double score = (Double) homework.get("score");

            if (!lastStudentId.equals(studentId)) {
                collection.remove(new BasicDBObject("_id", homework.get("_id")));
                System.out.println("Removed");
            }

            System.out.println(homework + " student_id: " + studentId + " score: " + score);

            lastStudentId = studentId;
        }

        System.out.println("Total homeworks: " + homeworks.count());
    }
}
