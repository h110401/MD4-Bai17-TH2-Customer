package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.Customer;
import rikkei.academy.service.customer.ICustomerService;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers")
    public String showList(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "/customer/list";
    }

    @GetMapping("/create-customer")
    public String formCreate(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    @PostMapping("/create-customer")
    public String createCustomer(
            Customer customer,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("message", "Create success!");
        customerService.save(customer);
        return "redirect:/create-customer";
    }

    @GetMapping("/edit-customer/{id}")
    public String showEditForm(@PathVariable("id") Optional<Customer> customer, Model model) {
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "/customer/edit";
        } else {
            return "/error.404";
        }
    }

    @PostMapping("/edit-customer")
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete-customer/{id}")
    public String showDeleteForm(@PathVariable("id") Optional<Customer> customer, Model model) {
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get());
            return "/customer/delete";

        } else {
            return "/error.404";
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customers";
    }


}
