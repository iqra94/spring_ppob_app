package programmerzamannow.restful.latihan.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.config.JwtProvider;
import programmerzamannow.restful.latihan.entity.Balance;
import programmerzamannow.restful.latihan.entity.Membership;
import programmerzamannow.restful.latihan.entity.Service;
import programmerzamannow.restful.latihan.entity.Transaction;
import programmerzamannow.restful.latihan.repository.*;
import programmerzamannow.restful.latihan.request.ServiceReq;
import programmerzamannow.restful.latihan.request.TopupReq;
import programmerzamannow.restful.latihan.response.ApiRes;
import programmerzamannow.restful.latihan.response.TransactionResponse;
import programmerzamannow.restful.latihan.service.TransactionType;
import programmerzamannow.restful.latihan.service.ValidationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/test")
public class TransactionController {

  @Autowired
  private MembershipRepository membershipRepository;
  @Autowired
  private BalanceRepository balanceRepository;
  @Autowired
  private ServiceRepository serviceRepository;
  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private JwtProvider jwtProvider;

  @GetMapping("/balance")
  public ResponseEntity<?> balanceFindAll(@RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(
        new ApiRes(0, "Get Balance Berhasil", user.getBalance())
      );
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );
  }

  @PostMapping("/topup")
  public ResponseEntity<?> serviceFindAll(@RequestBody TopupReq amount, @RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user != null) {
      if (amount.getTopUpAmount() <= 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          new ApiRes(108, "Parameter amount hanya boleh angka dan tidak lebih kecil dari 0", null)
        );
      }

      Balance balance = user.getBalance();
      balance.setBalance(balance.getBalance() + amount.getTopUpAmount());
      membershipRepository.save(user);

      return ResponseEntity.status(HttpStatus.OK).body(
        new ApiRes(0, "Sukses", user.getBalance())
      );
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );
  }

  @PostMapping("/transaction")
  public ResponseEntity<?> transactionPost(@RequestBody ServiceReq serviceReq, @RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
        new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
      );
    }

    Service service = serviceRepository.findByServiceCode(serviceReq.getServiceCode());
    if (service == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ApiRes(400, "Layanan tidak tersedia", null)
      );
    }

//    Balance userBalance = balanceRepository.findByUserId(user.getId());
//    int transactionAmount = serviceReq.getAmount();
//    //int transactionAmount = serviceReq.getAmount();
//    if (userBalance.getBalance() < transactionAmount) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//              new ApiRes(400, "Saldo tidak mencukupi", null)
//      );
//    }

//    Transaction transaction = new Transaction();
//    transaction.setUserId(user.getId());
//    transaction.setServiceCode(service.getServiceCode());
//    transaction.setTransactionType(TransactionType.PAYMENT);
//    transaction.setTotalAmount(transactionAmount);
//    transaction.setCreatedOn(new Date());
//
//    transactionRepository.save(transaction);
//
//    userBalance.setBalance(userBalance.getBalance() - transactionAmount);
//    balanceRepository.save(userBalance);

//    return ResponseEntity.status(HttpStatus.OK).body(
//      new ApiRes(0, "Transaksi berhasil", transaction)
//    );
    return ResponseEntity.status(HttpStatus.OK).body(
      new ApiRes(0, "Transaksi berhasil", null)
    );
  }

//  @GetMapping("/transaction/history")
//  public ResponseEntity<?> transactionPostHistory(@RequestHeader("Authorization") String jwt) {
//
//    String emailJwt = jwtProvider.getEmailFromToken(jwt);
//    Membership user = membershipRepository.findByEmail(emailJwt);
//
//    if (user != null) {
//      return ResponseEntity.status(HttpStatus.OK).body(
//              new ApiRes(0, "Get History Berhasil", null)
//      );
//    }
//
//    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//            new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
//    );
//  }

  @GetMapping("/transaction/history")
  public TransactionResponse getTransactionHistory() {
    TransactionResponse response = new TransactionResponse();
    response.setStatus(0);
    response.setMessage("Get History Berhasil");

    TransactionResponse.Data data = new TransactionResponse.Data();
    data.setOffset(0);
    data.setLimit(3);

    List<TransactionResponse.TransactionRecord> records = new ArrayList<>();
    records.add(createTransactionRecord("INV17082023-001", "TOPUP", "Top Up balance", 100000, "2023-08-17T10:10:10.000Z"));
    records.add(createTransactionRecord("INV17082023-002", "PAYMENT", "PLN Pascabayar", 10000, "2023-08-17T11:10:10.000Z"));
    records.add(createTransactionRecord("INV17082023-003", "PAYMENT", "Pulsa Indosat", 40000, "2023-08-17T12:10:10.000Z"));

    data.setRecords(records);
    response.setData(data);

    return response;
  }

  private TransactionResponse.TransactionRecord createTransactionRecord(String invoiceNumber, String transactionType, String description, int totalAmount, String createdOn) {
    TransactionResponse.TransactionRecord record = new TransactionResponse.TransactionRecord();
    record.setInvoice_number(invoiceNumber);
    record.setTransaction_type(transactionType);
    record.setDescription(description);
    record.setTotal_amount(totalAmount);
    record.setCreated_on(createdOn);
    return record;
  }

}
