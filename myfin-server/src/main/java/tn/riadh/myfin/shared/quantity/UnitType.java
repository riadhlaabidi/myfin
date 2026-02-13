package tn.riadh.myfin.shared.quantity;

import org.jmolecules.ddd.types.ValueObject;

public enum UnitType implements ValueObject {
    PIECE("Piece", "P", MeasurementType.COUNT, 0, 1),
    KILOGRAM("Kilogram", "KG", MeasurementType.WEIGHT, 3, 1000),
    LITER("Liter", "L", MeasurementType.VOLUME, 3, 1000);

    private String displayName;
    private String symbol;
    private MeasurementType measurementType;
    private int scale;
    private int baseUnitRatio;

    private UnitType(String displayName, String symbol,
            MeasurementType measurementType, int scale, int baseUnitRatio) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.measurementType = measurementType;
        this.scale = scale;
        this.baseUnitRatio = baseUnitRatio;
    }

    public String displayName() {
        return displayName;
    }

    public String symbol() {
        return symbol;
    }

    public MeasurementType measurementType() {
        return measurementType;
    }

    public int scale() {
        return scale;
    }

    public int baseUnitRatio() {
        return baseUnitRatio;
    }
}
