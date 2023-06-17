package traverse;

public class FileScanOutput {
    boolean hasPostCode;
    boolean hasPesel;
    boolean hasPhone;
    boolean hasBirthDate;
    boolean hasEmail;
    boolean hasName;
    public boolean hasPostCode() {
        return hasPostCode;
    }

    public void setHasPostCode(boolean hasPostCode) {
        this.hasPostCode = hasPostCode;
    }

    public boolean hasPesel() {
        return hasPesel;
    }

    public void setHasPesel(boolean hasPesel) {
        this.hasPesel = hasPesel;
    }

    public boolean hasPhone() {
        return hasPhone;
    }

    public void setHasPhone(boolean hasPhone) {
        this.hasPhone = hasPhone;
    }

    public boolean hasEmail() {
        return hasEmail;
    }

    public void setHasEmail(boolean hasEmail) {
        this.hasEmail = hasEmail;
    }

    public boolean hasName() {
        return hasName;
    }

    public void setHasName(boolean hasName) {
        this.hasName = hasName;
    }
}
