package com.scs.accounts.service;

import com.scs.accounts.dto.CustomerDto;

public interface IAccountsService {


  /**
   *
   * @param customerDto
   */
  void createAccount (CustomerDto customerDto);
}
