package net.yawk.client.modmanager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModDetails {
	
	String name();
	String desc();
	int defaultKey() default -1;
	Mod.Type type();
	
}
