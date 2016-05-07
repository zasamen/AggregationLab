package SeaTransport.Tooklits;

public class ValueChecker {

    public static boolean checkIntPositiveValue(String field, String fieldName) {
        if (field.trim().isEmpty()) {
            Windows.showAlert("Invalid " + fieldName);
            return false;
        } else
            try {
                int i = Integer.parseInt(field);
                if (i<=0) throw  new NumberFormatException();
                return true;
            } catch (NumberFormatException nfe) {
                Windows.showAlert("Invalid " + fieldName);
                return false;
            }
    }
    public static boolean checkStringFullValue(String field,String fieldName){
        if (field.trim().isEmpty()){
            Windows.showAlert("Invalid"+fieldName);
            return false;
        }
        return true;
    }

}
