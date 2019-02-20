package com.amit.validator.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by AMIT JANGID on 19/02/2019.
**/
public class ReflectionUtils
{
    private ReflectionUtils()
    {
        // NOOP
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
    **/
    public static Class[] getClasses(final String packageName) throws ClassNotFoundException, IOException
    {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        final String path = packageName.replace('.', '/');
        final Enumeration<URL> resources = classLoader.getResources(path);
        final List<File> dirs = new ArrayList<>();

        while (resources.hasMoreElements())
        {
            final URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        final ArrayList<Class> classes = new ArrayList<>();

        for (final File directory : dirs)
        {
            classes.addAll(findClasses(directory, packageName));
        }

        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
    **/
    public static List<Class> findClasses(final File directory, final String packageName)
            throws ClassNotFoundException
    {
        final List<Class> classes = new ArrayList<>();

        if (!directory.exists())
        {
            return classes;
        }

        final File[] files = directory.listFiles();

        if (files != null)
        {
            for (final File file : files)
            {
                if (file.isDirectory())
                {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                }
                else if (file.getName().endsWith(".class"))
                {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }

        return classes;
    }
}
