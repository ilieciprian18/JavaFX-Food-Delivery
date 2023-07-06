package Classes;

public class Voucher {

    private String codVoucher;
    private int value;
    private String status;

    public void setCodVoucher(String codVoucher) {
        this.codVoucher = codVoucher;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodVoucher() {
        return codVoucher;
    }

    public int getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }
}
