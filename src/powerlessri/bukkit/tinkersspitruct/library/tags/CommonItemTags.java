package powerlessri.bukkit.tinkersspitruct.library.tags;

public enum CommonItemTags {
    
    IS_STACK_IMMOVABLE("isStackImmovable"),
    CLICK_EVENT_CATEGORY("clickEventCategory"),
    CLICK_EVENT_ID("clickEventId");
    
    String key;
    
    private CommonItemTags(String key) {
        this.key = key;
    }
    
    
    public String getKey() {
        return key;
    }
    
    @Override
    public String toString() {
        return this.getKey();
    }
    
}
