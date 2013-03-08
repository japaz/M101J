package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import javax.xml.soap.SOAPPart;

/**
 * Created with IntelliJ IDEA.
 * User: alberto
 * Date: 1/03/13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World From Spark";
            }
        });
    }
}
