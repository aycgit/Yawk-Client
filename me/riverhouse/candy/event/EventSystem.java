package me.riverhouse.candy.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import me.riverhouse.candy.utils.Wrapper;

public class EventSystem {

	private static HashMap<Object, ArrayList<Method>> listeners = new HashMap<Object, ArrayList<Method>>();
	
	public static void register(Object object){
		for(Method method : object.getClass().getDeclaredMethods()){
			if(isMethodValid(method)){
				if(listeners.containsKey(object)){
					listeners.get(object).add(method);
				}else{
					ArrayList<Method> methods = new ArrayList<Method>();
					listeners.put(object, methods);
					listeners.get(object).add(method);
				}
			}
		}
	}


	public static void unregister(Object object){
		listeners.remove(object);
	}
	
	private static boolean isMethodValid(Method method){
		return method.isAnnotationPresent(EventListener.class) && method.getParameterTypes().length == 1;
	}

	public static Event call(Event event){
		HashMap<Object, ArrayList<Method>> temp = new HashMap<Object, ArrayList<Method>>();

		for(Object object : listeners.keySet()){
			temp.put(object, listeners.get(object));
		}

		for(Object object : temp.keySet()){
			for(Method method : temp.get(object)){
				if(event.getClass() ==  method.getParameterTypes()[0]){
					if(event.isCancelled()) break;

					try {
						method.invoke(object, event);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return event;
	}

}
