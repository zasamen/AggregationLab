package SeaTransport.Tooklits;

import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Vessel;
import SeaTransport.Ships.VesselComponent.Aircraft;
import SeaTransport.Ships.VesselComponent.Armament;
import SeaTransport.Ships.VesselComponent.Engine;
import SeaTransport.Ships.VesselComponent.Sail;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class TXTSerializer implements Serializer {

    @Override
    public Object deserialize(InputStream is) {
        ArrayList<Vessel> vessels = new ArrayList<>();
        String string;
        Class aClass;
        Object object;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(br.ready()){
                string = br.readLine().trim();
                if ("object".equals(getRecType(string))) {
                    aClass = Class.forName(getClassName(string));
                    object = aClass.newInstance();
                    setFields(aClass, br, object);
                    br.readLine();
                    if (string.startsWith("<object item ")) {
                        vessels.add((Vessel) object);
                    }
                }
            }
        }
        catch(Exception exc){
            Windows.showAlert(exc+"");
        }
        return vessels;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void serialize(OutputStream os, Object object) {
        String outputString = "";
        ArrayList<Vessel> vessels = (ArrayList<Vessel>)object;
        outputString += "<list ";
        Class c = vessels.getClass();
        outputString += c.getName() + ">\n";

        for(Object obj : vessels){
            outputString += handleObject(obj, 1);
        }

        outputString += "</list>";
        System.out.println(outputString);
        try {
            os.write(outputString.getBytes());
            os.flush();
        }
        catch (Exception exc){
            Windows.showAlert(exc+"");
        }
    }

    private static String getPropertyType(String s){
        return s.split(" ")[1];
    }

    private static String getValue(String s){
        return s.split(" ")[2].replace(">", "");
    }

    private static void setFields(Class c, BufferedReader br, Object obj){
        Class tempC;
        Object tempObj;
        String s;
        String value;
        try {
            if (c != null) {
                for (Field f : c.getDeclaredFields()) {
                    f.setAccessible(true);
                    s = br.readLine().trim();

                    if ("object".equals(getRecType(s))) {
                        tempC = Class.forName(getClassName(s));
                        tempObj = tempC.newInstance();
                        setFields(tempC, br, tempObj);

                        switch (getPropertyType(s)) {
                            case "engine":
                                f.set(obj, (Engine) tempObj);
                                break;
                            case "aircraft":
                                f.set(obj, (Aircraft) tempObj);
                                break;
                            case "armament":
                                f.set(obj, (Armament) tempObj);
                                break;
                            case "sail":
                                f.set(obj, (Sail) tempObj);
                                break;
                            case "shallop":
                                f.set(obj, (Shallop) tempObj);
                                break;
                        }
                        br.readLine();
                    } else {
                        value = getValue(s);
                        switch (getPropertyType(s)) {
                            case "java.lang.String":
                                f.set(obj, value);
                                break;
                            case "int":
                                f.set(obj, Integer.parseInt(value));
                                break;
                            }
                    }
                }
                setFields(c.getSuperclass(), br, obj);
            }
        }
        catch(Exception exc){
            Windows.showAlert(exc+"");
        }
    }

    private static String handleObject(Object obj, int level){
        Class c = obj.getClass();
        return "<object item "+c.getName() + ">\n"+handleFields(c, level, obj)+"</object>\n";
    }

    private static String handleFields(Class c, int level, Object obj){
        String outputString = "";
        if(c != null) {
            try {
                for (Field f : c.getDeclaredFields()) {
                    f.setAccessible(true);
                    outputString += addTabs(level)+(f.getType().getName().contains("SeaTransport.Ships.") ?
                            "<object " + f.getName() + " " + f.getType().getName() + ">\n" + handleFields(f.getType(), level + 1, f.get(obj)) + addTabs(level) + "</object>\n" :
                            "<" + f.getName() + " " + f.getType().getName() + " " + f.get(obj) + ">\n");
                }
                outputString += handleFields(c.getSuperclass(), level, obj);
            } catch (Exception exc) {
                Windows.showAlert(exc+"");
            }
        }
        return outputString;
    }

    @NotNull
    private static String addTabs(int n){
        return ("\t").concat((n==0)?"":addTabs(n-1));
    }

    private static String getRecType(String s){
        return s.split(" ")[0].replace("<", "").replace("\t", "");
    }

    private static String getClassName(String s){
        return s.split(" ")[2].replace(">", "");
    }
}
