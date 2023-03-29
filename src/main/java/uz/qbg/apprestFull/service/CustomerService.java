package uz.qbg.apprestFull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import uz.qbg.apprestFull.entity.Customer;
import uz.qbg.apprestFull.payload.CustomerDto;
import uz.qbg.apprestFull.payload.Result;
import uz.qbg.apprestFull.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    /**
     * BU YERDA BARCHA MIJOZLARRO'YXATINI QAYTARAMIZ
     *
     * @return CUSTOMERS
     */
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }


    /**
     * ID ORQALI BITTA MIJOZ QAYTARAMIZ
     * @param id INTEGER
     * @return CUSTOMER
     * AGAR ID ORQALI MIJOZ TOPILMASA NULL QAYTADI
     */
     public Customer getCustomer (@PathVariable Integer id){
         Optional<Customer> optionalCustomer = customerRepository.findById(id);
         return optionalCustomer.orElse(null);
     }


    /**
     * yangi mijoz qaytaradi
     * @return Result
     */
    public Result addCustomer( CustomerDto customerDto){
        boolean byPhoneNumber = customerRepository.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (byPhoneNumber)
            return new Result("bu tel raqam boshqa mijozda bor",false);
        Customer customer = new Customer();
        customer.setFulName(customerDto.getFulName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
        return new Result("ro'yxatga Yangi mijoz qo'shildi",true);
    }

    /**
     * ID ORQALI MIJOZNI TAXRIRLASH
     * @param id
     * @param customerDto
     * @return RESULT
     */
    public Result put (Integer id,CustomerDto customerDto){
        boolean byPhoneNumberAndIdNot = customerRepository.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(),id);
        if (byPhoneNumberAndIdNot)
            return new Result("bunday tel raqamli mijoz mavjud",false);
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()){
            return new Result("bunday mijoz mavjud emas",false);
        }
        Customer customer = optionalCustomer.get();
        customer.setFulName(customerDto.getFulName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
        return new Result("mijoz taxrirlandi",true);

    }

    public Result delete (Integer id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()){
            return new Result("bunday idga ega mijoz yo'q ",false);
        }
        customerRepository.deleteById(id);
        return new Result("mijoz ro'yxatdan o'chirildi",true);

    }



}
