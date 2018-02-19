package supermarket.Product;

public enum ProductMeasure {
    UNKNOWN(-1),
    KG(0),
    PIECES(1);

    private int measureCode;

    private ProductMeasure(int code) {
        measureCode = code;
    }

    public static ProductMeasure getByCode(int code) {
        for(ProductMeasure type : values()) {
            if(type.measureCode == code) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
