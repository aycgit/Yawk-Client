package net.yawk.client.modmanager;

public enum ModType {
	
	COMBAT("Combat"), MOVEMENT("Movement"), WORLD("World"), BUILDING("Building"), PLUGIN("Plugin");
	
    private final String name;
    
	private ModType(String name) {
        this.name = name;
    }
	
    public String getName() {
		return name;
	}
}
