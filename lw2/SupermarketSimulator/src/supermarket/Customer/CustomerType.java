package supermarket.Customer;

public enum CustomerType {
    Child(0),
    Adult(1),
    Retired(2);

    private int typeCode;

    private CustomerType(int code) {
        typeCode = code;
    }

    public static CustomerType getByCode(int code) {
        for(CustomerType type : values()) {
            if(type.typeCode == code) {
                return type;
            }
        }
        return Adult;
    }
}
