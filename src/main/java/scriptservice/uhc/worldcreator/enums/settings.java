package scriptservice.uhc.worldcreator.enums;

public enum settings {
    GoldBoost("GoldBoost",("§6"+"Boost d'or"+": §l§c"+"%s"),                0,0, 4, 1),
    DiamondBoost("DiamondBoost",("§6"+"Boost du diamands"+": §l§c"+"%s"),   0,0, 4, 1),
    CaveBoost("CaveBoost",("§6"+"Boost des caves"+": §l§c"+"%s"),           200,0, 600, 200),
    WorldBorder("WorldBorder",("§6"+"Bordure de la map"+": §l§c"+"%s"),     1000,300, 1250, 50),
    StretchY("StretchY", ("§6"+"Etirement Vertical"+": §l§c"+"%s"),         25, 1, 25, 1),
    BaseSize("BaseSize",("§6"+"Amplification des Reliefs"+": §l§c"+"%s"),   8,1, 50, 1);

    private final String displayName;
    private final String name;
    private int value;
    private final int minValue;
    private final int maxValue;
    private final int defaultValue;
    private final int step;

    settings(String displayName, String name, int value, int minValue, int maxValue, int step) {
        this.displayName = displayName;
        this.name = name;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = value;
        this.step = step;
    }

    //---- functions ----//
    //--- get ---//
    public String getDisplayName() {return displayName;}
    public String getName() {return name;}
    public int getValue() {return value;}
    public int getMinValue() {return minValue;}
    public int getMaxValue() {return maxValue;}
    public int getDefaultValue() {return defaultValue;}
    public int getStep() {return step;}

    //--- set ---//
    public void setValue(int value) {this.value = value;}
}
