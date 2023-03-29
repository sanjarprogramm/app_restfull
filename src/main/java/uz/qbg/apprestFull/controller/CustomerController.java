package uz.qbg.apprestFull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.qbg.apprestFull.entity.Customer;
import uz.qbg.apprestFull.payload.CustomerDto;
import uz.qbg.apprestFull.payload.Result;
import uz.qbg.apprestFull.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    /**
     * BU YERDA BARCHA MIJOZLARRO'YXATINI QAYTARAMIZ
     *
     * @return CUSTOMERS
     */
    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }



    /**
     * ID ORQALI BITTA MIJOZ QAYTARAMIZ
     *
     * @param id INTEGER
     * @return CUSTOMER
     */
    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);


    }


    /**
     * yangi mijoz qaytaradi
     * @return Result
     */
    @PostMapping(value = "/add")
    public Result addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);

    }


    /**
     * ID ORQALI MIJOZ MALUMOTLARIGA UZGARISH KIRITISH
     * @param id
     * @param customerDto
     * @return RESULT
     */
    @PutMapping(value = "/put/{id}")
    public Result putCustomer(@PathVariable Integer id ,@RequestBody CustomerDto customerDto){
        return customerService.put(id, customerDto);


    }

    /**
     * ID ORQALI MIJOZNI O'CHIRISH
     * @param id
     * @return REZULT
     */
    @DeleteMapping("/delete/{id}")
    public Result delete (@PathVariable Integer id){
        return customerService.delete(id);
    }

}
