package rikkei.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rikkei.academy.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
