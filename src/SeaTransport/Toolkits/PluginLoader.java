package SeaTransport.Toolkits;

import PluginPackage.Plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {

    private File file=null;

    public PluginLoader(File file){
        this.file=file;
    }
    @SuppressWarnings("unchecked")
    public Class<Plugin> loadPlugin(String pluginName){
        Class<Plugin> pluginClass=null;
        try {
            URLClassLoader loader=URLClassLoader.newInstance(new URL[]{file.toURI().toURL()},ClassLoader.getSystemClassLoader());
            pluginClass=(Class<Plugin>)loader.loadClass(pluginName);
        }catch (MalformedURLException|ClassNotFoundException murle){
            murle.printStackTrace();
        }
        return pluginClass;
    }
}

