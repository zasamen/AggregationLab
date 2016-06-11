package SeaTransport.Toolkits;

import SeaTransport.Ships.Vessel;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TXTSerializer implements Serializer {

    @Override
    public Object deserialize(InputStream is) {
        ArrayList<Vessel> vessels = new ArrayList<>();
        String string;
        Class aClass;
        Object object;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            string = br.readLine();
            while(string!=null){
                string = string.trim();
                if ("object".equals(getRecType(string))) {
                    aClass = Class.forName(getClassName(string));
                    object = aClass.newInstance();
                    setFields(aClass, br, object);
                    br.readLine();
                    if (string.startsWith("<object item ")) {
                        vessels.add((Vessel) object);
                    }
                }
                string = br.readLine();
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
        List<Vessel> vessels = (List<Vessel>)object;
        outputString += "<list ";
        Class c = vessels.getClass();
        outputString += c.getName() + ">\n";

        for(Object obj : vessels){
            outputString += handleObject(obj, 1);
        }

        outputString += "</list>";
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

    private static void setFields(Class c, BufferedReader br, Object object){
        Class aClass;
        Object tempObject;
        String string;
        String value;
        try {
            if (c != null) {
                for (Field f : c.getDeclaredFields()) {
                    f.setAccessible(true);
                    string = br.readLine().trim();

                    if ("object".equals(getRecType(string))) {
                        aClass = Class.forName(getClassName(string));
                        tempObject = aClass.newInstance();
                        setFields(aClass, br, tempObject);
                        f.set(object,tempObject);
                        br.readLine();
                    } else {
                        value = getValue(string);
                        switch (getPropertyType(string)) {
                            case "java.lang.String":
                                f.set(object, value);
                                break;
                            case "int":
                                f.set(object, Integer.parseInt(value));
                                break;
                            }
                    }
                }
                setFields(c.getSuperclass(), br, object);
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
