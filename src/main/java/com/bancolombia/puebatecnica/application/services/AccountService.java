package com.bancolombia.puebatecnica.application.services;


import com.bancolombia.puebatecnica.application.utils.UtilsAccount;
import com.bancolombia.puebatecnica.application.ports.input.AccountServicePort;
import com.bancolombia.puebatecnica.application.ports.output.AccountPersistencePort;
import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.domain.models.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService implements AccountServicePort {

    private UtilsAccount utilsAccount;
    private final AccountPersistencePort accountPersistencePort;
    @Override
    public String createAccount(String name, String surname, String id, String idType){

        String numberAccount = utilsAccount.generateAccountNumber();
        while(true){
            Account account = accountPersistencePort.getAccountById(numberAccount);
            if(account==null){
                break;
            }
            numberAccount = utilsAccount.generateAccountNumber();
        }
        Account account = Account.builder()
                .numberAccount(numberAccount)
                .identification(id)
                .identificationType(idType)
                .name(name)
                .surname(surname)
                .balance(0L)
                .build();
        return accountPersistencePort.save(account);

    }

    @Override
    public String getBalance(String numberAccount){
        try{
            return accountPersistencePort.getAccountById(numberAccount).getBalance().toString();
        }catch (Exception e){
            throw new DefaultException("account-error","noAccountFound");
        }
    }


}
