package me.riverhouse.candy.module;

import java.util.ArrayList;

import com.google.common.reflect.ClassPath;

public class ModuleManager {

	private ArrayList<String> modulePaths = new ArrayList<String>();
	private ArrayList<Module> modules = new ArrayList<Module>();

	public ModuleManager(ArrayList<String> paths) {
		for(String modulePath : paths)
			this.addClassesFromPackage(modulePath);
	}

	public ArrayList<Module> getModules() {
		return modules;
	}

	public void addClassesFromPackage(String packageName) {
		for (Class<?> clazz : getClasses(packageName)) {
			try {
				Object obj = clazz.newInstance();
				if (obj instanceof Module) {
					modules.add((Module) obj);
				}
			} catch (Exception e) {}
		}
	}

	public Module getModuleFromName(String name){
		for(Module m : modules){
			if(m.getName().equalsIgnoreCase(name)) return m;
		}

		return null;
	}

	public Module getModuleFromClass(Class clas) {
		for(Module m : this.getModules()) {
			if(m.getClass() == clas) {
				return m;
			}
		}
		return null;
	}
	
	private ArrayList<Class<?>> getClasses(final String packageName){
        final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        try {
            final ClassLoader loader = Thread.currentThread().getContextClassLoader();
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getAllClasses()) {
                if (info.getName().startsWith(packageName)) {
                    final Class<?> clazz = info.load();
                    classes.add(clazz);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return classes;
    }

	public boolean hasMod(Module m) {
		
		for(Module module : modules){
			if(module == m) return true;
		}
		
		return false;
	}

}
