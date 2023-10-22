package programmerzamannow.restful.latihan.response;

import java.util.List;

public class TransactionResponse {
  private int status;
  private String message;
  private Data data;

  public TransactionResponse() {
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public static class Data {
    private int offset;
    private int limit;
    private List<TransactionRecord> records;

    public Data() {
    }

    public int getOffset() {
      return offset;
    }

    public void setOffset(int offset) {
      this.offset = offset;
    }

    public int getLimit() {
      return limit;
    }

    public void setLimit(int limit) {
      this.limit = limit;
    }

    public List<TransactionRecord> getRecords() {
      return records;
    }

    public void setRecords(List<TransactionRecord> records) {
      this.records = records;
    }
  }

  public static class TransactionRecord {
    private String invoice_number;
    private String transaction_type;
    private String description;
    private int total_amount;
    private String created_on;

    public TransactionRecord() {
    }

    public String getInvoice_number() {
      return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
      this.invoice_number = invoice_number;
    }

    public String getTransaction_type() {
      return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
      this.transaction_type = transaction_type;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public int getTotal_amount() {
      return total_amount;
    }

    public void setTotal_amount(int total_amount) {
      this.total_amount = total_amount;
    }

    public String getCreated_on() {
      return created_on;
    }

    public void setCreated_on(String created_on) {
      this.created_on = created_on;
    }
  }
}

