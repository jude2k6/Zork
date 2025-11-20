package org.zorkrip.util;
/*
package org.zorkrip;

import java.lang.reflect.Field;
import java.util.Map;

public class DebugPrinter {
    public static void main(String[] args) {
        Room outside, theatre, pub, lab, office;

        // create rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        DebugPrinter.printObject(theatre);
    }

        public static void printObject(Object obj) {
            if (obj == null) {
                System.out.println("null");
                return;
            }

            Class<?> clazz = obj.getClass();
            System.out.println("Class: " + clazz.getName());
            System.out.println("{");

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);

                    if (value instanceof Map<?, ?> map) {
                        System.out.print("  " + field.getName() + " = {");
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            Object key = entry.getKey();
                            Object val = entry.getValue();
                            // If Room, print description instead of object reference
                            if (val instanceof Room room) {
                                System.out.print(key + "=" + room.getDescription() + " ");
                            } else {
                                System.out.print(key + "=" + val + " ");
                            }
                        }
                        System.out.println("}");
                    } else {
                        System.out.println("  " + field.getName() + " = " + value);
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("  " + field.getName() + " = [inaccessible]");
                }
            }

            System.out.println("}");

}}

*/
