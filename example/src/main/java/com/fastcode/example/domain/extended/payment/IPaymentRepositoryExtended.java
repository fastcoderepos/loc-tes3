package com.fastcode.example.domain.extended.payment;

import org.springframework.stereotype.Repository;
import com.fastcode.example.domain.core.payment.IPaymentRepository;
@Repository("paymentRepositoryExtended")
public interface IPaymentRepositoryExtended extends IPaymentRepository {

	//Add your custom code here
}

