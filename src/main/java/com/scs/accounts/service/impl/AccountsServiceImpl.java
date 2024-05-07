package com.scs.accounts.service.impl;

import com.scs.accounts.constants.AccountsConstants;
import com.scs.accounts.dto.CustomerDto;
import com.scs.accounts.entity.Accounts;
import com.scs.accounts.entity.Customer;
import com.scs.accounts.mapper.CustomerMapper;
import com.scs.accounts.repository.AccountsRepository;
import com.scs.accounts.repository.CustomerRepository;
import com.scs.accounts.service.IAccountsService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
 private AccountsRepository accountsRepository;
 private CustomerRepository customerRepository;

  /**
   * @param customerDto
   */
  @Override
  public void createAccount(CustomerDto customerDto) {

   Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
   Optional<Customer>  optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
   Customer savedCustomer = customerRepository.save(customer);
   accountsRepository.save(createNewAccount(savedCustomer));
  }

 private Accounts createNewAccount(Customer customer) {
  Accounts newAccount = new Accounts();
  newAccount.setCustomerId(customer.getCustomerId());
  long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

  newAccount.setAccountNumber(randomAccNumber);
  newAccount.setAccountType(AccountsConstants.SAVINGS);
  newAccount.setBranchAddress(AccountsConstants.ADDRESS);
  return newAccount;
 }

}
