package SeaTransport.Tooklits;

public class ValueChecker {

    public static boolean checkIntPositiveValue(String field, String fieldName) {
        if (field.trim().isEmpty()) {
            Windows.showAlert("Invalid " + fieldName);
            return false;
        } else if ((Integer.valueOf(field) <= 0)) {
            Windows.showAlert("Invalid " + fieldName);
            return false;
        }
        return true;
    }
    public static boolean checkStringFullValue(String field,String fieldName){
        if (field.trim().isEmpty()){
            Windows.showAlert("Invalid"+fieldName);
            return false;
        }
        return true;
    }

}
