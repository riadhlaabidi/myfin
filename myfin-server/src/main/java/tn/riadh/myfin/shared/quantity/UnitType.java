package tn.riadh.myfin.shared.quantity;

import org.jmolecules.ddd.types.ValueObject;

public enum UnitType implements ValueObject {
    PIECE("Piece", "P", MeasurementType.COUNT, 0),
    KILOGRAM("Kilogram", "KG", MeasurementType.WEIGHT, 3);

    private String displayName;
    private String symbol;
    private MeasurementType measurementType;
    private int scale;

    private UnitType(String displayName, String symbol,
            MeasurementType measurementType, int scale) {
        this.displayName = displayName;
        this.symbol = symbol;
        this.measurementType = measurementType;
        this.scale = scale;
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

    public boolean isCountable() {
        return measurementType == MeasurementType.COUNT;
    }

    public boolean isWeighable() {
        return measurementType == MeasurementType.WEIGHT;
    }
}
